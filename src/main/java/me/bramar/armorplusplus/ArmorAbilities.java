package me.bramar.armorplusplus;

import static me.bramar.armorplusplus.Method.GLOWING;
import static me.bramar.armorplusplus.Method.changeArmorInArmorSlot;
import static me.bramar.armorplusplus.Method.getSmeltableItems;
import static me.bramar.armorplusplus.Method.ifWearingAll;
import static me.bramar.armorplusplus.Method.replaceArmorInInventory;
import static me.bramar.armorplusplus.Method.sendActionBar;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Snow;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.bramar.armorplusplus.events.ArmorEquipEvent;
import me.bramar.armorplusplus.events.PlayerLandEvent;

/**
 * Contains all abilities.
 * <p>
 * Required for all armor abilities to work.
 */
public class ArmorAbilities implements Listener {
	
	private ArrayList<UUID> anti = new ArrayList<UUID>();
	private ArrayList<UUID> cooldown = new ArrayList<UUID>();
	private ArrayList<UUID> scooldown = new ArrayList<UUID>();
	private ArrayList<UUID> dispenser = new ArrayList<UUID>();
	private ArrayList<UUID> pull = new ArrayList<UUID>();
	private ArrayList<UUID> smeltCooldown = new ArrayList<UUID>();
	private ArrayList<UUID> smeltsneaking = new ArrayList<UUID>();
	private ArrayList<UUID> pistonCooldown = new ArrayList<UUID>();
	private ArrayList<UUID> wscooldown = new ArrayList<UUID>();
	private ArrayList<UUID> teleportCooldown = new ArrayList<UUID>();
	private ArrayList<UUID> boneCooldown = new ArrayList<UUID>();
	private ArrayList<UUID> snowCooldown = new ArrayList<UUID>();
	
