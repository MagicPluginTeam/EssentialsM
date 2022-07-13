package kr.feathers.mc.listener;

import kr.feathers.bot.utils.ChatSyncUtils;
import kr.feathers.utils.DataContainor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

@SuppressWarnings("all")
public class ChatSync extends ListenerAdapter implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ChatSyncUtils.sendJoinMessage(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        ChatSyncUtils.sendQuitMessage(e.getPlayer());
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        ChatSyncUtils.sendChatMessage(e.getMessage(), e.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        ChatSyncUtils.sendPlayerDeath(e.getEntity().getPlayer(), e.getDeathMessage());
    }

    /*
    @EventHandler
    public void onGoalAdvancement(PlayerAdvancementDoneEvent e) {
        if (e.getAdvancement() == null || e.getAdvancement().getKey().getKey().contains("recipe/")) return;
Configurationally
        ChatSyncUtils.sendGoalAdvancement(e.getPlayer(), e.getAdvancement());
    }
    */

    public void onMessageReceived(MessageReceivedEvent e) {
        if (!e.getTextChannel().getId().equals(DataContainor.getChatSyncChannelID())) { return; }
        if (e.getAuthor().isBot()) { return; }

        String str = DataContainor.getChatReceivedFromDiscordMessage()
                        .replace("%user_name%", e.getMember().getEffectiveName())
                        .replace("%message%", e.getMessage().getContentRaw());

        Bukkit.broadcastMessage(str);
    }
}
