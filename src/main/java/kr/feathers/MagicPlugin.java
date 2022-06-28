package kr.feathers;

import kr.feathers.Listener.JoinQuit;
import kr.feathers.commands.MPCommand;
import kr.feathers.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@SuppressWarnings("all")
public class MagicPlugin extends JavaPlugin implements CommandExecutor {
    private static MagicPlugin plugin;
    public static YamlConfiguration config;
    private Logger log;
    public static String prefix;

    public static MagicPlugin getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        log = getLogger();
        prefix = config.getString("prefix");

        /* ## <- Setting Executor -> ## */
        getCommand("mp").setExecutor(new MPCommand());

        /* ## <- Setting Event Listener -> ## */
        Bukkit.getPluginManager().registerEvents(new JoinQuit(), plugin);

        log.info("[ <- MagicPlugin 활성화 -> ]");
    }

    @Override
    public void onDisable() {
        ConfigUtils.savePluginConfig(plugin, config);

        log.info("[ <- MagicPlugin 비활성화 -> ]");
    }
}

