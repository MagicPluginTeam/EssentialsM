package kr.feathers.mc.utils;

import org.bukkit.entity.Player;

public class PlayerUtils {
    public static String getAvatarUrl(Player p) {
        return "https://cravatar.eu/helmavatar/" + p.getUniqueId() + "/100.png";
    }
}
