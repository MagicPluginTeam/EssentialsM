package kr.feathers.Listener;

import kr.feathers.utils.JoinQuitMessageManager;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuit implements Listener {
    /* ## <-- 추후에 디코 연동 기능 제작 예정으로, Join&Quit 부분 코드가 길어질 것을 예상하여 별도로 생성함. --> ## */

    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(JoinQuitMessageManager.getSerializedJoinMessage().replace("%player%", e.getPlayer().getName()));
    }

    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(JoinQuitMessageManager.getSerializedQuitMessage().replace("%player%", e.getPlayer().getName()));
    }
}
