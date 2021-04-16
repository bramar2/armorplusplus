package me.bramar.armorplusplus.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Consumer;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Check updates for the plugin
 */
public class UpdateChecker {

    private Plugin plugin;
    private int resourceId;
    private final String USER_AGENT;
    
    /**
     * Class constructor.
     * 
     * @param plugin The plugin that needs to be checked.
     * @param resourceId SpigotMC resource ID of the plugin.
     */
    public UpdateChecker(Plugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
        this.USER_AGENT = plugin.getName();
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
    private JsonElement requestJSON(String requestUrl) throws Exception {
    	try {
    		URL url = new URL(requestUrl);
    		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    		connection.addRequestProperty("User-Agent", this.USER_AGENT);
    		InputStream inputStream = connection.getInputStream();
    		InputStreamReader reader = new InputStreamReader(inputStream);
    		JsonElement element = new JsonParser().parse(reader);
    		return element;
    	}catch(Exception e1) {
    		throw e1;
    	}
    }
    private String decodeString(String encodedString) throws Exception {
    	return new String(Base64.getDecoder().decode(encodedString.replaceAll(" ", "").getBytes()));
    }
    public String getAuthor() throws Exception {
    	JsonElement element = requestJSON("https://api.spiget.org/v2/resources/" + resourceId + "/author");
    	return element.getAsJsonObject().get("name").getAsString();
    }
    public String getLatestTitle() throws Exception {
    	JsonElement element = requestJSON("https://api.spiget.org/v2/resources/" + resourceId + "/updates/latest");
    	return element.getAsJsonObject().get("title").getAsString().replaceAll("\\<.*?\\>", "");
    }
    public String getLatestChangelog() throws Exception {
    	JsonElement element = requestJSON("https://api.spiget.org/v2/resources/" + resourceId + "/updates/latest");
    	return decodeString(element.getAsJsonObject().get("description").getAsString()).replaceAll("\\<.*?\\>", "");
    }
    public String getDownloadUrl() throws Exception {
    	JsonElement element = requestJSON("https://api.spiget.org/v2/resources/" + resourceId);
    	return "https://spigotmc.org/" + element.getAsJsonObject().get("file").getAsJsonObject().get("url").getAsString();
    }
    public String getDownloadSize() throws Exception {
    	JsonElement element = requestJSON("https://api.spiget.org/v2/resources/" + resourceId);
    	return element.getAsJsonObject().get("file").getAsJsonObject().get("size").getAsDouble() + " " + element.getAsJsonObject().get("file").getAsJsonObject().get("sizeUnit").getAsString();
    }
    public String getAdditionalInfo() throws Exception {
    	JsonElement element = requestJSON("https://api.spiget.org/v2/resources/" + resourceId);
    	return element.getAsJsonObject().get("links").getAsJsonObject().get("additionalInformation").getAsString();
    }
    public String getPluginName() throws Exception {
    	JsonElement element = requestJSON("https://api.spiget.org/v2/resources/" + resourceId);
    	return element.getAsJsonObject().get("name").getAsString();
    }
    public String getPluginTag() throws Exception {
    	JsonElement element = requestJSON("https://api.spiget.org/v2/resources/" + resourceId);
    	return element.getAsJsonObject().get("tag").getAsString();
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
 