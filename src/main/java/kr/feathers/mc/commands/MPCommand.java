package kr.feathers.mc.commands;

import kr.feathers.mc.MagicPluginMain;
import kr.feathers.mc.utils.ConfigUtils;
import kr.feathers.mc.utils.LocationUtils;
import kr.feathers.utils.DataContainor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static kr.feathers.mc.MagicPluginMain.config;
import static kr.feathers.mc.MagicPluginMain.prefix;

@SuppressWarnings("all")
public class MPCommand implements CommandExecutor, TabCompleter {
    MagicPluginMain plugin = MagicPluginMain.getInstance();

    @Nullable
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(prefix + "§cThis command is only can used by player.");
            return false;
        }

        if (args.length == 0) {
            p.sendMessage(prefix + " §7Wrong Command: §c/mp help");
            return false;
        }

        else if (args[0].equalsIgnoreCase("ping")) {
            p.sendMessage(prefix + " §cYour Ping: §6" + p.getPing());
        }
        else if (args[0].equalsIgnoreCase("rtp")) {
            if (!p.getWorld().getName().equals("world")) {
                p.sendMessage(prefix + " §cYou can only use this command in world.");
                return false;
            }

            p.teleport(LocationUtils.getRandomLocation(p, 100, 5000, 100, 5000));
            p.sendMessage(prefix + " §cYou have been teleported to a random location.");

            return true;
        }
        else if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();

            config = ConfigUtils.loadDefaultPluginConfig(plugin);
            prefix = DataContainor.getPrefix();
        }
        else if (args[0].equalsIgnoreCase("help")) {
            p.sendMessage(prefix + " §cHelp: §6/mp ping §c- §fCheck your ping.");
            p.sendMessage(prefix + " §cHelp: §6/mp rtp §c- §fTeleport to a random location.");
            p.sendMessage(prefix + " §cHelp: §6/mp reload §c- §fReload config.");
        }
        else {
            p.sendMessage(prefix + " §cTry this: §6/mp help");
        }

        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String labal, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("ping", "rtp", "reload", "help");
        }
        
        return List.of("");
    }
}
