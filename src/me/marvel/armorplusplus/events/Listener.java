package me.marvel.armorplusplus.events;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

/**
 * Listens for custom events
 */
public class Listener {
	private Plugin plugin;
	private HashMap<UUID, Vector> playerVectorData = new HashMap<UUID, Vector>();
	
	public Listener(Plugin p) {
		this.plugin = p;
	}
	public void listen() {
		playerLandEvent();
	}
	public void playerLandEvent() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				for(Player p : plugin.getServer().getOnlinePlayers()) {
					if(((LivingEntity)p).isOnGround()) {
						if(playerVectorData.containsKey(p.getUniqueId())) {
							if(p.getLocation().getBlock().getType() != Material.WATER) plugin.getServer().getPluginManager().callEvent(new PlayerLandEvent(p, p.getUniqueId(), playerVectorData.get(p.getUniqueId())));
							playerVectorData.remove(p.getUniqueId());
						}
					}else {
						if(p.isFlying()) {
							if(playerVectorData.containsKey(p.getUniqueId())) playerVectorData.remove(p.getUniqueId());
						}else {
							if(playerVectorData.containsKey(p.getUniqueId())) {
								playerVectorData.remove(p.getUniqueId());
								playerVectorData.put(p.getUniqueId(), p.getVelocity());
							}else {
								playerVectorData.put(p.getUniqueId(), p.getVelocity());
							}
						}
					}
				}
			}
		}, 0L, 1L);
	}
}
