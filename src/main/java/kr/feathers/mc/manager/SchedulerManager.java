package kr.feathers.mc.manager;

import kr.feathers.mc.shceduler.AFKScheduler;
import kr.feathers.utils.DataContainor;

public class SchedulerManager {
    public static void startAFKScheduler(long period) {
        AFKScheduler.start(period);
    }

    public static void stopAFKScheduler() {
        AFKScheduler.stop();
    }

    public static void InitScheduler() {
        if (DataContainor.isAFKEnabled()) {
            startAFKScheduler(DataContainor.getAFKTime());
        }
    }
}
