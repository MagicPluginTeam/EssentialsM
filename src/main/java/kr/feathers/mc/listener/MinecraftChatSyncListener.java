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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("all")
public class MinecraftChatSyncListener implements Listener {
    @Nullable
    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        ChatSyncUtils.sendJoinMessage(e.getPlayer());
    }

    @Nullable
    @EventHandler
    public void onQuit(@NotNull PlayerQuitEvent e) {
        ChatSyncUtils.sendQuitMessage(e.getPlayer());
    }

    @Nullable
    @EventHandler
    public void onAsyncPlayerChat(@NotNull AsyncPlayerChatEvent e) {
        ChatSyncUtils.sendChatMessage(e.getMessage(), e.getPlayer());
    }

    @Nullable
    @EventHandler
    public void onPlayerDeath(@NotNull PlayerDeathEvent e) {
        ChatSyncUtils.sendPlayerDeath(e.getEntity().getPlayer(), e.getDeathMessage());
    }
}
