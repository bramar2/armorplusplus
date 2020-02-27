package me.Marvel.ArmorPlusPlus.Commands;

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
				return new ArrayList<String>();
			}else {
				return null;
			}
		}
		return null;
	}
}
