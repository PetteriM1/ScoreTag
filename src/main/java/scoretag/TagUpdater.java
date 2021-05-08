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
        try {
            for (Player p : plugin.getServer().getOnlinePlayers().values()) {
                String tag = (String) plugin.placeholderAPI.getDeclaredMethod("translateString", String.class, Player.class).invoke(PlaceholderAPI.getInstance(),
                        plugin.tag.replace("%factions_name%", plugin.getFaction(p)).replace("%device_os%", plugin.getOS(p)), p);
                if (!p.getScoreTag().equals(tag)) {
                    p.setScoreTag(tag);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
