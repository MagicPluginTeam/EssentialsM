package kr.feathers.mc.listener;

import kr.feathers.mc.utils.RandomCoordinate;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import static kr.feathers.mc.MagicPluginMain.prefix;

public class Event implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if (!e.getPlayer().getWorld().getName().equals("world")) { return; }

        Player p = e.getPlayer();

        if (e.isBedSpawn() || e.isAnchorSpawn()) { return; }

        e.setRespawnLocation(RandomCoordinate.getRandomLocation(p, 100, 5000, 100, 5000));
        p.sendMessage(prefix + " §cYou have been spawned in random location.");
    }
}
