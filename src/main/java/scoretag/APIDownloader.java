package scoretag;

import cn.nukkit.Server;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;

public class APIDownloader {

    public static void checkAndRun(Main plugin) {
        Server server = plugin.getServer();

        if (server.getPluginManager().getPlugin("PlaceholderAPI") != null) return;

        plugin.getLogger().info("Downloading PlaceholderAPI...");

        String placeholderApi = server.getFilePath() + "/plugins/PlaceholderAPI.jar";

        try {
            FileOutputStream fos = new FileOutputStream(placeholderApi);
            fos.getChannel().transferFrom(Channels.newChannel(new URL("https://sites.google.com/site/downloadcenterpetterim1/PlaceholderAPI.jar").openStream()), 0, Long.MAX_VALUE);
            fos.close();
        } catch (Exception e) {
            server.getLogger().logException(e);
            server.getPluginManager().disablePlugin(plugin);
            return;
        }

        plugin.getLogger().info("PlaceholderAPI downloaded successfully");
        server.getPluginManager().loadPlugin(placeholderApi);
    }
}