	private void check(Player p) {
		Integer min;
		Integer max;
		min = 1;
		max = 25;
		double seminote = (int) ((Math.random()*((max-min)+1))+min) - 1;
		int note = (int) seminote;
		Note n = new me.bramar.armorplusplus.Note();
		Material mat = p.getLocation().subtract(0, 1, 0).getBlock().getType();
		if(mat == Material.GOLD_BLOCK) {
			n.gold(p, note);
		}else if(mat == Material.CLAY) {
			n.clay(p, note);
		}else if(mat == Material.PACKED_ICE) {
			n.pice(p, note);
		}else if(mat == Material.BONE_BLOCK) {
			n.bone(p, note);
		}else if(mat == Material.IRON_BLOCK) {
			n.iron(p, note);
		}else if(mat == Material.SOUL_SAND) {
			n.soulsand(p, note);
		}else if(mat == Material.HAY_BLOCK) {
			n.hay(p, note);
		}else if(mat == Material.EMERALD_BLOCK) {
			n.emerald(p, note);
		}else if(mat == Material.PUMPKIN | mat == Material.CARVED_PUMPKIN) {
			n.pumpkin(p, note);
		}else if(mat == Material.GLOWSTONE) {
			n.glowstone(p, note);
		}else if(mat == Material.BLACK_WOOL | mat == Material.BLUE_WOOL | mat == Material.BROWN_WOOL | mat == Material.CYAN_WOOL | mat == Material.GRAY_WOOL | mat == Material.GREEN_WOOL | mat == Material.LIGHT_BLUE_WOOL | mat == Material.LIGHT_GRAY_WOOL | mat == Material.LIME_WOOL | mat == Material.MAGENTA_WOOL | mat == Material.ORANGE_WOOL | mat == Material.PINK_WOOL | mat == Material.PURPLE_WOOL | mat == Material.RED_WOOL | mat == Material.WHITE_WOOL | mat == Material.YELLOW_WOOL) {
			n.wool(p, note);
		}else if(mat == Material.GLASS | mat == Material.GLASS_PANE | mat == Material.SEA_LANTERN | mat == Material.CONDUIT | mat == Material.BEACON | mat == Material.BLACK_STAINED_GLASS | mat == Material.BLACK_STAINED_GLASS_PANE | mat == Material.BLUE_STAINED_GLASS | mat == Material.BLUE_STAINED_GLASS_PANE | mat == Material.BROWN_STAINED_GLASS | mat == Material.BROWN_STAINED_GLASS_PANE | mat == Material.CYAN_STAINED_GLASS | mat == Material.CYAN_STAINED_GLASS_PANE | mat == Material.GRAY_STAINED_GLASS | mat == Material.GRAY_STAINED_GLASS_PANE | mat == Material.GREEN_STAINED_GLASS | mat == Material.GREEN_STAINED_GLASS_PANE | mat == Material.LIGHT_BLUE_STAINED_GLASS | mat == Material.LIGHT_BLUE_STAINED_GLASS_PANE | mat == Material.LIGHT_GRAY_STAINED_GLASS | mat == Material.LIGHT_GRAY_STAINED_GLASS_PANE | mat == Material.LIME_STAINED_GLASS | mat == Material.LIME_STAINED_GLASS_PANE | mat == Material.MAGENTA_STAINED_GLASS | mat == Material.MAGENTA_STAINED_GLASS_PANE | mat == Material.ORANGE_STAINED_GLASS | mat == Material.ORANGE_STAINED_GLASS_PANE | mat == Material.PINK_STAINED_GLASS | mat == Material.PINK_STAINED_GLASS_PANE | mat == Material.PURPLE_STAINED_GLASS | mat == Material.PURPLE_STAINED_GLASS_PANE | mat == Material.RED_STAINED_GLASS | mat == Material.RED_STAINED_GLASS_PANE | mat == Material.WHITE_STAINED_GLASS | mat == Material.WHITE_STAINED_GLASS_PANE | mat == Material.YELLOW_STAINED_GLASS | mat == Material.YELLOW_STAINED_GLASS_PANE) {
			n.glass(p, note);
		}else if(mat == Material.ACACIA_PLANKS | mat == Material.BIRCH_PLANKS | mat == Material.DARK_OAK_PLANKS | mat == Material.JUNGLE_PLANKS | mat == Material.OAK_PLANKS | mat == Material.SPRUCE_PLANKS | mat == Material.ACACIA_LOG | mat == Material.BIRCH_LOG | mat == Material.DARK_OAK_LOG | mat == Material.JUNGLE_LOG | mat == Material.OAK_LOG | mat == Material.SPRUCE_LOG | mat == Material.ACACIA_WOOD | mat == Material.BIRCH_WOOD | mat == Material.DARK_OAK_WOOD | mat == Material.JUNGLE_WOOD | mat == Material.OAK_WOOD | mat == Material.SPRUCE_WOOD | mat == Material.STRIPPED_ACACIA_LOG | mat == Material.STRIPPED_BIRCH_LOG | mat == Material.STRIPPED_DARK_OAK_LOG | mat == Material.STRIPPED_JUNGLE_LOG | mat == Material.STRIPPED_OAK_LOG | mat == Material.STRIPPED_SPRUCE_LOG | mat == Material.STRIPPED_ACACIA_WOOD | mat == Material.STRIPPED_BIRCH_WOOD | mat == Material.STRIPPED_DARK_OAK_WOOD | mat == Material.STRIPPED_JUNGLE_WOOD | mat == Material.STRIPPED_OAK_WOOD | mat == Material.STRIPPED_SPRUCE_WOOD | mat == Material.NOTE_BLOCK | mat == Material.BOOKSHELF | mat == Material.CHEST | mat == Material.TRAPPED_CHEST | mat == Material.CRAFTING_TABLE | mat == Material.JUKEBOX | mat == Material.DAYLIGHT_DETECTOR | mat == Material.BROWN_MUSHROOM_BLOCK | mat == Material.RED_MUSHROOM_BLOCK | mat == Material.LOOM | mat == Material.BARREL | mat == Material.CARTOGRAPHY_TABLE | mat == Material.FLETCHING_TABLE | mat == Material.LECTERN | mat == Material.SMITHING_TABLE | mat == Material.CAMPFIRE | mat == Material.COMPOSTER | mat == Material.ACACIA_SLAB | mat == Material.BIRCH_SLAB | mat == Material.DARK_OAK_SLAB | mat == Material.JUNGLE_SLAB | mat == Material.OAK_SLAB | mat == Material.SPRUCE_SLAB) {
			n.wood(p, note);
		}else if(mat == Material.STONECUTTER | mat == Material.BLAST_FURNACE | mat == Material.SMOKER | mat == Material.OBSERVER | mat == Material.BONE_BLOCK | mat == Material.MAGMA_BLOCK | mat == Material.COAL_BLOCK | mat == Material.DROPPER | mat == Material.ENDER_CHEST | mat == Material.END_PORTAL_FRAME | mat == Material.ENCHANTING_TABLE | mat == Material.NETHERRACK | mat == Material.FURNACE | mat == Material.SPAWNER | mat == Material.OBSIDIAN | mat == Material.BRICKS | mat == Material.SANDSTONE | mat == Material.RED_SANDSTONE | mat == Material.CHISELED_SANDSTONE | mat == Material.CHISELED_RED_SANDSTONE | mat == Material.SMOOTH_SANDSTONE | mat == Material.SMOOTH_RED_SANDSTONE | mat == Material.DISPENSER | mat == Material.BEDROCK | mat == Material.COAL_ORE | mat == Material.DIAMOND_ORE | mat == Material.EMERALD_ORE | mat == Material.GOLD_ORE | mat == Material.IRON_ORE | mat == Material.LAPIS_ORE | mat == Material.NETHER_QUARTZ_ORE | mat == Material.REDSTONE_ORE | mat == Material.COBBLESTONE | mat == Material.STONE | mat == Material.ANDESITE | mat == Material.GRANITE | mat == Material.DIORITE | mat == Material.POLISHED_ANDESITE | mat == Material.POLISHED_GRANITE | mat == Material.POLISHED_DIORITE) {
			n.stone(p, note);
		}else if(mat == Material.SAND | mat == Material.GRAVEL | mat == Material.BLACK_CONCRETE_POWDER | mat == Material.BLUE_CONCRETE_POWDER | mat == Material.BROWN_CONCRETE_POWDER | mat == Material.CYAN_CONCRETE_POWDER | mat == Material.GRAY_CONCRETE_POWDER | mat == Material.GREEN_CONCRETE_POWDER | mat == Material.LIGHT_BLUE_CONCRETE_POWDER | mat == Material.LIGHT_GRAY_CONCRETE_POWDER | mat == Material.LIME_CONCRETE_POWDER | mat == Material.MAGENTA_CONCRETE_POWDER | mat == Material.ORANGE_CONCRETE_POWDER | mat == Material.PINK_CONCRETE_POWDER | mat == Material.PURPLE_CONCRETE_POWDER | mat == Material.RED_CONCRETE_POWDER | mat == Material.WHITE_CONCRETE_POWDER | mat == Material.YELLOW_CONCRETE_POWDER) {
			n.gravity(p, note);
		}else {
			n.other(p, note);
		}
	}
	HashMap<UUID, ArrayList<PotionEffect>> effectInfo = new HashMap<UUID, ArrayList<PotionEffect>>();
	me.bramar.armorplusplus.ArmorPlusPlus plugin;
	private void loadEffect(Player player) {
		effectInfo.put(player.getUniqueId(), getActiveEffects(player));
	}
	private ArrayList<PotionEffect> getActiveEffects(Player player) {
		return new ArrayList<PotionEffect>(player.getActivePotionEffects());
	}
	public ArmorAbilities(me.bramar.armorplusplus.ArmorPlusPlus ArmorPlusPlus) {
		plugin = ArmorPlusPlus;
		for(Player player : Bukkit.getOnlinePlayers()) {
			loadEffect(player);
		}
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				try {
					for(String disabledArmorName : plugin.disabledArmors) {
						if(disabledArmorName.equalsIgnoreCase("WITCH")) return;
					}
				}catch(Exception ignored) {}
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(!effectInfo.containsKey(p.getUniqueId())) {
						loadEffect(p);
						continue;
					}
					String lore = plugin.ver16 ? ChatColor.DARK_PURPLE + "Witch - Makes your effects last double as much" : "Witch - Makes your effects last double as much";
					if(!ifWearingAll(p, "Witch", lore)) return;
					HashMap<PotionEffectType, PotionEffect> old = new HashMap<PotionEffectType, PotionEffect>();
					HashMap<PotionEffectType, PotionEffect> current = new HashMap<PotionEffectType, PotionEffect>();
					for(PotionEffect potion : effectInfo.get(p.getUniqueId())) {
						old.put(potion.getType(), potion);
					}
					for(PotionEffect potion : p.getActivePotionEffects()) {
						current.put(potion.getType(), potion);
					}
					for(PotionEffectType type : PotionEffectType.values()) {
						if(!old.containsKey(type) && current.containsKey(type)) {
							// Potion effect added
							PotionEffect potion = current.get(type);
							PotionEffect doubled = new PotionEffect(type, potion.getDuration() * 2/*This is 2x but sometimes it might triple the effects cuz idk*/, potion.getAmplifier(), potion.isAmbient(), potion.hasParticles(), potion.hasIcon());
							p.removePotionEffect(type);
							p.addPotionEffect(doubled);
						}
					}
					effectInfo.remove(p.getUniqueId());
					effectInfo.put(p.getUniqueId(), getActiveEffects(p));
				}
			}
		}, 0L, 20L);
	}
	
	@EventHandler
	public void onAnvilRename(InventoryClickEvent e) {
		try {
			if(!(e.getClickedInventory() instanceof AnvilInventory)) return;
			if(!(e.getSlot() == 2)) return;
			AnvilInventory inventory = (AnvilInventory) e.getClickedInventory();
			ItemStack item = inventory.getItem(0);
			boolean correctarmor = false;
			for(String lore : item.getItemMeta().getLore()) {
				if(lore.equalsIgnoreCase(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)")) {
					correctarmor = true;
					break;
				}
			}
			if(!correctarmor) return;
			if(item.getItemMeta().getDisplayName().equalsIgnoreCase(inventory.getRenameText())) return;
			
			e.setCancelled(true);
			
			if(e.getWhoClicked() instanceof Player) {
				Player p = (Player) e.getWhoClicked();
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
				p.sendMessage(ChatColor.RED + "This item cannot be renamed. Renaming it would lose its ability!");
				p.setLevel(p.getLevel());
			}
		}catch(Exception e1) {
			
		}
	}
	
	public void dirtRegrowth() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
				for(String disabledArmorName : plugin.disabledArmors) {
					if(disabledArmorName.equalsIgnoreCase("DIRT")) return;
				}
				ItemStack helm = p.getInventory().getHelmet();
				ItemStack chest = p.getInventory().getChestplate();
				ItemStack legs = p.getInventory().getLeggings();
				ItemStack boots = p.getInventory().getBoots();
				String name1 = helm.getItemMeta().getDisplayName();
				String name2 = chest.getItemMeta().getDisplayName();
				String name3 = legs.getItemMeta().getDisplayName();
				String name4 = boots.getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Dirt Helmet") && name2.equalsIgnoreCase("Dirt Chestplate") && name3.equalsIgnoreCase("Dirt Leggings") && name4.equalsIgnoreCase("Dirt Boots")) {
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(ChatColor.GREEN + "Regrowth - Repair durability slowly") && lore2.equalsIgnoreCase(ChatColor.GREEN + "Regrowth - Repair durability slowly") && lore3.equalsIgnoreCase(ChatColor.GREEN + "Regrowth - Repair durability slowly") && lore4.equalsIgnoreCase(ChatColor.GREEN + "Regrowth - Repair durability slowly")) {
						int d1 = ((org.bukkit.inventory.meta.Damageable)helm.getItemMeta()).getDamage();
						int d2 = ((org.bukkit.inventory.meta.Damageable)chest.getItemMeta()).getDamage();
						int d3 = ((org.bukkit.inventory.meta.Damageable)legs.getItemMeta()).getDamage();
						int d4 = ((org.bukkit.inventory.meta.Damageable)boots.getItemMeta()).getDamage();
						if(d1 != 0) {
							ItemMeta meta = helm.getItemMeta();
							if(meta instanceof Damageable) ((Damageable)meta).setDamage(d1 - 1);
							helm.setItemMeta(meta);
							p.getInventory().setHelmet(helm);
						}
						if(d2 != 0) {
							ItemMeta meta = chest.getItemMeta();
							if(meta instanceof Damageable) ((Damageable)meta).setDamage(d2 - 1);
							chest.setItemMeta(meta);
							p.getInventory().setChestplate(chest);
						}
						if(d3 != 0) {
							ItemMeta meta = legs.getItemMeta();
							if(meta instanceof Damageable) ((Damageable)meta).setDamage(d3 - 1);
							legs.setItemMeta(meta);
							p.getInventory().setLeggings(legs);
						}
						if(d4 != 0) {
							ItemMeta meta = boots.getItemMeta();
							if(meta instanceof Damageable) ((Damageable)meta).setDamage(d4 - 1);
							boots.setItemMeta(meta);
							p.getInventory().setBoots(boots);
						}
					}
				}
			}catch(NullPointerException e) {
				
			}
			
		}
		
	}
	
	@EventHandler
	public void craftingArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
					if(disabledArmorName.equalsIgnoreCase("CRAFTING_TABLE")) return;
			}
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				String name1 = p.getInventory().getHelmet().getItemMeta().getDisplayName();
				String name2 = p.getInventory().getChestplate().getItemMeta().getDisplayName();
				String name3 = p.getInventory().getLeggings().getItemMeta().getDisplayName();
				String name4 = p.getInventory().getBoots().getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Crafting Helmet") && name2.equalsIgnoreCase("Crafting Chestplate") && name3.equalsIgnoreCase("Crafting Leggings") && name4.equalsIgnoreCase("Crafting Boots")) {
					String lore1 = p.getInventory().getHelmet().getItemMeta().getLore().get(0);
					String lore2 = p.getInventory().getChestplate().getItemMeta().getLore().get(0);
					String lore3 = p.getInventory().getLeggings().getItemMeta().getLore().get(0);
					String lore4 = p.getInventory().getBoots().getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(ChatColor.GOLD + "Crafter - Opens a crafting table") && lore2.equalsIgnoreCase(ChatColor.GOLD + "Crafter - Opens a crafting table") && lore3.equalsIgnoreCase(ChatColor.GOLD + "Crafter - Opens a crafting table") && lore4.equalsIgnoreCase(ChatColor.GOLD + "Crafter - Opens a crafting table")) {
						p.openWorkbench(p.getLocation(), true);
					}
				}
			}
		}catch(Exception e1) {
			
		}
	}
	@ArmorEquip
	@EventHandler
	public void glassArmor(ArmorEquipEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
					if(disabledArmorName.equalsIgnoreCase("GLASS")) return;
			}			
			Player p = e.getPlayer();
			if(ifWearingAll(Method.getArmor(p, e), "Glass", ChatColor.GRAY + "Invisibility - Provides Invisibility")) {
				try {
					p.removePotionEffect(PotionEffectType.INVISIBILITY);
				}catch(Exception ignored) {}
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 9));
			}else if(!ifWearingAll(Method.getArmor(p, e), "Glass", ChatColor.GRAY + "Invisibility - Provides Invisibility")) {
				try {
					p.removePotionEffect(PotionEffectType.INVISIBILITY);
				}catch(Exception ignored) {}
			}
		}catch(Exception catched) {}
	}
	
	@EventHandler
	public void furnaceArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
					if(disabledArmorName.equalsIgnoreCase("FURNACE")) return;
			}			
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				String name1 = p.getInventory().getHelmet().getItemMeta().getDisplayName();
				String name2 = p.getInventory().getChestplate().getItemMeta().getDisplayName();
				String name3 = p.getInventory().getLeggings().getItemMeta().getDisplayName();
				String name4 = p.getInventory().getBoots().getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Furnace Helmet") && name2.equalsIgnoreCase("Furnace Chestplate") && name3.equalsIgnoreCase("Furnace Leggings") && name4.equalsIgnoreCase("Furnace Boots")) {
					String lore = ChatColor.DARK_RED + "AutoSmelt - Smelts nearest dropped items";
					String lore1 = p.getInventory().getHelmet().getItemMeta().getLore().get(0);
					String lore2 = p.getInventory().getChestplate().getItemMeta().getLore().get(0);
					String lore3 = p.getInventory().getLeggings().getItemMeta().getLore().get(0);
					String lore4 = p.getInventory().getBoots().getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						if(smeltCooldown.contains(p.getUniqueId())) return;
						smeltsneaking.add(p.getUniqueId());
						if(smeltCooldown.contains(p.getUniqueId())) return;
						HashMap<Material, Material> smeltable_items = getSmeltableItems();
						World w = p.getWorld();
						ArrayList<Entity> nearbyEntities = new ArrayList<Entity>(w.getNearbyEntities(p.getLocation(), 5.0, 5.0, 5.0));
						ArrayList<Item> item = new ArrayList<Item>();
						for(Entity entity : nearbyEntities) {
							if(entity instanceof Item) {
								item.add(((Item)entity));
							}
						}
						for(Item i : item) {
							if(smeltable_items.containsKey(i.getItemStack().getType())) {
								smeltCooldown.add(p.getUniqueId());
								plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									public void run() {
										smeltCooldown.remove(p.getUniqueId());
									}
								}, 10 * 20L);
								break;
							}
						}
						int count = 0;
						int max = 16;
						for(Item i : item) {
							if(count >= max) break;
							if(smeltable_items.containsKey(i.getItemStack().getType())) {
								Location loc = i.getLocation();
								Material type = i.getItemStack().getType();
								int amount = i.getItemStack().getAmount();
								ItemStack itemStack = i.getItemStack();
								i.remove();
								ItemStack stack = new ItemStack(smeltable_items.get(type));
								if(!(count + amount > max)) {
									// count + amount is not > max
									// meaning count + amount = 1 - 32
									stack.setAmount(amount);
									w.dropItem(loc, stack);
									count += amount;
								}else {
									// newAmount = 16 - count;
									int newAmount = max - count;
									int spawnBack = amount - newAmount;
									// spawn the smelted
									stack.setAmount(newAmount);
									w.dropItem(loc, stack);
									// spawn it back
									itemStack.setAmount(spawnBack);
									w.dropItem(loc, itemStack);
									break;
								}
							}
						}
					}
					
				}
			}
		}catch(Exception e1) {
			
		}
	}
	@EventHandler
	public void tntArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("TNT")) return;
			}
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				String name1 = p.getInventory().getHelmet().getItemMeta().getDisplayName();
				String name2 = p.getInventory().getChestplate().getItemMeta().getDisplayName();
				String name3 = p.getInventory().getLeggings().getItemMeta().getDisplayName();
				String name4 = p.getInventory().getBoots().getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("TNT Helmet") && name2.equalsIgnoreCase("TNT Chestplate") && name3.equalsIgnoreCase("TNT Leggings") && name4.equalsIgnoreCase("TNT Boots")) {
					String lore = ChatColor.RED + "Explosive - Explodes when sneaking";
					String lore1 = p.getInventory().getHelmet().getItemMeta().getLore().get(0);
					String lore2 = p.getInventory().getChestplate().getItemMeta().getLore().get(0);
					String lore3 = p.getInventory().getLeggings().getItemMeta().getLore().get(0);
					String lore4 = p.getInventory().getBoots().getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						if(!(cooldown.contains(p.getUniqueId()))) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 10, false, false));
							Entity tnts = p.getWorld().spawnEntity(p.getLocation(), EntityType.PRIMED_TNT);
							if(tnts instanceof org.bukkit.entity.TNTPrimed) {
								TNTPrimed tnt = (TNTPrimed) tnts;
								tnt.setFuseTicks(0);
								cooldown.add(p.getUniqueId());
								plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									public void run() {
										cooldown.remove(p.getUniqueId());
									}
								}, 20);
							}
						}
					}
					
				}
			}
		}catch(Exception e1) {
			
		}
	}
	
	public void melody() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("NOTE_BLOCK")) return;
			}
		}catch(Exception ignored) {}
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
				String name1 = p.getInventory().getHelmet().getItemMeta().getDisplayName();
				String name2 = p.getInventory().getChestplate().getItemMeta().getDisplayName();
				String name3 = p.getInventory().getLeggings().getItemMeta().getDisplayName();
				String name4 = p.getInventory().getBoots().getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Note Helmet") && name2.equalsIgnoreCase("Note Chestplate") && name3.equalsIgnoreCase("Note Leggings") && name4.equalsIgnoreCase("Note Boots")) {
					String lore1 = p.getInventory().getHelmet().getItemMeta().getLore().get(0);
					String lore2 = p.getInventory().getChestplate().getItemMeta().getLore().get(0);
					String lore3 = p.getInventory().getLeggings().getItemMeta().getLore().get(0);
					String lore4 = p.getInventory().getBoots().getItemMeta().getLore().get(0);
					String lore = ChatColor.LIGHT_PURPLE + "Musical - Every step you take becomes a musical melody";
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						check(p);
						}
					}
				}catch(NullPointerException error) {
					
				}
		}
	}

	public void pumpkinFeed() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("PUMPKIN")) return;
			}
		}catch(Exception ignored) {}		
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
				ItemStack helm = p.getInventory().getHelmet();
				ItemStack chest = p.getInventory().getChestplate();
				ItemStack legs = p.getInventory().getLeggings();
				ItemStack boots = p.getInventory().getBoots();
				String name1 = helm.getItemMeta().getDisplayName();
				String name2 = chest.getItemMeta().getDisplayName();
				String name3 = legs.getItemMeta().getDisplayName();
				String name4 = boots.getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Pumpkin Helmet") && name2.equalsIgnoreCase("Pumpkin Chestplate") && name3.equalsIgnoreCase("Pumpkin Leggings") && name4.equalsIgnoreCase("Pumpkin Boots")) {
					String lore = ChatColor.RED + "Feeder - Automatically feeds the wearer";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						/* Put the ability below. This is auto-generated
						 * from ArmorCreator program
						 * 
						 */
						if(p.getFoodLevel() != 20) {
							p.setFoodLevel(p.getFoodLevel() + 1);
						}
					}
				}
			}catch(NullPointerException e) {
				
			}
			
		}
		
	}
	public void melonFeed() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("MELON")) return;
			}
		}catch(Exception ignored) {}	
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
				ItemStack helm = p.getInventory().getHelmet();
				ItemStack chest = p.getInventory().getChestplate();
				ItemStack legs = p.getInventory().getLeggings();
				ItemStack boots = p.getInventory().getBoots();
				String name1 = helm.getItemMeta().getDisplayName();
				String name2 = chest.getItemMeta().getDisplayName();
				String name3 = legs.getItemMeta().getDisplayName();
				String name4 = boots.getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Melon Helmet") && name2.equalsIgnoreCase("Melon Chestplate") && name3.equalsIgnoreCase("Melon Leggings") && name4.equalsIgnoreCase("Melon Boots")) {
					String lore = ChatColor.RED + "Feeder - Automatically feeds the wearer";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						/* Put the ability below. This is auto-generated
						 * from ArmorCreator program
						 */
						if(p.getFoodLevel() != 20) {
							p.setFoodLevel(p.getFoodLevel() + 1);
						}
						
					}
				}
			}catch(NullPointerException e) {
				
			}
			
		}
		
	}
	@EventHandler
	public void spongeArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SPONGE")) return;
			}
		}catch(Exception ignored) {}
		try {
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				ItemStack helm = p.getInventory().getHelmet();
				ItemStack chest = p.getInventory().getChestplate();
				ItemStack legs = p.getInventory().getLeggings();
				ItemStack boots = p.getInventory().getBoots();
				String name1 = helm.getItemMeta().getDisplayName();
				String name2 = chest.getItemMeta().getDisplayName();
				String name3 = legs.getItemMeta().getDisplayName();
				String name4 = boots.getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Sponge Helmet") && name2.equalsIgnoreCase("Sponge Chestplate") && name3.equalsIgnoreCase("Sponge Leggings") && name4.equalsIgnoreCase("Sponge Boots")) {
					String lore = ChatColor.YELLOW + "Absorbent - Absorbs nearby liquids";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						if(!(scooldown.contains(p.getUniqueId()))) {
							scooldown.add(p.getUniqueId());
							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								public void run() {
									try {
										scooldown.remove(p.getUniqueId());
									}catch(Exception e) {
										
									}
								}
							}, 20 * 6);
							int radius = 3;
							boolean sponged = false;
							for (int x = -(radius); x <= radius; x ++) {
								for(int y = -(radius); y <= radius; y ++) {
									for(int z = -(radius); z <= radius; z ++) {
										org.bukkit.block.Block block = p.getLocation().getBlock().getRelative(x, y, z);
										if(block.getType().equals(Material.WATER)) {
											sponged = true;
											block.setType(Material.AIR);
										}else if(block.getType().equals(Material.TALL_SEAGRASS)) {
											sponged = true;
											block.setType(Material.AIR);
										}else if(block.getType().equals(Material.SEAGRASS)) {
											sponged = true;
											block.setType(Material.AIR);
										}
									}
								}
							}
							if(!sponged) {try {
								scooldown.remove(p.getUniqueId());
							}catch(Exception e1) {
								
							}}
						}
					}
				}
			}
		}catch(Exception e1) {
			
		}
	}
	@EventHandler
	public void dispenserArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("DISPENSER")) return;
			}
		}catch(Exception ignored) {}	
		try {
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				ItemStack helm = p.getInventory().getHelmet();
				ItemStack chest = p.getInventory().getChestplate();
				ItemStack legs = p.getInventory().getLeggings();
				ItemStack boots = p.getInventory().getBoots();
				String name1 = helm.getItemMeta().getDisplayName();
				String name2 = chest.getItemMeta().getDisplayName();
				String name3 = legs.getItemMeta().getDisplayName();
				String name4 = boots.getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Dispenser Helmet") && name2.equalsIgnoreCase("Dispenser Chestplate") && name3.equalsIgnoreCase("Dispenser Leggings") && name4.equalsIgnoreCase("Dispenser Boots")) {
					String lore = ChatColor.WHITE + "Arrow Defence - Fires arrows outwards";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						/* Put the ability below. This is auto-generated
						 * from ArmorCreator program
						 */
						if(!(dispenser.contains(p.getUniqueId()))) {
							dispenser.add(p.getUniqueId());
							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								public void run() {
									dispenser.remove(p.getUniqueId());
								}
							}, 20 * 7);
							Location loc = p.getLocation().clone();
							float yaw = new Float(loc.getYaw());
							loc.setPitch(-10);
							loc.setYaw(loc.getYaw() + 45);
							
							Arrow a = p.launchProjectile(Arrow.class, loc.getDirection());
							a.setPickupStatus(PickupStatus.CREATIVE_ONLY);
							loc.setYaw(yaw);
							
							loc.setYaw(loc.getYaw() + 90);
							
							a = p.launchProjectile(Arrow.class, loc.getDirection());
							a.setPickupStatus(PickupStatus.CREATIVE_ONLY);
							loc.setYaw(yaw);
							
							loc.setYaw(loc.getYaw() + 135);
							
							a = p.launchProjectile(Arrow.class, loc.getDirection());
							a.setPickupStatus(PickupStatus.CREATIVE_ONLY);
							loc.setYaw(yaw);
							
							loc.setYaw(loc.getYaw() + 180);
							
							a = p.launchProjectile(Arrow.class, loc.getDirection());
							a.setPickupStatus(PickupStatus.CREATIVE_ONLY);
							loc.setYaw(yaw);
							
							loc.setYaw(loc.getYaw() + 225);
							
							a = p.launchProjectile(Arrow.class, loc.getDirection());
							a.setPickupStatus(PickupStatus.CREATIVE_ONLY);
							loc.setYaw(yaw);
							
							loc.setYaw(loc.getYaw() + 270);
							
							a = p.launchProjectile(Arrow.class, loc.getDirection());
							a.setPickupStatus(PickupStatus.CREATIVE_ONLY);
							loc.setYaw(yaw);
							
							loc.setYaw(loc.getYaw() + 315);
							
							a = p.launchProjectile(Arrow.class, loc.getDirection());
							a.setPickupStatus(PickupStatus.CREATIVE_ONLY);
							loc.setYaw(yaw);
							
							loc.setYaw(loc.getYaw() + 360);
							
							a = p.launchProjectile(Arrow.class, loc.getDirection());
							a.setPickupStatus(PickupStatus.CREATIVE_ONLY);
							loc.setYaw(yaw);
							
						}
					}
				}
			}
		}catch(Exception e1) {
			
		}
	}
	@ArmorEquip
	@EventHandler
	public void prismarineArmor(ArmorEquipEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("PRISMARINE")) return;
			}
		}catch(Exception ignored) {}
		try {
			Player p = e.getPlayer();
			if(ifWearingAll(Method.getArmor(p, e), "Prismarine", ChatColor.AQUA + "Diving Suit - Provides Depth Strider, Respiration")) {
				try {
					p.removePotionEffect(PotionEffectType.NIGHT_VISION);
				}catch(Exception ignored) {}
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 2));
			}else if(!ifWearingAll(Method.getArmor(p, e), "Prismarine", ChatColor.AQUA + "Diving Suit - Provides Depth Strider, Respiration")) {
				try {
					p.removePotionEffect(PotionEffectType.NIGHT_VISION);
				}catch(Exception ignored) {}
			}
		}catch(Exception catched) {}
	}
	@EventHandler
	public void enderArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("ENDER")) return;
			}
		}catch(Exception ignored) {}
		try {
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				ItemStack helm = p.getInventory().getHelmet();
				ItemStack chest = p.getInventory().getChestplate();
				ItemStack legs = p.getInventory().getLeggings();
				ItemStack boots = p.getInventory().getBoots();
				String name1 = helm.getItemMeta().getDisplayName();
				String name2 = chest.getItemMeta().getDisplayName();
				String name3 = legs.getItemMeta().getDisplayName();
				String name4 = boots.getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Ender Helmet") && name2.equalsIgnoreCase("Ender Chestplate") && name3.equalsIgnoreCase("Ender Leggings") && name4.equalsIgnoreCase("Ender Boots")) {
					String lore = plugin.ver16 ? ChatColor.DARK_PURPLE + "Ender Hoarder - Provides access to your ender chest" : "Ender Hoarder - Provides access to your ender chest";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						p.openInventory(p.getEnderChest());
						p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
					}
				}
			}
		}catch(Exception e1) {
			
		}
	}
	public void lapisExpGive() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("LAPIS")) return;
			}
		}catch(Exception ignored) {}	
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
				ItemStack helm = p.getInventory().getHelmet();
				ItemStack chest = p.getInventory().getChestplate();
				ItemStack legs = p.getInventory().getLeggings();
				ItemStack boots = p.getInventory().getBoots();
				String name1 = helm.getItemMeta().getDisplayName();
				String name2 = chest.getItemMeta().getDisplayName();
				String name3 = legs.getItemMeta().getDisplayName();
				String name4 = boots.getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Lapis Helmet") && name2.equalsIgnoreCase("Lapis Chestplate") && name3.equalsIgnoreCase("Lapis Leggings") && name4.equalsIgnoreCase("Lapis Boots")) {
					String lore = ChatColor.GREEN + "Experience Giving - Gives experience over time";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						float xp = p.getExp();
						boolean done = false;
						for(float i = 0.25F; i > 0.01F; i -= 0.01F) {
							try {
								if(done) {
									break;
								}else {
									float total = xp + i;
									p.setExp(total);
									done = true;
								}
							}catch(IllegalArgumentException e) {
								done = false;
							}
						}
						if(done == false) {
							p.setExp(0.25F);
							p.setLevel(p.getLevel() + 1);
						}
					}
				}
			}catch(NullPointerException e) {
				
			}
			
		}
		
	}
	public void cactusArmor(Player p) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("CACTUS")) return;
			}
		}catch(Exception ignored) {}	
		try {
			ItemStack helm = p.getInventory().getHelmet();
			ItemStack chest = p.getInventory().getChestplate();
			ItemStack legs = p.getInventory().getLeggings();
			ItemStack boots = p.getInventory().getBoots();
			String name1 = helm.getItemMeta().getDisplayName();
			String name2 = chest.getItemMeta().getDisplayName();
			String name3 = legs.getItemMeta().getDisplayName();
			String name4 = boots.getItemMeta().getDisplayName();
			if(name1.equalsIgnoreCase("Cactus Helmet") && name2.equalsIgnoreCase("Cactus Chestplate") && name3.equalsIgnoreCase("Cactus Leggings") && name4.equalsIgnoreCase("Cactus Boots")) {
				String lore = ChatColor.GREEN + "Prickly - Pricks colliding enemies and";
				String lore1 = helm.getItemMeta().getLore().get(0);
				String lore2 = chest.getItemMeta().getLore().get(0);
				String lore3 = legs.getItemMeta().getLore().get(0);
				String lore4 = boots.getItemMeta().getLore().get(0);
				if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
					/* Put the ability below. This is auto-generated
					 * from ArmorCreator program
					 */
					anti.add(p.getUniqueId());
					List<Entity> prickled = (List<Entity>) p.getWorld().getNearbyEntities(p.getLocation(), 0.5, 0.5, 0.5);
					for(Entity entity : prickled) {
						if(!(anti.contains(entity.getUniqueId()))) {
							if(entity instanceof org.bukkit.entity.Damageable) {
								org.bukkit.entity.Damageable damage = (org.bukkit.entity.Damageable) entity;
								try {
									damage.damage(1);
								}catch(IllegalArgumentException e) {
									damage.setHealth(0);
								}
							}
						}
					}
					anti.remove(p.getUniqueId());
				}
			}
		}catch(NullPointerException e) {
			
		}
		
	}
	@ArmorEquip
	@EventHandler
	public void leavesArmor(ArmorEquipEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("LEAVES")) return;
			}
		}catch(Exception ignored) {}	
		try {
			Player p = e.getPlayer();
			if(ifWearingAll(Method.getArmor(p, e), "Leaves", ChatColor.GREEN + "Lightweight - Fall slowly, like a leaf")) {
				try {
					p.removePotionEffect(PotionEffectType.SLOW_FALLING);
				}catch(Exception ignored) {}
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Integer.MAX_VALUE, 0));
			}else if(!ifWearingAll(Method.getArmor(p, e), "Leaves", ChatColor.GREEN + "Lightweight - Fall slowly, like a leaf")) {
				try {
					p.removePotionEffect(PotionEffectType.SLOW_FALLING);
				}catch(Exception ignored) {}
			}
		}catch(Exception catched) {}
	}
	@ArmorEquip
	@EventHandler
	public void sugarcaneArmor(ArmorEquipEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SUGAR_CANE")) return;
			}
		}catch(Exception ignored) {}
		try {
			Player p = e.getPlayer();
			if(ifWearingAll(Method.getArmor(p, e), "Sugar Cane", ChatColor.YELLOW + "Speedy - Increases Speed")) {
				try {
					p.removePotionEffect(PotionEffectType.SPEED);
				}catch(Exception ignored) {}
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
			}else if(!ifWearingAll(Method.getArmor(p, e), "Sugar Cane", ChatColor.YELLOW + "Speedy - Increases Speed")) {
				try {
					p.removePotionEffect(PotionEffectType.SPEED);
				}catch(Exception ignored) {}
			}
		}catch(Exception catched) {}
	}
	@EventHandler
	public void stickypistonArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("STICKY_PISTON")) return;
			}
		}catch(Exception ignored) {}	
		try {
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				ItemStack helm = p.getInventory().getHelmet();
				ItemStack chest = p.getInventory().getChestplate();
				ItemStack legs = p.getInventory().getLeggings();
				ItemStack boots = p.getInventory().getBoots();
				String name1 = helm.getItemMeta().getDisplayName();
				String name2 = chest.getItemMeta().getDisplayName();
				String name3 = legs.getItemMeta().getDisplayName();
				String name4 = boots.getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Sticky Piston Helmet") && name2.equalsIgnoreCase("Sticky Piston Chestplate") && name3.equalsIgnoreCase("Sticky Piston Leggings") && name4.equalsIgnoreCase("Sticky Piston Boots")) {
					String lore = ChatColor.GRAY + "Puller - Pulls in nearby entities";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						/* Put the ability below. This is auto-generated
						 * from ArmorCreator program
	                     * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
						 */
						if(!(pull.contains(p.getUniqueId()))) {
							pull.add(p.getUniqueId());
							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								public void run() {
									pull.remove(p.getUniqueId());
								}
							}, 20 * 7);
							int count = 0;
							for(Entity entity : p.getWorld().getNearbyEntities(p.getLocation(), 4, 4, 4)) {
									entity.teleport(p);
									count++;
							}
							try {
								sendActionBar(p, "[{\"text\":\"You pulled \"},{\"text\":\"" + (count - 1) + "\",\"color\":\"green\"},{\"text\":\" entities!\"}]");
							}catch(Exception e1) {
								p.sendMessage("You pulled " + ChatColor.GREEN + (count - 1) + ChatColor.RESET + " entities!");
							}
						}
					}
				}
			}
		}catch(Exception e1) {
			
		}
	}
	@EventHandler
	public void sandArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SAND")) return;
			}
		}catch(Exception ignored) {}	
		try {
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				ItemStack helm = p.getInventory().getHelmet();
				ItemStack chest = p.getInventory().getChestplate();
				ItemStack legs = p.getInventory().getLeggings();
				ItemStack boots = p.getInventory().getBoots();
				String name1 = helm.getItemMeta().getDisplayName();
				String name2 = chest.getItemMeta().getDisplayName();
				String name3 = legs.getItemMeta().getDisplayName();
				String name4 = boots.getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Sand Helmet") && name2.equalsIgnoreCase("Sand Chestplate") && name3.equalsIgnoreCase("Sand Leggings") && name4.equalsIgnoreCase("Sand Boots")) {
					String lore = ChatColor.GRAY + "Falling - Falls faster in air and sinks faster in";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						Vector v = p.getVelocity().setY(p.getVelocity().getY() - 0.2);
						p.setVelocity(v);
					}
				}
			}
		}catch(Exception e1) {
			
		}
	}
	@ArmorEquip
	@EventHandler
	public void quartzArmor(ArmorEquipEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("QUARTZ")) return;
			}
		}catch(Exception ignored) {}	
		try {
			Player p = e.getPlayer();
			if(ifWearingAll(Method.getArmor(p, e), "Quartz", ChatColor.WHITE + "Powerful - Increases speed and strength")) {
				try {
					p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					p.removePotionEffect(PotionEffectType.SPEED);
				}catch(Exception ignored) {}
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
			}else if(!ifWearingAll(Method.getArmor(p, e), "Quartz", ChatColor.WHITE + "Powerful - Increases speed and strength")) {
				try {
					p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					p.removePotionEffect(PotionEffectType.SPEED);
				}catch(Exception ignored) {}
			}
		}catch(Exception catched) {}
	}
	public void obsidianArmor(Player p) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("OBSIDIAN")) return;
			}
		}catch(Exception ignored) {}	
		try {
			ItemStack helm = p.getInventory().getHelmet();
			ItemStack chest = p.getInventory().getChestplate();
			ItemStack legs = p.getInventory().getLeggings();
			ItemStack boots = p.getInventory().getBoots();
			String name1 = helm.getItemMeta().getDisplayName();
			String name2 = chest.getItemMeta().getDisplayName();
			String name3 = legs.getItemMeta().getDisplayName();
			String name4 = boots.getItemMeta().getDisplayName();
			if(name1.equalsIgnoreCase("Obsidian Helmet") && name2.equalsIgnoreCase("Obsidian Chestplate") && name3.equalsIgnoreCase("Obsidian Leggings") && name4.equalsIgnoreCase("Obsidian Boots")) {
				String lore = ChatColor.GRAY + "Immovable";
				String lore1 = helm.getItemMeta().getLore().get(0);
				String lore2 = chest.getItemMeta().getLore().get(0);
				String lore3 = legs.getItemMeta().getLore().get(0);
				String lore4 = boots.getItemMeta().getLore().get(0);
				if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
					/* Put the ability below. This is auto-generated
					 * from ArmorCreator program
                     * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
					 */
					if(ifWearingAll(p, "Obsidian", lore)) {
						ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
						ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
						ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
						ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
						LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
						LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
						LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
						LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
						m1.setDisplayName("Obsidian Helmet");
						m2.setDisplayName("Obsidian Chestplate");
						m3.setDisplayName("Obsidian Leggings");
						m4.setDisplayName("Obsidian Boots");
						ArrayList<String> lores = new ArrayList<String>();
				        lores.add(ChatColor.GRAY + "Immovable");
				        lores.add(ChatColor.BOLD + (ChatColor.DARK_RED + "Flame Resistant"));
				        lores.add(ChatColor.BOLD + (ChatColor.RED + "Health Boost"));
				        lores.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
				        m1.setColor(Color.fromRGB(35, 1, 48));m2.setColor(Color.fromRGB(35, 1, 48));m3.setColor(Color.fromRGB(35, 1, 48));m4.setColor(Color.fromRGB(35, 1, 48));
				        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
						m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
						m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
						m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
						m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "obsidianHelm"), PersistentDataType.BYTE, Byte.parseByte("1"));
						m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "obsidianChest"), PersistentDataType.BYTE, Byte.parseByte("1"));
						m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "obsidianLegs"), PersistentDataType.BYTE, Byte.parseByte("1"));
						m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "obsidianFeet"), PersistentDataType.BYTE, Byte.parseByte("1"));
						m1.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
						m2.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
						m3.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
						m4.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
						
						m1.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 5, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
						m2.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
						m3.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 5, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
						m4.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 5, Operation.ADD_NUMBER, EquipmentSlot.FEET));
						
						m1.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.max_health", 5, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
						m2.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.max_health", 5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
						m3.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.max_health", 5, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
						m4.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.max_health", 5, Operation.ADD_NUMBER, EquipmentSlot.FEET));
						
						m1.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
						m2.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
						m3.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
						m4.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
						
						m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
						m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
						m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
						m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
						m1.setLore(lores);
						m2.setLore(lores);
						m3.setLore(lores);
						m4.setLore(lores);
						if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
						if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
						if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
						if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);

						armor1.setItemMeta(m1);
						armor2.setItemMeta(m2);
						armor3.setItemMeta(m3);
						armor4.setItemMeta(m4);
						changeArmorInArmorSlot(p, armor1, armor2, armor3, armor4);
					}
				}
			}
		}catch(Exception e1) {

		}
		try {
			ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
			ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
			ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
			ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
			LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
			LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
			LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
			m1.setDisplayName("Obsidian Helmet");
			m2.setDisplayName("Obsidian Chestplate");
			m3.setDisplayName("Obsidian Leggings");
			m4.setDisplayName("Obsidian Boots");
			
			m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "obsidianHelm"), PersistentDataType.BYTE, Byte.parseByte("1"));
			m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "obsidianChest"), PersistentDataType.BYTE, Byte.parseByte("1"));
			m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "obsidianLegs"), PersistentDataType.BYTE, Byte.parseByte("1"));
			m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "obsidianFeet"), PersistentDataType.BYTE, Byte.parseByte("1"));
			
			ArrayList<String> lore = new ArrayList<String>();
	        lore.add(ChatColor.GRAY + "Immovable");
	        lore.add(ChatColor.DARK_RED + "Flame Resistant");
	        lore.add(ChatColor.RED + "Health Boost");
	        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
	        m1.setColor(Color.fromRGB(35, 1, 48));m2.setColor(Color.fromRGB(35, 1, 48));m3.setColor(Color.fromRGB(35, 1, 48));m4.setColor(Color.fromRGB(35, 1, 48));
	        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
			m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
			m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
			m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

			m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			
			m1.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 5, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			m2.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			m3.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 5, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			m4.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 5, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			
			m1.setLore(lore);
			m2.setLore(lore);
			m3.setLore(lore);
			m4.setLore(lore);
			if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
			if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
			if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
			if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);

			armor1.setItemMeta(m1);
			armor2.setItemMeta(m2);
			armor3.setItemMeta(m3);
			armor4.setItemMeta(m4);
			ItemStack[] newArmor = {armor1, armor2, armor3, armor4};
			if(!ifWearingAll(p, "Obsidian", ChatColor.GRAY + "Immovable")) replaceArmorInInventory(p, "obsidian", newArmor, true, Enchantment.PROTECTION_FIRE);
		}catch(Exception e1) {
			
		}

	}
	@ArmorEquip
	@EventHandler
	public void emeraldArmor(ArmorEquipEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("EMERALD")) return;
			}
		}catch(Exception ignored) {}	
		try {
			Player p = e.getPlayer();
			if(ifWearingAll(Method.getArmor(p, e), "Emerald", ChatColor.DARK_GREEN + "Lucky - Greatly increases Fortune,")) {
				try {
					p.removePotionEffect(PotionEffectType.LUCK);
				}catch(Exception ignored) {}
				p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, 1));
			}else if(!ifWearingAll(Method.getArmor(p, e), "Emerald", ChatColor.DARK_GREEN + "Lucky - Greatly increases Fortune,")) {
				try {
					p.removePotionEffect(PotionEffectType.LUCK);
				}catch(Exception ignored) {}
			}
		}catch(Exception catched) {}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void emeraldLootingEffect(EntityDeathEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("EMERALD")) return;
			}
		}catch(Exception ignored) {}	
		try {
			LivingEntity entity = e.getEntity();
			if(entity.getType() == EntityType.WITHER) return;
			if(entity.getType() == EntityType.ARMOR_STAND) return;
			if(entity instanceof ChestedHorse) return;
			if(entity instanceof Player) return;
			if(!(entity instanceof Mob)) return;
			Player p = entity.getKiller();
			Location loc = entity.getLocation();
			if(ifWearingAll(p, "Emerald", ChatColor.DARK_GREEN + "Lucky - Greatly increases Fortune,")) {
				Random r = new Random();
				boolean spawnParticle = false;
				// drops
				for(ItemStack item : e.getDrops()) {
					int percentage = (r.nextInt(100)) + 1;
					if(percentage <= 33) {
						// 1 extra
						spawnParticle = true;
						ItemStack itemStack = new ItemStack(item.getType());
						itemStack.setAmount(1);
						loc.getWorld().dropItemNaturally(loc, itemStack);
					}else if(percentage <= 66) {
						// 2 extra
						spawnParticle = true;
						ItemStack itemStack = new ItemStack(item.getType());
						itemStack.setAmount(2);
						loc.getWorld().dropItemNaturally(loc, itemStack);
					}else {
						// 0 extra
						
					}
				}
				// equipment chances
				if(((Mob)(entity)).getEquipment().getArmorContents().length != 0) {
					for(ItemStack armor : entity.getEquipment().getArmorContents()) {
						int percentage = (r.nextInt(100)) + 1;
						if(percentage <= 2) {
							// Equipment!
							spawnParticle = true;
							loc.getWorld().dropItemNaturally(loc, armor);
						}
					}
				}
				if(spawnParticle) {
					try {
						p.spawnParticle(Particle.COMPOSTER, loc.add(0, 1, 0), 30, 1, 1, 1);
					}catch(Exception e1) {
						// end rod, happy villager (green)
						try {
							p.spawnParticle(Particle.VILLAGER_HAPPY, loc.add(0, 1, 0), 30, 1, 1, 1);
						}catch(Exception e2) {
							try {
								p.spawnParticle(Particle.END_ROD, loc.add(0, 1, 0), 30);
							}catch(Exception e3) {
								
							}
						}
					}
				}
			}
		}catch(Exception e1) {
			
		}
	}
	boolean spawnParticle = false;
	@EventHandler(priority = EventPriority.HIGHEST)
	public void emeraldFortuneEffect(BlockBreakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("EMERALD")) return;
			}
		}catch(Exception ignored) {}	
		Player p = e.getPlayer();
		if(ifWearingAll(p, "Emerald", ChatColor.DARK_GREEN + "Lucky - Greatly increases Fortune,")) {
			Location loc = e.getBlock().getLocation();
			try {
				Material mat = e.getBlock().getType();
				Random r = new Random();
				spawnParticle = false;
				try {
					if(p.getGameMode().equals(GameMode.SURVIVAL) || p.getGameMode().equals(GameMode.ADVENTURE)) {
						
						ItemStack i = p.getInventory().getItemInMainHand();
						if(i != null) {
							if(!(i.containsEnchantment(Enchantment.SILK_TOUCH))) {
								
							}else return;
						}
					}else return;
				}catch(Exception e1) {
					
				}
				if(mat == Material.COAL_ORE || mat == Material.DIAMOND_ORE || mat == Material.EMERALD_ORE || mat == Material.LAPIS_ORE || mat == Material.NETHER_QUARTZ_ORE) {
					try {
						if(p.getGameMode().equals(GameMode.SURVIVAL) || p.getGameMode().equals(GameMode.ADVENTURE)) {
							ItemStack inHand = p.getInventory().getItemInMainHand();
							if(inHand != null) {
								if(!(inHand.containsEnchantment(Enchantment.SILK_TOUCH))) {
									
								}else return;
							}
						}else return;
					}catch(Exception e1) {
						
					}
					ItemStack item = new ItemStack((mat == Material.COAL_ORE ? Material.COAL : (mat == Material.DIAMOND_ORE ? Material.DIAMOND : (mat == Material.EMERALD_ORE ? Material.EMERALD : (mat == Material.LAPIS_ORE ? Material.LAPIS_LAZULI : (mat == Material.NETHER_QUARTZ_ORE ? Material.QUARTZ : null))))));
					int percentage = r.nextInt(100) + 1;
					if(percentage <= 50) {
						// none
						
					}else if(percentage <= 75) {
						// 1 extra
						item.setAmount(1);
						loc.getWorld().dropItemNaturally(loc, item);
						spawnParticle = true;
					}else {
						// 2 extra
						item.setAmount(2);
						loc.getWorld().dropItemNaturally(loc, item);
						spawnParticle = true;
					}
				}else if(mat == Material.GLOWSTONE || mat == Material.MELON || mat == Material.REDSTONE_ORE || mat == Material.SEA_LANTERN) {
					
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {public void run() {
						
						List<Entity> oldentities = new ArrayList<Entity>(loc.getWorld().getNearbyEntities(loc, 1, 1, 1));
						List<Item> droppedItems = new ArrayList<Item>();
						for(Entity entity : oldentities) {
							if(entity instanceof Item) {
								droppedItems.add(((Item)entity));
							}else continue;
						}
						if(mat == Material.GLOWSTONE) {
							int max = 4;
							Item i = null;
							for(Item d : droppedItems) {
								if(d.getItemStack().getType() == Material.GLOWSTONE_DUST) {
									i = d;
									break;
								}
							}
							if(i == null) {
								
								return;
							}
							int percentage = r.nextInt(100) + 1;
							if(percentage <= 50) {
								// 2 extra
								spawnParticle = true;
								i.getItemStack().setAmount(((i.getItemStack().getAmount() + 2) < (max + 1)) ? (i.getItemStack().getAmount() + 2) : max);
								p.updateInventory();
							}else {
								// 1 extra
								spawnParticle = true;
								i.getItemStack().setAmount(((i.getItemStack().getAmount() + 1) < (max + 1)) ? (i.getItemStack().getAmount() + 1) : max);
								p.updateInventory();
							}
						}else if(mat == Material.MELON) {
							int max = 9;
							Item i = null;
							for(Item d : droppedItems) {
								if(d.getItemStack().getType() == Material.MELON_SLICE) {
									i = d;
									break;
								}
							}
							if(i == null) {
								return;
							}
							int percentage = r.nextInt(100) + 1;
							if(percentage <= 50) {
								// 2 extra
								spawnParticle = true;
								i.getItemStack().setAmount(((i.getItemStack().getAmount() + 2) < (max + 1)) ? (i.getItemStack().getAmount() + 2) : max);
								p.updateInventory();
							}else {
								// 1 extra
								spawnParticle = true;
								i.getItemStack().setAmount(((i.getItemStack().getAmount() + 1) < (max + 1)) ? (i.getItemStack().getAmount() + 1) : max);
								p.updateInventory();
							}
						}else if(mat == Material.REDSTONE_ORE) {
							int max = 5;
							Item i = null;
							for(Item d : droppedItems) {
								if(d.getItemStack().getType() == Material.REDSTONE) {
									i = d;
									break;
								}
							}
							if(i == null) {
								
								return;
							}
							int percentage = r.nextInt(100) + 1;
							if(percentage <= 50) {
								// 2 extra
								spawnParticle = true;
								i.getItemStack().setAmount(((i.getItemStack().getAmount() + 2) < (max + 1)) ? (i.getItemStack().getAmount() + 2) : max);
								p.updateInventory();
							}else {
								// 1 extra
								spawnParticle = true;
								i.getItemStack().setAmount(((i.getItemStack().getAmount() + 1) < (max + 1)) ? (i.getItemStack().getAmount() + 1) : max);
								p.updateInventory();
							}
						}else if(mat == Material.SEA_LANTERN) {
							int max = 5;
							Item i = null;
							for(Item d : droppedItems) {
								if(d.getItemStack().getType() == Material.PRISMARINE_CRYSTALS) {
									i = d;
									break;
								}
							}
							if(i == null) {
								
								return;
							}
							int percentage = r.nextInt(100) + 1;
							if(percentage <= 50) {
								// 2 extra
								spawnParticle = true;
								i.getItemStack().setAmount(((i.getItemStack().getAmount() + 2) < (max + 1)) ? (i.getItemStack().getAmount() + 2) : max);
								p.updateInventory();
							}else {
								// 1 extra
								spawnParticle = true;
								i.getItemStack().setAmount(((i.getItemStack().getAmount() + 1) < (max + 1)) ? (i.getItemStack().getAmount() + 1) : max);
								p.updateInventory();
							}
						}
						
					}}, 2L);
				}else if(mat == Material.GRAVEL) {
					e.setCancelled(true);
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {public void run() {
						loc.getBlock().setType(Material.AIR);
						if(p.getInventory().getItemInMainHand() != null) {
							Material itemMat = p.getInventory().getItemInMainHand().getType();
							if(itemMat == Material.WOODEN_SHOVEL || itemMat == Material.STONE_SHOVEL || itemMat == Material.IRON_SHOVEL || itemMat == Material.GOLDEN_SHOVEL || itemMat == Material.DIAMOND_SHOVEL) {
								if(p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
									loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.GRAVEL));
								}else if(p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
									loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.FLINT));
									if(p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) < 3) spawnParticle = true;
								}else {
									int percentage = r.nextInt(100) + 1;
									if(percentage <= 25) {
										loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.FLINT));
										spawnParticle = true;
									}else {
										loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.GRAVEL));
									}
								}
							}else {int percentage = r.nextInt(100) + 1;
							if(percentage <= 25) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.FLINT));
								spawnParticle = true;
							}else {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.GRAVEL));
							}}
						}else {
							int percentage = r.nextInt(100) + 1;
							if(percentage <= 25) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.FLINT));
								spawnParticle = true;
							}else {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.GRAVEL));
							}
						}
					}}, 3L);
				}else if(mat == Material.JUNGLE_LEAVES) {
					e.setCancelled(true);
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {public void run() {
						loc.getBlock().setType(Material.AIR);
						int fortune = 2;
						if(p.getInventory().getItemInMainHand() != null) {
							if(p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
								fortune += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
							}
						}
						if(fortune == 2) {
							int percentage = r.nextInt(32) + 1;
							if(percentage == 1) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.JUNGLE_SAPLING));
								spawnParticle = true;
							}
						}else if(fortune == 3) {
							int percentage = r.nextInt(24) + 1;
							if(percentage == 1) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.JUNGLE_SAPLING));
								spawnParticle = true;
							}
						}else if(fortune >= 4) {
							int percentage = r.nextInt(10) + 1;
							if(percentage == 1) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.JUNGLE_SAPLING));
								spawnParticle = true;
							}
						}
						// stick drop
						if(!spawnParticle) {
							if(fortune == 2) {
								int percentage = r.nextInt(40) + 1;
								if(percentage == 1) {
									ItemStack item = new ItemStack(Material.STICK);
									boolean random = r.nextBoolean();
									if(random) item.setAmount(1);
									else item.setAmount(2);
									loc.getWorld().dropItemNaturally(loc, item);
									spawnParticle = true;
								}
							}else if(fortune == 3) {
								int percentage = r.nextInt(30) + 1;
								if(percentage == 1) {
									ItemStack item = new ItemStack(Material.STICK);
									boolean random = r.nextBoolean();
									if(random) item.setAmount(1);
									else item.setAmount(2);
									loc.getWorld().dropItemNaturally(loc, item);
									spawnParticle = true;
								}
							}else if(fortune >= 4) {
								int percentage = r.nextInt(10) + 1;
								if(percentage == 1) {
									ItemStack item = new ItemStack(Material.STICK);
									boolean random = r.nextBoolean();
									if(random) item.setAmount(1);
									else item.setAmount(2);
									loc.getWorld().dropItemNaturally(loc, item);
									spawnParticle = true;
								}
							}
						}
					}}, 3L);
				}else if(mat == Material.OAK_LEAVES || mat == Material.DARK_OAK_LEAVES) {
					e.setCancelled(true);
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {public void run() {
						loc.getBlock().setType(Material.AIR);
						int fortune = 2;
						if(p.getInventory().getItemInMainHand() != null) {
							if(p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
								fortune += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
							}
						}
						if(fortune == 2) {
							int percentage = r.nextInt(160) + 1;
							if(percentage == 1) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.APPLE));
								spawnParticle = true;
							}
						}else if(fortune == 3) {
							int percentage = r.nextInt(120) + 1;
							if(percentage == 1) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.APPLE));
								spawnParticle = true;
							}
						}else if(fortune >= 4) {
							int percentage = r.nextInt(40) + 1;
							if(percentage == 1) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.APPLE));
								spawnParticle = true;
							}
						}
						if(!spawnParticle) {
							if(fortune == 2) {
								int percentage = r.nextInt(12) + 1;
								if(percentage == 1) {
									loc.getWorld().dropItemNaturally(loc, new ItemStack(mat == Material.OAK_LEAVES ? Material.OAK_SAPLING : Material.DARK_OAK_SAPLING));
									spawnParticle = true;
								}
							}else if(fortune == 3) {
								int percentage = r.nextInt(10) + 1;
								if(percentage == 1) {
									loc.getWorld().dropItemNaturally(loc, new ItemStack(mat == Material.OAK_LEAVES ? Material.OAK_SAPLING : Material.DARK_OAK_SAPLING));
									spawnParticle = true;
								}
							}else if(fortune >= 4) {
								int percentage = r.nextInt(10) + 1;
								if(percentage == 1) {
									loc.getWorld().dropItemNaturally(loc, new ItemStack(mat == Material.OAK_LEAVES ? Material.OAK_SAPLING : Material.DARK_OAK_SAPLING));
									spawnParticle = true;
								}
							}
						}
						if(!spawnParticle) {
							if(fortune == 2) {
								int percentage = r.nextInt(40) + 1;
								if(percentage == 1) {
									ItemStack item = new ItemStack(Material.STICK);
									boolean random = r.nextBoolean();
									if(random) item.setAmount(1);
									else item.setAmount(2);
									loc.getWorld().dropItemNaturally(loc, item);
									spawnParticle = true;
								}
							}else if(fortune == 3) {
								int percentage = r.nextInt(30) + 1;
								if(percentage == 1) {
									ItemStack item = new ItemStack(Material.STICK);
									boolean random = r.nextBoolean();
									if(random) item.setAmount(1);
									else item.setAmount(2);
									loc.getWorld().dropItemNaturally(loc, item);
									spawnParticle = true;
								}
							}else if(fortune >= 4) {
								int percentage = r.nextInt(10) + 1;
								if(percentage == 1) {
									ItemStack item = new ItemStack(Material.STICK);
									boolean random = r.nextBoolean();
									if(random) item.setAmount(1);
									else item.setAmount(2);
									loc.getWorld().dropItemNaturally(loc, item);
									spawnParticle = true;
								}
							}
						}
					}}, 3L);
				}else if(mat == Material.ACACIA_LEAVES || mat == Material.BIRCH_LEAVES || mat == Material.SPRUCE_LEAVES) {
					e.setCancelled(true);
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {public void run() {
						loc.getBlock().setType(Material.AIR);
						int fortune = 2;
						if(p.getInventory().getItemInMainHand() != null) {
							if(p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
								fortune += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
							}
						}
						if(fortune == 2) {
							int percentage = r.nextInt(12) + 1;
							if(percentage == 1) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(mat == Material.ACACIA_LEAVES ? Material.ACACIA_SAPLING : (mat == Material.BIRCH_LEAVES ? Material.BIRCH_SAPLING : Material.SPRUCE_SAPLING)));
								spawnParticle = true;
							}
						}else if(fortune == 3) {
							int percentage = r.nextInt(10) + 1;
							if(percentage == 1) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(mat == Material.ACACIA_LEAVES ? Material.ACACIA_SAPLING : (mat == Material.BIRCH_LEAVES ? Material.BIRCH_SAPLING : Material.SPRUCE_SAPLING)));
								spawnParticle = true;
							}
						}else if(fortune >= 4) {
							int percentage = r.nextInt(10) + 1;
							if(percentage == 1) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(mat == Material.ACACIA_LEAVES ? Material.ACACIA_SAPLING : (mat == Material.BIRCH_LEAVES ? Material.BIRCH_SAPLING : Material.SPRUCE_SAPLING)));
								spawnParticle = true;
							}
						}
						if(!spawnParticle) {
							if(fortune == 2) {
								int percentage = r.nextInt(40) + 1;
								if(percentage == 1) {
									ItemStack item = new ItemStack(Material.STICK);
									boolean random = r.nextBoolean();
									if(random) item.setAmount(1);
									else item.setAmount(2);
									loc.getWorld().dropItemNaturally(loc, item);
									spawnParticle = true;
								}
							}else if(fortune == 3) {
								int percentage = r.nextInt(30) + 1;
								if(percentage == 1) {
									ItemStack item = new ItemStack(Material.STICK);
									boolean random = r.nextBoolean();
									if(random) item.setAmount(1);
									else item.setAmount(2);
									loc.getWorld().dropItemNaturally(loc, item);
									spawnParticle = true;
								}
							}else if(fortune >= 4) {
								int percentage = r.nextInt(10) + 1;
								if(percentage == 1) {
									ItemStack item = new ItemStack(Material.STICK);
									boolean random = r.nextBoolean();
									if(random) item.setAmount(1);
									else item.setAmount(2);
									loc.getWorld().dropItemNaturally(loc, item);
									spawnParticle = true;
								}
							}
						}
					}}, 3L);
				}
				
				
				if(spawnParticle) {
					try {
						p.spawnParticle(Particle.COMPOSTER, loc.add(0, 1, 0), 30, 1, 1, 1);
					}catch(Exception e1) {
						// end rod, happy villager (green)
						try {
							p.spawnParticle(Particle.VILLAGER_HAPPY, loc.add(0, 1, 0), 30, 1, 1, 1);
						}catch(Exception e2) {
							try {
								p.spawnParticle(Particle.END_ROD, loc.add(0, 1, 0), 30);
							}catch(Exception e3) {
								
							}
						}
					}
				}
			}catch(Exception e1) {
				
			}
		}
	}
	@EventHandler
	public void pistonArmor(PlayerToggleSneakEvent event) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("PISTON")) return;
			}
		}catch(Exception ignored) {}
		try {
			Player p = event.getPlayer();
			if(event.isSneaking()) {
				if(ifWearingAll(p, "Piston", ChatColor.GRAY + "Pusher - Pushes nearby entities")) {
					if(pistonCooldown.contains(p.getUniqueId())) return;
					pistonCooldown.add(p.getUniqueId());
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							try {
								if(pistonCooldown.contains(p.getUniqueId())) {
									pistonCooldown.remove(p.getUniqueId());
								}
							}catch(Exception e) {
								
							}
						}
					}, 7 * 20L);
					List<Entity> entities = new ArrayList<Entity>(p.getWorld().getNearbyEntities(p.getLocation(), 4, 4, 4));
					if(entities.size() == 0) {
						if(pistonCooldown.contains(p.getUniqueId())) pistonCooldown.remove(p.getUniqueId());
					}
					boolean sound = false;
					int count = 0;
					for(Entity e : entities) {
						if(e instanceof Player) {
							if(p.getUniqueId().equals(e.getUniqueId())) continue;
							if(((Player)e).getGameMode() == GameMode.SPECTATOR) continue;
						}else if(!(e instanceof Mob)) continue;
						if(e.getVehicle() != null) {
							if(p.getVehicle() != null) {
								if(e.getVehicle().getUniqueId().equals(p.getVehicle().getUniqueId())) continue;
								else e = e.getVehicle();
							}else e = e.getVehicle();
						}
						Vector vec = e.getLocation().toVector().subtract(p.getLocation().toVector()).setY(0).normalize();
						Vector oldVec = vec.clone();
						if(Double.isNaN(vec.getX()) || Double.isNaN(vec.getY()) || Double.isNaN(vec.getZ()) || vec.length() == 0) continue;
						vec.setY(0.45);
						vec.normalize();
						vec.multiply(1.6);
						vec.setY(vec.getY() + 0);
						if(vec.getY() > 7.5) vec.setY(7.5);
						if(Integer.parseInt(String.valueOf(Math.floor(p.getLocation().getY())).replace(".0", "")) == Integer.parseInt(String.valueOf(Math.floor(e.getLocation().getY())).replace(".0", ""))) vec.setY(0);
						else if(Integer.parseInt(String.valueOf(Math.floor(p.getLocation().getY())).replace(".0", "")) > Integer.parseInt(String.valueOf(Math.floor(e.getLocation().getY())).replace(".0", ""))) vec.setY(-(vec.getY()));
						else {
							oldVec.setY(0.8);
							oldVec.normalize();
							oldVec.multiply(1.6);
							oldVec.setY(oldVec.getY() + 0);
							if(oldVec.getY() > 14) oldVec.setY(14);
							vec = oldVec.clone();
						}
						e.setVelocity(vec);
						count++;
						sound = true;
					}
					if(sound) {
						Random r = new Random();
						int random = r.nextInt(6) + 1;
						float pitch = 1.0F;
						switch(random) {
						case 1:
							pitch = 0.7F;
						case 2:
							pitch = 0.8F;
						case 3:
							pitch = 0.9F;
						case 4:
							pitch = 1.0F;
						case 5:
							pitch = 1.1F;
						case 6:
							pitch = 1.2F;
						}
						p.playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 1, pitch);
						try {
							sendActionBar(p, "[{\"text\":\"You pushed \"},{\"text\":\"" + (count) + "\",\"color\":\"green\"},{\"text\":\" entities!\"}]");
						}catch(Exception e) {
							p.sendMessage("You pushed " + ChatColor.GREEN + (count - 1) + ChatColor.RESET + " entities!");
						}
					}else {
						if(pistonCooldown.contains(p.getUniqueId())) pistonCooldown.remove(p.getUniqueId());
					}
				}
			}
		}catch(Exception e1) {
			
		}
	}
	@EventHandler
	public void wetspongeArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("WET_SPONGE")) return;
			}
		}catch(Exception ignored) {}	
		try {
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				if(ifWearingAll(p, "Wet Sponge", ChatColor.YELLOW + "Absorbent - Absorbs nearby liquids")) {
					if(!(wscooldown.contains(p.getUniqueId()))) {
						wscooldown.add(p.getUniqueId());
						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							public void run() {
								try {
									wscooldown.remove(p.getUniqueId());
								}catch(Exception e) {
									
								}
							}
						}, 20 * 6);
						int radius = 3;
						boolean sponged = false;
						for (int x = -(radius); x <= radius; x ++) {
							for(int y = -(radius); y <= radius; y ++) {
								for(int z = -(radius); z <= radius; z ++) {
									org.bukkit.block.Block block = p.getLocation().getBlock().getRelative(x, y, z);
									if(block.getType().equals(Material.WATER)) {
										sponged = true;
										block.setType(Material.AIR);
									}else if(block.getType().equals(Material.TALL_SEAGRASS)) {
										sponged = true;
										block.setType(Material.AIR);
									}else if(block.getType().equals(Material.SEAGRASS)) {
										sponged = true;
										block.setType(Material.AIR);
									}
								}
							}
						}
						if(!sponged) try {
							wscooldown.remove(p.getUniqueId());
						}catch(Exception e1) {
							
						}
					}
				}
			}
		}catch(Exception e1) {
			
		}
	}
	public void magmaArmor(Player p) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("MAGMA")) return;
			}
		}catch(Exception ignored) {}	
		try {
			ItemStack helm = p.getInventory().getHelmet();
			ItemStack chest = p.getInventory().getChestplate();
			ItemStack legs = p.getInventory().getLeggings();
			ItemStack boots = p.getInventory().getBoots();
			if(ifWearingAll(new ItemStack[] {helm, chest, legs, boots}, "Magma", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked")) {
				/* Put the ability below. This is auto-generated
				* from ArmorCreator program
                * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
				*/
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta l1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta l2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta l3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta l4 = (LeatherArmorMeta) armor4.getItemMeta();
				l1.setColor(Color.fromRGB(225, 45, 0));
				l2.setColor(Color.fromRGB(225, 45, 0));
				l3.setColor(Color.fromRGB(225, 45, 0));
				l4.setColor(Color.fromRGB(225, 45, 0));
				armor1.setItemMeta(l1);
				armor2.setItemMeta(l2);
				armor3.setItemMeta(l3);
				armor4.setItemMeta(l4);
				ItemMeta m1 = armor1.getItemMeta();
				ItemMeta m2 = armor2.getItemMeta();
				ItemMeta m3 = armor3.getItemMeta();
				ItemMeta m4 = armor4.getItemMeta();
				
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "magmaHelm"), PersistentDataType.BYTE, Byte.parseByte("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "magmaChest"), PersistentDataType.BYTE, Byte.parseByte("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "magmaLegs"), PersistentDataType.BYTE, Byte.parseByte("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "magmaFeet"), PersistentDataType.BYTE, Byte.parseByte("1"));
				
				m1.setDisplayName("Magma Helmet");
				m2.setDisplayName("Magma Chestplate");
				m3.setDisplayName("Magma Leggings");
				m4.setDisplayName("Magma Boots");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked");
		        lore.add(ChatColor.DARK_RED + "Flame Resistant - Provides Fire Protection IV");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				m1.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
				m2.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
				m3.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
				m4.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				changeArmorInArmorSlot(p, armor1, armor2, armor3, armor4);
			}else {
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta l1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta l2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta l3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta l4 = (LeatherArmorMeta) armor4.getItemMeta();
				l1.setColor(Color.fromRGB(225, 45, 0));
				l2.setColor(Color.fromRGB(225, 45, 0));
				l3.setColor(Color.fromRGB(225, 45, 0));
				l4.setColor(Color.fromRGB(225, 45, 0));
				armor1.setItemMeta(l1);
				armor2.setItemMeta(l2);
				armor3.setItemMeta(l3);
				armor4.setItemMeta(l4);
				ItemMeta m1 = armor1.getItemMeta();
				ItemMeta m2 = armor2.getItemMeta();
				ItemMeta m3 = armor3.getItemMeta();
				ItemMeta m4 = armor4.getItemMeta();
				
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "magmaHelm"), PersistentDataType.BYTE, Byte.parseByte("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "magmaChest"), PersistentDataType.BYTE, Byte.parseByte("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "magmaLegs"), PersistentDataType.BYTE, Byte.parseByte("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "magmaFeet"), PersistentDataType.BYTE, Byte.parseByte("1"));
				
				m1.setDisplayName("Magma Helmet");
				m2.setDisplayName("Magma Chestplate");
				m3.setDisplayName("Magma Leggings");
				m4.setDisplayName("Magma Boots");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked");
		        lore.add(ChatColor.DARK_RED + "Flame Resistant - Provides Fire Protection IV");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				ItemStack[] newArmor = {armor1, armor2, armor3, armor4};
				replaceArmorInInventory(p, "magma", newArmor, true, Enchantment.PROTECTION_FIRE);
			}
		}catch(Exception e1) {

		}
	}
	
	@EventHandler
	public void fiery(EntityDamageByEntityEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("MAGMA")) return;
			}
		}catch(Exception ignored) {}	
		try {
			if((e.getDamager() instanceof Player) && (e.getEntity() instanceof Player)) {
				boolean damagerWearing = ifWearingAll((Player)e.getDamager(), "Magma", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked") || ifWearingAll((Player)e.getDamager(), "Netherrack", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked");
				boolean damagedWearing = ifWearingAll((Player)e.getEntity(), "Magma", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked") || ifWearingAll((Player)e.getEntity(), "Netherrack", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked");
				
				Player damager = (Player) e.getDamager();
				Player damaged = (Player) e.getEntity();
				
				if(damagerWearing && damagedWearing) {
					if(!(damager.getFireTicks() > 0)) damager.setFireTicks(6/*seconds*/ * 20);
					if(!(damaged.getFireTicks() > 0)) damaged.setFireTicks(6/*seconds*/ * 20);
				}else if(damagerWearing) {
					if(!(damaged.getFireTicks() > 0)) damaged.setFireTicks(6/*seconds*/ * 20);
				}else if(damagedWearing) {
					if(!(damager.getFireTicks() > 0)) damager.setFireTicks(6/*seconds*/ * 20);
				}
			}else if(e.getDamager() instanceof Player) {
				if(ifWearingAll((Player)e.getDamager(), "Magma", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked")) {
					if(!(e.getEntity().getFireTicks() > 0)) e.getEntity().setFireTicks(6 /*seconds*/ * 20);
				}else if(ifWearingAll((Player)e.getDamager(), "Netherrack", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked")) {
					if(!(e.getEntity().getFireTicks() > 0)) e.getEntity().setFireTicks(6 /*seconds*/ * 20);
				}
			}else if(e.getEntity() instanceof Player) {
				if(ifWearingAll((Player)e.getEntity(), "Magma", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked")) {
					if(!(e.getDamager().getFireTicks() > 0)) e.getDamager().setFireTicks(6 /*seconds*/ * 20);
				}else if(ifWearingAll((Player)e.getEntity(), "Netherrack", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked")) {
					if(!(e.getDamager().getFireTicks() > 0)) e.getDamager().setFireTicks(6 /*seconds*/ * 20);
				}
			}	
		}catch(Exception ex) {
			
		}
	}
	public void brickArmor(Player p) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("BRICKS")) return;
			}
		}catch(Exception ignored) {}	
		try {
			ItemStack helm = p.getInventory().getHelmet();
			ItemStack chest = p.getInventory().getChestplate();
			ItemStack legs = p.getInventory().getLeggings();
			ItemStack boots = p.getInventory().getBoots();
			if(ifWearingAll(new ItemStack[] {helm, chest, legs, boots}, "Brick", ChatColor.RED + "Health Boost - Increases Max Health by 2 hearts")) {
				/* Put the ability below. This is auto-generated
				* from ArmorCreator program
                * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
				*/
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
				m1.setDisplayName("Brick Helmet");
				m2.setDisplayName("Brick Chestplate");
				m3.setDisplayName("Brick Leggings");
				m4.setDisplayName("Brick Boots");
				ArrayList<String> lore = new ArrayList<String>();
		        lore.add(ChatColor.RED + "Health Boost - Increases Max Health by 2 hearts");
		        lore.add(ChatColor.GRAY + "Immovable - Gives 25% Knockback Resistance");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		        m1.setColor(Color.fromRGB(145, 90, 74));m2.setColor(Color.fromRGB(145, 90, 74));m3.setColor(Color.fromRGB(145, 90, 74));m4.setColor(Color.fromRGB(145, 90, 74));
		        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "brickHelm"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "brickChest"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "brickLegs"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "brickFeet"), PersistentDataType.BYTE, Byte.valueOf("1"));
				
				m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				
				m1.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				
				m1.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				changeArmorInArmorSlot(p, armor1, armor2, armor3, armor4);
			}else {
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
				m1.setDisplayName("Brick Helmet");
				m2.setDisplayName("Brick Chestplate");
				m3.setDisplayName("Brick Leggings");
				m4.setDisplayName("Brick Boots");
				ArrayList<String> lore = new ArrayList<String>();
		        lore.add(ChatColor.RED + "Health Boost - Increases Max Health by 2 hearts");
		        lore.add(ChatColor.GRAY + "Immovable - Gives 25% Knockback Resistance");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		        m1.setColor(Color.fromRGB(145, 90, 74));m2.setColor(Color.fromRGB(145, 90, 74));m3.setColor(Color.fromRGB(145, 90, 74));m4.setColor(Color.fromRGB(145, 90, 74));
		        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "brickHelm"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "brickChest"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "brickLegs"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "brickFeet"), PersistentDataType.BYTE, Byte.valueOf("1"));
				
				m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				ItemStack[] newArmor = {armor1, armor2, armor3, armor4};
				replaceArmorInInventory(p, "brick", newArmor, true, Attribute.GENERIC_MAX_HEALTH);
			}
		}catch(Exception e1) {

		}

	}
	public void netherBrickArmor(Player p) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("NETHER_BRICKS")) return;
			}
		}catch(Exception ignored) {}	
		try {
			ItemStack helm = p.getInventory().getHelmet();
			ItemStack chest = p.getInventory().getChestplate();
			ItemStack legs = p.getInventory().getLeggings();
			ItemStack boots = p.getInventory().getBoots();
			if(ifWearingAll(new ItemStack[] {helm, chest, legs, boots}, "Nether Brick", ChatColor.RED + "Health Boost - Increases Max Health by 2 hearts")) {
				/* Put the ability below. This is auto-generated
				* from ArmorCreator program
                * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
				*/
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
				m1.setDisplayName("Nether Brick Helmet");
				m2.setDisplayName("Nether Brick Chestplate");
				m3.setDisplayName("Nether Brick Leggings");
				m4.setDisplayName("Nether Brick Boots");
				ArrayList<String> lore = new ArrayList<String>();
		        lore.add(ChatColor.RED + "Health Boost - Increases Max Health by 2 hearts");
		        lore.add(ChatColor.GRAY + "Immovable - Gives 25% Knockback Resistance");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		        m1.setColor(Color.fromRGB(62, 33, 38));m2.setColor(Color.fromRGB(62, 33, 38));m3.setColor(Color.fromRGB(62, 33, 38));m4.setColor(Color.fromRGB(62, 33, 38));
		        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "netherBrickHelm"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "netherBrickChest"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "netherBrickLegs"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "netherBrickFeet"), PersistentDataType.BYTE, Byte.valueOf("1"));
				
				m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				
				m1.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				
				m1.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				changeArmorInArmorSlot(p, armor1, armor2, armor3, armor4);
			}else {
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
				m1.setDisplayName("Nether Brick Helmet");
				m2.setDisplayName("Nether Brick Chestplate");
				m3.setDisplayName("Nether Brick Leggings");
				m4.setDisplayName("Nether Brick Boots");
				ArrayList<String> lore = new ArrayList<String>();
		        lore.add(ChatColor.RED + "Health Boost - Increases Max Health by 2 hearts");
		        lore.add(ChatColor.GRAY + "Immovable - Gives 25% Knockback Resistance");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		        m1.setColor(Color.fromRGB(62, 33, 38));m2.setColor(Color.fromRGB(62, 33, 38));m3.setColor(Color.fromRGB(62, 33, 38));m4.setColor(Color.fromRGB(62, 33, 38));
		        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "netherBrickHelm"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "netherBrickChest"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "netherBrickLegs"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "netherBrickFeet"), PersistentDataType.BYTE, Byte.valueOf("1"));
				
				m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				ItemStack[] newArmor = {armor1, armor2, armor3, armor4};
				
				replaceArmorInInventory(p, "netherBrick", newArmor, true, Attribute.GENERIC_MAX_HEALTH);
			}
		}catch(Exception e1) {

		}

	}
	public void redNetherBrickArmor(Player p) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("RED_NETHER_BRICKS")) return;
			}
		}catch(Exception ignored) {}	
		try {
			ItemStack helm = p.getInventory().getHelmet();
			ItemStack chest = p.getInventory().getChestplate();
			ItemStack legs = p.getInventory().getLeggings();
			ItemStack boots = p.getInventory().getBoots();
			if(ifWearingAll(new ItemStack[] {helm, chest, legs, boots}, "Red Nether Brick", ChatColor.RED + "Health Boost - Increases Max Health by 2 hearts")) {
				/* Put the ability below. This is auto-generated
				* from ArmorCreator program
                * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
				*/
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
				m1.setDisplayName("Red Nether Brick Helmet");
				m2.setDisplayName("Red Nether Brick Chestplate");
				m3.setDisplayName("Red Nether Brick Leggings");
				m4.setDisplayName("Red Nether Brick Boots");
				ArrayList<String> lore = new ArrayList<String>();
		        lore.add(ChatColor.RED + "Health Boost - Increases Max Health by 2 hearts");
		        lore.add(ChatColor.GRAY + "Immovable - Gives 25% Knockback Resistance");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		        m1.setColor(Color.fromRGB(119, 15, 16));m2.setColor(Color.fromRGB(119, 15, 16));m3.setColor(Color.fromRGB(119, 15, 16));m4.setColor(Color.fromRGB(119, 15, 16));
		        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "redNetherBrickHelm"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "redNetherBrickChest"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "redNetherBrickLegs"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "redNetherBrickFeet"), PersistentDataType.BYTE, Byte.valueOf("1"));

				m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				
				m1.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));

				m1.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.25, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				changeArmorInArmorSlot(p, armor1, armor2, armor3, armor4);
			}else {
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
				m1.setDisplayName("Red Nether Brick Helmet");
				m2.setDisplayName("Red Nether Brick Chestplate");
				m3.setDisplayName("Red Nether Brick Leggings");
				m4.setDisplayName("Red Nether Brick Boots");
				ArrayList<String> lore = new ArrayList<String>();
		        lore.add(ChatColor.RED + "Health Boost - Increases Max Health by 2 hearts");
		        lore.add(ChatColor.GRAY + "Immovable - Gives 25% Knockback Resistance");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		        m1.setColor(Color.fromRGB(119, 15, 16));m2.setColor(Color.fromRGB(119, 15, 16));m3.setColor(Color.fromRGB(119, 15, 16));m4.setColor(Color.fromRGB(119, 15, 16));
		        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "redNetherBrickHelm"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "redNetherBrickChest"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "redNetherBrickLegs"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "redNetherBrickFeet"), PersistentDataType.BYTE, Byte.valueOf("1"));

				m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				ItemStack[] newArmor = {armor1, armor2, armor3, armor4};
				
				replaceArmorInInventory(p, "redNetherBrick", newArmor, true, Attribute.GENERIC_MAX_HEALTH);
			}
		}catch(Exception e1) {

		}

	}
	@EventHandler
	public void slimeLandEvent(PlayerLandEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SLIME")) return;
			}
		}catch(Exception ignored) {}	
		Player p = e.getPlayer();
		if(!ifWearingAll(p, "Slime", ChatColor.GREEN + "Slimey - Bounces off floors")) return;
		if(!p.isSneaking()) {
			if(e.getVelocity().getY() > -0.35) return;
			if(e.getVelocity().getY() > -6) e.getPlayer().setVelocity(e.getPlayer().getVelocity().setY((e.getVelocity().getY() * -17/25)));
			else e.getPlayer().setVelocity(e.getPlayer().getVelocity().setY(6.0));
			e.getPlayer().setFallDistance(0);
		}else e.getPlayer().setFallDistance(0);
		try {
			p.playSound(p.getLocation(), Sound.BLOCK_SLIME_BLOCK_FALL, 1F, 1F);
		}catch(Exception ex) {
			
		}
	}
	
	@EventHandler
	public void slimeDamageEvent(EntityDamageEvent e) {
		if(e.getEntityType() != EntityType.PLAYER) return;
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SLIME")) return;
			}
		}catch(Exception ignored) {}	
		Player p = (Player) e.getEntity();
		if(!ifWearingAll(p, "Slime", ChatColor.GREEN + "Slimey - Bounces off floors")) return;
		if(e.getCause() == DamageCause.FALL) {
			try {
				p.stopSound(Sound.ENTITY_PLAYER_SMALL_FALL);
			}catch(Exception ex) {
				
			}
			try {
				p.stopSound(Sound.ENTITY_PLAYER_BIG_FALL);
			}catch(Exception ex) {
				
			}
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void endstoneArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("END_STONE")) return;
			}
		}catch(Exception ignored) {}	
		try {
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				if(ifWearingAll(p, "End Stone", plugin.ver16 ? ChatColor.DARK_PURPLE + "Ender - Teleports in the direction you're looking at" : "Ender - Teleports in the direction you're looking at")) {
					if(teleportCooldown.contains(p.getUniqueId())) return;
					if(p.getTargetBlock(null, 15).getType() == Material.AIR || p.getTargetBlock(null, 15).getType() == Material.LAVA) {
						try {
							sendActionBar(p, "{\"text\":\"You're looking at air or its too far away!\",\"color\":\"red\"}");
						}catch(Exception e1) {
							p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						}
						return;
					}
					p.teleport(p.getTargetBlock(null, 30).getLocation().add(0, 1, 0));
	                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 1F);
	                teleportCooldown.add(p.getUniqueId());
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							try {
								teleportCooldown.remove(p.getUniqueId());
							}catch(Exception e) {
								
							}
						}
					}, 15 * 20L);
				}
			}
		}catch(Exception e1) {
			
		}
	}
	public void iceArmor(Player p) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("ICE")) return;
			}
		}catch(Exception ignored) {}	
		try {
			ItemStack helm = p.getInventory().getHelmet();
			ItemStack chest = p.getInventory().getChestplate();
			ItemStack legs = p.getInventory().getLeggings();
			ItemStack boots = p.getInventory().getBoots();
			if(ifWearingAll(new ItemStack[] {helm, chest, legs, boots}, "Ice", ChatColor.AQUA + "Frosty - Provides Frost Walking II")) {
				/* Put the ability below. This is auto-generated
				* from ArmorCreator program
                * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
				*/
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
				m1.setDisplayName("Ice Helmet");
				m2.setDisplayName("Ice Chestplate");
				m3.setDisplayName("Ice Leggings");
				m4.setDisplayName("Ice Boots");
				ArrayList<String> lore = new ArrayList<String>();
		        lore.add(ChatColor.AQUA + "Frosty - Provides Frost Walking II");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		        m1.setColor(Color.fromRGB(156, 185, 237));m2.setColor(Color.fromRGB(156, 185, 237));m3.setColor(Color.fromRGB(156, 185, 237));m4.setColor(Color.fromRGB(156, 185, 237));
		        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

				m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "iceHelm"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "iceChest"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "iceLegs"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "iceFeet"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);
				
				m1.addEnchant(Enchantment.FROST_WALKER, 2, true);
				m2.addEnchant(Enchantment.FROST_WALKER, 2, true);
				m3.addEnchant(Enchantment.FROST_WALKER, 2, true);
				m4.addEnchant(Enchantment.FROST_WALKER, 2, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				changeArmorInArmorSlot(p, armor1, armor2, armor3, armor4);
			}else {
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
				m1.setDisplayName("Ice Helmet");
				m2.setDisplayName("Ice Chestplate");
				m3.setDisplayName("Ice Leggings");
				m4.setDisplayName("Ice Boots");
				ArrayList<String> lore = new ArrayList<String>();
		        lore.add(ChatColor.AQUA + "Frosty - Provides Frost Walking II");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		        m1.setColor(Color.fromRGB(156, 185, 237));m2.setColor(Color.fromRGB(156, 185, 237));m3.setColor(Color.fromRGB(156, 185, 237));m4.setColor(Color.fromRGB(156, 185, 237));
		        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

				m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "iceHelm"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "iceChest"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "iceLegs"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "iceFeet"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				ItemStack[] newArmor = {armor1, armor2, armor3, armor4};
				
				replaceArmorInInventory(p, "ice", newArmor, true, Enchantment.FROST_WALKER);
			}
		}catch(Exception e1) {

		}

	}
	@EventHandler
	public void boneArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("BONE")) return;
			}
		}catch(Exception ignored) {}	
		try {
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				if(ifWearingAll(p, "Bone", ChatColor.WHITE + "Bonemealer - Applies bone meal to nearby blocks.")) {
					/* Put the ability below. This is auto-generated
					* from ArmorCreator program
	                * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
					*/
					if(!plugin.ver16) return;
					if(boneCooldown.contains(p.getUniqueId())) return;
					boneCooldown.add(p.getUniqueId());
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							boneCooldown.remove(p.getUniqueId());
						}
					}, 10 * 20L);
					int radius = 2;
					for (int x = -(radius); x <= radius; x ++) {
						for(int y = -(radius); y <= radius; y ++) {
							for(int z = -(radius); z <= radius; z ++) {
								try {
									p.getLocation().getBlock().getRelative(x, y, z).applyBoneMeal(BlockFace.UP);
								}catch(Exception e1) {
									
								}
							}
						}
					}
				}
			}
		}catch(Exception e1) {
			
		}
	}
	@ArmorEquip
	@EventHandler
	public void soulsandArmor(ArmorEquipEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SOUL_SAND")) return;
			}
		}catch(Exception ignored) {}	
		try {
			Player p = e.getPlayer();
			if(ifWearingAll(Method.getArmor(p, e), "Soul Sand", ChatColor.GRAY + "Slow Motion - Live life in the slow lane")) {
				try {
					p.removePotionEffect(PotionEffectType.SLOW);
				}catch(Exception ignored) {}
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0));
			}else if(!ifWearingAll(Method.getArmor(p, e), "Soul Sand", ChatColor.GRAY + "Slow Motion - Live life in the slow lane")) {
				try {
					p.removePotionEffect(PotionEffectType.SLOW);
				}catch(Exception ignored) {}
			}
		}catch(Exception catched) {}
	}
	public void snowArmor(Player p) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SNOW")) return;
			}
		}catch(Exception ignored) {}	
		try {
			ItemStack helm = p.getInventory().getHelmet();
			ItemStack chest = p.getInventory().getChestplate();
			ItemStack legs = p.getInventory().getLeggings();
			ItemStack boots = p.getInventory().getBoots();
			if(ifWearingAll(new ItemStack[] {helm, chest, legs, boots}, "Snow", ChatColor.WHITE + "Snowy - Spawns snow and snowballs")) {
				/* Put the ability below. This is auto-generated
				* from ArmorCreator program
                * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
				*/
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
				m1.setDisplayName("Snow Helmet");
				m2.setDisplayName("Snow Chestplate");
				m3.setDisplayName("Snow Leggings");
				m4.setDisplayName("Snow Boots");
				ArrayList<String> lore = new ArrayList<String>();
		        lore.add(ChatColor.WHITE + "Snowy - Spawns snow and snowballs");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		        m1.setColor(Color.fromRGB(247, 251, 251));m2.setColor(Color.fromRGB(247, 251, 251));m3.setColor(Color.fromRGB(247, 251, 251));m4.setColor(Color.fromRGB(247, 251, 251));
		        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

				m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "snowHelm"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "snowChest"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "snowLegs"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "snowFeet"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);
				
				m1.addEnchant(Enchantment.FROST_WALKER, 2, true);
				m2.addEnchant(Enchantment.FROST_WALKER, 2, true);
				m3.addEnchant(Enchantment.FROST_WALKER, 2, true);
				m4.addEnchant(Enchantment.FROST_WALKER, 2, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				changeArmorInArmorSlot(p, armor1, armor2, armor3, armor4);
			}else {
				ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
				ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
				ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
				ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
				LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
				LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
				LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
				m1.setDisplayName("Snow Helmet");
				m2.setDisplayName("Snow Chestplate");
				m3.setDisplayName("Snow Leggings");
				m4.setDisplayName("Snow Boots");
				ArrayList<String> lore = new ArrayList<String>();
		        lore.add(ChatColor.WHITE + "Snowy - Spawns snow and snowballs");
		        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		        m1.setColor(Color.fromRGB(247, 251, 251));m2.setColor(Color.fromRGB(247, 251, 251));m3.setColor(Color.fromRGB(247, 251, 251));m4.setColor(Color.fromRGB(247, 251, 251));
		        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
				m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

				m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
				m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
				m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
				m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
				m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "snowHelm"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "snowChest"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "snowLegs"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "snowFeet"), PersistentDataType.BYTE, Byte.valueOf("1"));
				m1.setLore(lore);
				m2.setLore(lore);
				m3.setLore(lore);
				m4.setLore(lore);
				if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(GLOWING, 0, true);
				if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(GLOWING, 0, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				
				ItemStack[] newArmor = {armor1, armor2, armor3, armor4};
				replaceArmorInInventory(p, "snow", newArmor, true, Enchantment.FROST_WALKER);
			}
		}catch(Exception e1) {
			
		}

	}
	@EventHandler
	public void snowArmor(PlayerToggleSneakEvent e) {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SNOW")) return;
			}
		}catch(Exception ignored) {}	
		try {
			Player p = e.getPlayer();
			if(e.isSneaking()) {
				if(!snowCooldown.contains(p.getUniqueId()) && ifWearingAll(p, "Snow", ChatColor.WHITE + "Snowy - Spawns snow and snowballs")) {
					int radius = 3;
					boolean snowed = false;
					for(int x = -(radius); x <= radius; x++) {
						for(int y = -(radius); y <= radius; y++) {
							for(int z = -(radius); z <= radius; z++) {
								org.bukkit.block.Block block = p.getLocation().getBlock().getRelative(x, y, z);
								if(block.getType().isOccluding()) {
									org.bukkit.block.Block top = block.getLocation().add(0, 1, 0).getBlock();
									if(top.getType() == Material.SNOW) {
										Snow snow = (Snow) top.getBlockData();
										if(snow.getLayers() <= (snow.getMinimumLayers() + 1)) snow.setLayers(snow.getLayers()+1);
										top.setBlockData(snow);
										if(new Random().nextInt(100) + 1 <= 15) p.getWorld().dropItemNaturally(top.getLocation(), new ItemStack(Material.SNOWBALL));
										snowed = true;
									}else if(top.getType() == Material.AIR) {
										top.setType(Material.SNOW);
										top = p.getLocation().getBlock().getRelative(x, (y+1), z);
										Snow snow = (Snow) top.getBlockData();
										snow.setLayers(snow.getMinimumLayers());
										top.setBlockData(snow);
										if(new Random().nextInt(100) + 1 <= 15) p.getWorld().dropItemNaturally(top.getLocation(), new ItemStack(Material.SNOWBALL));
										snowed = true;
									}
								}
							}
						}
					}
					if(snowed) {
						snowCooldown.add(p.getUniqueId());
						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							public void run() {
								try {
									snowCooldown.remove(p.getUniqueId());
								}catch(Exception e) {
									
								}
							}
						}, 2 * 20L);
					}
					p.playSound(p.getLocation(), Sound.WEATHER_RAIN, 1, 1);
					
				}
			}
		}catch(Exception e1) {
			
		}
	}
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface ArmorEquip {}
}
