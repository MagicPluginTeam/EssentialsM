package kr.feathers.mc.commands;

import kr.feathers.bot.MagicPluginBot;
import kr.feathers.mc.MagicPluginMain;
import kr.feathers.mc.manager.AFKManager;
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

import static kr.feathers.bot.MagicPluginBot.initVars;
import static kr.feathers.bot.MagicPluginBot.isBotRunning;
import static kr.feathers.mc.MagicPluginMain.config;
import static kr.feathers.mc.MagicPluginMain.prefix;

@SuppressWarnings("all")
public class MPCommand implements CommandExecutor, TabCompleter {
    MagicPluginMain plugin = MagicPluginMain.getInstance();

    @Nullable
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(prefix + "§7This command is only can used by §cplayer§7.");
            return false;
        }

        if (args.length == 0) {
            p.sendMessage(prefix + " §7Wrong Command. - §c/mp help");
            return false;
        }

        else if (args[0].equalsIgnoreCase("ping")) {
            p.sendMessage(prefix + " §cYour Ping: §6" + p.getPing());

            return true;
        }
        else if (args[0].equalsIgnoreCase("rtp")) {
            if (args.length > 1) {
                p.sendMessage(prefix + " §7Usages: §6/mp rtp");
                return false;
            }

            if (!p.getWorld().getName().equals("world")) {
                p.sendMessage(prefix + " §cYou can only use this command in world.");
                return false;
            }

            p.teleport(LocationUtils.getRandomLocation(p, 100, 5000, 100, 5000));
            p.sendMessage(prefix + " §cYou have been teleported to a random location.");

            return true;
        }
        else if (args[0].equalsIgnoreCase("reload")) {
            if (args.length > 1) {
                p.sendMessage(prefix + " §7Usages: §6/mp reload");
                return false;
            }

            plugin.reloadConfig();

            config = ConfigUtils.loadDefaultPluginConfig(plugin);
            prefix = DataContainor.getPrefix();

            initVars();

            p.sendMessage(prefix + " §aConfig has been reloaded.");

            return true;
        }
        else if (args[0].equalsIgnoreCase("debug")) {
            if (!p.isOp()) {
                sender.sendMessage(prefix + " §7You §cdon't have permission §7to use this command.");
                return false;
            }

            if (args.length < 2) {
                sender.sendMessage(prefix + " §7Usages: §7/mp debug <function>");
                return false;
            }

            if (args[1].equalsIgnoreCase("afk")) {
                if (!(args.length == 3)) {
                    p.sendMessage(prefix + " §7Usages: §6/mp debug afk <true/false>");
                    return false;
                }

                if (args[2].equalsIgnoreCase("true")) {
                    AFKManager.setPlayerAFKStatus(p, true);
                    return true;
                }
                else if (args[2].equalsIgnoreCase("false")) {
                    AFKManager.setPlayerAFKStatus(p, false);
                    return true;
                }
                else {
                    p.sendMessage(prefix + " §7Usages: §6/mp debug afk <true/false>");
                    return false;
                }
            }
            else if (args[1].equalsIgnoreCase("bot")) {
                if (args.length > 2) {
                    p.sendMessage(prefix + " §7Usages: §6/mp debug bot");
                    return false;
                }

                if (isBotRunning()) {
                    p.sendMessage(prefix + " §aBot is currently running now.");
                }
                else {
                    p.sendMessage(prefix + " §cBot is currently not running now.");
                }

                return true;
            }
            else {
                p.sendMessage(prefix + " §7Usages: §6/mp debug <function>");
                return false;
            }
        }
        else if (args[0].equalsIgnoreCase("help")) {
            p.sendMessage("");
            p.sendMessage("§6/mp ping §c- §7Check your ping.");
            p.sendMessage("§6/mp rtp §c- §7Teleport to a random location.");
            p.sendMessage("§6/mp reload §c- §7Reload config.");
            p.sendMessage("");

            return true;
        }
        else {
            p.sendMessage(prefix + " §7Wrong Command. - §c/mp help");

            return false;
        }
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String labal, @NotNull String[] args) {
        if (args.length == 1) {
            return sender.isOp() ? List.of("ping", "rtp", "reload", "help", "debug") : List.of("ping", "rtp", "reload", "help");
        }
        else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("debug")) {
                return sender.isOp() ? List.of("afk", "bot") : List.of("");
            }
        }
        else if (args.length == 3) {
            if (args[1].equalsIgnoreCase("afk")) {
                return sender.isOp() ? List.of("true", "false") : List.of("");
            }
            else {
                return List.of("");
            }
        }

        return List.of("");
    }
}
