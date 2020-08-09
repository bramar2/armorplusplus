package me.Marvel.ArmorPlusPlus;

import java.util.ArrayList;
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
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

public class ArmorRecipe {
	
	ArmorPlusPlus plugin;
	org.bukkit.Server server;
	public ArmorRecipe(ArmorPlusPlus ArmorPlusPlus) {
		plugin = ArmorPlusPlus;
		server = plugin.getServer();
	}

	public void abilitiesLore() {
		ArrayList<String> lore = new ArrayList<String>();
		// Regrowth (Just paste)
		lore.add(ChatColor.GREEN + "Regrowth - Repair durability slowly");
		lore.add(ChatColor.GREEN + "Regrowth - but surely!");
		
	}
	
	public void craftArmor() {
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
		
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		
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
	}
	
	public void dirtArmor() {
		
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
		
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		
		
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
		
	}

	public void glassArmor() {
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
		
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		
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
	}

	public void furnaceArmor() {
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
		
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		
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
	}
	
	public void tntArmor() {
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
		
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		
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
	}

	public void noteArmor() {
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
		
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		
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
	}
	
	public void pumpkinArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void melonArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void spongeArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void dispenserArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void prismarineArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void enderArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void lapisArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void cactusArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void leavesArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void sugarcaneArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		
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
	}
	public void stickypistonArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void sandArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void quartzArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
	public void obsidianArmor() {
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
	}
	public void emeraldArmor() {
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
		m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
		m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);

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
	}
}
