package me.bramar.armorplusplus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.bramar.armorplusplus.events.ArmorEquipEvent;
import me.bramar.armorplusplus.events.ArmorType;

/**
 * Automates methods.
 * <p>
 * To make the code simpler.
 */
public class Method {
	
	public static Plugin plugin;
	public static String NMSVERSION;
	public static Enchantment GLOWING;
	/**
	 * e.g str = test. str2 = te. Matched!
	 * @param str2 The word that is gonna be matched to str
	 * @param str The word that is gonna be matched on str
	 */
	public static boolean matches(String str2, String str) {
		int length = str.length();
		for(int i = 1; i <= length; i++) {
			String substring = str.substring(0, i);
			if(str2.equalsIgnoreCase(substring)) return true;
		}
		return false;
	}
	
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
	
	public static boolean ifWearingAll(ItemStack[] armor, String armorname, String lore1) {
		boolean result = false;
		try {
			ItemStack helm = armor[0];
			ItemStack chest = armor[1];
			ItemStack legs = armor[2];
			ItemStack feet = armor[3];
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
	/**
	 * Get String NBT Tag of an Item
	 * @param item The item
	 * @param path The NBT Tag
	 */
	public static Map.Entry<Object, String> getNBTTag(ItemStack item, String path) throws Exception {
		Object stack = getNMSClass("CraftItemStack", PackageType.CRAFTBUKKIT_INVENTORY).getMethod("asNMSCopy", ItemStack.class).invoke(null, item);
		Object compound = stack.getClass().getMethod("getOrCreateTag").invoke(stack);
		Object nbt = compound.getClass().getMethod("get", String.class).invoke(compound, path);
		String str = (String) nbt.getClass().getMethod("asString").invoke(nbt);
		return new Map.Entry<Object, String>() {

			@Override
			public Object getKey() {
				return nbt;
			}

			@Override
			public String getValue() {
				return str;
			}

			@Override
			public String setValue(String value) {
				return null;
			}
		};
	}
	public static Map.Entry<Object, String> getNBTTag(Object nbtBase, String path) throws Exception {
		Object compound = getNMSClass("NBTTagCompound", PackageType.MINECRAFT_SERVER).cast(nbtBase);
		Object nbt = compound.getClass().getMethod("get", String.class).invoke(compound, path);
		String str = (String) nbt.getClass().getMethod("asString").invoke(nbt);
		return new Map.Entry<Object, String>() {

			@Override
			public Object getKey() {
				return nbt;
			}

			@Override
			public String getValue() {
				return str;
			}

			@Override
			public String setValue(String value) {
				return null;
			}
		};
	}
	
	public static boolean hasNBTTag(ItemStack item, String tag) throws Exception {	
		Object stack = getNMSClass("CraftItemStack", PackageType.CRAFTBUKKIT_INVENTORY).getMethod("asNMSCopy", ItemStack.class).invoke(null, item);
		Object compound = stack.getClass().getMethod("getOrCreateTag").invoke(stack);
		return (boolean) compound.getClass().getMethod("hasKey", String.class).invoke(compound, tag);
	}
	public static boolean hasNBTTag(Object compound, String tag) throws Exception {
		return (boolean) compound.getClass().getMethod("hasKey", String.class).invoke(compound, tag);
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
	
	public static ItemStack[] getArmor(Player p, ArmorEquipEvent e) {
		ItemStack[] armor = {(e.getType() == ArmorType.HELMET ? e.getNewArmorPiece() : p.getInventory().getHelmet()),
				(e.getType() == ArmorType.CHESTPLATE ? e.getNewArmorPiece() : p.getInventory().getChestplate()), 
				(e.getType() == ArmorType.LEGGINGS ? e.getNewArmorPiece() : p.getInventory().getLeggings()),
				(e.getType() == ArmorType.BOOTS ? e.getNewArmorPiece() : p.getInventory().getBoots())};
		return armor;
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
	public static ItemStack getItem(Material material, String name) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack getCommandItem(Material material, String id) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		String materialName = material.name().toLowerCase().replace("_", " ");
		materialName = materialName.substring(0, 1).toUpperCase() + materialName.substring(1);
		materialName += " [ID = " + id + "]";
		
		meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "armorId"), PersistentDataType.STRING, id);
		
		meta.setDisplayName(materialName);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack getCommandItem(ItemStack item, String id) {
		ItemMeta meta = item.getItemMeta();
		String materialName = item.getType().name().toLowerCase().replace("_", " ");
		materialName = materialName.substring(0, 1).toUpperCase() + materialName.substring(1);
		materialName += " [ID = " + id + "]";
		
		meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "armorId"), PersistentDataType.STRING, id);
		
		meta.setDisplayName(materialName);
		item.setItemMeta(meta);
		return item;
	}/*
	public static List<PotionEffect> getPotionEffects(PotionEffect... potionEffects) {
		return Arrays.asList(potionEffects);
	}
	public static ItemStack getPotion(PotionType type, Color color, ArrayList<PotionEffect> effects, boolean showEffects) {
		ItemStack potion = type == PotionType.WATER ? new ItemStack(Material.POTION) : (type == PotionType.AWKWARD ? new ItemStack(Material.POTION) : (type == PotionType.LINGERING ? new ItemStack(Material.LINGERING_POTION) : (type == PotionType.SPLASH ? new ItemStack(Material.SPLASH_POTION) : new ItemStack(Material.GLASS_BOTTLE))));
		if(type == PotionType.EMPTY) return potion;
		PotionMeta meta = (PotionMeta) potion.getItemMeta();
		if(color != null) meta.setColor(color);
		if(effects != null) for(PotionEffect potionEffect : effects) {meta.addCustomEffect(potionEffect, true);}
		if(!showEffects) meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		potion.setItemMeta(meta);
		return potion;
	}*/
	
