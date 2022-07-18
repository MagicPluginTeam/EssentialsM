package kr.feathers.mc.shceduler;

import kr.feathers.mc.MagicPluginMain;
import kr.feathers.mc.manager.AFKManager;
import org.bukkit.Bukkit;

@SuppressWarnings("all")
public class AFKScheduler {

    public static void start(long periodTicks) {
        Bukkit.getScheduler().runTaskTimer(MagicPluginMain.getInstance(), () -> {
            AFKManager.check();
        }, 0L, periodTicks);
    }
}
