package me.marvel.armorplusplus.commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ArmorPlusPlus implements CommandExecutor, Listener {	
	
	Plugin plugin;
	me.marvel.armorplusplus.ArmorPlusPlus main;
	
	public ArmorPlusPlus(Plugin plugin, me.marvel.armorplusplus.ArmorPlusPlus main) {
		this.plugin = plugin;
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("armorplusplus.cheat")) {
				if(args.length == 0) {
					Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Choose!");
					ArrayList<ItemStack> content = new ArrayList<ItemStack>();
					for(int i = 0; i < 12; i++) {
						content.add(new ItemStack(Material.AIR));
					}
					ItemStack item1 = new ItemStack(Material.LEATHER_CHESTPLATE);
					ItemMeta meta = item1.getItemMeta();
					meta.addEnchant(Enchantment.DAMAGE_ALL, 0, true);
					meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					meta.setDisplayName("Armor++'s armor");
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(ChatColor.WHITE + "Click here to check all of");
					lore.add(ChatColor.WHITE + "Armor++'s armor!");
					meta.setLore(lore);
					item1.setItemMeta(meta);
					content.add(item1);
					content.add(new ItemStack(Material.AIR));
					ItemStack item2 = new ItemStack(Material.COMPASS);
					meta = item2.getItemMeta();
					meta.addEnchant(Enchantment.DAMAGE_ALL, 0, true);
					meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					lore = new ArrayList<String>();
					lore.add(ChatColor.WHITE + "Click here to check updates of");
					lore.add(ChatColor.WHITE + "ArmorPlusPlus plugin.");
					meta.setLore(lore);
					item2.setItemMeta(meta);
					content.add(item2);
					for(int i = 0; i < 12; i++) {
						content.add(new ItemStack(Material.AIR));
					}
					inv.setContents(content.toArray(new ItemStack[content.size() - 1]));
					p.openInventory(inv);
				}else {
					if(args[0].equalsIgnoreCase("gui")) {
						openGui(p);
					}else if(args[0].equalsIgnoreCase("check")) {
						if(main.outdated) {
							p.sendMessage(main.msg);
						}else {
							p.sendMessage("Checking the web...");
							try {
								String url = "https://www.spigotmc.org/resources/armorplusplus.74748/";
								String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36";
								Document doc = Jsoup.connect(url).userAgent(userAgent).get();
								Element resourceInfo = doc.select("div.resourceInfo").get(0);
								String version = resourceInfo.select("h1 span.muted").get(0).text();
								if(!version.equalsIgnoreCase(plugin.getDescription().getVersion())) {
									// run something
									boolean noteligible = false;
									try {
										int a = Integer.parseInt(plugin.getDescription().getVersion().replace(".", "").replace(" ", ""));
										if(a == 0) {
											// do nothing
										}
									}catch(Exception e) {
										p.sendMessage(ChatColor.DARK_RED + "ArmorPlusPlus > Detected invalid version. Please don't modify the plugin's version.");
										noteligible = true;
									}
									if(noteligible) {}
									else if(!noteligible & Integer.parseInt(version.replace(".", "").replace(" ", "")) < Integer.parseInt(plugin.getDescription().getVersion().replace(".", "").replace(" ", ""))) {
										p.sendMessage(ChatColor.DARK_RED + "ArmorPlusPlus > Detected invalid version. Please don't modify the plugin's version.");
									}else if(!noteligible) {
										// run something
										main.outdated = true;
										main.msg = ChatColor.GREEN + "ArmorPlusPlus > Available update version " + version + ". Current plugin has the version " + plugin.getDescription().getVersion() + ". Download at " + url;
										p.sendMessage(main.msg);
									}
								}else {
									main.uptodate = true;
									p.sendMessage(ChatColor.GREEN + "ArmorPlusPlus > No available updates. This version is up-to-date.");
								}
							}catch(Exception e) {
								p.sendMessage(ChatColor.RED + "ArmorPlusPlus > Failed to check version updates. Please check the internet connection. If you believe this is an error, Contact the author of the plugin.");
								if(main.uptodate) p.sendMessage(ChatColor.GREEN + "According to the last check, this version of plugin is up-to-date.");
							}
						}
					}else {
						p.sendMessage(ChatColor.RED + "Invalid argument. Only 'gui' and 'check' is allowed.");
					}
				}
			}else {
				p.sendMessage("Unknown command. Type \"help\" to get help!");
			}
		}else {
			System.out.println("You must be a player to do this!");
		}
		return true;
	}
	@EventHandler
	public void gui(InventoryClickEvent e) {
		if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "All Armor++'s armor")) {
			if(e.getCurrentItem() != null) {
				if(e.getWhoClicked() instanceof Player) {
					Player p = (Player) e.getWhoClicked();if(e.getClickedInventory() instanceof PlayerInventory) return;
					e.setCancelled(true);					
					if(e.getSlot() == 0) {
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
						
						
						// Less armor for dirt!
						m1.removeAttributeModifier(Attribute.GENERIC_ARMOR);
						m2.removeAttributeModifier(Attribute.GENERIC_ARMOR);
						m3.removeAttributeModifier(Attribute.GENERIC_ARMOR);
						m4.removeAttributeModifier(Attribute.GENERIC_ARMOR);
						m1.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
						m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
						m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
						m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
						m1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
						m2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
						m3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
						m4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 0, true);
						armor1.setItemMeta(m1);
						armor2.setItemMeta(m2);
						armor3.setItemMeta(m3);
						armor4.setItemMeta(m4);
						
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 1) {
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
						
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 2) {
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
						
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 3) {
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
						m2.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 4, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
						m3.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
						m4.addAttributeModifier(Attribute.GENERIC_ARMOR, new org.bukkit.attribute.AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
						
						armor1.setItemMeta(m1);
						armor2.setItemMeta(m2);
						armor3.setItemMeta(m3);
						armor4.setItemMeta(m4);
						
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 4) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 5) {
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
						
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 6) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 7) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 8) {
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
						
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 9) {
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
						
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 10) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 11) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 12) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 13) {
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
						
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 14) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 15) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 16) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 17) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 18) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 19) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void mainGUI(InventoryClickEvent e) {
		if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Choose!")) {
			if(e.getCurrentItem() != null) {
				if(e.getWhoClicked() instanceof Player) {
					Player p = (Player) e.getWhoClicked();
					if(e.getClickedInventory() instanceof PlayerInventory) return;
					e.setCancelled(true);
					if(e.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE) {
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, 2, 0);
						p.performCommand("armorplusplus gui");
					}else if(e.getCurrentItem().getType() == Material.COMPASS) {
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, 2, 0);
						p.performCommand("armorplusplus check");
					}
				}
			}
		}
	}
	
	public void openGui(Player p) {
		Inventory gui = Bukkit.createInventory(p, 54, ChatColor.GOLD + "All Armor++'s armor");
		
		ItemStack dirt = new ItemStack(Material.DIRT);
		ItemStack ct = new ItemStack(Material.CRAFTING_TABLE);
		gui.setItem(0, dirt);
		gui.setItem(1, ct);
		gui.setItem(2, new ItemStack(Material.GLASS));
		gui.setItem(3, new ItemStack(Material.FURNACE));
		gui.setItem(4, new ItemStack(Material.TNT));
		gui.setItem(5, new ItemStack(Material.NOTE_BLOCK));
		gui.setItem(6, new ItemStack(Material.CARVED_PUMPKIN));
		gui.setItem(7, new ItemStack(Material.MELON));
		gui.setItem(8, new ItemStack(Material.SPONGE));
		gui.setItem(9, new ItemStack(Material.DISPENSER));
		gui.setItem(10, new ItemStack(Material.PRISMARINE));
		gui.setItem(11, new ItemStack(Material.LAPIS_BLOCK));
		gui.setItem(12, new ItemStack(Material.CACTUS));
		gui.setItem(13, new ItemStack(Material.OAK_LEAVES));
		gui.setItem(14, new ItemStack(Material.SUGAR_CANE));
		gui.setItem(15, new ItemStack(Material.STICKY_PISTON));
		gui.setItem(16, new ItemStack(Material.SAND));
		gui.setItem(17, new ItemStack(Material.QUARTZ_BLOCK));
		gui.setItem(18, new ItemStack(Material.OBSIDIAN));
		gui.setItem(19, new ItemStack(Material.EMERALD_BLOCK));
		p.openInventory(gui);
	}
}
