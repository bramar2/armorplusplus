package me.marvel.armorplusplus;

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
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow.PickupStatus;
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
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class ArmorAbilities implements Listener {
	
	private ArrayList<UUID> anti = new ArrayList<UUID>();
	private ArrayList<UUID> cooldown = new ArrayList<UUID>();
	private ArrayList<UUID> melodycooldown = new ArrayList<UUID>();
	private ArrayList<UUID> scooldown = new ArrayList<UUID>();
	private ArrayList<UUID> dispenser = new ArrayList<UUID>();
	private ArrayList<UUID> exp = new ArrayList<UUID>();
	private ArrayList<UUID> pull = new ArrayList<UUID>();
	private ArrayList<UUID> smeltCooldown = new ArrayList<UUID>();
	private ArrayList<UUID> smeltsneaking = new ArrayList<UUID>();
	private ArrayList<UUID> pistonCooldown = new ArrayList<UUID>();
	private ArrayList<UUID> wscooldown = new ArrayList<UUID>();
	
	private void check(Player p) {
		Integer min;
		Integer max;
		min = 1;
		max = 25;
		double seminote = (int) ((Math.random()*((max-min)+1))+min) - 1;
		int note = (int) seminote;
		Note n = new me.marvel.armorplusplus.Note();
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
	
	ArmorPlusPlus plugin;
	public ArmorAbilities(ArmorPlusPlus ArmorPlusPlus) {
		plugin = ArmorPlusPlus;
	}
	@SuppressWarnings("deprecation")
	public void dirtArmor() {
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
				if(name1.equalsIgnoreCase("Dirt Helmet") && name2.equalsIgnoreCase("Dirt Chestplate") && name3.equalsIgnoreCase("Dirt Leggings") && name4.equalsIgnoreCase("Dirt Boots")) {
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(ChatColor.GREEN + "Regrowth - Repair durability slowly") && lore2.equalsIgnoreCase(ChatColor.GREEN + "Regrowth - Repair durability slowly") && lore3.equalsIgnoreCase(ChatColor.GREEN + "Regrowth - Repair durability slowly") && lore4.equalsIgnoreCase(ChatColor.GREEN + "Regrowth - Repair durability slowly")) {
						short d1 = helm.getDurability();
						short d2 = chest.getDurability();
						short d3 = legs.getDurability();
						short d4 = boots.getDurability();
						if(d1 != 0) {
							helm.setDurability((short) (d1 - 1));
							p.getInventory().setHelmet(helm);
						}
						if(d2 != 0) {
							chest.setDurability((short) (d2 - 1));
							p.getInventory().setChestplate(chest);
						}
						if(d3 != 0) {
							legs.setDurability((short) (d3 - 1));
							p.getInventory().setLeggings(legs);
						}
						if(d4 != 0) {
							boots.setDurability((short) (d4 - 1));
							p.getInventory().setBoots(boots);
						}
					}
				}
			}catch(NullPointerException e) {
				
			}
			
		}
		
	}
	
	public void craftArmor() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
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
						if(p.isSneaking() == true) {
							p.openWorkbench(p.getLocation(), true);
						}
					}
				}
			}catch(NullPointerException e) {
				
			}
		}
	}
	public void invisibility() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
				String name1 = p.getInventory().getHelmet().getItemMeta().getDisplayName();
				String name2 = p.getInventory().getChestplate().getItemMeta().getDisplayName();
				String name3 = p.getInventory().getLeggings().getItemMeta().getDisplayName();
				String name4 = p.getInventory().getBoots().getItemMeta().getDisplayName();
				if(name1.equalsIgnoreCase("Glass Helmet") && name2.equalsIgnoreCase("Glass Chestplate") && name3.equalsIgnoreCase("Glass Leggings") && name4.equalsIgnoreCase("Glass Boots")) {
					String lore1 = p.getInventory().getHelmet().getItemMeta().getLore().get(0);
					String lore2 = p.getInventory().getChestplate().getItemMeta().getLore().get(0);
					String lore3 = p.getInventory().getLeggings().getItemMeta().getLore().get(0);
					String lore4 = p.getInventory().getBoots().getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(ChatColor.GRAY + "Invisibility - Provides Invisibility") && lore2.equalsIgnoreCase(ChatColor.GRAY + "Invisibility - Provides Invisibility") && lore3.equalsIgnoreCase(ChatColor.GRAY + "Invisibility - Provides Invisibility") && lore4.equalsIgnoreCase(ChatColor.GRAY + "Invisibility - Provides Invisibility")) {
						p.removePotionEffect(PotionEffectType.INVISIBILITY);
						p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 5, 9, false, false, false));
					}
				}	
				
			}catch(NullPointerException e) {
				
			}
		}
	}
	public void smelt() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
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
						if(p.isSneaking() == false) return;
						
						smeltsneaking.add(p.getUniqueId());
						if(smeltCooldown.contains(p.getUniqueId())) return;
						HashMap<Material, Material> smeltable_items = Method.getSmeltableItems();
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
				
			}catch(NullPointerException e) {
				
			}
		}
	}
	public void explode() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
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
						if(p.isSneaking() == true) {
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
				
			}catch(NullPointerException e) {
				
			}
		}
	}

	@EventHandler
	public void melody(PlayerMoveEvent e) {
		try {
			Player p = e.getPlayer();
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
					if(!(melodycooldown.contains(p.getUniqueId()))) {
							check(p);
							melodycooldown.add(p.getUniqueId());
							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								public void run() {
									melodycooldown.remove(p.getUniqueId());
								}
							}, 20 * 2);
						}
					}
				}
			}catch(NullPointerException error) {
				
			}
	}

	public void pumpkinArmor() {
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
	public void melonArmor() {
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
	
	public void spongeArmor() {
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
				if(name1.equalsIgnoreCase("Sponge Helmet") && name2.equalsIgnoreCase("Sponge Chestplate") && name3.equalsIgnoreCase("Sponge Leggings") && name4.equalsIgnoreCase("Sponge Boots")) {
					String lore = ChatColor.YELLOW + "Absorbent - Absorbs nearby liquids";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						if(p.isSneaking() == true) {
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
								if(!sponged) try {
									scooldown.remove(p.getUniqueId());
								}catch(Exception e) {
									
								}
							}
						}
					}
				}
			}catch(NullPointerException e) {
				
			}
			
		}
		
	}

	public void dispenserArmor() {
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
						if(p.isSneaking() == true) {
							if(!(dispenser.contains(p.getUniqueId()))) {
								dispenser.add(p.getUniqueId());
								plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									public void run() {
										dispenser.remove(p.getUniqueId());
									}
								}, 20 * 7);
								org.bukkit.entity.Arrow a1 = (org.bukkit.entity.Arrow) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARROW);
								org.bukkit.entity.Arrow a2 = (org.bukkit.entity.Arrow) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARROW);
								org.bukkit.entity.Arrow a3 = (org.bukkit.entity.Arrow) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARROW);
								org.bukkit.entity.Arrow a4 = (org.bukkit.entity.Arrow) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARROW);
								a1.setPickupStatus(PickupStatus.CREATIVE_ONLY);
								a2.setPickupStatus(PickupStatus.CREATIVE_ONLY);
								a3.setPickupStatus(PickupStatus.CREATIVE_ONLY);
								a4.setPickupStatus(PickupStatus.CREATIVE_ONLY);
								a1.setVelocity(new org.bukkit.util.Vector(1.5, 0.25, 0.0));
								a2.setVelocity(new org.bukkit.util.Vector(0.0, 0.25, 1.5));
								a3.setVelocity(new org.bukkit.util.Vector(-1.5, 0.25, 0.0));
								a4.setVelocity(new org.bukkit.util.Vector(0.0, 0.25, -1.5));
								
							}
						}
					}
				}
			}catch(NullPointerException e) {
				
			}
			
		}
		
	}
	public void prismarineArmor() {
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
				if(name1.equalsIgnoreCase("Prismarine Helmet") && name2.equalsIgnoreCase("Prismarine Chestplate") && name3.equalsIgnoreCase("Prismarine Leggings") && name4.equalsIgnoreCase("Prismarine Boots")) {
					String lore = ChatColor.AQUA + "Diving Suit - Provides Depth Strider, Respiration";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						/* Put the ability below. This is auto-generated
						 * from ArmorCreator program
						 */
						if(p.getLocation().getBlock().getType().equals(Material.KELP) | p.getLocation().getBlock().getType().equals(Material.WATER) | p.getLocation().getBlock().getType().equals(Material.SEAGRASS) | p.getLocation().getBlock().getType().equals(Material.TALL_SEAGRASS)) {
							p.removePotionEffect(PotionEffectType.NIGHT_VISION);
							p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10, 2, false, false));
						}
					}
				}
			}catch(NullPointerException e) {
				
			}
			
		}
		
	}
	public void enderArmor() {
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
				if(name1.equalsIgnoreCase("Ender Helmet") && name2.equalsIgnoreCase("Ender Chestplate") && name3.equalsIgnoreCase("Ender Leggings") && name4.equalsIgnoreCase("Ender Boots")) {
					String lore = "Ender Hoarder - Provides access to your ender chest";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						if(p.isSneaking()) {
							p.openInventory(p.getEnderChest());
							p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
						}
					}
				}
			}catch(NullPointerException e) {
				
			}
			
		}
		
	}
	public void lapisArmor() {
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
						/* Put the ability below. This is auto-generated
						 * from ArmorCreator program
						 */
						if(!(exp.contains(p.getUniqueId()))) {
							exp.add(p.getUniqueId());
							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								public void run() {
									exp.remove(p.getUniqueId());
								}
							}, 20 * 10);
							Float xp = p.getExp();
							Boolean done = false;
							for(float i = 0.25F; i > 0.01F; i -= 0.01F) {
								try {
									if(done) {
										break;
									}else {
										Float total = xp + i;
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
				}
			}catch(NullPointerException e) {
				
			}
			
		}
		
	}
	public void cactusArmor() {
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
						List<Entity> prickled = (List<Entity>) p.getWorld().getNearbyEntities(p.getLocation(), 1, 1, 1);
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
		
	}
	
	public void leavesArmor() {
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
				if(name1.equalsIgnoreCase("Leaves Helmet") && name2.equalsIgnoreCase("Leaves Chestplate") && name3.equalsIgnoreCase("Leaves Leggings") && name4.equalsIgnoreCase("Leaves Boots")) {
					String lore = ChatColor.GREEN + "Lightweight - Fall slowly, like a leaf";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						/* Put the ability below. This is auto-generated
						 * from ArmorCreator program
						 */
						p.removePotionEffect(PotionEffectType.SLOW_FALLING);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 25, 0, false, false));
					}
				}
			}catch(NullPointerException e) {
				
			}
			
		}
		
	}
	public void sugarcaneArmor() {
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
				if(name1.equalsIgnoreCase("Sugar Cane Helmet") && name2.equalsIgnoreCase("Sugar Cane Chestplate") && name3.equalsIgnoreCase("Sugar Cane Leggings") && name4.equalsIgnoreCase("Sugar Cane Boots")) {
					String lore = ChatColor.YELLOW + "Speedy - Increases Speed";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						/* Put the ability below. This is auto-generated
						 * from ArmorCreator program
						 */
						p.removePotionEffect(PotionEffectType.SPEED);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 25, 1, false, false));
					}
				}
			}catch(NullPointerException e) {
				
			}
		}
	}
	public void stickypistonArmor() {
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
						if(p.isSneaking()) {
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
								p.sendMessage("You pulled " + ChatColor.GREEN + (count - 1) + ChatColor.RESET + " entities!");
							}
						}
					}
				}
			}catch(NullPointerException e) {

			}

		}

	}
	public void sandArmor() {
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
				if(name1.equalsIgnoreCase("Sand Helmet") && name2.equalsIgnoreCase("Sand Chestplate") && name3.equalsIgnoreCase("Sand Leggings") && name4.equalsIgnoreCase("Sand Boots")) {
					String lore = ChatColor.GRAY + "Falling - Falls faster in air and sinks faster in";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						/* Put the ability below. This is auto-generated
						 * from ArmorCreator program
                         * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
						 */
						if(p.isSneaking()) {
							Vector v = p.getVelocity().setY(p.getVelocity().getY() - 0.2);
							p.setVelocity(v);
						}
					}
				}
			}catch(NullPointerException e) {

			}

		}

	}
	public void quartzArmor() {
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
				if(name1.equalsIgnoreCase("Quartz Helmet") && name2.equalsIgnoreCase("Quartz Chestplate") && name3.equalsIgnoreCase("Quartz Leggings") && name4.equalsIgnoreCase("Quartz Boots")) {
					String lore = ChatColor.WHITE + "Powerful - Increases speed and strength";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(lore) && lore2.equalsIgnoreCase(lore) && lore3.equalsIgnoreCase(lore) && lore4.equalsIgnoreCase(lore)) {
						/* Put the ability below. This is auto-generated
						 * from ArmorCreator program
                         * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
						 */
						p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10, 0, false, false));
						p.removePotionEffect(PotionEffectType.SPEED);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 1, false, false));
					}
				}
			}catch(NullPointerException e) {

			}

		}

	}
	public void obsidianArmor() {
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
						if(Method.ifWearingAll(p, "Obsidian", lore)) {
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
							
							m1.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.75, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							m2.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.75, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							m3.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.75, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
							m4.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", 0.75, Operation.ADD_NUMBER, EquipmentSlot.FEET));
							
							m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
							m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
							m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
							m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
							m1.setLore(lores);
							m2.setLore(lores);
							m3.setLore(lores);
							m4.setLore(lores);
							m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
							m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
							m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
							m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

							armor1.setItemMeta(m1);
							armor2.setItemMeta(m2);
							armor3.setItemMeta(m3);
							armor4.setItemMeta(m4);
							Method.changeArmorInArmorSlot(p, armor1, armor2, armor3, armor4);
						}
					}
				}
			}catch(Exception e) {

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
				m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
				m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
				m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
				m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

				armor1.setItemMeta(m1);
				armor2.setItemMeta(m2);
				armor3.setItemMeta(m3);
				armor4.setItemMeta(m4);
				ItemStack[] newArmor = {armor1, armor2, armor3, armor4};
				if(!Method.ifWearingAll(p, "Obsidian", ChatColor.GRAY + "Immovable")) Method.replaceArmorInInventory(p, "obsidian", newArmor, true, Enchantment.PROTECTION_FIRE);
			}catch(Exception e) {
				
			}

		}

	}
	public void emeraldArmor() {
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
				if(name1.equalsIgnoreCase("Emerald Helmet") && name2.equalsIgnoreCase("Emerald Chestplate") && name3.equalsIgnoreCase("Emerald Leggings") && name4.equalsIgnoreCase("Emerald Boots")) {
					String checkLore = ChatColor.DARK_GREEN + "Lucky - Greatly increases Fortune,";
					String lore1 = helm.getItemMeta().getLore().get(0);
					String lore2 = chest.getItemMeta().getLore().get(0);
					String lore3 = legs.getItemMeta().getLore().get(0);
					String lore4 = boots.getItemMeta().getLore().get(0);
					if(lore1.equalsIgnoreCase(checkLore) && lore2.equalsIgnoreCase(checkLore) && lore3.equalsIgnoreCase(checkLore) && lore4.equalsIgnoreCase(checkLore)) {
						/* Put the ability below. This is auto-generated
						 * from ArmorCreator program
                         * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
						 */
						p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 10, 1, true, false));
					}
				}
			}catch(NullPointerException e) {

			}
			
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void emeraldLootingEffect(EntityDeathEvent e) {
		try {
			LivingEntity entity = e.getEntity();
			Player p = entity.getKiller();
			Location loc = entity.getLocation();
			if(Method.ifWearingAll(p, "Emerald", ChatColor.DARK_GREEN + "Lucky - Greatly increases Fortune,")) {
				if(!(entity instanceof Mob)) return;
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
	@EventHandler
	public void emeraldFortuneEffect(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(Method.ifWearingAll(p, "Emerald", ChatColor.DARK_GREEN + "Lucky - Greatly increases Fortune,")) {
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
	public void pistonArmor() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
				if(Method.ifWearingAll(p, "Piston", ChatColor.GRAY + "Pusher - Pushes nearby entities")) {
					/* Put the ability below. This is auto-generated
					* from ArmorCreator program
                    * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
					*/
					if(!p.isSneaking()) return;
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
					for(Entity e : entities) {
						if(e instanceof Player) {
							if(p.equals((Player)e)) continue;
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
					}else {
						if(pistonCooldown.contains(p.getUniqueId())) pistonCooldown.remove(p.getUniqueId());
					}
				}
			}catch(Exception e) {

			}

		}

	}
	public void wetspongeArmor() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
				if(Method.ifWearingAll(p, "Wet Sponge", ChatColor.YELLOW + "Absorbent - Absorbs nearby liquids")) {
					/* Put the ability below. This is auto-generated
					* from ArmorCreator program
                    * TO-DO AUTO-GENERATED BY ARMORCREATOR PROGRAM
					*/
					if(p.isSneaking() == true) {
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
							}catch(Exception e) {
								
							}
						}
					}
				}
			}catch(Exception e) {
				
			}

		}

	}
	public void magmaArmor() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
				if(Method.ifWearingAll(p, "Magma", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked")) {
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
					
					Method.changeArmorInArmorSlot(p, armor1, armor2, armor3, armor4);
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
					m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
					m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
					m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
					m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

					armor1.setItemMeta(m1);
					armor2.setItemMeta(m2);
					armor3.setItemMeta(m3);
					armor4.setItemMeta(m4);
					
					ItemStack[] newArmor = {armor1, armor2, armor3, armor4};
					Method.replaceArmorInInventory(p, "magma", newArmor, true, Enchantment.PROTECTION_FIRE);
				}
			}catch(Exception e) {

			}

		}

	}
	
	@EventHandler
	public void fiery(EntityDamageByEntityEvent e) {
		try {
			if((e.getDamager() instanceof Player) && (e.getEntity() instanceof Player)) {
				boolean damagerWearing = Method.ifWearingAll((Player)e.getDamager(), "Magma", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked") || Method.ifWearingAll((Player)e.getDamager(), "Netherrack", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked");
				boolean damagedWearing = Method.ifWearingAll((Player)e.getEntity(), "Magma", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked") || Method.ifWearingAll((Player)e.getEntity(), "Netherrack", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked");
				
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
				if(Method.ifWearingAll((Player)e.getDamager(), "Magma", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked")) {
					if(!(e.getEntity().getFireTicks() > 0)) e.getEntity().setFireTicks(6 /*seconds*/ * 20);
				}else if(Method.ifWearingAll((Player)e.getDamager(), "Netherrack", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked")) {
					if(!(e.getEntity().getFireTicks() > 0)) e.getEntity().setFireTicks(6 /*seconds*/ * 20);
				}
			}else if(e.getEntity() instanceof Player) {
				if(Method.ifWearingAll((Player)e.getEntity(), "Magma", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked")) {
					if(!(e.getDamager().getFireTicks() > 0)) e.getDamager().setFireTicks(6 /*seconds*/ * 20);
				}else if(Method.ifWearingAll((Player)e.getEntity(), "Netherrack", ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked")) {
					if(!(e.getDamager().getFireTicks() > 0)) e.getDamager().setFireTicks(6 /*seconds*/ * 20);
				}
			}	
		}catch(Exception ex) {
			
		}
	}
}
