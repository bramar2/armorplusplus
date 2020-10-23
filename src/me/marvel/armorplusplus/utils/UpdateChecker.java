package me.marvel.armorplusplus.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Consumer;

/**
 * Check updates for the plugin
 */
public class UpdateChecker {

    private Plugin plugin;
    private int resourceId;
    
    /**
     * Class constructor.
     * 
     * @param plugin The plugin that needs to be checked.
     * @param resourceId SpigotMC resource ID of the plugin.
     */
    public UpdateChecker(Plugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }
    
    /**
     * Get version from the web using SpigotMC API
     * 
     * @param consumer Returns the version in the consumer. Can be null, if IOException is handled.
     */
    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId + "&t=" + System.currentTimeMillis() + (new Random().nextInt(99999) + 1)).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                consumer.accept(null);
            }
        });
    }
    /**
     * @return Returns the plugin.
     */
    public Plugin getPlugin() {
    	return plugin;
    }
    /**
     * @return Returns SpigotMC resource ID of the plugin.
     */
    public int getId() {
    	return resourceId;
    }
}
 