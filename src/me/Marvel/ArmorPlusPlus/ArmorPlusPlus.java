package me.Marvel.ArmorPlusPlus;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.Marvel.ArmorPlusPlus.Commands.TabComplete;
public class ArmorPlusPlus extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Armor++ plugin enabled v" + this.getDescription().getVersion());
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		getCommand("armorplusplus").setExecutor(new me.Marvel.ArmorPlusPlus.Commands.ArmorPlusPlus(this));
		getCommand("armorplusplus").setTabCompleter(new TabComplete());
		getServer().getPluginManager().registerEvents(new me.Marvel.ArmorPlusPlus.Commands.ArmorPlusPlus(this), this);
		getServer().getPluginManager().registerEvents(new ArmorAbilities(this), this);
		Method.plugin = this;
		loadRecipe();
		loadAbilities();
	}

	private void loadRecipe() {
		ArmorRecipe ar = new ArmorRecipe(this);
		ar.furnaceArmor();
		ar.glassArmor();
		ar.craftArmor();
		ar.dirtArmor();
		ar.tntArmor();
		ar.noteArmor();
		ar.pumpkinArmor();
		ar.melonArmor();
		ar.spongeArmor();
		ar.dispenserArmor();
		ar.prismarineArmor();
		ar.enderArmor();
		ar.lapisArmor();
		ar.cactusArmor();
		ar.leavesArmor();
		ar.sugarcaneArmor();
		ar.stickypistonArmor();
		ar.sandArmor();
		ar.quartzArmor();
		ar.obsidianArmor();
		ar.emeraldArmor();
	}
	private void loadAbilities() {
			// For every 2.5 second
			ArmorAbilities ability = new ArmorAbilities(this);
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					ability.dirtArmor();
				}
			}, 0L, 50L);
			// For every 6 second
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					ability.pumpkinArmor();
					ability.melonArmor();
				}
			}, 0L, 120L);
			
			// For every tick
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					ability.craftArmor();
					ability.invisibility();
					ability.smelt();
					ability.explode();
					ability.spongeArmor();
					ability.dispenserArmor();
					ability.prismarineArmor();
					ability.enderArmor();
					ability.lapisArmor();
					ability.leavesArmor();
					ability.sugarcaneArmor();
					ability.stickypistonArmor();
					ability.sandArmor();
					ability.quartzArmor();
					ability.obsidianArmor();
					ability.emeraldArmor();
				}
			}, 0L, 1L);
			
			// For every 12 tickss
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					ability.cactusArmor();
				}
			}, 0L, 12L);
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Armor++ plugin disabled v" + this.getDescription().getVersion());
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
	}
	

}