	public static Class<?> getNMSClass(String name, PackageType packageType) throws ClassNotFoundException {
		return Class.forName((packageType == PackageType.MINECRAFT_SERVER ? "net.minecraft.server." : "org.bukkit.craftbukkit.") + NMSVERSION + (packageType == PackageType.CRAFTBUKKIT_INVENTORY ? ".inventory" : (packageType == PackageType.CRAFTBUKKIT_ENTITY ? ".entity" : "")) + "." + name);
	}
	
	public static void sendActionBar(Player p, String jsonMessage) throws Exception {
		
		Object packet = getNMSClass("PacketPlayOutTitle", PackageType.MINECRAFT_SERVER).getConstructor(getNMSClass("PacketPlayOutTitle", PackageType.MINECRAFT_SERVER).getDeclaredClasses()[0], getNMSClass("IChatBaseComponent", PackageType.MINECRAFT_SERVER)).newInstance(getNMSClass("PacketPlayOutTitle", PackageType.MINECRAFT_SERVER).getDeclaredClasses()[0].getField("ACTIONBAR").get(null), getNMSClass("IChatBaseComponent", PackageType.MINECRAFT_SERVER).getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, jsonMessage));
		Object handle = p.getClass().getMethod("getHandle").invoke(p);
		Object connection = handle.getClass().getField("playerConnection").get(handle);
		connection.getClass().getMethod("sendPacket", getNMSClass("Packet", PackageType.MINECRAFT_SERVER)).invoke(connection, packet);
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
	
