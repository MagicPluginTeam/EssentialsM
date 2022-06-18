package yejunho10.magicplugin;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import yejunho10.magicplugin.event.Event;

@SuppressWarnings("all")
public class GUIPlugin extends JavaPlugin implements CommandExecutor {
    private static GUIPlugin instance;

    @Override
    public void onEnable() {
        getLogger().info("[플러그인이 활성화됩니다]");

        instance = this;

        getServer().getPluginManager().registerEvents(new Event(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("[플러그인이 비활성화됩니다]");
    }

    public static GUIPlugin getInstance() {
        return instance;
    }
}

