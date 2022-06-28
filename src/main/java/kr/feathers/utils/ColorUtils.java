package kr.feathers.utils;

import org.bukkit.ChatColor;

public class ColorUtils {
    public static String applyColor(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
