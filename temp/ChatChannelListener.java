package kr.feathers.mc.listener;

import kr.feathers.mc.manager.ChatChannelManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import kr.feathers.mc.MagicPluginMain;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("all")
public class ChatChannelListener implements Listener {
    private static final MagicPluginMain plugin = MagicPluginMain.getInstance();

    @Nullable
    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        ChatChannelManager.initPlayer(e.getPlayer());
    }

    @Nullable
    @EventHandler
    public void onQuit(@NotNull PlayerQuitEvent e) {
        ChatChannelManager.leaveChannel(e.getPlayer());
    }

    @Nullable
    @EventHandler
    public void onAsyncChat(@NotNull AsyncPlayerChatEvent e) {
        ChatChannelManager.sendMessage(e.getPlayer(), e.getMessage(), e.isCancelled());
        e.setCancelled(true);
    }
}