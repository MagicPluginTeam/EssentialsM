package yejunho10.magicplugin;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("all")
public class GUIPlugin extends JavaPlugin implements CommandExecutor {
    private static GUIPlugin instance;

    @Override
    public void onEnable() {
        getLogger().info("[Plugin Enabled]");

        instance = this;

        getServer().getPluginManager().registerEvents(new Event(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("[Plugin Disabled]");
    }

    public static GUIPlugin getInstance() {
        return instance;
    }
}

