package me.marvel.armorplusplus;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Automates methods.
 * <p>
 * To make the code simpler.
 */
public class Method {
	
	public static Plugin plugin;
	public static boolean ifWearingAll(Player p, String armorname, String lore1) {
		boolean result = false;
		try {
			ItemStack helm = p.getInventory().getHelmet();
			ItemStack chest = p.getInventory().getChestplate();
			ItemStack legs = p.getInventory().getLeggings();
			ItemStack feet = p.getInventory().getBoots();
			if(helm.getItemMeta().getDisplayName().equalsIgnoreCase(armorname + " Helmet") &&
				chest.getItemMeta().getDisplayName().equalsIgnoreCase(armorname + " Chestplate") &&
				legs.getItemMeta().getDisplayName().equalsIgnoreCase(armorname + " Leggings") && 
				feet.getItemMeta().getDisplayName().equalsIgnoreCase(armorname + " Boots")) {
				if(helm.getItemMeta().getLore().get(0).equalsIgnoreCase(lore1) && 
					chest.getItemMeta().getLore().get(0).equalsIgnoreCase(lore1) &&
					legs.getItemMeta().getLore().get(0).equalsIgnoreCase(lore1) && 
					feet.getItemMeta().getLore().get(0).equalsIgnoreCase(lore1)) {
					result = true;
				}else {
					result = false;
				}
			}else {
				result = false;
			}
			return result;
		}catch(Exception e) {
			return false;
		}
	}
	public static void changeArmorInArmorSlot(Player p, ItemStack helm, ItemStack chest, ItemStack legs, ItemStack feet) {
		try {
			p.getInventory().setHelmet(helm);
			p.getInventory().setChestplate(chest);
			p.getInventory().setLeggings(legs);
			p.getInventory().setBoots(feet);
		}catch(Exception e) {
			
		}
	}
	public static void replaceArmorInInventory(Player p, String tag, ItemStack[] newArmor, boolean checkAttr, Attribute attribute) {
		ItemStack[] content = p.getInventory().getContents();
		for(int i = 0; i < content.length; i++) {
			ItemStack item = content[i];
			try {
				if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Helm"), PersistentDataType.BYTE)) {
					// helmet
					if(!checkAttr) p.getInventory().setItem(i, newArmor[0]);
					else {
						if(!item.getItemMeta().hasAttributeModifiers()) continue;
						boolean hasAttribute = false;
						for(Entry<Attribute, AttributeModifier> entry : item.getItemMeta().getAttributeModifiers().entries()) {
							if(entry.getKey() == attribute) {
								hasAttribute = true;
								break;
							}
						}
						if(hasAttribute) {
							p.getInventory().setItem(i, newArmor[0]);
						}
					}
				}else if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Chest"), PersistentDataType.BYTE)) {
					// chestplate
					if(!checkAttr) p.getInventory().setItem(i, newArmor[1]);
					else {
						if(!item.getItemMeta().hasAttributeModifiers()) continue;
						boolean hasAttribute = false;
						for(Entry<Attribute, AttributeModifier> entry : item.getItemMeta().getAttributeModifiers().entries()) {
							if(entry.getKey() == attribute) {
								hasAttribute = true;
								break;
							}
						}
						if(hasAttribute) {
							p.getInventory().setItem(i, newArmor[1]);
						}
					}
				}else if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Legs"), PersistentDataType.BYTE)) {
					// legs
					if(!checkAttr) p.getInventory().setItem(i, newArmor[2]);
					else {
						if(!item.getItemMeta().hasAttributeModifiers()) continue;
						boolean hasAttribute = false;
						for(Entry<Attribute, AttributeModifier> entry : item.getItemMeta().getAttributeModifiers().entries()) {
							if(entry.getKey() == attribute) {
								hasAttribute = true;
								break;
							}
						}
						if(hasAttribute) {
							p.getInventory().setItem(i, newArmor[2]);
						}
					}
				}else if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Feet"), PersistentDataType.BYTE)) {
					// feet
					if(!checkAttr) p.getInventory().setItem(i, newArmor[3]);
					else {
						if(!item.getItemMeta().hasAttributeModifiers()) continue;
						boolean hasAttribute = false;
						for(Entry<Attribute, AttributeModifier> entry : item.getItemMeta().getAttributeModifiers().entries()) {
							if(entry.getKey() == attribute) {
								hasAttribute = true;
								break;
							}
						}
						if(hasAttribute) {
							p.getInventory().setItem(i, newArmor[3]);
						}
					}
				}
			}catch(Exception e) {
				
			}
			continue;
		}
	}
	
	public static void addPotionEffect(Player p, PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean icon) {
		if(!p.hasPotionEffect(type)) p.addPotionEffect(new PotionEffect(type, duration, amplifier, ambient, particles, icon));
		else {
			boolean give = false;
			for(PotionEffect potionEffect : p.getActivePotionEffects()) {
				if(potionEffect.getType() == type && potionEffect.getDuration() < 3) {
					give = true;
					break;
				}
			}
			if(give) p.addPotionEffect(new PotionEffect(type, duration, amplifier, ambient, particles, icon));
		}
	}
	
	public static void replaceArmorInInventory(Player p, String tag, ItemStack[] newArmor, boolean checkEnch, Enchantment ench) {
		ItemStack[] content = p.getInventory().getContents();
		for(int i = 0; i < content.length; i++) {
			ItemStack item = content[i];
			try {
				if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Helm"), PersistentDataType.BYTE)) {
					// helmet
					if(!checkEnch) p.getInventory().setItem(i, newArmor[0]);
					else if(checkEnch) {
						if(!(item.containsEnchantment(ench))) continue;
						p.getInventory().setItem(i, newArmor[0]);
					}else {
						plugin.getLogger().warning("[ArmorPlusPlus/WARN] Invalid boolean value. Unable to replace some armor. Please contact author to prevent Custom Armors having enchantments/attributes that wasn't supposed to be there! Other Info: at me.marvel.armorplusplus.Method.java:69 - Failed to replace armor[0]");
					}
				}else if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Chest"), PersistentDataType.BYTE)) {
					// chestplate
					if(!checkEnch) p.getInventory().setItem(i, newArmor[1]);
					else if(checkEnch) {
						if(!(item.containsEnchantment(ench))) continue;
						p.getInventory().setItem(i, newArmor[1]);
					}else {
						plugin.getLogger().warning("[ArmorPlusPlus/WARN] Invalid boolean value. Unable to replace some armor. Please contact author to prevent Custom Armors having enchantments/attributes that wasn't supposed to be there! Other Info: at me.marvel.armorplusplus.Method.java:78 - Failed to replace armor[1]");
					}
				}else if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Legs"), PersistentDataType.BYTE)) {
					// legs
					if(!checkEnch) p.getInventory().setItem(i, newArmor[2]);
					else if(checkEnch) {
						if(!(item.containsEnchantment(ench))) continue;
						p.getInventory().setItem(i, newArmor[2]);
					}else {
						plugin.getLogger().warning("[ArmorPlusPlus/WARN] Invalid boolean value. Unable to replace some armor. Please contact author to prevent Custom Armors having enchantments/attributes that wasn't supposed to be there! Other Info: at me.marvel.armorplusplus.Method.java:87 - Failed to replace armor[2]");
					}
				}else if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Feet"), PersistentDataType.BYTE)) {
					// feet
					if(!checkEnch) p.getInventory().setItem(i, newArmor[3]);
					else if(checkEnch) {
						if(!(item.containsEnchantment(ench))) continue;
						p.getInventory().setItem(i, newArmor[3]);
					}else {
						plugin.getLogger().warning("[ArmorPlusPlus/WARN] Invalid boolean value. Unable to replace some armor. Please contact author to prevent Custom Armors having enchantments/attributes that wasn't supposed to be there! Other Info: at me.marvel.armorplusplus.Method.java:96 - Failed to replace armor[3]");
					}
				}
			}catch(Exception e) {
				
			}
			continue;
		}
	}
	public static HashMap<Material, Material> getSmeltableItems() {
		HashMap<Material, Material> mat = new HashMap<Material, Material>();
		// 1st input, 2nd output
		mat.put(Material.PORKCHOP, Material.COOKED_PORKCHOP);
		mat.put(Material.BEEF, Material.COOKED_BEEF);
		mat.put(Material.CHICKEN, Material.COOKED_CHICKEN);
		mat.put(Material.COD, Material.COOKED_COD);
		mat.put(Material.SALMON, Material.COOKED_SALMON);
		mat.put(Material.POTATO, Material.BAKED_POTATO);
		mat.put(Material.MUTTON, Material.COOKED_MUTTON);
		mat.put(Material.RABBIT, Material.COOKED_RABBIT);
		mat.put(Material.KELP, Material.DRIED_KELP);
		
		mat.put(Material.IRON_ORE, Material.IRON_INGOT);
		mat.put(Material.GOLD_ORE, Material.GOLD_INGOT);
		mat.put(Material.getMaterial("ANCIENT_DEBRIS"), Material.getMaterial("NETHERITE_SCRAP"));
		mat.put(Material.SAND, Material.GLASS);
		mat.put(Material.RED_SAND, Material.GLASS);
		mat.put(Material.COBBLESTONE, Material.STONE);
		mat.put(Material.SANDSTONE, Material.SMOOTH_SANDSTONE);
		mat.put(Material.RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE);
		mat.put(Material.STONE, Material.SMOOTH_STONE);
		mat.put(Material.QUARTZ_BLOCK, Material.SMOOTH_QUARTZ);
		mat.put(Material.CLAY_BALL, Material.BRICK);
		mat.put(Material.NETHERRACK, Material.NETHER_BRICK);
		mat.put(Material.NETHER_BRICKS, Material.getMaterial("CRACKED_NETHER_BRICKS"));
		mat.put(Material.CLAY, Material.TERRACOTTA);
		mat.put(Material.STONE_BRICKS, Material.CRACKED_STONE_BRICKS);
		mat.put(Material.BLACK_TERRACOTTA, Material.BLACK_GLAZED_TERRACOTTA);
		mat.put(Material.BLUE_TERRACOTTA, Material.BLUE_GLAZED_TERRACOTTA);
		mat.put(Material.BROWN_TERRACOTTA, Material.BROWN_GLAZED_TERRACOTTA);
		mat.put(Material.CYAN_TERRACOTTA, Material.CYAN_GLAZED_TERRACOTTA);
		mat.put(Material.GRAY_TERRACOTTA, Material.GRAY_GLAZED_TERRACOTTA);
		mat.put(Material.GREEN_TERRACOTTA, Material.GREEN_GLAZED_TERRACOTTA);
		mat.put(Material.LIGHT_BLUE_TERRACOTTA, Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
		mat.put(Material.LIGHT_GRAY_TERRACOTTA, Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
		mat.put(Material.LIME_TERRACOTTA, Material.LIME_GLAZED_TERRACOTTA);
		mat.put(Material.MAGENTA_TERRACOTTA, Material.MAGENTA_GLAZED_TERRACOTTA);
		mat.put(Material.ORANGE_TERRACOTTA, Material.ORANGE_GLAZED_TERRACOTTA);
		mat.put(Material.PINK_TERRACOTTA, Material.PINK_GLAZED_TERRACOTTA);
		mat.put(Material.PURPLE_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA);
		mat.put(Material.RED_TERRACOTTA, Material.RED_GLAZED_TERRACOTTA);
		mat.put(Material.WHITE_TERRACOTTA, Material.WHITE_GLAZED_TERRACOTTA);
		mat.put(Material.YELLOW_TERRACOTTA, Material.YELLOW_GLAZED_TERRACOTTA);
		mat.put(Material.CACTUS, Material.GREEN_DYE);
		
		mat.put(Material.OAK_LOG, Material.CHARCOAL);
		mat.put(Material.OAK_WOOD, Material.CHARCOAL);
		mat.put(Material.STRIPPED_OAK_LOG, Material.CHARCOAL);
		mat.put(Material.STRIPPED_OAK_WOOD, Material.CHARCOAL);
		mat.put(Material.SPRUCE_LOG, Material.CHARCOAL);
		mat.put(Material.SPRUCE_WOOD, Material.CHARCOAL);
		mat.put(Material.STRIPPED_SPRUCE_LOG, Material.CHARCOAL);
		mat.put(Material.STRIPPED_SPRUCE_WOOD, Material.CHARCOAL);
		mat.put(Material.BIRCH_LOG, Material.CHARCOAL);
		mat.put(Material.BIRCH_WOOD, Material.CHARCOAL);
		mat.put(Material.STRIPPED_BIRCH_LOG, Material.CHARCOAL);
		mat.put(Material.STRIPPED_BIRCH_WOOD, Material.CHARCOAL);
		mat.put(Material.JUNGLE_LOG, Material.CHARCOAL);
		mat.put(Material.JUNGLE_WOOD, Material.CHARCOAL);
		mat.put(Material.STRIPPED_JUNGLE_LOG, Material.CHARCOAL);
		mat.put(Material.STRIPPED_JUNGLE_WOOD, Material.CHARCOAL);
		mat.put(Material.ACACIA_LOG, Material.CHARCOAL);
		mat.put(Material.ACACIA_WOOD, Material.CHARCOAL);
		mat.put(Material.STRIPPED_ACACIA_LOG, Material.CHARCOAL);
		mat.put(Material.STRIPPED_ACACIA_WOOD, Material.CHARCOAL);
		mat.put(Material.DARK_OAK_LOG, Material.CHARCOAL);
		mat.put(Material.DARK_OAK_WOOD, Material.CHARCOAL);
		mat.put(Material.STRIPPED_DARK_OAK_LOG, Material.CHARCOAL);
		mat.put(Material.STRIPPED_DARK_OAK_WOOD, Material.CHARCOAL);
		mat.put(Material.getMaterial("CRIMSON_STEM"), Material.CHARCOAL);
		mat.put(Material.getMaterial("CRIMSON_HYPHAE"), Material.CHARCOAL);
		mat.put(Material.getMaterial("STRIPPED_CRIMSON_STEM"), Material.CHARCOAL);
		mat.put(Material.getMaterial("STRIPPED_CRIMSON_HYPHAE"), Material.CHARCOAL);
		mat.put(Material.getMaterial("WARPED_STEM"), Material.CHARCOAL);
		mat.put(Material.getMaterial("WARPED_HYPHAE"), Material.CHARCOAL);
		mat.put(Material.getMaterial("STRIPPED_WARPED_STEM"), Material.CHARCOAL);
		mat.put(Material.getMaterial("STRIPPED_WARPED_HYPHAE"), Material.CHARCOAL);
		
		mat.put(Material.CHORUS_FRUIT, Material.POPPED_CHORUS_FRUIT);
		mat.put(Material.SPONGE, Material.WET_SPONGE);
		mat.put(Material.SEA_PICKLE, Material.LIME_DYE);
		mat.put(Material.DIAMOND_ORE, Material.DIAMOND);
		mat.put(Material.LAPIS_ORE, Material.LAPIS_LAZULI);
		mat.put(Material.REDSTONE_ORE, Material.REDSTONE);
		mat.put(Material.COAL_ORE, Material.COAL);
		mat.put(Material.EMERALD_ORE, Material.EMERALD);
		mat.put(Material.getMaterial("NETHER_GOLD_ORE"), Material.GOLD_INGOT);
		mat.put(Material.NETHER_QUARTZ_ORE, Material.QUARTZ);
		
		mat.put(Material.IRON_SWORD, Material.IRON_NUGGET);
		mat.put(Material.IRON_PICKAXE, Material.IRON_NUGGET);
		mat.put(Material.IRON_AXE, Material.IRON_NUGGET);
		mat.put(Material.IRON_SHOVEL, Material.IRON_NUGGET);
		mat.put(Material.IRON_HOE, Material.IRON_NUGGET);
		mat.put(Material.IRON_HELMET, Material.IRON_NUGGET);
		mat.put(Material.IRON_CHESTPLATE, Material.IRON_NUGGET);
		mat.put(Material.IRON_LEGGINGS, Material.IRON_NUGGET);
		mat.put(Material.IRON_BOOTS, Material.IRON_NUGGET);
		mat.put(Material.IRON_HORSE_ARMOR, Material.IRON_NUGGET);
		mat.put(Material.CHAINMAIL_HELMET, Material.IRON_NUGGET);
		mat.put(Material.CHAINMAIL_CHESTPLATE, Material.IRON_NUGGET);
		mat.put(Material.CHAINMAIL_LEGGINGS, Material.IRON_NUGGET);
		mat.put(Material.CHAINMAIL_BOOTS, Material.IRON_NUGGET);
		
		mat.put(Material.GOLDEN_SWORD, Material.GOLD_NUGGET);
		mat.put(Material.GOLDEN_PICKAXE, Material.GOLD_NUGGET);
		mat.put(Material.GOLDEN_AXE, Material.GOLD_NUGGET);
		mat.put(Material.GOLDEN_SHOVEL, Material.GOLD_NUGGET);
		mat.put(Material.GOLDEN_HOE, Material.GOLD_NUGGET);
		mat.put(Material.GOLDEN_HELMET, Material.GOLD_NUGGET);
		mat.put(Material.GOLDEN_CHESTPLATE, Material.GOLD_NUGGET);
		mat.put(Material.GOLDEN_LEGGINGS, Material.GOLD_NUGGET);
		mat.put(Material.GOLDEN_BOOTS, Material.GOLD_NUGGET);
		mat.put(Material.GOLDEN_HORSE_ARMOR, Material.GOLD_NUGGET);
		
		return mat;
	}
	
}
