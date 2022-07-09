package kr.feathers.utils;

import kr.feathers.mc.MagicPluginMain;
import kr.feathers.utils.Quadruple;
import net.dv8tion.jda.api.OnlineStatus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
                Bukkit.getLogger().warning("Invalid status in config.yml & set status to online");
                return OnlineStatus.ONLINE;
        }
    }

    public static String getChatSyncJoinMessage() {
        return config.getString("ChatSync.joinMessage");
    }

    public static String getChatSyncQuitMessage() {
        return config.getString("ChatSync.quitMessage");
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

    public static Boolean isPVPBattleEnabled() {
        return config.getBoolean("PVPBattle.enable");
    }

    public static Integer getPVPBattleTme() {
        return config.getInt("PVPBattle.time");
    }

    public static Boolean isPVPStadium1Enabled() {
        return config.getBoolean("PVPStadium.1.enable");
    }

    public static Boolean isPVPStadium2Enabled() {
        return config.getBoolean("PVPStadium.2.enable");
    }

    public static Boolean isPVPStadium3Enabled() {
        return config.getBoolean("PVPStadium.3.enable");
    }

    public static Boolean isPVPStadium4Enabled() {
        return config.getBoolean("PVPStadium.4.enable");
    }

    public static Boolean isPVPStadium5Enabled() {
        return config.getBoolean("PVPStadium.5.enable");
    }

    public static Location getPVPStadiumSpawn(Integer stadiumNum, Integer spawnNum) {
        if (stadiumNum < 1 || stadiumNum > 5) { return null; }
        if (spawnNum < 1 || spawnNum > 2) { return null; }

        String key = "PVPStadium." + stadiumNum + ".spawn." + spawnNum;

        return new Location(Bukkit.getWorld(config.getString(key + ".world")),
                config.getDouble(key + ".x"),
                config.getDouble(key + ".y"),
                config.getDouble(key + ".z"));
    }

    public static Boolean isPVPRankEnabled() {
        return config.getBoolean("PVPRank.enable");
    }

    public static Boolean isPVPRankRewardEnabled() {
        return config.getBoolean("PVPRank.reward.enable");
    }

    public static ItemStack getPVPRankRewardItem(Integer rank) {
        if (rank < 1 || rank > 3) { return null; }

        return new ItemStack(Material.getMaterial(config.getString("PVPRank.reward." + rank + ".item")), config.getInt("PVPRank.reward." + rank + ".amount"));
    }

    public static Boolean isVerifyCommandEnabled() {
        return config.getBoolean("VerifyCommandEnabled");
    }
}
