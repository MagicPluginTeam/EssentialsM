package kr.feathers.mc.manager;

import kr.feathers.utils.DataContainor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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
    private static Map<Player, Location> beforeLocations = new HashMap<>();
    private static Map<Player, GameMode> beforeGameModes = new HashMap<>();

    public static void check() {
        //if there's no online players, clear all
        if (Bukkit.getOnlinePlayers().size() == 0) {
            playerLocations.clear();
            afkingPlayers.clear();
            return;
        }

        // remove offline players
        playerLocations.forEach((key, value) -> {
            if (key.isOnline()) {
                playerLocations.remove(key);
                beforeGameModes.remove(key);
                afkingPlayers.remove(key);
            }
        });

        //save all online player's location
        Bukkit.getOnlinePlayers().forEach(p -> {
            if (!playerLocations.containsKey(p)) {
                return;
            }

            if (playerLocations.get(p).getX() == p.getLocation().getX()
                && playerLocations.get(p).getY() == p.getLocation().getY()
                && playerLocations.get(p).getZ() == p.getLocation().getZ()) {
                setPlayerAFKStatus(p, true);
            }
            else {
                setPlayerAFKStatus(p, false);
            }

            playerLocations.put(p, p.getLocation());
        });
    }

    public static void setPlayerAFKStatus(Player p, boolean isAFKNow) {
        if (isAFKNow) { //true
            beforeLocations.put(p, p.getLocation());

            afkingPlayers.add(p);

            p.sendMessage(DataContainor.getAFKMessage()
                    .replace("%player%", p.getName()));

            if (DataContainor.isAFKTeleportEnabled()) {
                p.teleport(DataContainor.getAFKTeleportLocation());
            }

            p.setGameMode(GameMode.SPECTATOR);
        }
        else { //false
            if (!afkingPlayers.contains(p)) {
                return;
            }

            p.sendMessage(DataContainor.getAFKDisableMessage()
                    .replace("%player%", p.getName()));

            if (DataContainor.isAFKTeleportEnabled()) {
                p.teleport(beforeLocations.get(p));

                beforeLocations.remove(p);
            }

            afkingPlayers.remove(p);

            p.setGameMode(GameMode.SURVIVAL);
        }
    }

    public static void playerQuit(Player p) {
        if (playerLocations.containsKey(p)) {
            playerLocations.remove(p);
        }

        setPlayerAFKStatus(p, false);
    }

    public static boolean isAFKNow(Player p) {
        if (afkingPlayers.contains(p)) {
            return true;
        }

        return false;
    }
}