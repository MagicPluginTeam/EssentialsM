package kr.feathers.bot.utils;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import static kr.feathers.bot.MagicPluginBot.jda;

public class RichPresenceUtils {
    public static void setStatus(OnlineStatus os) {
        jda.getPresence().setStatus(os);
    }

    public static void setActivity(Activity ac, Boolean idle) {
        jda.getPresence().setPresence(ac, idle);
    }
}
