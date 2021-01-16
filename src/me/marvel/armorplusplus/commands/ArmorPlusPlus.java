package me.marvel.armorplusplus.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import me.marvel.armorplusplus.Method;

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
	
	// Never will be used on an update. Only used when developing.
	// PLAYER	
	public void test(Player p, String[] args) {
		
	}
	// CONSOLE
	public void test(String[] args) {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				if(!p.hasPermission("armorplusplus.checkupdate")) {
					p.sendMessage(ChatColor.RED + "No permission!");
					return true;
				}
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
					if(p.hasPermission("armorplusplus.cheat")) openGui(p);
					else p.sendMessage(ChatColor.RED + "No permission!");
				}else if(args[0].equalsIgnoreCase("check")) {
					if(!p.hasPermission("armorplusplus.checkupdate")) {
						p.sendMessage(ChatColor.RED + "No permission!");
						return true;
					}
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
				}else if(args[0].equalsIgnoreCase("give")) {
					if(!p.hasPermission("armorplusplus.cheat")) {
						p.sendMessage(ChatColor.RED + "No permission!");
						return true;
					}
					if(args.length == 1) {
						p.sendMessage(ChatColor.RED + "Usage: /armorplusplus give <player> <armor_id>");
						return true;
					}
					Player player = null;
					try {
						player = Bukkit.getPlayer(args[1]);
					}catch(Exception e) {
						
					}
					if(player == null) {
						p.sendMessage(ChatColor.RED + "Player not found!");
						return true;
					}
					int value = -1;
					String armorname = "";
					try {
						int i = 0;
						LinkedHashMap<String, String> map = Method.getArmorIdtoName();
						for(Map.Entry<String, String> entry : map.entrySet()) {
							String id = entry.getKey();
							if(id.equalsIgnoreCase(args[2])) {
								value = i;
								armorname = entry.getValue();
								break;
							}
							i++;
						}
					}catch(Exception e) {
						
					}
					if(value == -1 || armorname == "") {
						p.sendMessage(ChatColor.RED + "Invalid armor id!");
						return true;
					}
					openGui(player);
					InventoryView view = player.getOpenInventory();
					player.closeInventory();
					
					main.invevents.gui(new InventoryClickEvent(view, SlotType.CONTAINER, value, ClickType.LEFT, InventoryAction.NOTHING));
					p.sendMessage(ChatColor.GREEN + "Successfully given " + player.getName() + " " + armorname + " armor!");
				}else if(args[0].equalsIgnoreCase("reload")) {
					if(!p.hasPermission("armorplusplus.reload")) {
						p.sendMessage(ChatColor.RED + "No permission!");
						return true;
					}
					plugin.reloadConfig();
					main.reloadConfig();
					main.checkMissingConfig(p, true);
					// DISABLED-ARMORS AND GLOWING CONFIG RELOAD
					main.loadArmorIds();
					for(int i = 0;i < main.ar.activeRecipes.size(); i++) {
						try {
							ShapedRecipe recipe = main.ar.activeRecipes.get(i);
							plugin.getServer().removeRecipe(recipe.getKey());
							main.ar.activeRecipes.remove(recipe);
						}catch(Exception e) {
							
						}
					}
					main.loadRecipe();
					main.interval = plugin.getConfig().getInt("tick-interval");
					plugin.getServer().getScheduler().cancelTasks(plugin);
					main.loadAbilities();
					main.loadMetrics();
					main.listener.listen();
					//
					p.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
				}else if(args[0].equalsIgnoreCase("resetconfig")) {
					if(!p.hasPermission("armorplusplus.config")) {
						p.sendMessage(ChatColor.RED + "No permission!");
						return true;
					}
					if(args.length == 1) {
						p.sendMessage(ChatColor.RED + "Be sure to save configuration if needed! Please do '/armorplusplus resetconfig confirm' to confirm this action.");
					}else {
						if(args[1].equalsIgnoreCase("confirm")) {
							// RESET CONFIG
							File config = new File(plugin.getDataFolder().getAbsolutePath() + "/config.yml");
							if(!config.exists()) {
								p.sendMessage(ChatColor.RED + "The configuration file doesn't exist! Creating one...");
							}
							if(config.exists()) config.delete();
							plugin.saveDefaultConfig();
							plugin.getConfig().options().copyDefaults(true);
							p.sendMessage(ChatColor.GREEN + "The configuration is successfully reset! To properly reset so nothing breaks, do /armorplusplus reload.");
						}else {
							p.sendMessage(ChatColor.RED + "Be sure to save configuration if needed! Please do '/armorplusplus resetconfig confirm' to confirm this action.");
						}
					}
				}else if(args[0].equalsIgnoreCase("config")) {
					if(!p.hasPermission("armorplusplus.config")) {
						p.sendMessage(ChatColor.RED + "No permission!");
						return true;
					}
					p.sendMessage(ChatColor.RED + "Note: This command is unsupported and could cause errors to recipes.");
					List<String> missing = main.checkMissingConfig(p, true);
					Method.openConfigGUI(p, missing);
				}else {
					if(p.hasPermission("armorplusplus.checkupdate") || p.hasPermission("armorplusplus.cheat") || p.hasPermission("armorplusplus.reload") || p.hasPermission("armorplusplus.config")) p.sendMessage(ChatColor.RED + "Invalid argument. Only "
							+ "'gui', 'check', 'reload', 'give', 'resetconfig', and 'config' (Unsupported) is allowed");
					else p.sendMessage(ChatColor.RED + "No permission!");
				}
			}
		}else {
			if(args.length != 0) {
				if(args[0].equalsIgnoreCase("test")) {
					test(args);
				}
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
				}else if(args[0].equalsIgnoreCase("reload")) {
					ConsoleCommandSender p = plugin.getServer().getConsoleSender();
					plugin.reloadConfig();
					main.reloadConfig();
					main.checkMissingConfig();
					// DISABLED-ARMORS AND GLOWING CONFIG RELOAD
					main.loadArmorIds();
					for(int i = 0;i < main.ar.activeRecipes.size(); i++) {
						try {
							ShapedRecipe recipe = main.ar.activeRecipes.get(i);
							plugin.getServer().removeRecipe(recipe.getKey());
							main.ar.activeRecipes.remove(recipe);
						}catch(Exception e) {
							
						}
					}
					main.loadRecipe();
					//
					p.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
				}else if(args[0].equalsIgnoreCase("resetconfig")) {
					ConsoleCommandSender p = plugin.getServer().getConsoleSender();
					if(args.length == 1) {
						p.sendMessage(ChatColor.RED + "Be sure to save configuration if needed! Please do '/armorplusplus resetconfig confirm' to confirm this action.");
					}else {
						if(args[1].equalsIgnoreCase("confirm")) {
							// RESET CONFIG
							File config = new File(plugin.getDataFolder().getAbsolutePath() + "/config.yml");
							if(!config.exists()) {
								p.sendMessage(ChatColor.RED + "The configuration file doesn't exist! Creating one...");
							}
							if(config.exists()) config.delete();
							plugin.saveDefaultConfig();
							plugin.getConfig().options().copyDefaults(true);
							p.sendMessage(ChatColor.GREEN + "The configuration is successfully reset! To use the resetted configuration, do /armorplusplus reload. To edit the configuration, do /armorplusplus config.");
						}else {
							p.sendMessage(ChatColor.RED + "Be sure to save configuration if needed! Please do '/armorplusplus resetconfig confirm' to confirm this action.");
						}
					}
				}else {
					System.out.println("Invalid argument. Only 'check', 'reload', and 'resetconfig' is allowed. Other commands can only be run by a player.");
				}
			}else System.out.println("You must be a player to do this!");
		}
		return true;
	}
	
	public Inventory openGui(Player p) {
		Inventory gui = Bukkit.createInventory(p, 54, ChatColor.GOLD + "All Armor++'s armor");
		
		gui.setItem(0, Method.getCommandItem(Material.DIRT, "DIRT"));
		gui.setItem(1, Method.getCommandItem(Material.CRAFTING_TABLE, "CRAFTING_TABLE"));
		gui.setItem(2, Method.getCommandItem(Material.GLASS, "GLASS"));
		gui.setItem(3, Method.getCommandItem(Material.FURNACE, "FURNACE"));
		gui.setItem(4, Method.getCommandItem(Material.TNT, "TNT"));
		gui.setItem(5, Method.getCommandItem(Material.NOTE_BLOCK, "NOTE_BLOCK"));
		gui.setItem(6, Method.getCommandItem(Material.CARVED_PUMPKIN, "PUMPKIN"));
		gui.setItem(7, Method.getCommandItem(Material.MELON, "MELON"));
		gui.setItem(8, Method.getCommandItem(Material.SPONGE, "SPONGE"));
		gui.setItem(9, Method.getCommandItem(Material.DISPENSER, "DISPENSER"));
		gui.setItem(10, Method.getCommandItem(Material.PRISMARINE, "PRISMARINE"));
		gui.setItem(11, Method.getCommandItem(Material.LAPIS_BLOCK, "LAPIS"));
		gui.setItem(12, Method.getCommandItem(Material.CACTUS, "CACTUS"));
		gui.setItem(13, Method.getCommandItem(Material.OAK_LEAVES, "LEAVES"));
		gui.setItem(14, Method.getCommandItem(Material.SUGAR_CANE, "SUGAR_CANE"));
		gui.setItem(15, Method.getCommandItem(Material.STICKY_PISTON, "STICKY_PISTON"));
		gui.setItem(16, Method.getCommandItem(Material.SAND, "SAND"));
		gui.setItem(17, Method.getCommandItem(Material.QUARTZ_BLOCK, "QUARTZ"));
		gui.setItem(18, Method.getCommandItem(Material.OBSIDIAN, "OBSIDIAN"));
		gui.setItem(19, Method.getCommandItem(Material.EMERALD_BLOCK, "EMERALD"));
		gui.setItem(20, Method.getCommandItem(Material.PISTON, "PISTON"));
		gui.setItem(21, Method.getCommandItem(Material.WET_SPONGE, "WET_SPONGE"));
		gui.setItem(22, Method.getCommandItem(Material.MAGMA_BLOCK, "MAGMA"));
		gui.setItem(23, Method.getCommandItem(Material.NETHERRACK, "NETHERRACK"));
		gui.setItem(24, Method.getCommandItem(Material.ENDER_CHEST, "ENDER"));
		gui.setItem(25, Method.getCommandItem(Material.BRICKS, "BRICKS"));
		gui.setItem(26, Method.getCommandItem(Material.NETHER_BRICKS, "NETHER_BRICKS"));
		gui.setItem(27, Method.getCommandItem(Material.RED_NETHER_BRICKS, "RED_NETHER_BRICKS"));
		gui.setItem(28, Method.getCommandItem(Material.SLIME_BLOCK, "SLIME"));
		gui.setItem(29, Method.getCommandItem(Material.END_STONE, "END_STONE"));
		gui.setItem(30, Method.getCommandItem(Material.ICE, "ICE"));
		gui.setItem(31, Method.getCommandItem(Material.BONE_BLOCK, "BONE"));
		gui.setItem(32, Method.getCommandItem(Material.SOUL_SAND, "SOUL_SAND"));
		gui.setItem(33, Method.getCommandItem(Material.SNOW_BLOCK, "SNOW"));
		try {
			p.openInventory(gui);
		}catch(Exception e) {
		
		}
		return gui;
	}
}
