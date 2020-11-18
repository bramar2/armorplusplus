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
import org.bukkit.command.ConsoleCommandSender;
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

/**
 * To register /armorplusplus
 * <p>
 * Contains all the GUI, and features from the command.
 */
public class ArmorPlusPlus implements CommandExecutor, Listener {	
	
	Plugin plugin;
	me.marvel.armorplusplus.ArmorPlusPlus main;
	
	public ArmorPlusPlus(Plugin plugin, me.marvel.armorplusplus.ArmorPlusPlus main) {
		this.plugin = plugin;
		this.main = main;
	}
	
	// Never will be used on a release. Only used when developing.
	public void test(Player p, String[] args) {
		
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
					if(args[0].equalsIgnoreCase("test")) {
						// Command for testing
						test(p, args);
					}
					if(args[0].equalsIgnoreCase("gui")) {
						openGui(p);
					}else if(args[0].equalsIgnoreCase("check")) {
						if(main.outdated) {
							p.sendMessage(main.msg);
						}else {
							if(main.outdated) {
								p.sendMessage(main.msg);
							}else {
								p.sendMessage("Checking...");
								main.uc.getVersion((version) -> {
									if(version == null) {
										p.sendMessage(ChatColor.RED + "ArmorPlusPlus > Unable to check for update.");
										if(main.outdated) {
											p.sendMessage(ChatColor.GREEN + "According to the last update check, the current version is outdated.");
											p.sendMessage(main.msg);
										}else if(main.uptodate) {
											p.sendMessage(ChatColor.GREEN + "According to the last update check, there is no available updates.");
										}
									}else if(version.equalsIgnoreCase(main.getDescription().getVersion())) {
										main.outdated = false;
										main.uptodate = true;
										p.sendMessage(ChatColor.GREEN + "ArmorPlusPlus > No available updates.");
									}else {
										main.outdated = true;
										main.uptodate = false;
										main.msg = ChatColor.GREEN + "ArmorPlusPlus > Available update version " + version + ". Download at https://www.spigotmc.org/resources/armorplusplus.74748/";
										p.sendMessage(main.msg);
										
									}
								});
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
			if(args.length != 0) {
				if(args[0].equalsIgnoreCase("check")) {
					ConsoleCommandSender p = main.getServer().getConsoleSender();
					p.sendMessage("Checking...");
					main.uc.getVersion((version) -> {
						if(version == null) {
							p.sendMessage(ChatColor.RED + "ArmorPlusPlus > Unable to check for update.");
							if(main.outdated) {
								p.sendMessage(ChatColor.GREEN + "According to the last update check, the current version is outdated.");
								p.sendMessage(main.msg);
							}else if(main.uptodate) {
								p.sendMessage(ChatColor.GREEN + "According to the last update check, there is no available updates.");
							}
						}else if(version.equalsIgnoreCase(main.getDescription().getVersion())) {
							main.outdated = false;
							main.uptodate = true;
							p.sendMessage(ChatColor.GREEN + "ArmorPlusPlus > No available updates.");
						}else {
							main.outdated = true;
							main.uptodate = false;
							main.msg = ChatColor.GREEN + "ArmorPlusPlus > Available update version " + version + ". Download at https://www.spigotmc.org/resources/armorplusplus.74748/";
							main.getServer().getConsoleSender().sendMessage(main.msg);
							
						}
					});
				}else System.out.println("You must be a player to do this!");
			}else System.out.println("You must be a player to do this!");
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
					}else if(e.getSlot() == 20) {
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
					}else if(e.getSlot() == 21) {
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
					}else if(e.getSlot() == 22) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 23) {
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
					}else if(e.getSlot() == 24) {
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
						p.getInventory().addItem(armor1);
						p.getInventory().addItem(armor2);
						p.getInventory().addItem(armor3);
						p.getInventory().addItem(armor4);
					}else if(e.getSlot() == 25) {
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
					}else if(e.getSlot() == 26) {
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
					}else if(e.getSlot() == 27) {
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
					}else if(e.getSlot() == 28) {
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
					}else if(e.getSlot() == 29) {
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
					}else if(e.getSlot() == 30) {
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
					}else if(e.getSlot() == 31) {
						if(checkVersion16(p, true, true)) return;
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
		gui.setItem(20, new ItemStack(Material.PISTON));
		gui.setItem(21, new ItemStack(Material.WET_SPONGE));
		gui.setItem(22, new ItemStack(Material.MAGMA_BLOCK));
		gui.setItem(23, new ItemStack(Material.NETHERRACK));
		gui.setItem(24, new ItemStack(Material.ENDER_CHEST));
		gui.setItem(25, new ItemStack(Material.BRICKS));
		gui.setItem(26, new ItemStack(Material.NETHER_BRICKS));
		gui.setItem(27, new ItemStack(Material.RED_NETHER_BRICKS));
		gui.setItem(28, new ItemStack(Material.SLIME_BLOCK));
		gui.setItem(29, new ItemStack(Material.END_STONE));
		gui.setItem(30, new ItemStack(Material.ICE));
		gui.setItem(31, new ItemStack(Material.BONE_BLOCK));
		p.openInventory(gui);
	}
	private boolean checkVersion16(Player p, boolean makeVillagerSound, boolean sendMessage) {
		if(!main.ver16) {
			if(makeVillagerSound) p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
			p.sendMessage(ChatColor.RED + "The armor is only available in 1.16 and newer versions!");
			return true;
		}else return false;
	}
}
