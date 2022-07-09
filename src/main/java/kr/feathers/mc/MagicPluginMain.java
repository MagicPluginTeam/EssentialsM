package kr.feathers.mc;

import kr.feathers.mc.listener.ChatSync;
import kr.feathers.mc.listener.Event;
import kr.feathers.mc.listener.JoinQuit;
import kr.feathers.mc.commands.MPCommand;
import kr.feathers.mc.utils.ConfigUtils;
import kr.feathers.utils.DataContainor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.logging.Logger;

import static kr.feathers.bot.MagicPluginBot.initJDA;
import static kr.feathers.bot.MagicPluginBot.jda;
import static org.bukkit.Bukkit.getPluginCommand;
import static org.bukkit.Bukkit.getPluginManager;

@SuppressWarnings("all")
public class MagicPluginMain extends JavaPlugin implements CommandExecutor {
    private static MagicPluginMain plugin;
    public static YamlConfiguration config;
    private Logger log;
    private ConsoleCommandSender console;
    public static String prefix;

    public static MagicPluginMain getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        log = getLogger();
        prefix = DataContainor.getPrefix();
        console = Bukkit.getConsoleSender();

        /* ## <- Setting Executor -> ## */
        getPluginCommand("mp").setExecutor(new MPCommand());

        /* ## <- Setting Event Listener -> ## */
        getPluginManager().registerEvents(new JoinQuit(), this);
        getPluginManager().registerEvents(new ChatSync(), this);
        getPluginManager().registerEvents(new Event(), this);

        try {
            initJDA();
        }
        catch (LoginException e) {
            e.printStackTrace();
        }

        console.sendMessage("");
        console.sendMessage("§3     __   __  _______       ");
        console.sendMessage("§3    |  |_|  ||       |      §cEnabling §6EssentialsM");
        console.sendMessage("§3    |       ||    _  |      §4■ Authors: §7" + getDescription().getAuthors().toString());
        console.sendMessage("§3    |       ||   |_| |      §4■ Version: §7" + getDescription().getVersion());
        console.sendMessage("§3    |       ||    ___|      §4■ Special Thanks to: §7darksoldier1404");
        console.sendMessage("§3    | ||_|| ||   |          §b■ Copyright 2022, MagicPluginTeam");
        console.sendMessage("§3    |_|   |_||___|          ");
        console.sendMessage("");
    }

    @Override
    public void onDisable() {
        if (!(jda == null)) {
            jda.shutdownNow();
        }
        else {
            log.warning("§cBot token is not available, do not shutting down JDA.");
        }

        console.sendMessage("");
        console.sendMessage("§3     __   __  _______       ");
        console.sendMessage("§3    |  |_|  ||       |      §cDisabling §6EssentialsM");
        console.sendMessage("§3    |       ||    _  |      §4■ Authors: §7" + getDescription().getAuthors().toString());
        console.sendMessage("§3    |       ||   |_| |      §4■ Version: §7" + getDescription().getVersion());
        console.sendMessage("§3    |       ||    ___|      §4■ Special Thanks to: §7darksoldier1404");
        console.sendMessage("§3    | ||_|| ||   |          §b■ Copyright 2022, MagicPluginTeam");
        console.sendMessage("§3    |_|   |_||___|          ");
        console.sendMessage("");
    }
}

