package kr.feathers.mc.utils;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import static kr.feathers.mc.MagicPluginMain.config;

@SuppressWarnings("all")
public class DataContainor {
    public static String getPrefix() {
        return config.getString("LogPrefix");
    }

    /* [ <-- Join&Quit Message --> ] */
    public static Boolean isJoinMessageEnabled() {
        return config.getBoolean("Join.enable");
    }

    public static Boolean isQuitMessageEnabled() {
        return config.getBoolean("Quit.enable");
    }

    public static String getJoinMessage() {
        return config.getString("Join.message");
    }

    public static String getQuitMessage() {
        return config.getString("Quit.message");
    }

    public static String getBotToken() {
        return config.getString("BotToken");
    }

    public static String getBotCommandPrefix() {
        return config.getString("BotCommandPrefix");
    }

    public static String getWelcomeByeChannelID() {
        return config.getString("WelcomeByeChannelID");
    }

    public static String getVerifyChannelID() {
        return config.getString("VerifyChannelID");
    }

    public static String getChatSyncChannelID() {
        return config.getString("ChatSyncChannelID");
    }

    public static String getVerifiedRoleID() {
        return config.getString("VerifiedRoleID");
    }

    public static String getBotRichPresence() {
        return config.getString("BotStatus.richPresence");
    }

    public static OnlineStatus getBotStatus() {
        return OnlineStatus.valueOf(config.getString("BotStatus.status"));
    }

    public static String getChatSyncJoinMessage() {
        return config.getString("ChatSync.JoinMessage");
    }

    public static String getChatSyncQuitMessage() {
        return config.getString("ChatSync.QuitMessage");
    }

    public static String getGoalAdvancementMessage() {
        return config.getString("ChatSync.goalAdvancementMessage");
    }

    public static String getPlayerDeathMessage() {
        return config.getString("ChatSync.playerDeathMessage");
    }
}
