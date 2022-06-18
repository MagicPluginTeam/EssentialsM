package yejunho10.magicplugin.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("all")
public class Event implements Listener {
    private Logger log = Bukkit.getLogger();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        log.log(Level.INFO, "{0}님이 {1}위치의 {2}블럭을 {3}으로 파괴했습니다.",
                new Object[]{p.getName(), e.getBlock().getLocation(), e.getBlock().getType().toString(), p.getInventory().getItemInMainHand().getType().toString()});
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        log.log(Level.INFO, "{0}님이 {1}위치에 {2}블럭을 설치했습니다.",
                new Object[]{p.getName(), e.getBlock().getLocation(), e.getBlock().getType().toString()});
    }

    @EventHandler
    public void on
}