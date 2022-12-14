package scoretag;

import cn.nukkit.Player;

public class TagUpdater implements Runnable {

    private final Main plugin;

    public TagUpdater(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        try {
            for (Player p : plugin.getServer().getOnlinePlayers().values()) {
                String tag = (String) plugin.tag.replace("%factions_name%", plugin.getFaction(p)).replace("%device_os%", plugin.getOS(p));
                if (Main.api != null) {
                    tag = Main.api.translateString(tag, p);
                    if (!p.getScoreTag().equals(tag)) {
                        p.setScoreTag(tag);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
