package me.bramar.armorplusplus;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class GlowingEnchantment extends EnchantmentWrapper {

	public GlowingEnchantment(String namespace, Plugin plugin) {
		super(namespace, plugin);
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ARMOR;
	}
	
	
	@Override
	public int getMaxLevel() {
		return 10;
	}

	@Override
	public String getName() {
		return "Glowing";
	}

	@Override
	public int getStartLevel() {
		return 0;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}
	
}
abstract class EnchantmentWrapper extends Enchantment {
	EnchantmentWrapper(String namespace, Plugin plugin) {
		super(new NamespacedKey(plugin, namespace));
	}
}
