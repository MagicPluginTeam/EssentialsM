package kr.feathers.mc.listener;

import kr.feathers.mc.manager.AFKManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("all")
public class MinecraftAFKListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!((int) e.getFrom().getX() == (int) e.getTo().getX()
                && (int) e.getFrom().getY() == (int) e.getTo().getY()
                && (int) e.getFrom().getZ() == (int) e.getTo().getZ())) {
            AFKManager.setPlayerAFKStatus(e.getPlayer(), false);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        AFKManager.playerQuit(e.getPlayer());
    }
}
