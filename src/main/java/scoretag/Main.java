package scoretag;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class Main extends PluginBase {

    public static Config config;

    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        APIDownloader.checkAndRun(this);
        getServer().getScheduler().scheduleDelayedRepeatingTask(this, new TagUpdater(this), config.getInt("update"), config.getInt("update"));
    }
}