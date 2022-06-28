package AMD_5900X;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@SuppressWarnings("all")
public class MagicPlugin extends JavaPlugin implements CommandExecutor {
    private static MagicPlugin instance;
    public YamlConfiguration config;
    public Logger log;
    public String prefix;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("[Plugin Enabled]");
    }

    @Override
    public void onDisable() {
        getLogger().info("[Plugin Disabled]");
    }

    public static MagicPlugin getInstance() {
        return instance;
    }
}

