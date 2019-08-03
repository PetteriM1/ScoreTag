package scoretag;

import cn.nukkit.Player;

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;

public class TagUpdater extends Thread {

    private Main plugin;

    public TagUpdater(Main plugin) {
        this.plugin = plugin;
        setName("TagUpdater");
    }

    @Override
    public void run() {
        for (Player p : plugin.getServer().getOnlinePlayers().values()) {
            String tag = PlaceholderAPI.getInstance().translateString(plugin.config.getString("tag").replace("%factions_name%", plugin.getFaction(p)).replace("%device_os%", plugin.getOS(p)), p);
            if (!p.getScoreTag().equals(tag)) {
                p.setScoreTag(tag);
            }
        }
    }
}