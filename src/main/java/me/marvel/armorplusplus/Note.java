package me.marvel.armorplusplus;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Customizations for Note armor.
 * <p>
 * To make different sounds on different blocks.
 */
public class Note {

	Float pitch;

	public void other(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void gold(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void clay(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void pice(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void bone(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void iron(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void soulsand(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void hay(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void emerald(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void pumpkin(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void glowstone(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void wool(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void glass(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void wood(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void stone(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
	public void gravity(Player p, int note) {
		switch (note) {
		case 0:
			pitch = 0.5F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			break;
		case 1:
			pitch = 0.529732F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 2:
			pitch = 0.561231F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 3:
			pitch = 0.594604F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 4:
			pitch = 0.629961F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 5:
			pitch = 0.667420F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 6:
			pitch = 0.707107F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 7:
			pitch = 0.749154F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 8:
			pitch = 0.793701F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 9:
			pitch = 0.840896F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 10:
			pitch = 0.890899F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 11:
			pitch = 0.943874F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 12:
			pitch = 1F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 13:
			pitch = 1.059463F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 14:
			pitch = 1.122462F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 15:
			pitch = 1.189207F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 16:
			pitch = 1.259921F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 17:
			pitch = 1.334840F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 18:
			pitch = 1.414214F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 19:
			pitch = 1.498307F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 20:
			pitch = 1.587401F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 21:
			pitch = 1.681793F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 22:
			pitch = 1.781797F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 23:
			pitch = 1.887749F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		case 24:
			pitch = 2F;
			p.getWorld().spawnParticle(Particle.NOTE, p.getLocation().add(0, 1, 0), 1);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1, pitch);
			
			break;
		default:
			p.sendMessage("An error occured. Please contact me (The author of Armor++) at spigotmc.org");
			break;
	}
	}
}