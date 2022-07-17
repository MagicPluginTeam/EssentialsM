package kr.feathers.mc.manager;

import kr.feathers.mc.shceduler.AFKScheduler;
import kr.feathers.utils.DataContainor;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

@SuppressWarnings("all")
public class SchedulerManager {
    private static final Logger log = Bukkit.getLogger();

    private static void startAFKScheduler(long period) {
        AFKScheduler.start(period);
    }

    public static void InitScheduler() {
        if (DataContainor.isAFKEnabled()) {
            startAFKScheduler(DataContainor.getAFKTime());
        }

        log.info("[EssentialsM] Schedulers Started!");
    }
}
