package kr.feathers.utils;

import net.dv8tion.jda.api.OnlineStatus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

@SuppressWarnings("all")
public class DataContainor {
    public static YamlConfiguration config, data = new YamlConfiguration();

    /* [ <-- Public Settings --> ] */
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

    /* [ <-- Discord Bot Settings --> ] */
    public static String getBotToken() {
        return config.getString("BotToken");
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
                Bukkit.getLogger().warning("Invalid status in config.yml & set status to online");
                return OnlineStatus.ONLINE;
        }
    }

    public static Boolean isVerifyCommandEnabled() {
        return config.getBoolean("VerifyCommandEnabled");
    }

    public static Boolean isStatusCommandEnabled() {
        return config.getBoolean("StatusCommandEnabled");
    }

    /* [ <-- ChatSync --> ] */

    public static String getChatSyncJoinMessage() {
        return config.getString("ChatSync.joinMessage");
    }

    public static String getChatSyncQuitMessage() {
        return config.getString("ChatSync.quitMessage");
    }

    public static String getChatSyncPlayerDeathMessage() {
        return config.getString("ChatSync.playerDeathMessage");
    }

    public static String getChatSyncMessage() {
        return config.getString("ChatSync.message");
    }

    public static String getChatReceivedFromDiscordMessage() {
            return config.getString("ChatSync.messageReceivedFromDiscord");
    }

    public static String getChatSyncPlayerAFKMessage() {
        return config.getString("ChatSync.afkMessage");
    }

    public static boolean isChatSyncChatMessageEnabled() {
        return config.getBoolean("EnabledChatSync.chat");
    }

    public static boolean isChatSyncJoinMessageEnabled() {
        return config.getBoolean("EnabledChatSync.join");
    }

    public static boolean isChatSyncQuitMessageEnabled() {
        return config.getBoolean("EnabledChatSync.quit");
    }

    public static boolean isChatSyncPlayerDeathMessageEnabled() {
        return config.getBoolean("EnabledChatSync.death");
    }

    public static boolean isChatSyncPlayerAFKMessageEnabled() {
        return config.getBoolean("EnabledChatSync.afk");
    }

    /* [ <-- AFK --> ] */
    public static Boolean isAFKEnabled() {
        return config.getBoolean("AFK.enable");
    }

    public static Integer getAFKTime() {
        return config.getInt("AFK.time");
    }

    public static String getAFKMessage() {
        return config.getString("AFK.message");
    }

    public static String getAFKDisableMessage() {
        return config.getString("AFK.disable_message");
    }

    public static Boolean isAFKTeleportEnabled() {
        return config.getBoolean("AFK.teleport.enable");
    }

    public static Location getAFKTeleportLocation() {
        return new Location(
                Bukkit.getWorld(config.getString("AFK.teleport.location.world")),
                config.getDouble("AFK.teleport.location.x"),
                config.getDouble("AFK.teleport.location.y"),
                config.getDouble("AFK.teleport.location.z"));
    }

    /* [ <-- ChatChannel --> ] */
    public static Boolean isChatChannelEnabled() {
        return config.getBoolean("ChatChannel.enable");
    }

    public static String getChatMessage() {
        return config.getString("ChatChannel.message");
    }
}