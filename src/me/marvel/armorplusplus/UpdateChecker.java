package me.marvel.armorplusplus;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Consumer;

public class UpdateChecker {
	
	/*
	 * CODE IS TAKEN FROM THE OFFICIAL SPIGOTMC.ORG
	 * FURTHER INFORMATION: https://www.spigotmc.org/wiki/creating-an-update-checker-that-checks-for-updates/
	 */

    private Plugin plugin;
    private int resourceId;

    public UpdateChecker(Plugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                consumer.accept(null);
            }
        });
    }
    public Plugin getPlugin() {
    	return plugin;
    }
    public int getId() {
    	return resourceId;
    }
}
 
