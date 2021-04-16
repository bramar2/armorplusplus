package me.marvel.armorplusplus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Contains all armor recipes.
 * <p>
 * Required for all armor to be crafted.
 */
public class ArmorRecipe {
	
	ArmorPlusPlus plugin;
	org.bukkit.Server server;
	public ArmorRecipe(ArmorPlusPlus ArmorPlusPlus) {
		plugin = ArmorPlusPlus;
		server = plugin.getServer();
	}
	private ItemStack witchPotion;
	public ArrayList<ShapedRecipe> activeRecipes = new ArrayList<ShapedRecipe>();
	public void craftingArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("CRAFTING_TABLE")) return;
			}
		}catch(Exception ignored) {}
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta m1 = armor1.getItemMeta();
		ItemMeta m2 = armor2.getItemMeta();
		ItemMeta m3 = armor3.getItemMeta();
		ItemMeta m4 = armor4.getItemMeta();
		m1.setDisplayName("Crafting Helmet");
		m2.setDisplayName("Crafting Chestplate");
		m3.setDisplayName("Crafting Leggings");
		m4.setDisplayName("Crafting Boots");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Crafter - Opens a crafting table");
		lore.add(ChatColor.GOLD + "Crafter - when sneaking");
		lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);
		
		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);
		
		
		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "crafting_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "crafting_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "crafting_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "crafting_boots"), armor4);
		
		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");
		
		recipe1.setIngredient('*', Material.CRAFTING_TABLE);
		recipe2.setIngredient('*', Material.CRAFTING_TABLE);
		recipe3.setIngredient('*', Material.CRAFTING_TABLE);
		recipe4.setIngredient('*', Material.CRAFTING_TABLE);
		
		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	
	public void dirtArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("DIRT")) return;
			}
		}catch(Exception ignored) {}		
		// Item and the meta
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta m1 = armor1.getItemMeta();
		ItemMeta m2 = armor2.getItemMeta();
		ItemMeta m3 = armor3.getItemMeta();
		ItemMeta m4 = armor4.getItemMeta();
		m1.setDisplayName("Dirt Helmet");
		m2.setDisplayName("Dirt Chestplate");
		m3.setDisplayName("Dirt Leggings");
		m4.setDisplayName("Dirt Boots");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "Regrowth - Repair durability slowly");
		lore.add(ChatColor.GREEN + "Regrowth - but surely! (1 / 2.5 seconds)");
		lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);
		
		
		// Less armor for dirt!
		m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		
		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);

		
		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "dirt_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "dirt_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "dirt_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "dirt_boots"), armor4);
		
		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");
		
		recipe1.setIngredient('*', Material.DIRT);
		recipe2.setIngredient('*', Material.DIRT);
		recipe3.setIngredient('*', Material.DIRT);
		recipe4.setIngredient('*', Material.DIRT);
		
		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}

	public void glassArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("GLASS")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemStack armor2 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.CHAINMAIL_BOOTS);
		
		ItemMeta m1 = armor1.getItemMeta();
		ItemMeta m2 = armor2.getItemMeta();
		ItemMeta m3 = armor3.getItemMeta();
		ItemMeta m4 = armor4.getItemMeta();
		
		m1.setDisplayName("Glass Helmet");
		m2.setDisplayName("Glass Chestplate");
		m3.setDisplayName("Glass Leggings");
		m4.setDisplayName("Glass Boots");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Invisibility - Provides Invisibility");
		lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);
		
		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);
		
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "glass_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "glass_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "glass_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "glass_boots"), armor4);
		
		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");
		
		recipe1.setIngredient('*', Material.GLASS);
		recipe2.setIngredient('*', Material.GLASS);
		recipe3.setIngredient('*', Material.GLASS);
		recipe4.setIngredient('*', Material.GLASS);
		
		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}

	public void furnaceArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("FURNACE")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		
		m1.setColor(Color.fromRGB(64, 64, 64));m2.setColor(Color.fromRGB(64, 64, 64));m3.setColor(Color.fromRGB(64, 64, 64));m4.setColor(Color.fromRGB(64, 64, 64));
		m1.setDisplayName("Furnace Helmet");
		m2.setDisplayName("Furnace Chestplate");
		m3.setDisplayName("Furnace Leggings");
		m4.setDisplayName("Furnace Boots");
		
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.DARK_RED + "AutoSmelt - Smelts nearest dropped items");
		lore.add(ChatColor.DARK_RED + "AutoSmelt - that is unsmelted");
		lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		
		// Additional more armor
		
		m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		
		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		
		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);
		
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "furnace_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "furnace_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "furnace_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "furnace_boots"), armor4);
		
		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");
		
		recipe1.setIngredient('*', Material.FURNACE);
		recipe2.setIngredient('*', Material.FURNACE);
		recipe3.setIngredient('*', Material.FURNACE);
		recipe4.setIngredient('*', Material.FURNACE);
		
		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	
	public void tntArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("TNT")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		
		m1.setColor(Color.fromRGB(150, 0, 0));m2.setColor(Color.fromRGB(150, 0, 0));m3.setColor(Color.fromRGB(150, 0, 0));m4.setColor(Color.fromRGB(150, 0, 0));
		m1.setDisplayName("TNT Helmet");
		m2.setDisplayName("TNT Chestplate");
		m3.setDisplayName("TNT Leggings");
		m4.setDisplayName("TNT Boots");
		
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED + "Explosive - Explodes when sneaking");
		lore.add(ChatColor.RED + "Explosive - with 1 second cooldown");
		lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);
		
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "tnt_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "tnt_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "tnt_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "tnt_boots"), armor4);
		
		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");
		
		recipe1.setIngredient('*', Material.TNT);
		recipe2.setIngredient('*', Material.TNT);
		recipe3.setIngredient('*', Material.TNT);
		recipe4.setIngredient('*', Material.TNT);
		
		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}

	public void noteArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("NOTE_BLOCK")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Note Helmet");
		m2.setDisplayName("Note Chestplate");
		m3.setDisplayName("Note Leggings");
		m4.setDisplayName("Note Boots");
		
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.LIGHT_PURPLE + "Musical - Every step you take becomes a musical melody");
		lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);
		
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "note_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "note_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "note_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "note_boots"), armor4);
		
		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");
		
		recipe1.setIngredient('*', Material.NOTE_BLOCK);
		recipe2.setIngredient('*', Material.NOTE_BLOCK);
		recipe3.setIngredient('*', Material.NOTE_BLOCK);
		recipe4.setIngredient('*', Material.NOTE_BLOCK);
		
		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	
	public void pumpkinArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("PUMPKIN")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setColor(Color.fromRGB(229, 174, 45));m2.setColor(Color.fromRGB(229, 174, 45));m3.setColor(Color.fromRGB(229, 174, 45));m4.setColor(Color.fromRGB(229, 174, 45));
		m1.setDisplayName("Pumpkin Helmet");
		m2.setDisplayName("Pumpkin Chestplate");
		m3.setDisplayName("Pumpkin Leggings");
		m4.setDisplayName("Pumpkin Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.RED + "Feeder - Automatically feeds the wearer");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "pumpkin_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "pumpkin_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "pumpkin_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "pumpkin_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.CARVED_PUMPKIN);
		recipe2.setIngredient('*', Material.CARVED_PUMPKIN);
		recipe3.setIngredient('*', Material.CARVED_PUMPKIN);
		recipe4.setIngredient('*', Material.CARVED_PUMPKIN);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void melonArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("MELON")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Melon Helmet");
		m2.setDisplayName("Melon Chestplate");
		m3.setDisplayName("Melon Leggings");
		m4.setDisplayName("Melon Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.RED + "Feeder - Automatically feeds the wearer");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(154, 255, 51));m2.setColor(Color.fromRGB(154, 255, 51));m3.setColor(Color.fromRGB(154, 255, 51));m4.setColor(Color.fromRGB(154, 255, 51));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "melon_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "melon_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "melon_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "melon_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.MELON);
		recipe2.setIngredient('*', Material.MELON);
		recipe3.setIngredient('*', Material.MELON);
		recipe4.setIngredient('*', Material.MELON);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void spongeArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SPONGE")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Sponge Helmet");
		m2.setDisplayName("Sponge Chestplate");
		m3.setDisplayName("Sponge Leggings");
		m4.setDisplayName("Sponge Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "Absorbent - Absorbs nearby liquids");
        lore.add(ChatColor.YELLOW + "Absorbent - when sneaking (6 second cooldown)");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(204, 245, 0));m2.setColor(Color.fromRGB(204, 245, 0));m3.setColor(Color.fromRGB(204, 245, 0));m4.setColor(Color.fromRGB(204, 245, 0));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "sponge_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "sponge_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "sponge_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "sponge_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.SPONGE);
		recipe2.setIngredient('*', Material.SPONGE);
		recipe3.setIngredient('*', Material.SPONGE);
		recipe4.setIngredient('*', Material.SPONGE);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void dispenserArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("DISPENSER")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemStack armor2 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemMeta m1 = armor1.getItemMeta();
		ItemMeta m2 = armor2.getItemMeta();
		ItemMeta m3 = armor3.getItemMeta();
		ItemMeta m4 = armor4.getItemMeta();
		m1.setDisplayName("Dispenser Helmet");
		m2.setDisplayName("Dispenser Chestplate");
		m3.setDisplayName("Dispenser Leggings");
		m4.setDisplayName("Dispenser Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE + "Arrow Defence - Fires arrows outwards");
        lore.add(ChatColor.WHITE + "Arrow Defence - when sneaking");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "dispenser_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "dispenser_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "dispenser_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "dispenser_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.DISPENSER);
		recipe2.setIngredient('*', Material.DISPENSER);
		recipe3.setIngredient('*', Material.DISPENSER);
		recipe4.setIngredient('*', Material.DISPENSER);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void prismarineArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("PRISMARINE")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Prismarine Helmet");
		m2.setDisplayName("Prismarine Chestplate");
		m3.setDisplayName("Prismarine Leggings");
		m4.setDisplayName("Prismarine Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.AQUA + "Diving Suit - Provides Depth Strider, Respiration");
        lore.add(ChatColor.AQUA + "Diving Suit - and Night Vision in water");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(27, 142, 230));m2.setColor(Color.fromRGB(27, 142, 230));m3.setColor(Color.fromRGB(27, 142, 230));m4.setColor(Color.fromRGB(27, 142, 230));
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		
		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
        m1.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
		m2.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
		m3.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
		m4.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
        m1.addEnchant(Enchantment.OXYGEN, 1, true);
		m2.addEnchant(Enchantment.OXYGEN, 1, true);
		m3.addEnchant(Enchantment.OXYGEN, 1, true);
		m4.addEnchant(Enchantment.OXYGEN, 1, true);
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "prismarine_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "prismarine_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "prismarine_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "prismarine_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.PRISMARINE);
		recipe2.setIngredient('*', Material.PRISMARINE);
		recipe3.setIngredient('*', Material.PRISMARINE);
		recipe4.setIngredient('*', Material.PRISMARINE);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void enderArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("ENDER")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.IRON_HELMET);
		ItemStack armor2 = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.IRON_BOOTS);
		ItemMeta m1 = armor1.getItemMeta();
		ItemMeta m2 = armor2.getItemMeta();
		ItemMeta m3 = armor3.getItemMeta();
		ItemMeta m4 = armor4.getItemMeta();
		m1.setDisplayName("Ender Helmet");
		m2.setDisplayName("Ender Chestplate");
		m3.setDisplayName("Ender Leggings");
		m4.setDisplayName("Ender Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_PURPLE + "Ender Hoarder - Provides access to your ender chest");
        lore.add(ChatColor.DARK_PURPLE + "Ender Hoarder - when sneaking");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "ender_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "ender_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "ender_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "ender_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.ENDER_CHEST);
		recipe2.setIngredient('*', Material.ENDER_CHEST);
		recipe3.setIngredient('*', Material.ENDER_CHEST);
		recipe4.setIngredient('*', Material.ENDER_CHEST);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void lapisArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("LAPIS")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Lapis Helmet");
		m2.setDisplayName("Lapis Chestplate");
		m3.setDisplayName("Lapis Leggings");
		m4.setDisplayName("Lapis Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Experience Giving - Gives experience over time");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(0, 0, 150));m2.setColor(Color.fromRGB(0, 0, 150));m3.setColor(Color.fromRGB(0, 0, 150));m4.setColor(Color.fromRGB(0, 0, 150));
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		
		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "lapis_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "lapis_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "lapis_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "lapis_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.LAPIS_BLOCK);
		recipe2.setIngredient('*', Material.LAPIS_BLOCK);
		recipe3.setIngredient('*', Material.LAPIS_BLOCK);
		recipe4.setIngredient('*', Material.LAPIS_BLOCK);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void cactusArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("CACTUS")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Cactus Helmet");
		m2.setDisplayName("Cactus Chestplate");
		m3.setDisplayName("Cactus Leggings");
		m4.setDisplayName("Cactus Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Prickly - Pricks colliding enemies and");
        lore.add(ChatColor.GREEN + "Prickly - provides Thorns");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(53, 203, 78));m2.setColor(Color.fromRGB(53, 203, 78));m3.setColor(Color.fromRGB(53, 203, 78));m4.setColor(Color.fromRGB(53, 203, 78));
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		
		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
        m1.addEnchant(Enchantment.THORNS, 1, true);
		m2.addEnchant(Enchantment.THORNS, 1, true);
		m3.addEnchant(Enchantment.THORNS, 1, true);
		m4.addEnchant(Enchantment.THORNS, 1, true);
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "cactus_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "cactus_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "cactus_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "cactus_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.CACTUS);
		recipe2.setIngredient('*', Material.CACTUS);
		recipe3.setIngredient('*', Material.CACTUS);
		recipe4.setIngredient('*', Material.CACTUS);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void leavesArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("LEAVES")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Leaves Helmet");
		m2.setDisplayName("Leaves Chestplate");
		m3.setDisplayName("Leaves Leggings");
		m4.setDisplayName("Leaves Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Lightweight - Fall slowly, like a leaf");
        lore.add(ChatColor.GREEN + "Lightweight - in the wind");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(0, 100, 0));m2.setColor(Color.fromRGB(0, 100, 0));m3.setColor(Color.fromRGB(0, 100, 0));m4.setColor(Color.fromRGB(0, 100, 0));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "oak_leaves_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "oak_leaves_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "oak_leaves_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "oak_leaves_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.OAK_LEAVES);
		recipe2.setIngredient('*', Material.OAK_LEAVES);
		recipe3.setIngredient('*', Material.OAK_LEAVES);
		recipe4.setIngredient('*', Material.OAK_LEAVES);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		
		ShapedRecipe recipe1a = new ShapedRecipe(new NamespacedKey(plugin, "acacia_leaves_helmet"), armor1);
		ShapedRecipe recipe2a = new ShapedRecipe(new NamespacedKey(plugin, "acacia_leaves_chestplate"), armor2);
		ShapedRecipe recipe3a = new ShapedRecipe(new NamespacedKey(plugin, "acacia_leaves_leggings"), armor3);
		ShapedRecipe recipe4a = new ShapedRecipe(new NamespacedKey(plugin, "acacia_leaves_boots"), armor4);

		recipe1a.shape("***", "* *");
		recipe2a.shape("* *", "***", "***");
		recipe3a.shape("***", "* *", "* *");
		recipe4a.shape("* *", "* *");

		recipe1a.setIngredient('*', Material.ACACIA_LEAVES);
		recipe2a.setIngredient('*', Material.ACACIA_LEAVES);
		recipe3a.setIngredient('*', Material.ACACIA_LEAVES);
		recipe4a.setIngredient('*', Material.ACACIA_LEAVES);
		
		server.addRecipe(recipe1a);
		server.addRecipe(recipe2a);
		server.addRecipe(recipe3a);
		server.addRecipe(recipe4a);
		
		ShapedRecipe recipe1b = new ShapedRecipe(new NamespacedKey(plugin, "birch_leaves_helmet"), armor1);
		ShapedRecipe recipe2b = new ShapedRecipe(new NamespacedKey(plugin, "birch_leaves_chestplate"), armor2);
		ShapedRecipe recipe3b = new ShapedRecipe(new NamespacedKey(plugin, "birch_leaves_leggings"), armor3);
		ShapedRecipe recipe4b = new ShapedRecipe(new NamespacedKey(plugin, "birch_leaves_boots"), armor4);

		recipe1b.shape("***", "* *");
		recipe2b.shape("* *", "***", "***");
		recipe3b.shape("***", "* *", "* *");
		recipe4b.shape("* *", "* *");

		recipe1b.setIngredient('*', Material.BIRCH_LEAVES);
		recipe2b.setIngredient('*', Material.BIRCH_LEAVES);
		recipe3b.setIngredient('*', Material.BIRCH_LEAVES);
		recipe4b.setIngredient('*', Material.BIRCH_LEAVES);
		
		server.addRecipe(recipe1b);
		server.addRecipe(recipe2b);
		server.addRecipe(recipe3b);
		server.addRecipe(recipe4b);
		
		ShapedRecipe recipe1c = new ShapedRecipe(new NamespacedKey(plugin, "dark_oak_leaves_helmet"), armor1);
		ShapedRecipe recipe2c = new ShapedRecipe(new NamespacedKey(plugin, "dark_oak_leaves_chestplate"), armor2);
		ShapedRecipe recipe3c = new ShapedRecipe(new NamespacedKey(plugin, "dark_oak_leaves_leggings"), armor3);
		ShapedRecipe recipe4c = new ShapedRecipe(new NamespacedKey(plugin, "dark_oak_leaves_boots"), armor4);

		recipe1c.shape("***", "* *");
		recipe2c.shape("* *", "***", "***");
		recipe3c.shape("***", "* *", "* *");
		recipe4c.shape("* *", "* *");

		recipe1c.setIngredient('*', Material.DARK_OAK_LEAVES);
		recipe2c.setIngredient('*', Material.DARK_OAK_LEAVES);
		recipe3c.setIngredient('*', Material.DARK_OAK_LEAVES);
		recipe4c.setIngredient('*', Material.DARK_OAK_LEAVES);
		
		server.addRecipe(recipe1c);
		server.addRecipe(recipe2c);
		server.addRecipe(recipe3c);
		server.addRecipe(recipe4c);
		
		ShapedRecipe recipe1d = new ShapedRecipe(new NamespacedKey(plugin, "jungle_leaves_helmet"), armor1);
		ShapedRecipe recipe2d = new ShapedRecipe(new NamespacedKey(plugin, "jungle_leaves_chestplate"), armor2);
		ShapedRecipe recipe3d = new ShapedRecipe(new NamespacedKey(plugin, "jungle_leaves_leggings"), armor3);
		ShapedRecipe recipe4d = new ShapedRecipe(new NamespacedKey(plugin, "jungle_leaves_boots"), armor4);

		recipe1d.shape("***", "* *");
		recipe2d.shape("* *", "***", "***");
		recipe3d.shape("***", "* *", "* *");
		recipe4d.shape("* *", "* *");

		recipe1d.setIngredient('*', Material.JUNGLE_LEAVES);
		recipe2d.setIngredient('*', Material.JUNGLE_LEAVES);
		recipe3d.setIngredient('*', Material.JUNGLE_LEAVES);
		recipe4d.setIngredient('*', Material.JUNGLE_LEAVES);
		
		server.addRecipe(recipe1d);
		server.addRecipe(recipe2d);
		server.addRecipe(recipe3d);
		server.addRecipe(recipe4d);
		
		ShapedRecipe recipe1e = new ShapedRecipe(new NamespacedKey(plugin, "spruce_leaves_helmet"), armor1);
		ShapedRecipe recipe2e = new ShapedRecipe(new NamespacedKey(plugin, "spruce_leaves_chestplate"), armor2);
		ShapedRecipe recipe3e = new ShapedRecipe(new NamespacedKey(plugin, "spruce_leaves_leggings"), armor3);
		ShapedRecipe recipe4e = new ShapedRecipe(new NamespacedKey(plugin, "spruce_leaves_boots"), armor4);

		recipe1e.shape("***", "* *");
		recipe2e.shape("* *", "***", "***");
		recipe3e.shape("***", "* *", "* *");
		recipe4e.shape("* *", "* *");

		recipe1e.setIngredient('*', Material.SPRUCE_LEAVES);
		recipe2e.setIngredient('*', Material.SPRUCE_LEAVES);
		recipe3e.setIngredient('*', Material.SPRUCE_LEAVES);
		recipe4e.setIngredient('*', Material.SPRUCE_LEAVES);
		
		server.addRecipe(recipe1e);
		server.addRecipe(recipe2e);
		server.addRecipe(recipe3e);
		server.addRecipe(recipe4e);
		
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
		
		if(!activeRecipes.contains(recipe1a)) activeRecipes.add(recipe1a);
		if(!activeRecipes.contains(recipe2a)) activeRecipes.add(recipe2a);
		if(!activeRecipes.contains(recipe3a)) activeRecipes.add(recipe3a);
		if(!activeRecipes.contains(recipe4a)) activeRecipes.add(recipe4a);
		
		if(!activeRecipes.contains(recipe1b)) activeRecipes.add(recipe1b);
		if(!activeRecipes.contains(recipe2b)) activeRecipes.add(recipe2b);
		if(!activeRecipes.contains(recipe3b)) activeRecipes.add(recipe3b);
		if(!activeRecipes.contains(recipe4b)) activeRecipes.add(recipe4b);
		
		if(!activeRecipes.contains(recipe1c)) activeRecipes.add(recipe1c);
		if(!activeRecipes.contains(recipe2c)) activeRecipes.add(recipe2c);
		if(!activeRecipes.contains(recipe3c)) activeRecipes.add(recipe3c);
		if(!activeRecipes.contains(recipe4c)) activeRecipes.add(recipe4c);
		
		if(!activeRecipes.contains(recipe1d)) activeRecipes.add(recipe1d);
		if(!activeRecipes.contains(recipe2d)) activeRecipes.add(recipe2d);
		if(!activeRecipes.contains(recipe3d)) activeRecipes.add(recipe3d);
		if(!activeRecipes.contains(recipe4d)) activeRecipes.add(recipe4d);
		
		if(!activeRecipes.contains(recipe1e)) activeRecipes.add(recipe1e);
		if(!activeRecipes.contains(recipe2e)) activeRecipes.add(recipe2e);
		if(!activeRecipes.contains(recipe3e)) activeRecipes.add(recipe3e);
		if(!activeRecipes.contains(recipe4e)) activeRecipes.add(recipe4e);
	}
	public void sugarcaneArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SUGAR_CANE")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Sugar Cane Helmet");
		m2.setDisplayName("Sugar Cane Chestplate");
		m3.setDisplayName("Sugar Cane Leggings");
		m4.setDisplayName("Sugar Cane Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "Speedy - Increases Speed");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(0, 185, 0));m2.setColor(Color.fromRGB(0, 185, 0));m3.setColor(Color.fromRGB(0, 185, 0));m4.setColor(Color.fromRGB(0, 185, 0));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);
		
		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "sugar_cane_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "sugar_cane_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "sugar_cane_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "sugar_cane_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.SUGAR_CANE);
		recipe2.setIngredient('*', Material.SUGAR_CANE);
		recipe3.setIngredient('*', Material.SUGAR_CANE);
		recipe4.setIngredient('*', Material.SUGAR_CANE);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void stickypistonArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("STICKY_PISTON")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Sticky Piston Helmet");
		m2.setDisplayName("Sticky Piston Chestplate");
		m3.setDisplayName("Sticky Piston Leggings");
		m4.setDisplayName("Sticky Piston Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Puller - Pulls in nearby entities");
        lore.add(ChatColor.GRAY + "Puller - when sneaking");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(75, 192, 75));m2.setColor(Color.fromRGB(75, 192, 75));m3.setColor(Color.fromRGB(75, 192, 75));m4.setColor(Color.fromRGB(75, 192, 75));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "sticky_piston_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "sticky_piston_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "sticky_piston_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "sticky_piston_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.STICKY_PISTON);
		recipe2.setIngredient('*', Material.STICKY_PISTON);
		recipe3.setIngredient('*', Material.STICKY_PISTON);
		recipe4.setIngredient('*', Material.STICKY_PISTON);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void sandArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SAND")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta m1 = armor1.getItemMeta();
		ItemMeta m2 = armor2.getItemMeta();
		ItemMeta m3 = armor3.getItemMeta();
		ItemMeta m4 = armor4.getItemMeta();
		m1.setDisplayName("Sand Helmet");
		m2.setDisplayName("Sand Chestplate");
		m3.setDisplayName("Sand Leggings");
		m4.setDisplayName("Sand Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Falling - Falls faster in air and sinks faster in");
        lore.add(ChatColor.GRAY + "Falling - water while sneaking");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "sand_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "sand_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "sand_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "sand_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.SAND);
		recipe2.setIngredient('*', Material.SAND);
		recipe3.setIngredient('*', Material.SAND);
		recipe4.setIngredient('*', Material.SAND);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void quartzArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("QUARTZ")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemStack armor2 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemMeta m1 = armor1.getItemMeta();
		ItemMeta m2 = armor2.getItemMeta();
		ItemMeta m3 = armor3.getItemMeta();
		ItemMeta m4 = armor4.getItemMeta();
		m1.setDisplayName("Quartz Helmet");
		m2.setDisplayName("Quartz Chestplate");
		m3.setDisplayName("Quartz Leggings");
		m4.setDisplayName("Quartz Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE + "Powerful - Increases speed and strength");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "quartz_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "quartz_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "quartz_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "quartz_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.QUARTZ_BLOCK);
		recipe2.setIngredient('*', Material.QUARTZ_BLOCK);
		recipe3.setIngredient('*', Material.QUARTZ_BLOCK);
		recipe4.setIngredient('*', Material.QUARTZ_BLOCK);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void obsidianArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("OBSIDIAN")) return;
			}
		}catch(Exception ignored) {}		
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
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "obsidian_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "obsidian_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "obsidian_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "obsidian_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.OBSIDIAN);
		recipe2.setIngredient('*', Material.OBSIDIAN);
		recipe3.setIngredient('*', Material.OBSIDIAN);
		recipe4.setIngredient('*', Material.OBSIDIAN);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void emeraldArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("EMERALD")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Emerald Helmet");
		m2.setDisplayName("Emerald Chestplate");
		m3.setDisplayName("Emerald Leggings");
		m4.setDisplayName("Emerald Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_GREEN + "Lucky - Greatly increases Fortune,");
        lore.add(ChatColor.DARK_GREEN + "Looting, and Luck");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(101, 233, 139));m2.setColor(Color.fromRGB(101, 233, 139));m3.setColor(Color.fromRGB(101, 233, 139));m4.setColor(Color.fromRGB(101, 233, 139));
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		
		m1.getPersistentDataContainer().set(new NamespacedKey(plugin, "emeraldHelm"), PersistentDataType.BYTE, Byte.parseByte("1"));
		m2.getPersistentDataContainer().set(new NamespacedKey(plugin, "emeraldChest"), PersistentDataType.BYTE, Byte.parseByte("1"));
		m3.getPersistentDataContainer().set(new NamespacedKey(plugin, "emeraldLegs"), PersistentDataType.BYTE, Byte.parseByte("1"));
		m4.getPersistentDataContainer().set(new NamespacedKey(plugin, "emeraldFeet"), PersistentDataType.BYTE, Byte.parseByte("1"));
		
		
		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "emerald_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "emerald_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "emerald_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "emerald_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.EMERALD_BLOCK);
		recipe2.setIngredient('*', Material.EMERALD_BLOCK);
		recipe3.setIngredient('*', Material.EMERALD_BLOCK);
		recipe4.setIngredient('*', Material.EMERALD_BLOCK);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void pistonArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("PISTON")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta m1 = armor1.getItemMeta();
		ItemMeta m2 = armor2.getItemMeta();
		ItemMeta m3 = armor3.getItemMeta();
		ItemMeta m4 = armor4.getItemMeta();
		m1.setDisplayName("Piston Helmet");
		m2.setDisplayName("Piston Chestplate");
		m3.setDisplayName("Piston Leggings");
		m4.setDisplayName("Piston Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Pusher - Pushes nearby entities");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "piston_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "piston_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "piston_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "piston_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.PISTON);
		recipe2.setIngredient('*', Material.PISTON);
		recipe3.setIngredient('*', Material.PISTON);
		recipe4.setIngredient('*', Material.PISTON);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	
	public void wetspongeArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("WET_SPONGE")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Wet Sponge Helmet");
		m2.setDisplayName("Wet Sponge Chestplate");
		m3.setDisplayName("Wet Sponge Leggings");
		m4.setDisplayName("Wet Sponge Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "Absorbent - Absorbs nearby liquids");
        lore.add(ChatColor.YELLOW + "Absorbent - when sneaking (6 second cooldown)");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(204, 245, 0));m2.setColor(Color.fromRGB(204, 245, 0));m3.setColor(Color.fromRGB(204, 245, 0));m4.setColor(Color.fromRGB(204, 245, 0));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "wet_sponge_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "wet_sponge_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "wet_sponge_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "wet_sponge_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.WET_SPONGE);
		recipe2.setIngredient('*', Material.WET_SPONGE);
		recipe3.setIngredient('*', Material.WET_SPONGE);
		recipe4.setIngredient('*', Material.WET_SPONGE);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void magmaArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("MAGMA")) return;
			}
		}catch(Exception ignored) {}		
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
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "magma_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "magma_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "magma_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "magma_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.MAGMA_BLOCK);
		recipe2.setIngredient('*', Material.MAGMA_BLOCK);
		recipe3.setIngredient('*', Material.MAGMA_BLOCK);
		recipe4.setIngredient('*', Material.MAGMA_BLOCK);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	
	public void netherrackArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("NETHERRACK")) return;
			}
		}catch(Exception ignored) {}		
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Netherrack Helmet");
		m2.setDisplayName("Netherrack Chestplate");
		m3.setDisplayName("Netherrack Leggings");
		m4.setDisplayName("Netherrack Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.RED + "Fiery - Ignites enemies after attacking or being attacked");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(160, 66, 66));m2.setColor(Color.fromRGB(160, 66, 66));m3.setColor(Color.fromRGB(160, 66, 66));m4.setColor(Color.fromRGB(160, 66, 66));
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "netherrack_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "netherrack_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "netherrack_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "netherrack_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.NETHERRACK);
		recipe2.setIngredient('*', Material.NETHERRACK);
		recipe3.setIngredient('*', Material.NETHERRACK);
		recipe4.setIngredient('*', Material.NETHERRACK);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	
	public void brickArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("BRICKS")) return;
			}
		}catch(Exception ignored) {}		
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
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "brick_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "brick_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "brick_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "brick_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.BRICKS);
		recipe2.setIngredient('*', Material.BRICKS);
		recipe3.setIngredient('*', Material.BRICKS);
		recipe4.setIngredient('*', Material.BRICKS);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void netherBrickArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("NETHER_BRICKS")) return;
			}
		}catch(Exception ignored) {}		
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
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "nether_brick_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "nether_brick_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "nether_brick_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "nether_brick_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.NETHER_BRICKS);
		recipe2.setIngredient('*', Material.NETHER_BRICKS);
		recipe3.setIngredient('*', Material.NETHER_BRICKS);
		recipe4.setIngredient('*', Material.NETHER_BRICKS);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void redNetherBrickArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("RED_NETHER_BRICKS")) return;
			}
		}catch(Exception ignored) {}	
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
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "red_nether_brick_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "red_nether_brick_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "red_nether_brick_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "red_nether_brick_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.RED_NETHER_BRICKS);
		recipe2.setIngredient('*', Material.RED_NETHER_BRICKS);
		recipe3.setIngredient('*', Material.RED_NETHER_BRICKS);
		recipe4.setIngredient('*', Material.RED_NETHER_BRICKS);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void slimeArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SLIME")) return;
			}
		}catch(Exception ignored) {}	
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Slime Helmet");
		m2.setDisplayName("Slime Chestplate");
		m3.setDisplayName("Slime Leggings");
		m4.setDisplayName("Slime Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Slimey - Bounces off floors");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(115, 188, 98));m2.setColor(Color.fromRGB(115, 188, 98));m3.setColor(Color.fromRGB(115, 188, 98));m4.setColor(Color.fromRGB(115, 188, 98));
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "slime_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "slime_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "slime_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "slime_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.SLIME_BLOCK);
		recipe2.setIngredient('*', Material.SLIME_BLOCK);
		recipe3.setIngredient('*', Material.SLIME_BLOCK);
		recipe4.setIngredient('*', Material.SLIME_BLOCK);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void endstoneArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("END_STONE")) return;
			}
		}catch(Exception ignored) {}	
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("End Stone Helmet");
		m2.setDisplayName("End Stone Chestplate");
		m3.setDisplayName("End Stone Leggings");
		m4.setDisplayName("End Stone Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_PURPLE + "Ender - Teleports in the direction you're looking at");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(216, 217, 156));m2.setColor(Color.fromRGB(216, 217, 156));m3.setColor(Color.fromRGB(216, 217, 156));m4.setColor(Color.fromRGB(216, 217, 156));
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "end_stone_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "end_stone_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "end_stone_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "end_stone_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.END_STONE);
		recipe2.setIngredient('*', Material.END_STONE);
		recipe3.setIngredient('*', Material.END_STONE);
		recipe4.setIngredient('*', Material.END_STONE);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void iceArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("ICE")) return;
			}
		}catch(Exception ignored) {}	
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
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "ice_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "ice_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "ice_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "ice_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.ICE);
		recipe2.setIngredient('*', Material.ICE);
		recipe3.setIngredient('*', Material.ICE);
		recipe4.setIngredient('*', Material.ICE);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	
	public void boneArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("BONE")) return;
			}
		}catch(Exception ignored) {}	
		if(!plugin.ver16) return;
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Bone Helmet");
		m2.setDisplayName("Bone Chestplate");
		m3.setDisplayName("Bone Leggings");
		m4.setDisplayName("Bone Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE + "Bonemealer - Applies bone meal to nearby blocks.");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(219, 214, 191));m2.setColor(Color.fromRGB(219, 214, 191));m3.setColor(Color.fromRGB(219, 214, 191));m4.setColor(Color.fromRGB(219, 214, 191));
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "bone_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "bone_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "bone_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "bone_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.BONE_BLOCK);
		recipe2.setIngredient('*', Material.BONE_BLOCK);
		recipe3.setIngredient('*', Material.BONE_BLOCK);
		recipe4.setIngredient('*', Material.BONE_BLOCK);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void soulsandArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SOUL_SAND")) return;
			}
		}catch(Exception ignored) {}	
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Soul Sand Helmet");
		m2.setDisplayName("Soul Sand Chestplate");
		m3.setDisplayName("Soul Sand Leggings");
		m4.setDisplayName("Soul Sand Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Slow Motion - Live life in the slow lane");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(110, 89, 76));m2.setColor(Color.fromRGB(110, 89, 76));m3.setColor(Color.fromRGB(110, 89, 76));m4.setColor(Color.fromRGB(110, 89, 76));
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);

		m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "soul_sand_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "soul_sand_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "soul_sand_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "soul_sand_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.SOUL_SAND);
		recipe2.setIngredient('*', Material.SOUL_SAND);
		recipe3.setIngredient('*', Material.SOUL_SAND);
		recipe4.setIngredient('*', Material.SOUL_SAND);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void snowArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("SNOW")) return;
			}
		}catch(Exception ignored) {}	
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
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "snow_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "snow_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "snow_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "snow_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', Material.SNOW_BLOCK);
		recipe2.setIngredient('*', Material.SNOW_BLOCK);
		recipe3.setIngredient('*', Material.SNOW_BLOCK);
		recipe4.setIngredient('*', Material.SNOW_BLOCK);

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	public void witchPotion() {
		try {
			for(String disabledItemName : plugin.disabledItems) {
				if(disabledItemName.equalsIgnoreCase("WITCHPOTION")) return;
			}
		}catch(Exception ignored) {}
		ItemStack potion = new ItemStack(Material.POTION);
		PotionMeta meta = (PotionMeta) potion.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Witch Potion");
		meta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + "\"Some days, you have to put on the potion", ChatColor.DARK_PURPLE + "to remind them who they are dealing with\"", ChatColor.DARK_PURPLE + "- Witch"));
		meta.setColor(Color.PURPLE);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 15 * 20, 1, true, true), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 7 * 20, 1, true, true), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.ABSORPTION, 60 * 20, 0, true, true), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 0, true, true), true);
		meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "witch_potion"), PersistentDataType.BYTE, (byte) 1);
		if(plugin.getConfig().getBoolean("glowing-armor")) potion.addUnsafeEnchantment(Method.GLOWING, 0);
		potion.setItemMeta(meta);
		this.witchPotion = potion;
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "witch_potion"), potion);
		recipe.shape("GGG", "GBG", "GGG");
		recipe.setIngredient('G', Material.GLOWSTONE);
		recipe.setIngredient('B', Material.POTION);
		if(server.getRecipesFor(recipe.getResult()).size() == 0) server.addRecipe(recipe);
		if(!activeRecipes.contains(recipe)) activeRecipes.add(recipe);
	}
	public ItemStack getWitchPotion() {
		return witchPotion;
	}
	@SuppressWarnings("deprecation")
	public void witchArmor() {
		try {
			for(String disabledArmorName : plugin.disabledArmors) {
				if(disabledArmorName.equalsIgnoreCase("WITCH")) return;
			}
		}catch(Exception ignored) {}
		ItemStack armor1 = new ItemStack(Material.LEATHER_HELMET);
		ItemStack armor2 = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack armor3 = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack armor4 = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta m1 = (LeatherArmorMeta) armor1.getItemMeta();
		LeatherArmorMeta m2 = (LeatherArmorMeta) armor2.getItemMeta();
		LeatherArmorMeta m3 = (LeatherArmorMeta) armor3.getItemMeta();
		LeatherArmorMeta m4 = (LeatherArmorMeta) armor4.getItemMeta();
		m1.setDisplayName("Witch Helmet");
		m2.setDisplayName("Witch Chestplate");
		m3.setDisplayName("Witch Leggings");
		m4.setDisplayName("Witch Boots");
		ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_PURPLE + "Witch - Makes your effects last double as much");
        lore.add(ChatColor.GOLD + "(4 pieces must be worn for abilities to work)");
        m1.setColor(Color.fromRGB(125, 49, 120));m2.setColor(Color.fromRGB(125, 49, 120));m3.setColor(Color.fromRGB(125, 49, 120));m4.setColor(Color.fromRGB(125, 49, 120));
        m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		m1.setLore(lore);
		m2.setLore(lore);
		m3.setLore(lore);
		m4.setLore(lore);
		if(plugin.getConfig().getBoolean("glowing-armor")) m1.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m2.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m3.addEnchant(Method.GLOWING, 0, true);
		if(plugin.getConfig().getBoolean("glowing-armor")) m4.addEnchant(Method.GLOWING, 0, true);

		armor1.setItemMeta(m1);
		armor2.setItemMeta(m2);
		armor3.setItemMeta(m3);
		armor4.setItemMeta(m4);


		// Shaped Recipe
		ShapedRecipe recipe1 = new ShapedRecipe(new NamespacedKey(plugin, "witch_helmet"), armor1);
		ShapedRecipe recipe2 = new ShapedRecipe(new NamespacedKey(plugin, "witch_chestplate"), armor2);
		ShapedRecipe recipe3 = new ShapedRecipe(new NamespacedKey(plugin, "witch_leggings"), armor3);
		ShapedRecipe recipe4 = new ShapedRecipe(new NamespacedKey(plugin, "witch_boots"), armor4);

		recipe1.shape("***", "* *");
		recipe2.shape("* *", "***", "***");
		recipe3.shape("***", "* *", "* *");
		recipe4.shape("* *", "* *");

		recipe1.setIngredient('*', new RecipeChoice.ExactChoice(this.witchPotion));
		recipe2.setIngredient('*', new RecipeChoice.ExactChoice(this.witchPotion));
		recipe3.setIngredient('*', new RecipeChoice.ExactChoice(this.witchPotion));
		recipe4.setIngredient('*', new RecipeChoice.ExactChoice(this.witchPotion));

		server.addRecipe(recipe1);
		server.addRecipe(recipe2);
		server.addRecipe(recipe3);
		server.addRecipe(recipe4);
		if(!activeRecipes.contains(recipe1)) activeRecipes.add(recipe1);
		if(!activeRecipes.contains(recipe2)) activeRecipes.add(recipe2);
		if(!activeRecipes.contains(recipe3)) activeRecipes.add(recipe3);
		if(!activeRecipes.contains(recipe4)) activeRecipes.add(recipe4);
	}
	
}

