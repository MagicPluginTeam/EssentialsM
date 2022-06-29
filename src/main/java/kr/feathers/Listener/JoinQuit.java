package kr.feathers.Listener;

import kr.feathers.utils.ColorUtils;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static kr.feathers.MagicPlugin.config;

@SuppressWarnings("all")
public class JoinQuit implements Listener {
    /* ## <-- 추후에 디코 연동 기능 제작 예정으로, Join&Quit 부분 코드가 길어질 것을 예상하여 별도로 생성함. --> ## */

    public void onJoin(PlayerJoinEvent e) {
        if (config.getBoolean("Join.enable")) {
            e.setJoinMessage(ColorUtils.applyColor(
                    config.getString("Join.message")
                            .replace("%player%", e.getPlayer().getName())));
        }
    }
}
