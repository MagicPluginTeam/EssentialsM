package kr.feathers.mc.commands;

import kr.feathers.mc.MagicPluginMain;
import kr.feathers.mc.utils.RandomCoordinate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static kr.feathers.mc.MagicPluginMain.prefix;

@SuppressWarnings("all")
public class MPCommand implements CommandExecutor {
    MagicPluginMain core = MagicPluginMain.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(prefix + "§cThis command is only can used by player.");
            return false;
        }

        if (args.length == 0) {
            p.sendMessage(prefix + " §cTry this: §6/mp help");
            return false;
        }

        else if (args[0].equalsIgnoreCase("ping")) {
            p.sendMessage(prefix + " §cPing: §6" + p.getPing());
        }
        else if (args[0].equalsIgnoreCase("rtp")) {
            if (!p.getWorld().getName().equals("world")) {
                p.sendMessage(prefix + " §cYou can only use this command in world.");
                return false;
            }

            p.teleport(RandomCoordinate.getRandomLocation(p, 100, 5000, 100, 5000));
            p.sendMessage(prefix + " §cYou have been teleported to a random location.");

            return true;
        }
        else if (args[0].equalsIgnoreCase("help")) {
            p.sendMessage("");
            p.sendMessage("§6Check your ping: §c/mp ping");
            p.sendMessage("§6Teleport to a random location: §c/mp rtp");
            p.sendMessage("");
        }
        else {
            p.sendMessage(prefix + " §cTry this: §6/mp help");
            return true;
        }

        return false;
    }
}
