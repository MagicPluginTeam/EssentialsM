package kr.feathers.mc.listener;

import kr.feathers.mc.manager.ChatChannelManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import kr.feathers.mc.MagicPluginMain;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("all")
public class ChatChannelListener implements Listener {
    private static final MagicPluginMain plugin = MagicPluginMain.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ChatChannelManager.initPlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        ChatChannelManager.leaveChannel(e.getPlayer());
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent e) {
        if (e.isCancelled()) {
            return;
        }

        ChatChannelManager.sendMessage(e.getPlayer(), e.getMessage());

        e.setCancelled(true);
    }
}