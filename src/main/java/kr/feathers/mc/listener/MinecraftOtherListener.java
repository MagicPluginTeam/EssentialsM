package kr.feathers.mc.listener;

import kr.feathers.mc.utils.LocationUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

import static kr.feathers.mc.MagicPluginMain.prefix;

public class MinecraftOtherListener implements org.bukkit.event.Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if (!e.getPlayer().getWorld().getName().equals("world")) { return; }

        Player p = e.getPlayer();

        if (e.isBedSpawn() || e.isAnchorSpawn()) { return; }

        e.setRespawnLocation(LocationUtils.getRandomLocation(p, 100, 5000, 100, 5000));
        p.sendMessage(prefix + " §cYou have been spawned in random location.");
    }
}
