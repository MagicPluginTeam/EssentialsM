package kr.feathers.mc.utils;

import kr.feathers.mc.MagicPluginMain;
import kr.feathers.mc.commands.MPCommand;
import kr.feathers.mc.listener.*;
import kr.feathers.utils.DataContainor;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

import static kr.feathers.bot.MagicPluginBot.jda;
import static org.bukkit.Bukkit.getPluginCommand;
import static org.bukkit.Bukkit.getPluginManager;

@SuppressWarnings("all")
public class InitUtils {
    private static final MagicPluginMain plugin = MagicPluginMain.getInstance();
    private static final Logger log = Bukkit.getLogger();

    public static void registerEvents() {
        getPluginManager().registerEvents(new MinecraftChatSyncListener(), plugin);
        getPluginManager().registerEvents(new MinecraftOtherListener(), plugin);

        if (!(DataContainor.isJoinMessageEnabled() && DataContainor.isQuitMessageEnabled())) {
            getPluginManager().registerEvents(new PlayerJoinQuitListener(), plugin);
        }
        if (DataContainor.isAFKEnabled()) {
            getPluginManager().registerEvents(new MinecraftAFKListener(), plugin);
        }
        /*
        if (DataContainor.isChatChannelEnabled()) {
            getPluginManager().registerEvents(new ChatChannelListener(), plugin);
        }
        */

        log.info("[EssentialsM] Events Registered!");
    }

    public static void registerCommandExecutor() {
        getPluginCommand("mp").setExecutor(new MPCommand());

        log.info("[EssentialsM] Commands Registered!");
    }

    public static void registerCommandTabCompleter() {
        getPluginCommand("mp").setTabCompleter(new MPCommand());

        log.info("[EssentialsM] Command TabCompleters Registered!");
    }

    public static void updateBotCommands() {
        if (jda == null) { return; }

        if (DataContainor.isStatusCommandEnabled()) {
            jda
                    .upsertCommand("status", "Check the status of server.")
                    .queue();
        }

        if (DataContainor.isVerifyCommandEnabled()) {
            jda
                    .upsertCommand("verify", "Verify you are not a bot.")
                    .addOption(OptionType.STRING, "code", "enter the code you received in the verify channel")
                    .queue();
        }

        log.info("[EssentialsM] Bot Slash Commands Registered!");
    }
}