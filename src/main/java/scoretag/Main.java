package scoretag;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class Main extends PluginBase {

    String tag;
    boolean useOs;
    boolean useTotalHealth;

    @Override
    public void onEnable() {
        if (!APIDownloader.checkAndRun(this)) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        saveDefaultConfig();
        Config config = getConfig();
        tag = config.getString("tag");
        useOs = tag.contains("%device_os%");
        useTotalHealth = tag.contains("%total_health%");

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().error("PlaceholderAPI not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getScheduler().scheduleDelayedRepeatingTask(this, new TagUpdater(this), config.getInt("update"), config.getInt("update"));
    }
}
