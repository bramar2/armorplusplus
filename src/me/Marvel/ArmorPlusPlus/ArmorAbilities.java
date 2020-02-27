package me.Marvel.ArmorPlusPlus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class ArmorAbilities implements Listener {
	
	ArrayList<UUID> anti = new ArrayList<UUID>();
	ArrayList<UUID> cooldown = new ArrayList<UUID>();
	ArrayList<UUID> melodycooldown = new ArrayList<UUID>();
	ArrayList<UUID> scooldown = new ArrayList<UUID>();
	ArrayList<UUID> dispenser = new ArrayList<UUID>();
	ArrayList<UUID> exp = new ArrayList<UUID>();
	ArrayList<UUID> pull = new ArrayList<>();
	
	private void check(Player p) {
		Integer min;
		Integer max;
		min = 1;
		max = 25;
		double seminote = (int) ((Math.random()*((max-min)+1))+min) - 1;
		int note = (int) seminote;
		Note n = new me.Marvel.ArmorPlusPlus.Note();
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
						String name;
						ConsoleCommandSender console;
						
						name = p.getName();
						console = Bukkit.getServer().getConsoleSender();
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:iron_ore\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:iron_ore\"}}] run data modify entity @s Item.id set value \"minecraft:iron_ingot\"");
						
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:gold_ore\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:gold_ore\"}}] run data modify entity @s Item.id set value \"minecraft:gold_ingot\"");
						
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:oak_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:oak_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
						
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:stripped_oak_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:stripped_oak_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
						
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:spruce_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:spruce_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
					
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:birch_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:birch_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
						
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:jungle_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:jungle_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
						
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:acacia_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:acacia_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
						
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:dark_oak_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:dark_oak_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
					
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:stripped_spruce_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:stripped_spruce_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
						
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:stripped_birch_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:stripped_birch_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
						
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:stripped_jungle_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:stripped_jungle_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
						
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:stripped_acacia_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:stripped_acacia_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
						
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] at @s[nbt={Item:{id:\"minecraft:stripped_dark_oak_log\"}}] run particle minecraft:lava ~ ~ ~");
						Bukkit.dispatchCommand(console, "execute at " + name + " as @e[type=item,distance=..5] as @s[nbt={Item:{id:\"minecraft:stripped_dark_oak_log\"}}] run data modify entity @s Item.id set value \"minecraft:charcoal\"");
						
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
										scooldown.remove(p.getUniqueId());
									}
								}, 20 * 6);
								int radius = 3;
								for (int x = -(radius); x <= radius; x ++) {
									for(int y = -(radius); y <= radius; y ++) {
										for(int z = -(radius); z <= radius; z ++) {
											org.bukkit.block.Block block = p.getLocation().getBlock().getRelative(x, y, z);
											if(block.getType().equals(Material.WATER)) {
												block.setType(Material.AIR);
											}else if(block.getType().equals(Material.TALL_SEAGRASS)) {
												block.setType(Material.AIR);
											}else if(block.getType().equals(Material.SEAGRASS)) {
												block.setType(Material.AIR);
											}
										}
									}
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
					}
				}
			}catch(NullPointerException e) {

			}

		}

	}
}
