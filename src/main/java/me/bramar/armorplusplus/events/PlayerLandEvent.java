package me.bramar.armorplusplus.events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;

/**
 * PlayerLandEvent
 * <p>
 * Will be called when a player lands from a fall.
 */
public class PlayerLandEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	private Player p;
	private UUID uuid;
	private Vector v;
	
	public PlayerLandEvent(Player p, UUID uuid, Vector v) {
		this.p = p;
		this.uuid = uuid;
		this.v = v;
	}
	
	public Player getPlayer() {
		return p;
	}
	public UUID getUniqueId() {
		return uuid;
	}
	public Vector getVelocity() {
		return v;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

	public HandlerList getHandlers() {
		return handlers;
	}
}
