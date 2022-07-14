package kr.feathers.mc.utils;

import kr.feathers.mc.MagicPluginMain;
import kr.feathers.mc.commands.MPCommand;
import kr.feathers.mc.listener.ChatSync;
import kr.feathers.mc.listener.Event;
import kr.feathers.mc.listener.JoinQuit;
import kr.feathers.utils.DataContainor;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getPluginCommand;
import static org.bukkit.Bukkit.getPluginManager;

@SuppressWarnings("all")
public class InitUtils {
    private static final MagicPluginMain plugin = MagicPluginMain.getInstance();
    private static final Logger log = Bukkit.getLogger();

    public static void registerEvents() {
        getPluginManager().registerEvents(new JoinQuit(), plugin);
        getPluginManager().registerEvents(new ChatSync(), plugin);
        getPluginManager().registerEvents(new Event(), plugin);

        log.info("Events Registered!");
    }

    public static void registerCommandExecutor() {
        getPluginCommand("mp").setExecutor(new MPCommand());

        log.info("Commands Registered!");
    }

    public static void registerCommandTabCompleter() {
        getPluginCommand("mp").setTabCompleter(new MPCommand());

        log.info("Command TabCompleters Registered!");
    }
}