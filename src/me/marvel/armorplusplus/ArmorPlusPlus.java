package me.marvel.armorplusplus;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.marvel.armorplusplus.commands.TabComplete;
public class ArmorPlusPlus extends JavaPlugin implements Listener {
	
	public boolean outdated = false;
	public boolean uptodate = false;
	public String msg = "";
	public UpdateChecker uc;
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {
		if(e.getPlayer().isOp() || e.getPlayer().hasPermission("armorplusplus.cheat")) {
			if(outdated) {
				if(msg != "") e.getPlayer().sendMessage(msg);
				else e.getPlayer().sendMessage(ChatColor.GREEN + "ArmorPlusPlus > Available update version. Current plugin is outdated. Download at https://www.spigotmc.org/resources/armorplusplus.74748/");
			}
		}
	}
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Armor++ plugin enabled v" + this.getDescription().getVersion());
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		try {
			if(getConfig().getBoolean("check-update")) {
				uc = new UpdateChecker(this, 74748);
				uc.getVersion((version) -> {
					if(version == null) {
						getServer().getConsoleSender().sendMessage(ChatColor.RED + "ArmorPlusPlus > Unable to check updates. To manually check updates, please type /armorplusplus check.");
					}else {
						if(version.equalsIgnoreCase(getDescription().getVersion())) {
							outdated = false;
							uptodate = true;
							getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ArmorPlusPlus > No available updates.");
						}else {
							outdated = true;
							uptodate = false;
							msg = ChatColor.GREEN + "ArmorPlusPlus > Available update version " + version + ". Download at https://www.spigotmc.org/resources/armorplusplus.74748/";
							getServer().getConsoleSender().sendMessage(msg);
						}
					}
				});
			}
		}catch(Exception e) {
			getLogger().warning(ChatColor.RED + "ArmorPlusPlus > Unable to check updates. To manually check updates, please type /armorplusplus check.");
		}
		
		getCommand("armorplusplus").setExecutor(new me.marvel.armorplusplus.commands.ArmorPlusPlus(this, this));
		getCommand("armorplusplus").setTabCompleter(new TabComplete());
		getServer().getPluginManager().registerEvents(new me.marvel.armorplusplus.commands.ArmorPlusPlus(this, this), this);
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
		ar.slimeArmor();
		ar.pistonArmor();
		ar.wetspongeArmor();
		ar.magmaArmor();
		ar.netherrackArmor();
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
					ability.pistonArmor();
					ability.wetspongeArmor();
					ability.magmaArmor();
					/*Netherrack armor not needing a method since it triggers an event.*/
				}
			}, 0L, 1L);
			
			// For every 12 ticks
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
