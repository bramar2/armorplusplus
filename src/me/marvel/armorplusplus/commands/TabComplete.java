package me.marvel.armorplusplus.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

/**
 * TabComplete for /armorplusplus
 */
public class TabComplete implements TabCompleter {
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("armorplusplus")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(!p.hasPermission("armorplusplus.checkupdate")) return null;
				List<String> list = new ArrayList<String>();
				list.add("check");
				if(p.hasPermission("armorplusplus.cheat")) list.add("gui");
				if(args.length == 1) { 
					if(args[0].toLowerCase().startsWith("c") && list.contains("gui")) list.remove("gui");
					else if(args[0].toLowerCase().startsWith("g") && list.contains("check")) list.remove("check");
					else if(!args[0].equalsIgnoreCase("")) list = new ArrayList<String>();
				}else if(args.length >= 2) {
					list = new ArrayList<String>();
				}
				return list;
			}else {
				return null;
			}
		}
		return null;
	}
}
