package kr.feathers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static kr.feathers.MagicPlugin.prefix;

public class MPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(prefix + " §6당신은 권한이 없습니다.");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(prefix + " §6/mp <command> <args>");
            return false;
        }

        sender.sendMessage(prefix + " 어쩌라고?");

        return false;
    }
}
