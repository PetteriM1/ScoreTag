package scoretag;

import cn.nukkit.Player;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;

public class TagUpdater implements Runnable {

    private final Main plugin;

    public TagUpdater(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player p : plugin.getServer().getOnlinePlayers().values()) {
            String tag = PlaceholderAPI.getInstance().translateString(plugin.tag.replace("%factions_name%", plugin.getFaction(p)).replace("%device_os%", plugin.getOS(p)), p);
            p.setScoreTag(tag);
        }
    }
}
