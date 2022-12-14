package scoretag;

import cn.nukkit.Server;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;

public class APIDownloader {

    public static void checkAndRun(Main plugin) {
        Server server = plugin.getServer();

        if (server.getPluginManager().getPlugin("KotlinLib") == null) {
            plugin.getLogger().info("Downloading KotlinLib...");

            String placeholderApi = server.getFilePath() + "/plugins/KotlinLib.jar";

            try {
                FileOutputStream fos = new FileOutputStream(placeholderApi);
                fos.getChannel().transferFrom(Channels.newChannel(new URL("https://cloudburstmc.org/resources/kotlinlib.48/version/2442/download").openStream()), 0, Long.MAX_VALUE);
                fos.close();
            } catch (Exception e) {
                plugin.getLogger().info("Failed to download KotlinLib!");
                server.getLogger().logException(e);
                server.getPluginManager().disablePlugin(plugin);
                return;
            }

            plugin.getLogger().info("PlaceholderAPI downloaded successfully");
            server.getPluginManager().loadPlugin(placeholderApi);
        }

        if (server.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            plugin.getLogger().info("Downloading PlaceholderAPI...");

            String placeholderApi = server.getFilePath() + "/plugins/PlaceholderAPI.jar";

            try {
                FileOutputStream fos = new FileOutputStream(placeholderApi);
                fos.getChannel().transferFrom(Channels.newChannel(new URL("https://cloudburstmc.org/resources/placeholderapi.104/version/1830/download").openStream()), 0, Long.MAX_VALUE);
                fos.close();
            } catch (Exception e) {
                plugin.getLogger().info("Failed to download PlaceholderAPI!");
                server.getLogger().logException(e);
                server.getPluginManager().disablePlugin(plugin);
                return;
            }

            plugin.getLogger().info("PlaceholderAPI downloaded successfully");
            server.getPluginManager().loadPlugin(placeholderApi);
        }
    }
}