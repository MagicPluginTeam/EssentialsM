package kr.feathers.mc.shceduler;

import kr.feathers.mc.manager.AFKManager;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("all")
public class AFKScheduler {
    private static Timer AFKTimer = new Timer();

    public static void start(long a) {
        AFKTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                AFKManager.check();
            }
        }, 0, a*20);
    }

    public static void stop() {
        AFKTimer.cancel();
    }
}
