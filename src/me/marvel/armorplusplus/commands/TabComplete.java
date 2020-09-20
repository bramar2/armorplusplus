package me.marvel.armorplusplus.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabComplete implements TabCompleter {
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("armorplusplus")) {
			if(sender instanceof Player) {
				List<String> list = new ArrayList<String>();
				list.add("check");
				list.add("gui");
				return list;
			}else {
				return null;
			}
		}
		return null;
	}
}
