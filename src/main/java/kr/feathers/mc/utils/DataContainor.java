package kr.feathers.mc.utils;

import kr.feathers.mc.MagicPluginMain;
import net.dv8tion.jda.api.OnlineStatus;

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
        switch(config.getString("BotStatus.status")) {
            case "online":
                return OnlineStatus.ONLINE;
            case "idle":
                return OnlineStatus.IDLE;
            case "dnd":
                return OnlineStatus.DO_NOT_DISTURB;
            case "invisible":
                return OnlineStatus.INVISIBLE;
            case "offline":
                return OnlineStatus.OFFLINE;
            default:
                MagicPluginMain.getInstance().getLogger().warning("Invalid status in config.yml & set status to online");
                return OnlineStatus.ONLINE;
        }
    }

    public static String getChatSyncJoinMessage() {
        return config.getString("ChatSync.joinMessage");
    }

    public static String getChatSyncQuitMessage() {
        return config.getString("ChatSync.quitMessage");
    }

    public static String getGoalAdvancementMessage() {
        return config.getString("ChatSync.goalAdvancementMessage");
    }

    public static String getPlayerDeathMessage() {
        return config.getString("ChatSync.playerDeathMessage");
    }

    public static String getChatSyncMessage() {
        return config.getString("ChatSync.message");
    }

    public static String getMessageReceivedFromDiscord() {
            return config.getString("ChatSync.messageReceivedFromDiscord");
    }

    public static String getServerStatusToChatSyncChannel() {
        return config.getString("ServerStatus.ChatSyncChannel");
    }

    public static Integer getServerStatusUpdateInterval() {
        return config.getInt("ServerStatus.UpdateInterval");
    }
}
