package me.marvel.armorplusplus.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.marvel.armorplusplus.Method;

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
				try {
					if(args[0].equalsIgnoreCase("give")) {
						if(args.length == 2) {
							for(Player player : Bukkit.getOnlinePlayers()) {
								list.add(player.getName());
								if(!args[1].equalsIgnoreCase("") && !args[1].equalsIgnoreCase(" ") && !Method.matches(args[1], player.getName())) list.remove(player.getName());
							}
						}else if(args.length == 3) {
							for(Map.Entry<String, String> entry : Method.getArmorIdtoName().entrySet()) {
								list.add(entry.getKey());
								if(!args[2].equalsIgnoreCase("") && !args[2].equalsIgnoreCase(" ") && !Method.matches(args[2], entry.getKey())) list.remove(entry.getKey());
							}
							for(Map.Entry<String, String> entry : Method.getCustomItems().entrySet()) {
								list.add(entry.getKey());
								if(!args[2].equalsIgnoreCase("") && !args[2].equalsIgnoreCase(" ") && !Method.matches(args[2], entry.getKey())) list.remove(entry.getKey());
							}
						}
						return list;
					}
				}catch(Exception e) {
					
				}
				list.add("check");
				if(p.hasPermission("armorplusplus.cheat")) list.add("gui");
				if(p.hasPermission("armorplusplus.cheat")) list.add("give");
				if(p.hasPermission("armorplusplus.cheat")) list.add("items");
				if(p.hasPermission("armorplusplus.reload")) list.add("reload");
				if(p.hasPermission("armorplusplus.config")) list.add("resetconfig");
				if(args.length >= 2) return new ArrayList<>();
				if(args.length == 1 && !args[0].equalsIgnoreCase("") && !args[0].equalsIgnoreCase(" ") && args[0] != null) {
					if(!Method.matches(args[0], "check")) list.remove("check");
					if(!Method.matches(args[0], "gui")) list.remove("gui");
					if(!Method.matches(args[0], "give")) list.remove("give");
					if(!Method.matches(args[0], "items")) list.remove("items");
					if(!Method.matches(args[0], "reload")) list.remove("reload");
					if(!Method.matches(args[0], "resetconfig")) list.remove("resetconfig");
				}
				return list;
			}else {
				return null;
			}
		}
		return null;
	}
}
