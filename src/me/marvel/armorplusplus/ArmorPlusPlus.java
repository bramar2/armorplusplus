package me.marvel.armorplusplus;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.marvel.armorplusplus.commands.InventoryEvents;
import me.marvel.armorplusplus.commands.TabComplete;
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
	private me.marvel.armorplusplus.events.Listener listener;
	public boolean ver16 = false;
	public ArrayList<String> armorIds = new ArrayList<String>();
	public List<String> disabledArmors;
	public ArmorRecipe ar;
	public static ArmorPlusPlus instance;
	public InventoryEvents invevents;
	/**
	 * Hashmap containing
	 * 1st String = Armor ID
	 * 2nd String = Armor Name
	 * e.g BONE_BLOCK -> Bone
	 */
	private final LinkedHashMap<String, String> armorIdtoName = Method.getArmorIdtoName();
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {
		if(e.getPlayer().isOp() || e.getPlayer().hasPermission("armorplusplus.cheat")) {
			Player p = e.getPlayer();
			if(outdated) {
				if(msg != "") p.sendMessage(msg);
				else p.sendMessage(ChatColor.GREEN + "ArmorPlusPlus > Available update version. Current plugin is outdated. Download at https://www.spigotmc.org/resources/armorplusplus.74748/");
			}
			if(hasMineTinker) {
				p.sendMessage(ChatColor.RED + "ArmorPlusPlus > Incompatibility plugin found: MineTinker. Note: This plugin will not work properly!");
			}
		}
	}
	
	@Override
	public void onEnable() {
		invevents = new InventoryEvents(this, this);
		ArmorPlusPlus.instance = this;
		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		Method.NMSVERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		Method.plugin = this;
		Method.GLOWING = new GlowingEnchantment("glowing", this);
		registerGlowing();
		ar = new ArmorRecipe(this);
		if(getServer().getVersion().toLowerCase().contains("1.16")) {
			ver16 = true;
		}else ver16 = false;
		getServer().getPluginManager().registerEvents(this, this);
		loadArmorIds();
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Armor++ plugin enabled v" + this.getDescription().getVersion());
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		try {
			List<String> missing = checkMissingConfig();
			if(missing.size() != 0) getLogger().warning("To get up-to-date configuration, do /armorplusplus resetconfig to reset the config.");
		}catch(Exception e) {
			e.printStackTrace();
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
							msg = ChatColor.GREEN + "ArmorPlusPlus > Available update version " + version + ". Download at https://www.spigotmc.org/resources/armorplusplus.74748/";
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
		listener = new me.marvel.armorplusplus.events.Listener(this);
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
			getServer().getConsoleSender().sendMessage(ChatColor.RED + "ArmorPlusPlus >  Incompatibility plugins found: MineTinker.");
			getServer().getConsoleSender().sendMessage(ChatColor.RED + "ArmorPlusPlus > This plugin will not work properly!");
		}else {
			getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ArmorPlusPlus/INFO] No incompatibility plugins found!");
		}
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
	    }
	}
	public void loadArmorIds() {
		try {
			disabledArmors = getConfig().getStringList("disabled-armors");
			Inventory gui = new me.marvel.armorplusplus.commands.ArmorPlusPlus(this, this).openGui((Player)null);
			for(ItemStack item : gui.getContents()) {
				try {
					String id = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "armorId"), PersistentDataType.STRING);
					armorIds.add(id);
				}catch(Exception e) {
					
				}
			}
			armorIds = new ArrayList<String>(new HashSet<String>(armorIds));
			for(String s : new ArrayList<String>(disabledArmors)) {
				disabledArmors.remove(s);
				if(armorIds.contains(s.toUpperCase())) {
					try {
						disabledArmors.add(s.toUpperCase());
						continue;
					}catch(Exception e) {
						
					}
				}
			}
		}catch(Exception e) {
			getServer().getConsoleSender().sendMessage(ChatColor.RED + "ArmorPlusPlus > An error occured while loading up armor ids and disabled armors.");
			e.printStackTrace();
		}
	}
	public void loadRecipe() {
		for(java.lang.reflect.Method method : ArmorRecipe.class.getMethods()) {
			method.setAccessible(true);
			if(method.getName().toLowerCase().contains("armor") &&
					method.getParameterCount() == 0) {
				try {
					if(disabledArmors == null) method.invoke(ar);
					if(disabledArmors.size() == 0) method.invoke(ar);
					else {
						boolean disabled = false;
						for(String s : disabledArmors) {
							try { 
								if(method.getName().toLowerCase().contains(armorIdtoName.get(s.toUpperCase()).toLowerCase().replace(" ", ""))) {
									disabled = true;
									break;
								}
							}catch(Exception e) {
								
							}
						}
						if(!disabled) method.invoke(ar);
					}
				}catch(Exception e) {
					
				}
			}
		}
	}
	private void loadAbilities() {
			// For every 2.5 second
			ArmorAbilities ability = new ArmorAbilities(this);
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					if(!disabledArmors.contains("DIRT")) ability.dirtRegrowth();
				}
			}, 0L, 50L);
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
					}
					// ARMOR PROTECTION 0 FIX
					getLogger().info("Ran changes to players having protection 0 armors and glowing armors.");
					// GLOWING ARMOR
					boolean glowing = getConfig().getBoolean("glowing-armor");
					for(Player p : Bukkit.getOnlinePlayers()) {
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
					
				}
			}, 0L, 10 * 60 * 20L);
			
			// For every tick
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
						for(java.lang.reflect.Method method : ArmorAbilities.class.getMethods()) {
							method.setAccessible(true);
							if(method.getName().toLowerCase().contains("armor") && !method.isAnnotationPresent(EventHandler.class) &&
									method.getParameterCount() == 0) {
								try {
									if(disabledArmors == null) method.invoke(ability);
									if(disabledArmors.size() == 0) method.invoke(ability);
									else {
										boolean disabled = false;
										for(String s : disabledArmors) {
											try {
												if(method.getName().toLowerCase().contains(armorIdtoName.get(s.toUpperCase()).toLowerCase().replace(" ", ""))) {
													disabled = true;
													break;
												}
											}catch(Exception e) {
												
											}
										}
										if(!disabled) method.invoke(ability);
									}
								}catch(Exception e) {
									
								}
							}
						}
					}
			}, 0L, 1L);
	}
	private void loadMetrics() {
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
		
		String missingMsg = "Outdated configuration file/the section is deleted. Missing configuration/s: ";
		for(int i = 0; i < missing.size(); i++) {
			missingMsg += "'" + missing.get(i) + "'";
			if(i != missing.size() - 1) missingMsg += ", ";
		}
		if(missing.size() != 0 && sendMessage) getLogger().warning(missingMsg);
		if(missing.size() != 0 && sendMessage) p.sendMessage(ChatColor.RED + missingMsg);
		return missing;
	}
	
	public void resetRecipe() {
		@SuppressWarnings("unchecked")
		List<ShapedRecipe> recipes = (List<ShapedRecipe>) ar.activeRecipes.clone();
		for(ShapedRecipe recipe : recipes) {
			try {
				getServer().removeRecipe(recipe.getKey());
				ar.activeRecipes.remove(recipe);
			}catch(Exception e) {
				
			}
		}
	}
	
	@Override
	public void onDisable() {
		resetRecipe();
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Armor++ plugin disabled v" + this.getDescription().getVersion());
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
	}
	

}
