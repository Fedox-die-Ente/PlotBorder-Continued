package de.nononitas.plotborder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlotBorderCmd implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "rl":
                case "reload":
                    if(sender.hasPermission("plotborder.admin")) {
                        PlotBorder.getPlugin().reloadConfig();
                        sender.sendMessage(PlotBorder.PREFIX + "§aConfig reloaded");
                    } else {
                        sender.sendMessage(PlotBorder.PREFIX + "§cNo permissions");
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        if(sender instanceof Player) {

            if(args.length == 1) {
                Player p = (Player) sender;
                if(p.hasPermission("plotborder.admin")) {
                    commands.add("rl");
                }

            }
        }


        return commands;
    }


}
