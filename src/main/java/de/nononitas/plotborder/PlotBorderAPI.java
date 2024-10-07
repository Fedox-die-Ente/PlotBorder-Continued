package de.nononitas.plotborder;

import com.plotsquared.core.configuration.ConfigurationUtil;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.function.pattern.Pattern;

/**
 * © 2024 Florian O and Fabian W.
 * Created on: 10/7/2024 9:17 PM
 * <p>
 * https://www.youtube.com/watch?v=tjBCjfB3Hq8
 */

public class PlotBorderAPI {

    public static boolean setPlotBorder(String type, Player player, String material) {
        PlotPlayer<Player> plotPlayer = PlotPlayer.from(player);
        Plot plot = plotPlayer.getCurrentPlot();

        if (plot == null) {
            player.sendMessage(PlotBorder.getColoredConfigString("not-on-plot"));
            return false;
        }

        if (!plot.getOwners().contains(player.getUniqueId()) && !player.hasPermission("plotborder.admin")) {
            player.sendMessage(PlotBorder.getColoredConfigString("not-your-plot"));
            return false;
        }

        Pattern pattern;
        try {
            pattern = ConfigurationUtil.BLOCK_BUCKET.parseString(material).toPattern();
        } catch (Exception e) {
            player.sendMessage("Invalid material: " + material);
            return false;
        }

        plot.getPlotModificationManager().setComponent(type, pattern, null, null);

        String changedMessage = PlotBorder.getColoredConfigString(type + "-changed").replaceAll("%name%", material);
        player.sendMessage(changedMessage);

        if (!player.hasPermission("plotborder.admin") && !player.hasPermission("plotborder.nocooldown." + type)) {
            PlotBorder.addCooldown(player);
        }

        return true;
    }

}
