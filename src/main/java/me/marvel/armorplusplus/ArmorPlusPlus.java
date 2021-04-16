package me.marvel.armorplusplus;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.marvel.armorplusplus.commands.InventoryEvents;
import me.marvel.armorplusplus.commands.TabComplete;
import me.marvel.armorplusplus.events.ArmorListener;
import me.marvel.armorplusplus.events.DispenserArmorListener;
import me.marvel.armorplusplus.utils.Metrics;
import me.marvel.armorplusplus.utils.UpdateChecker;


/**
 * Main class of the plugin.
 * <p>
 * Contains all the necessary stuff for the plugin to work.
 */
public class ArmorPlusPlus extends JavaPlugin implements Listener {
	
	public boolean outdated = false;
	public boolean uptodate = false;
	public String msg = "";
	public UpdateChecker uc;
	private boolean hasMineTinker = false;
	public me.marvel.armorplusplus.events.Listener listener;
	public boolean ver16 = false;
	public List<String> disabledArmors = new ArrayList<String>();
	public List<String> disabledItems = new ArrayList<String>();
	public ArmorRecipe ar;
	public static ArmorPlusPlus instance;
	public InventoryEvents invevents;
	public ArmorAbilities ability;
	/**
	 * Hashmap containing
	 * 1st String = Armor ID
	 * 2nd String = Armor Name
	 * e.g BONE_BLOCK -> Bone
	 */
//	public final LinkedHashMap<String, String> armorIdtoName = Method.getArmorIdtoName();
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {
		if(e.getPlayer().isOp() || e.getPlayer().hasPermission("armorplusplus.cheat")) {
			Player p = e.getPlayer();
			if(outdated) {
				if(msg != "") p.sendMessage(msg);
				else p.sendMessage(ChatColor.GREEN + "ArmorPlusPlus > Available update version. Current plugin is outdated. Download at https://www.spigotmc.org/resources/armorplusplus.74748/. More info at /armorplusplus check");
			}
			if(hasMineTinker) {
				p.sendMessage(ChatColor.RED + "ArmorPlusPlus > Incompatibility plugin found: MineTinker. Note: This plugin will not work properly!");
			}
		}
	}
	@Override
	public void onEnable() {
		Method.NMSVERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		invevents = new InventoryEvents(this, this);
		ArmorPlusPlus.instance = this;
		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		Method.plugin = this;
		Method.GLOWING = new GlowingEnchantment("glowing", this);
		registerGlowing();
		ar = new ArmorRecipe(this);
		if(getServer().getVersion().toLowerCase().contains("1.16")) {
			ver16 = true;
		}else ver16 = false;
		getServer().getPluginManager().registerEvents(this, this);
		loadArmorIds();
		try {
			getServer().getPluginManager().registerEvents(new ArmorListener(Method.armorEquipBlockedList()), this);
			try {
				Class.forName("org.bukkit.event.block.BlockDispenseArmorEvent");
				getServer().getPluginManager().registerEvents(new DispenserArmorListener(), this);
			}catch(Exception e1) {
				
			}
		}catch(Exception e1) {
			e1.printStackTrace();
			getLogger().warning("Failed to register armor equip events!");
		}
		try {
			List<String> missing = checkMissingConfig();
			if(missing.size() != 0) getLogger().warning("To get up-to-date configuration, do /armorplusplus resetconfig to reset the config.");
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			List<String> clone = getConfig().getStringList("disabled-items");
			this.disabledItems = new ArrayList<String>();
			for(String s : clone) {
				this.disabledItems.add(s.toLowerCase());
			}
		}catch(Exception e1) {
			this.disabledItems = new ArrayList<String>();
			getServer().getLogger().warning("Failed to load 'disabled-items' configuration!");
		}
		try {
			if(getConfig().getBoolean("check-update")) {
				uc = new UpdateChecker(this, 74748);
				uc.getVersion((version) -> {
					if(version == null) {
						getServer().getConsoleSender().sendMessage(ChatColor.RED + "ArmorPlusPlus > Unable to check updates. To manually check updates, please type /armorplusplus check.");
					}else {
						if(version.equalsIgnoreCase(getDescription().getVersion())) {
							outdated = false;
							uptodate = true;
							getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ArmorPlusPlus > No available updates.");
						}else {
							outdated = true;
							uptodate = false;
							msg = ChatColor.GREEN + "ArmorPlusPlus > Available update version " + version + ". Download at https://www.spigotmc.org/resources/armorplusplus.74748/. More info at /armorplusplus check";
							getServer().getConsoleSender().sendMessage(msg);
						}
					}
				});
			}
		}catch(Exception e) {
			getServer().getConsoleSender().sendMessage(ChatColor.RED + "ArmorPlusPlus > Unable to check updates. To manually check updates, please type /armorplusplus check.");
		}
		
		getCommand("armorplusplus").setExecutor(new me.marvel.armorplusplus.commands.ArmorPlusPlus(this, this));
		getCommand("armorplusplus").setTabCompleter(new TabComplete());
		getServer().getPluginManager().registerEvents(invevents, this);
		getServer().getPluginManager().registerEvents(new ArmorAbilities(this), this);
		listener = new me.marvel.armorplusplus.events.Listener(this, this);
		listener.listen();
		loadRecipe();
		loadAbilities();
		for(Plugin p : getServer().getPluginManager().getPlugins()) {
			String name = p.getName();
			if(name.equalsIgnoreCase("MineTinker")) {
				hasMineTinker = true;
			}
		}
		if(hasMineTinker) {
			getServer().getConsoleSender().sendMessage(ChatColor.RED + "ArmorPlusPlus > Incompatibility plugins found: MineTinker.");
			getServer().getConsoleSender().sendMessage(ChatColor.RED + "ArmorPlusPlus > This plugin will not work properly!");
		}else {
			getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ArmorPlusPlus > No incompatibility plugins found!");
		}
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Armor++ plugin enabled v" + this.getDescription().getVersion());
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		loadMetrics();
	}
	public void registerGlowing() {
	    try {
	        Field f = Enchantment.class.getDeclaredField("acceptingNew");
	        f.setAccessible(true);
	        f.set(null, true);
	        Enchantment.registerEnchantment(Method.GLOWING);
	    }catch (Exception e) {
	    	if(!e.getMessage().toLowerCase().contains(("Cannot set already-set enchantment".toLowerCase()))) e.printStackTrace();
	    	else getServer().getConsoleSender().sendMessage(ChatColor.RED + "Note: It is *not* recommended to reload and it might cause issues! Please reboot the server to make sure everything works properly.");
	    }
	}
	public void loadArmorIds() {
		try {
			List<String> clone = getConfig().getStringList("disabled-armors");
			this.disabledArmors = new ArrayList<String>();
			for(String s : clone) {
				this.disabledArmors.add(s.toLowerCase());
			}
		}catch(Exception e) {
			this.disabledArmors = new ArrayList<String>();
			getServer().getConsoleSender().sendMessage(ChatColor.RED + "ArmorPlusPlus > An error occured while loading up armor ids and disabled armors.");
			e.printStackTrace();
		}
		try {
			List<String> clone = getConfig().getStringList("disabled-items");
			this.disabledItems = new ArrayList<String>();
			for(String s : clone) {
				this.disabledItems.add(s.toLowerCase());
			}
		}catch(Exception e1) {
			this.disabledItems = new ArrayList<String>();
			getServer().getLogger().warning("Failed to load 'disabled-items' configuration!");
		}
	}
	public void loadRecipe() {
		ar.witchPotion();
		for(java.lang.reflect.Method method : ArmorRecipe.class.getMethods()) {
			method.setAccessible(true);
			if(method.getName().toLowerCase().contains("armor")) {
				try {
					method.invoke(ar);
				}catch(Exception e) {
					
				}
			}
		}
	}
	public void loadAbilities() {
		ability = new ArmorAbilities(this);
			// For every 2 second
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if(!disabledArmors.contains("NOTE_BLOCK")) ability.melody();
			}
		}, 0L, 40L);
			// For every 5 second
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					if(!disabledArmors.contains("DIRT")) ability.dirtRegrowth();
				}
			}, 0L, 100L);
			// For every 10 second
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					if(!disabledArmors.contains("LAPIS")) ability.lapisExpGive();
				}
			}, 0L, 200L);
			// For every 6 second
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					if(!disabledArmors.contains("PUMPKIN")) ability.pumpkinFeed();
					if(!disabledArmors.contains("MELON")) ability.melonFeed();
				}
			}, 0L, 120L);
			
			// Every 10 minutes, armor protection 0 fix.
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				public void run() {
					// ARMOR PROTECTION 0 FIX
					boolean glowing = getConfig().getBoolean("glowing-armor");
					for(Player p : Bukkit.getOnlinePlayers()) {
						for(int i = 0; i < p.getInventory().getContents().length; i++) {
							ItemStack item = p.getInventory().getItem(i);
							try {
								if(item == null) continue;
								if(item.getType() == Material.AIR) continue;
								if(!Method.getNBTTag(item, "Enchantments").getValue().contains("lvl:0s,id:\"minecraft:protection\"") || Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"minecraft:protection\",lvl:0s")) continue;
								if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) continue;
								ItemStack newItem = item.clone();
								ItemMeta newMeta = newItem.getItemMeta();
								newMeta.removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
								if(getConfig().getBoolean("glowing-armor")) newMeta.addEnchant(Method.GLOWING, 0, true);
								newItem.setItemMeta(newMeta);
								p.getInventory().setItem(i, newItem);
							}catch(Exception e) {
								
							}
						}
						// armor, and offhand check
						try {
							ItemStack item = p.getInventory().getItemInOffHand();
							if(item == null) throw new IllegalArgumentException();
							if(item.getType() == Material.AIR) throw new IllegalArgumentException();
							if(!Method.getNBTTag(item, "Enchantments").getValue().contains("lvl:0s,id:\"minecraft:protection\"") || Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"minecraft:protection\",lvl:0s")) throw new IllegalArgumentException("");
							if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) throw new IllegalArgumentException("");
							ItemStack newItem = item.clone();
							ItemMeta newMeta = newItem.getItemMeta();
							newMeta.removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
							if(getConfig().getBoolean("glowing-armor")) newMeta.addEnchant(Method.GLOWING, 0, true);
							newItem.setItemMeta(newMeta);
							p.getInventory().setItemInOffHand(newItem);
						}catch(Exception e) {
							
						}
						try {
							ItemStack item = p.getInventory().getHelmet();
							if(item == null) throw new IllegalArgumentException("");
							if(item.getType() == Material.AIR) throw new IllegalArgumentException("");
							if(!Method.getNBTTag(item, "Enchantments").getValue().contains("lvl:0s,id:\"minecraft:protection\"") || Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"minecraft:protection\",lvl:0s")) throw new IllegalArgumentException("");
							if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) throw new IllegalArgumentException("");
							ItemStack newItem = item.clone();
							ItemMeta newMeta = newItem.getItemMeta();
							newMeta.removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
							if(getConfig().getBoolean("glowing-armor")) newMeta.addEnchant(Method.GLOWING, 0, true);
							newItem.setItemMeta(newMeta);
							p.getInventory().setHelmet(newItem);
						}catch(Exception e) {
							
						}
						try {
							ItemStack item = p.getInventory().getChestplate();
							if(item == null) throw new IllegalArgumentException("");
							if(item.getType() == Material.AIR) throw new IllegalArgumentException("");
							if(!Method.getNBTTag(item, "Enchantments").getValue().contains("lvl:0s,id:\"minecraft:protection\"") || Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"minecraft:protection\",lvl:0s")) throw new IllegalArgumentException("");
							if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) throw new IllegalArgumentException("");
							ItemStack newItem = item.clone();
							ItemMeta newMeta = newItem.getItemMeta();
							newMeta.removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
							if(getConfig().getBoolean("glowing-armor")) newMeta.addEnchant(Method.GLOWING, 0, true);
							newItem.setItemMeta(newMeta);
							p.getInventory().setChestplate(newItem);
						}catch(Exception e) {
							
						}
						try {
							ItemStack item = p.getInventory().getLeggings();
							if(item == null) throw new IllegalArgumentException("");
							if(item.getType() == Material.AIR) throw new IllegalArgumentException("");
							if(!Method.getNBTTag(item, "Enchantments").getValue().contains("lvl:0s,id:\"minecraft:protection\"") || Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"minecraft:protection\",lvl:0s")) throw new IllegalArgumentException("");
							if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) throw new IllegalArgumentException("");
							ItemStack newItem = item.clone();
							ItemMeta newMeta = newItem.getItemMeta();
							newMeta.removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
							if(getConfig().getBoolean("glowing-armor")) newMeta.addEnchant(Method.GLOWING, 0, true);
							newItem.setItemMeta(newMeta);
							p.getInventory().setLeggings(newItem);
						}catch(Exception e) {
							
						}
						try {
							ItemStack item = p.getInventory().getBoots();
							if(item == null) throw new IllegalArgumentException("");
							if(item.getType() == Material.AIR) throw new IllegalArgumentException("");
							if(!Method.getNBTTag(item, "Enchantments").getValue().contains("lvl:0s,id:\"minecraft:protection\"") || Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"minecraft:protection\",lvl:0s")) throw new IllegalArgumentException("");
							if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) throw new IllegalArgumentException("");
							ItemStack newItem = item.clone();
							ItemMeta newMeta = newItem.getItemMeta();
							newMeta.removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
							if(getConfig().getBoolean("glowing-armor")) newMeta.addEnchant(Method.GLOWING, 0, true);
							newItem.setItemMeta(newMeta);
							p.getInventory().setBoots(newItem);
						}catch(Exception e) {
							
						}
						for(int i = 0; i < p.getInventory().getContents().length; i++) {
							try {
								ItemStack item = p.getInventory().getItem(i);
								if(item == null) continue;
								if(item.getType() == Material.AIR) continue;
								if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) continue;
								if(!glowing && Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
									ItemStack newItem = item.clone();
									ItemMeta newMeta = newItem.getItemMeta();
									newMeta.removeEnchant(Method.GLOWING);
									newItem.setItemMeta(newMeta);
									item = newItem;
								}else if(glowing && !Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
									ItemStack newItem = item.clone();
									ItemMeta newMeta = newItem.getItemMeta();
									newMeta.addEnchant(Method.GLOWING, 0, true);
									newItem.setItemMeta(newMeta);
									item = newItem;
								}
								p.getInventory().setItem(i, item);
							}catch(Exception e) {
								if(glowing) {
									try {
										ItemStack item = p.getInventory().getItem(i);
										if(!Method.hasNBTTag(item, "Enchantments") && Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) {
											ItemMeta itemmeta = item.getItemMeta();
											itemmeta.addEnchant(Method.GLOWING, 0, true);
											item.setItemMeta(itemmeta);
											p.getInventory().setItem(i, item);
										}
									}catch(Exception e1) {
										
									}
								}
							}
						}
						try {
							ItemStack item = p.getInventory().getItemInOffHand();
							if(item == null) throw new IllegalArgumentException();
							if(item.getType() == Material.AIR) throw new IllegalArgumentException();
							if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) throw new IllegalArgumentException();
							if(!glowing && Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
								ItemStack newItem = item.clone();
								ItemMeta newMeta = newItem.getItemMeta();
								newMeta.removeEnchant(Method.GLOWING);
								newItem.setItemMeta(newMeta);
								item = newItem;
							}else if(glowing && !Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
								ItemStack newItem = item.clone();
								ItemMeta newMeta = newItem.getItemMeta();
								newMeta.addEnchant(Method.GLOWING, 0, true);
								newItem.setItemMeta(newMeta);
								item = newItem;
							}
							p.getInventory().setItemInOffHand(item);
						}catch(Exception e) {
							if(glowing) {
								try {
									ItemStack item = p.getInventory().getItemInOffHand();
									if(!Method.hasNBTTag(item, "Enchantments") && Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) {
										ItemMeta itemmeta = item.getItemMeta();
										itemmeta.addEnchant(Method.GLOWING, 0, true);
										item.setItemMeta(itemmeta);
										p.getInventory().setItemInOffHand(item);
									}
								}catch(Exception e1) {
									
								}
							}
						}
						try {
							ItemStack item = p.getInventory().getHelmet();
							if(item == null) throw new IllegalArgumentException();
							if(item.getType() == Material.AIR) throw new IllegalArgumentException();
							if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) throw new IllegalArgumentException();
							if(!glowing && Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
								ItemStack newItem = item.clone();
								ItemMeta newMeta = newItem.getItemMeta();
								newMeta.removeEnchant(Method.GLOWING);
								newItem.setItemMeta(newMeta);
								item = newItem;
							}else if(glowing && !Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
								ItemStack newItem = item.clone();
								ItemMeta newMeta = newItem.getItemMeta();
								newMeta.addEnchant(Method.GLOWING, 0, true);
								newItem.setItemMeta(newMeta);
								item = newItem;
							}
							p.getInventory().setHelmet(item);
						}catch(Exception e) {
							if(glowing) {
								try {
									ItemStack item = p.getInventory().getHelmet();
									if(!Method.hasNBTTag(item, "Enchantments") && Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) {
										ItemMeta itemmeta = item.getItemMeta();
										itemmeta.addEnchant(Method.GLOWING, 0, true);
										item.setItemMeta(itemmeta);
										p.getInventory().setHelmet(item);
									}
								}catch(Exception e1) {
									
								}
							}
						}
						try {
							ItemStack item = p.getInventory().getChestplate();
							if(item == null) throw new IllegalArgumentException();
							if(item.getType() == Material.AIR) throw new IllegalArgumentException();
							if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) throw new IllegalArgumentException();
							if(!glowing && Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
								ItemStack newItem = item.clone();
								ItemMeta newMeta = newItem.getItemMeta();
								newMeta.removeEnchant(Method.GLOWING);
								newItem.setItemMeta(newMeta);
								item = newItem;
							}else if(glowing && !Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
								ItemStack newItem = item.clone();
								ItemMeta newMeta = newItem.getItemMeta();
								newMeta.addEnchant(Method.GLOWING, 0, true);
								newItem.setItemMeta(newMeta);
								item = newItem;
							}
							p.getInventory().setChestplate(item);
						}catch(Exception e) {
							if(glowing) {
								try {
									ItemStack item = p.getInventory().getChestplate();
									if(!Method.hasNBTTag(item, "Enchantments") && Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) {
										ItemMeta itemmeta = item.getItemMeta();
										itemmeta.addEnchant(Method.GLOWING, 0, true);
										item.setItemMeta(itemmeta);
										p.getInventory().setChestplate(item);
									}
								}catch(Exception e1) {
									
								}
							}
						}
						try {
							ItemStack item = p.getInventory().getLeggings();
							if(item == null) throw new IllegalArgumentException();
							if(item.getType() == Material.AIR) throw new IllegalArgumentException();
							if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) throw new IllegalArgumentException();
							if(!glowing && Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
								ItemStack newItem = item.clone();
								ItemMeta newMeta = newItem.getItemMeta();
								newMeta.removeEnchant(Method.GLOWING);
								newItem.setItemMeta(newMeta);
								item = newItem;
							}else if(glowing && !Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
								ItemStack newItem = item.clone();
								ItemMeta newMeta = newItem.getItemMeta();
								newMeta.addEnchant(Method.GLOWING, 0, true);
								newItem.setItemMeta(newMeta);
								item = newItem;
							}
							p.getInventory().setLeggings(item);
						}catch(Exception e) {
							if(glowing) {
								try {
									ItemStack item = p.getInventory().getLeggings();
									if(!Method.hasNBTTag(item, "Enchantments") && Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) {
										ItemMeta itemmeta = item.getItemMeta();
										itemmeta.addEnchant(Method.GLOWING, 0, true);
										item.setItemMeta(itemmeta);
										p.getInventory().setLeggings(item);
									}
								}catch(Exception e1) {
									
								}
							}
						}
						try {
							ItemStack item = p.getInventory().getBoots();
							if(item == null) throw new IllegalArgumentException();
							if(item.getType() == Material.AIR) throw new IllegalArgumentException();
							if(!Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) throw new IllegalArgumentException();
							if(!glowing && Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
								ItemStack newItem = item.clone();
								ItemMeta newMeta = newItem.getItemMeta();
								newMeta.removeEnchant(Method.GLOWING);
								newItem.setItemMeta(newMeta);
								item = newItem;
							}else if(glowing && !Method.getNBTTag(item, "Enchantments").getValue().contains("id:\"armorplusplus:glowing\"")) {
								ItemStack newItem = item.clone();
								ItemMeta newMeta = newItem.getItemMeta();
								newMeta.addEnchant(Method.GLOWING, 0, true);
								newItem.setItemMeta(newMeta);
								item = newItem;
							}
							p.getInventory().setBoots(item);
						}catch(Exception e) {
							if(glowing) {
								try {
									ItemStack item = p.getInventory().getBoots();
									if(!Method.hasNBTTag(item, "Enchantments") && Method.getNBTTag(Method.getNBTTag(item, "display").getKey(), "Lore").getValue().contains("'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gold\",\"text\":\"(4 pieces must be worn for abilities to work)\"}],\"text\":\"\"}'")) {
										ItemMeta itemmeta = item.getItemMeta();
										itemmeta.addEnchant(Method.GLOWING, 0, true);
										item.setItemMeta(itemmeta);
										p.getInventory().setBoots(item);
									}
								}catch(Exception e1) {
									
								}
							}
						}
					}
					getLogger().info("Ran changes to players having protection 0 armors and glowing armors.");
					
				}
			}, 0L, 10 * 60 * 20L);
			
			// For every second
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					for(Player p : Bukkit.getOnlinePlayers()) {
						ability.obsidianArmor(p);
						ability.cactusArmor(p);
						ability.magmaArmor(p);
						ability.brickArmor(p);
						ability.netherBrickArmor(p);
						ability.redNetherBrickArmor(p);
						ability.iceArmor(p);
						ability.snowArmor(p);
					}
				}
			}, 0L, 20L);
	}
	public void loadMetrics() {
		Metrics metrics = new Metrics(this, 9177);
		boolean already = false;
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				if(!already) metrics.addCustomChart(new Metrics.DrilldownPie("plugin_outdated", () -> {
					Map<String, Map<String, Integer>> map = new HashMap<>();
					Map<String, Integer> entry = new HashMap<>();
					entry.put((outdated == uptodate ? "Unknown" : outdated ? "Outdated" : "Up-to-date"), 1);
					if(outdated) {
						map.put("Outdated", entry);
					}else if(uptodate) {
						map.put("Up-to-date", entry);
					}else {
						map.put("Unknown", entry);
					}
					return map;
				}));
				else {
					uc.getVersion(version -> {
						if(getDescription().getVersion().equalsIgnoreCase(version)) {
							uptodate = true;
							outdated = false;
						}else if(version != null) {
							if(!outdated) {
								msg = ChatColor.GREEN + "ArmorPlusPlus > Available update version " + version + ". Download at https://www.spigotmc.org/resources/armorplusplus.74748/";
								getServer().getConsoleSender().sendMessage(msg);
								for(Player p : getServer().getOnlinePlayers()) {
									if(p.hasPermission("armorplusplus.cheat") || p.isOp()) {
										p.sendMessage(msg);
									}
								}
							}
							outdated = true;
							uptodate = false;
						}
					});
				}
			}
		}, 0L, 10 /*minutes*/ * 60 * 20L);
	}
	public List<String> checkMissingConfig() {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(getDataFolder().getAbsolutePath() + "/config.yml"));
		List<String> missing = new ArrayList<String>();
		if(!config.contains("check-update")) missing.add("check-update");
		if(!config.contains("disabled-armors")) missing.add("disabled-armors");
		if(!config.contains("glowing-armor")) missing.add("glowing-armor");
		if(!config.contains("disabled-items")) missing.add("disabled-items");
		
		String missingMsg = "Outdated configuration file/the section is deleted. Missing configuration/s: ";
		for(int i = 0; i < missing.size(); i++) {
			missingMsg += "'" + missing.get(i) + "'";
			if(i != missing.size() - 1) missingMsg += ", ";
		}
		if(missing.size() != 0) getLogger().warning(missingMsg);
		return missing;
	}
	
	public List<String> checkMissingConfig(Player p, boolean sendMessage) {
		YamlConfiguration config = null;
		try {
			config = YamlConfiguration.loadConfiguration(new File(getDataFolder().getAbsolutePath() + "/config.yml"));
		}catch(Exception e) {
			e.printStackTrace();
			if(sendMessage) getLogger().warning("Invalid configuration file!");
			if(sendMessage) p.sendMessage(ChatColor.RED + "Invalid configuration file!");
			return new ArrayList<String>();
		}
		if(config == null && sendMessage) getLogger().warning("Invalid configuration file!");
		if(config == null && sendMessage) p.sendMessage(ChatColor.RED + "Invalid configuration file!");
		if(config == null) return new ArrayList<String>();
		List<String> missing = new ArrayList<String>();
		if(!config.contains("check-update")) missing.add("check-update");
		if(!config.contains("disabled-armors")) missing.add("disabled-armors");
		if(!config.contains("glowing-armor")) missing.add("glowing-armor");
		if(!config.contains("disabled-items")) missing.add("disabled-items");
		
		String missingMsg = "Outdated configuration file/the section is deleted. Missing configuration/s: ";
		for(int i = 0; i < missing.size(); i++) {
			missingMsg += "'" + missing.get(i) + "'";
			if(i != missing.size() - 1) missingMsg += ", ";
		}
		if(missing.size() != 0 && sendMessage) getLogger().warning(missingMsg);
		if(missing.size() != 0 && sendMessage) p.sendMessage(ChatColor.RED + missingMsg);
		return missing;
	}
	
	@Override
	public void onDisable() {
		@SuppressWarnings("unchecked")
		List<ShapedRecipe> recipes = (List<ShapedRecipe>) ar.activeRecipes.clone();
		for(ShapedRecipe recipe : recipes) {
			try {
				getServer().removeRecipe(recipe.getKey());
				ar.activeRecipes.remove(recipe);
			}catch(Exception e) {
				
			}
		}
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Armor++ plugin disabled v" + this.getDescription().getVersion());
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
	}
	

}
