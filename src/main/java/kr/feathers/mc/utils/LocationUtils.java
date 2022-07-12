package kr.feathers.mc.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("all")
public class LocationUtils {
    public static Location getRandomLocation(Player p, int minx, int maxx, int minz, int maxz) {
        Location loc;
        int x, y, z;

        do {
            x = ThreadLocalRandom.current().nextInt(maxx - (minx) + 1) + (minx);
            z = ThreadLocalRandom.current().nextInt(maxz - (minz) + 1) + (minz);
            y = p.getWorld().getHighestBlockAt(x, z).getY();

            loc = new Location(p.getWorld(), x, y, z);
        } while(loc.getBlock().getType().equals(Material.WATER) || loc.getBlock().getType().equals(Material.LAVA));

        loc = new Location(p.getWorld(), x, y + 1, z);

        loc.getChunk().load();

        return loc;
    }
}
