package kr.feathers.mc;

import kr.feathers.mc.manager.SchedulerManager;
import kr.feathers.mc.utils.ConfigUtils;
import kr.feathers.mc.utils.InitUtils;
import kr.feathers.utils.DataContainor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.logging.Logger;

import static kr.feathers.bot.MagicPluginBot.initJDA;
import static kr.feathers.bot.MagicPluginBot.jda;

@SuppressWarnings("all")
public class MagicPluginMain extends JavaPlugin implements CommandExecutor {
    private static MagicPluginMain plugin;
    public static YamlConfiguration config, data = new YamlConfiguration();
    private Logger log = Bukkit.getLogger();;
    private ConsoleCommandSender console = Bukkit.getConsoleSender();
    public static String prefix;

    public static MagicPluginMain getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        init();

        console.sendMessage("");
        console.sendMessage("§3     __   __  _______       ");
        console.sendMessage("§3    |  |_|  ||       |      §6[ Enabling EssentialsM ]");
        console.sendMessage("§3    |       ||    _  |      §c■ Authors: §7" + getDescription().getAuthors().toString());
        console.sendMessage("§3    |       ||   |_| |      §c■ Version: §7" + getDescription().getVersion());
        console.sendMessage("§3    |       ||    ___|      §c■ Special Thanks to: §7darksoldier1404");
        console.sendMessage("§3    | ||_|| ||   |          §b■ Copyright 2022, MagicPluginTeam");
        console.sendMessage("§3    |_|   |_||___|          ");
        console.sendMessage("");
    }

    @Override
    public void onDisable() {
        console.sendMessage("");
        console.sendMessage("§3     __   __  _______       ");
        console.sendMessage("§3    |  |_|  ||       |      §6[ Disabling EssentialsM ]");
        console.sendMessage("§3    |       ||    _  |      §c■ Authors: §7" + getDescription().getAuthors().toString());
        console.sendMessage("§3    |       ||   |_| |      §c■ Version: §7" + getDescription().getVersion());
        console.sendMessage("§3    |       ||    ___|      §c■ Special Thanks to: §7darksoldier1404");
        console.sendMessage("§3    | ||_|| ||   |          §b■ Copyright 2022, MagicPluginTeam");
        console.sendMessage("§3    |_|   |_||___|          ");
        console.sendMessage("");

        if (!(jda == null)) {
            try {
                jda.shutdown();
            } catch (NoClassDefFoundError e) {}
        }
        else {
            log.warning("Bot token is not available, null JDA!");
        }

        Bukkit.getScheduler().cancelTasks(plugin);
    }

    public void init() {
        /* Initilize Variables */
        plugin = this;
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        prefix = DataContainor.getPrefix();

        if (!(new File(plugin.getDataFolder(), "data.yml")).exists()) {
            data.set("generate", true);
            ConfigUtils.saveCustomData(plugin, data, "data");
        }
        data = ConfigUtils.loadCustomData(plugin, "data");

        /* Initilize Scheduler */
        SchedulerManager.InitScheduler();

        /* Initilize Commands */
        InitUtils.registerEvents();
        InitUtils.registerCommandExecutor();

        /* Initilize JDA */
        try {
            initJDA();
        }
        catch (LoginException e) {
            e.printStackTrace();
        }
    }
}

