package scoretag;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class Main extends PluginBase {

    String tag;

    public void onEnable() {
        if (!APIDownloader.checkAndRun(this)) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        saveDefaultConfig();
        Config config = getConfig();
        tag = config.getString("tag");
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().error("PlaceholderAPI not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getServer().getScheduler().scheduleDelayedRepeatingTask(this, new TagUpdater(this), config.getInt("update"), config.getInt("update"));
    }

    public String getFaction(Player p) {
        try {
            Class.forName("com.massivecraft.factions.P");
            return com.massivecraft.factions.P.p.getPlayerFactionTag(p);
        } catch (Exception e) {
            return "null";
        }
    }

    public String getOS(Player p) {
        switch (p.getLoginChainData().getDeviceOS()) {
            case 1: return "Android";
            case 2: return "iOS";
            case 3: return "macOS";
            case 4: return "Fire OS";
            case 5: return "Gear VR";
            case 6: return "HoloLens";
            case 7: return "Windows 10";
            case 8: return "Windows";
            case 9: return "Dedicated";
            case 10: return "tvOS";
            case 11: return "PlayStation";
            case 12: return "Switch";
            case 13: return "Xbox";
            case 14: return "Windows Phone";
            default: return "Unknown";
        }
    }
}
