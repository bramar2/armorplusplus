package me.Marvel.ArmorPlusPlus;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import me.Marvel.ArmorPlusPlus.Commands.TabComplete;
public class ArmorPlusPlus extends JavaPlugin implements Listener {
	
	/*
	 * THIS PROGRAM USES JSOUP AS TO CHECK UPDATES.
	 * CREDITS TO JSOUP: JSOUP.ORG
	 */
	
	public boolean outdated = false;
	public boolean uptodate = false;
	public String msg = "";
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {
		if(e.getPlayer().isOp()) {
			if(outdated) {
				if(msg != "") e.getPlayer().sendMessage(msg);
				else e.getPlayer().sendMessage(ChatColor.GREEN + "ArmorPlusPlus > Available update version. Current plugin is outdated. Download at https://www.spigotmc.org/resources/armorplusplus.74748/");
			}
		}
	}
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		try {
			if(getConfig().getBoolean("check-update")) {
				try {
					String url = "https://www.spigotmc.org/resources/armorplusplus.74748/";
					String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36";
					Document doc = Jsoup.connect(url).userAgent(userAgent).get();
					Element resourceInfo = doc.select("div.resourceInfo").get(0);
					String version = resourceInfo.select("h1 span.muted").get(0).text();
					if(!version.equalsIgnoreCase(getDescription().getVersion())) {
						boolean noteligible = false;
						try {
							int a = Integer.parseInt(getDescription().getVersion().replace(".", "").replace(" ", ""));
							if(a == 0) {
								// do nothing
							}
						}catch(Exception e) {
							getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "ArmorPlusPlus > Detected invalid version. Please don't modify the plugin's version.");
							noteligible = true;
						}
						if(noteligible) {
							
						}
						else if(Integer.parseInt(version.replace(".", "").replace(" ", "")) < Integer.parseInt(getDescription().getVersion().replace(".", "").replace(" ", ""))) {
							getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "ArmorPlusPlus > Detected invalid version. Please don't modify the plugin's version.");
						}else {
							// run something
							outdated = true;
							msg = ChatColor.GREEN + "ArmorPlusPlus > Available update version " + version + ". Current plugin has the version " + getDescription().getVersion() + ". Download at " + url;
							getServer().getConsoleSender().sendMessage(msg);
							for(Player p : getServer().getOnlinePlayers()) {
								if(p.isOp()) {
									p.sendMessage(msg);
								}
							}
						}
					}else {
						uptodate = true;
						getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ArmorPlusPlus > No available updates. This version is up-to-date.");
					}
				}catch(Exception e) {
					getServer().getConsoleSender().sendMessage(ChatColor.RED + "ArmorPlusPlus > Failed to check version updates.");
				}
			}
		}catch(Exception e) {
			
		}
		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Armor++ plugin enabled v" + this.getDescription().getVersion());
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==========================");
		getCommand("armorplusplus").setExecutor(new me.Marvel.ArmorPlusPlus.Commands.ArmorPlusPlus(this, this));
		getCommand("armorplusplus").setTabCompleter(new TabComplete());
		getServer().getPluginManager().registerEvents(new me.Marvel.ArmorPlusPlus.Commands.ArmorPlusPlus(this, this), this);
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
