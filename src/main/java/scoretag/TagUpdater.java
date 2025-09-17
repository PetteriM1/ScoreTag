package scoretag;

import cn.nukkit.Player;
import cn.nukkit.utils.Utils;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;

import java.util.Locale;

public class TagUpdater implements Runnable {

    private final Main plugin;

    public TagUpdater(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player p : plugin.getServer().getOnlinePlayers().values()) {
            String str = plugin.tag;
            if (plugin.useOs) {
                str = str.replace("%device_os%", Utils.getOS(p));
            }
            if (plugin.useFaction) {
                str = str.replace("%factions_name%", Main.getFaction(p));
            }
            if (plugin.useTotalHealth) {
                str = str.replace("%total_health%", String.format(Locale.US, "%.1f", p.getHealth() + p.getAbsorption()));
            }

            p.setScoreTag(PlaceholderAPI.getInstance().translateString(str, p));
        }
    }
}
