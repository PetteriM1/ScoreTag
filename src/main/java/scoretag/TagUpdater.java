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
            p.setScoreTag(PlaceholderAPI.getInstance().translateString(plugin.config.getString("tag"), p));
        }
    }
}