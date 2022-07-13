package kr.feathers.mc.manager;

import kr.feathers.utils.DataContainor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public class AFKManager {
    private static Map<Player, Location> playerLocations = new HashMap<>();
    private static Set<Player> afkingPlayers = new HashSet<>();
    private static final Location afk_loc = DataContainor.getAFKTeleportLocation();

    public static void check() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!playerLocations.containsKey(p)) { continue; }

            if (playerLocations.get(p).equals(p.getLocation())) {
                setPlayerAFKStatus(p, true);
            }
        }
    }

    public static void setPlayerAFKStatus(Player p, boolean isAFKNow) {
        if (isAFKNow) { //true
            afkingPlayers.add(p);
        }
        else { //false
            if (!afkingPlayers.contains(p)) { return; }
            afkingPlayers.remove(p);
        }

        if (DataContainor.isAFKTeleportEnabled()) {
            p.teleport(afk_loc);
            p.sendMessage(DataContainor.getAFKMessage());
        }
    }
}