	public static LinkedHashMap<String, String> getArmorIdtoName() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		// id, name
		map.put("DIRT", "Dirt");
		map.put("CRAFTING_TABLE", "Crafting");
		map.put("GLASS", "Glass");
		map.put("FURNACE", "Furnace");
		map.put("TNT", "TNT");
		map.put("NOTE_BLOCK", "Note");
		map.put("PUMPKIN", "Pumpkin");
		map.put("MELON", "Melon");
		map.put("SPONGE", "Sponge");
		map.put("DISPENSER", "Dispenser");
		map.put("PRISMARINE", "Prismarine");
		map.put("LAPIS", "Lapis");
		map.put("CACTUS", "Cactus");
		map.put("LEAVES", "Leaves");
		map.put("SUGAR_CANE", "Sugar Cane");
		map.put("STICKY_PISTON", "Sticky Piston");
		map.put("SAND", "Sand");
		map.put("QUARTZ", "Quartz");
		map.put("OBSIDIAN", "Obsidian");
		map.put("EMERALD", "Emerald");
		map.put("PISTON", "Piston");
		map.put("WET_SPONGE", "Wet Sponge");
		map.put("MAGMA", "Magma");
		map.put("NETHERRACK", "Netherrack");
		map.put("ENDER", "Ender");
		map.put("BRICKS", "Brick");
		map.put("NETHER_BRICKS", "Nether Brick");
		map.put("RED_NETHER_BRICKS", "Red Nether Brick");
		map.put("SLIME", "Slime");
		map.put("END_STONE", "End Stone");
		map.put("ICE", "Ice");
		map.put("BONE", "Bone");
		map.put("SOUL_SAND", "Soul Sand");
		map.put("SNOW", "Snow");
		map.put("WITCH", "Witch");
		return map;
	}
	public static LinkedHashMap<String, String> getCustomItems() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("WITCHPOTION", "Witch Potion");
		return map;
	}
	public static List<String> armorEquipBlockedList() {
	    List<String> list = new ArrayList<String>();
	    list.add("FURNACE");
	    list.add("CHEST");
	    list.add("TRAPPED_CHEST");
	    list.add("BEACON");
	    list.add("DISPENSER");
	    list.add("DROPPER");
	    list.add("HOPPER");
	    list.add("WORKBENCH");
	    list.add("ENCHANTMENT_TABLE");
	    list.add("ENDER_CHEST");
	    list.add("ANVIL");
	    list.add("BED_BLOCK");
	    list.add("FENCE_GATE");
	    list.add("SPRUCE_FENCE_GATE");
	    list.add("BIRCH_FENCE_GATE");
	    list.add("ACACIA_FENCE_GATE");
	    list.add("JUNGLE_FENCE_GATE");
	    list.add("DARK_OAK_FENCE_GATE");
	    list.add("IRON_DOOR_BLOCK");
	    list.add("WOODEN_DOOR");
	    list.add("SPRUCE_DOOR");
	    list.add("BIRCH_DOOR");
	    list.add("JUNGLE_DOOR");
	    list.add("ACACIA_DOOR");
	    list.add("DARK_OAK_DOOR");
	    list.add("WOOD_BUTTON");
	    list.add("STONE_BUTTON");
	    list.add("TRAP_DOOR");
	    list.add("IRON_TRAPDOOR");
	    list.add("DIODE_BLOCK_OFF");
	    list.add("DIODE_BLOCK_ON");
	    list.add("REDSTONE_COMPARATOR_OFF");
	    list.add("REDSTONE_COMPARATOR_ON");
	    list.add("FENCE");
	    list.add("SPRUCE_FENCE");
	    list.add("BIRCH_FENCE");
	    list.add("JUNGLE_FENCE");
	    list.add("DARK_OAK_FENCE");
	    list.add("ACACIA_FENCE");
	    list.add("NETHER_FENCE");
	    list.add("BREWING_STAND");
	    list.add("CAULDRON");
	    list.add("LEGACY_SIGN_POST");
	    list.add("LEGACY_WALL_SIGN");
	    list.add("LEGACY_SIGN");
	    list.add("ACACIA_SIGN");
	    list.add("ACACIA_WALL_SIGN");
	    list.add("BIRCH_SIGN");
	    list.add("BIRCH_WALL_SIGN");
	    list.add("DARK_OAK_SIGN");
	    list.add("DARK_OAK_WALL_SIGN");
	    list.add("JUNGLE_SIGN");
	    list.add("JUNGLE_WALL_SIGN");
	    list.add("OAK_SIGN");
	    list.add("OAK_WALL_SIGN");
	    list.add("SPRUCE_SIGN");
	    list.add("SPRUCE_WALL_SIGN");
	    list.add("LEVER");
	    list.add("BLACK_SHULKER_BOX");
	    list.add("BLUE_SHULKER_BOX");
	    list.add("BROWN_SHULKER_BOX");
	    list.add("CYAN_SHULKER_BOX");
	    list.add("GRAY_SHULKER_BOX");
	    list.add("GREEN_SHULKER_BOX");
	    list.add("LIGHT_BLUE_SHULKER_BOX");
	    list.add("LIME_SHULKER_BOX");
	    list.add("MAGENTA_SHULKER_BOX");
	    list.add("ORANGE_SHULKER_BOX");
	    list.add("PINK_SHULKER_BOX");
	    list.add("PURPLE_SHULKER_BOX");
	    list.add("RED_SHULKER_BOX");
	    list.add("SILVER_SHULKER_BOX");
	    list.add("WHITE_SHULKER_BOX");
	    list.add("YELLOW_SHULKER_BOX");
	    list.add("DAYLIGHT_DETECTOR_INVERTED");
	    list.add("DAYLIGHT_DETECTOR");
	    list.add("BARREL");
	    list.add("BLAST_FURNACE");
	    list.add("SMOKER");
	    list.add("CARTOGRAPHY_TABLE");
	    list.add("COMPOSTER");
	    list.add("GRINDSTONE");
	    list.add("LECTERN");
	    list.add("LOOM");
	    list.add("STONECUTTER");
	    list.add("BELL");
	    return list;
	}
	public enum InventorySlotType {
		INVENTORY, HELMET, CHESTPLATE, LEGGINGS, BOOTS 
	}
	public enum PackageType {
		MINECRAFT_SERVER, CRAFTBUKKIT, CRAFTBUKKIT_INVENTORY, CRAFTBUKKIT_ENTITY
	}
	public enum PotionType {
		EMPTY, WATER, AWKWARD, SPLASH, LINGERING
	}
}
