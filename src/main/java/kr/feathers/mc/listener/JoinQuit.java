package kr.feathers.mc.listener;

import kr.feathers.utils.DataContainor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("all")
public class JoinQuit implements Listener {
    /* ## <-- 추후에 디코 연동 기능 제작 예정으로, Join&Quit 부분 코드가 길어질 것을 예상하여 별도로 생성함. --> ## */

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (DataContainor.isJoinMessageEnabled()) {
            e.setJoinMessage(
                    DataContainor.getJoinMessage()
                            .replace("%player%", e.getPlayer().getName()));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (DataContainor.isQuitMessageEnabled()) {
            e.setQuitMessage(
                    DataContainor.getQuitMessage()
                            .replace("%player%", e.getPlayer().getName()));
        }
    }
}
