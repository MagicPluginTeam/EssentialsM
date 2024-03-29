package kr.feathers.bot.utils;

import kr.feathers.bot.MagicPluginBot;
import kr.feathers.mc.manager.AFKManager;
import kr.feathers.mc.utils.PlayerUtils;
import kr.feathers.utils.DataContainor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.Color;

import static kr.feathers.bot.MagicPluginBot.jda;

@SuppressWarnings("all")
public class ChatSyncUtils {
    private static final String ChatSyncChannelID = DataContainor.getChatSyncChannelID();
    private static final TextChannel ChatSyncChannel = jda.getTextChannelById(ChatSyncChannelID);

    public static String getChatSyncChannelID() {
        return ChatSyncChannelID;
    }

    public static TextChannel getChatSyncChannel() {
        return ChatSyncChannel;
    }

    public static void sendChatMessage(String message, Player ChatPlayer) {
        if (!(MagicPluginBot.isBotRunning() || DataContainor.isChatSyncChatMessageEnabled())) { return; }

        String str = DataContainor.getChatSyncMessage()
                .replace("%player%", "`" + ChatPlayer.getName() + "`")
                .replace("%message%", message);

        ChatSyncChannel.sendMessage(str).queue();
    }

    public static void sendJoinMessage(Player JoinPlayer) {
        if (!(MagicPluginBot.isBotRunning() || DataContainor.isChatSyncJoinMessageEnabled())) { return; }

        String str = DataContainor.getChatSyncJoinMessage()
                .replace("%player%", JoinPlayer.getName());

        EmbedBuilder eb = new EmbedBuilder()
                .setAuthor(str, null, PlayerUtils.getAvatarUrl(JoinPlayer))
                .setDescription("Now Online: " + Bukkit.getOnlinePlayers().size() + " / " + Bukkit.getMaxPlayers())
                .setColor(Color.green);

        ChatSyncChannel.sendMessageEmbeds(eb.build()).queue();
    }

    public static void sendQuitMessage(Player QuitPlayer) {
        if (!(MagicPluginBot.isBotRunning() || DataContainor.isChatSyncQuitMessageEnabled())) { return; }

        String str = DataContainor.getChatSyncQuitMessage()
                .replace("%player%", QuitPlayer.getName());

        EmbedBuilder eb = new EmbedBuilder()
                .setAuthor(str, null, PlayerUtils.getAvatarUrl(QuitPlayer))
                .setDescription("Now Online: " + (Bukkit.getOnlinePlayers().size() - 1) + " / " + Bukkit.getMaxPlayers())
                .setColor(Color.red);

        ChatSyncChannel.sendMessageEmbeds(eb.build()).queue();
    }

    public static void sendPlayerDeath(Player DeathPlayer, String cause) {
        if (!(MagicPluginBot.isBotRunning() || DataContainor.isChatSyncPlayerDeathMessageEnabled())) { return; }

        String str = DataContainor.getChatSyncPlayerDeathMessage()
                .replace("%player%", DeathPlayer.getName())
                .replace("%cause%", cause);

        EmbedBuilder eb = new EmbedBuilder()
                .setAuthor(str, null, PlayerUtils.getAvatarUrl(DeathPlayer))
                .setColor(Color.BLACK);

        ChatSyncChannel.sendMessageEmbeds(eb.build()).queue();
    }

    public static void sendPlayerAFK(Player p) {
        if (!(MagicPluginBot.isBotRunning() || DataContainor.isChatSyncPlayerAFKMessageEnabled())) { return; }

        String str = DataContainor.getAFKMessage()
                .replace("%player%", p.getName())
                .replace("%status%", AFKManager.isAFKNow(p) ? "true" : "false");

        EmbedBuilder eb = new EmbedBuilder()
                .setAuthor(str, null, PlayerUtils.getAvatarUrl(p))
                .setColor(Color.CYAN);

        ChatSyncChannel.sendMessageEmbeds(eb.build()).queue();
    }
}
