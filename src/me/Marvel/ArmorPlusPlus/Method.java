package me.Marvel.ArmorPlusPlus;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class Method {
	/*
	 * Automates the method of making a recipe and checking armor for abilities, 
	 * so the code will be much simpler (i think & i hope)
	 * 2020
	 */
	public static Plugin plugin;
	public static boolean ifWearingAll(Player p, String armorname, String lore1) {
		boolean result = false;
		try {
			ItemStack helm = p.getInventory().getHelmet();
			ItemStack chest = p.getInventory().getChestplate();
			ItemStack legs = p.getInventory().getLeggings();
			ItemStack feet = p.getInventory().getBoots();
			if(helm.getItemMeta().getDisplayName().equalsIgnoreCase(armorname + " Helmet") &&
				chest.getItemMeta().getDisplayName().equalsIgnoreCase(armorname + " Chestplate") &&
				legs.getItemMeta().getDisplayName().equalsIgnoreCase(armorname + " Leggings") && 
				feet.getItemMeta().getDisplayName().equalsIgnoreCase(armorname + " Boots")) {
				if(helm.getItemMeta().getLore().get(0).equalsIgnoreCase(lore1) && 
					chest.getItemMeta().getLore().get(0).equalsIgnoreCase(lore1) &&
					legs.getItemMeta().getLore().get(0).equalsIgnoreCase(lore1) && 
					feet.getItemMeta().getLore().get(0).equalsIgnoreCase(lore1)) {
					result = true;
				}else {
					result = false;
				}
			}else {
				result = false;
			}
			return result;
		}catch(Exception e) {
			return false;
		}
	}
	public static void changeArmorInArmorSlot(Player p, ItemStack helm, ItemStack chest, ItemStack legs, ItemStack feet) {
		try {
			p.getInventory().setHelmet(helm);
			p.getInventory().setChestplate(chest);
			p.getInventory().setLeggings(legs);
			p.getInventory().setBoots(feet);
		}catch(Exception e) {
			
		}
	}
	public static void replaceArmorInInventory(Player p, String tag, ItemStack[] newArmor) {
		ItemStack[] content = p.getInventory().getContents();
		for(int i = 0; i < content.length; i++) {
			ItemStack item = content[i];
			try {
				if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Helm"), PersistentDataType.BYTE)) {
					// helmet
					p.getInventory().setItem(i, newArmor[0]);
				}else if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Chest"), PersistentDataType.BYTE)) {
					// chestplate
					p.getInventory().setItem(i, newArmor[1]);
				}else if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Legs"), PersistentDataType.BYTE)) {
					// legs
					p.getInventory().setItem(i, newArmor[2]);
				}else if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, tag + "Feet"), PersistentDataType.BYTE)) {
					// feet
					p.getInventory().setItem(i, newArmor[3]);
				}
			}catch(Exception e) {
				
			}
			continue;
		}
	}
}
