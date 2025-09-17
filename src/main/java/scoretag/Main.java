package scoretag;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class Main extends PluginBase {

    String tag;
    boolean useOs;
    boolean useFaction;
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
        useFaction = tag.contains("%factions_name%");
        useTotalHealth = tag.contains("%total_health%");

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().error("PlaceholderAPI not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getScheduler().scheduleDelayedRepeatingTask(this, new TagUpdater(this), config.getInt("update"), config.getInt("update"));
    }

    static String getFaction(Player p) {
        try {
            Class.forName("com.massivecraft.factions.P");
            return com.massivecraft.factions.P.p.getPlayerFactionTag(p);
        } catch (Exception e) {
            return "null";
        }
    }
}
