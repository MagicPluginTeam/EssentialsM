package kr.feathers.mc.listener;

import kr.feathers.utils.DataContainor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("all")
public class DiscordChatSyncListener extends ListenerAdapter {
    @Nullable
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        if (!e.getTextChannel().getId().equals(DataContainor.getChatSyncChannelID())) { return; }
        if (e.getAuthor().isBot()) { return; }

        String str = DataContainor.getChatReceivedFromDiscordMessage()
                .replace("%user_name%", e.getMember().getEffectiveName())
                .replace("%message%", e.getMessage().getContentRaw());

        Bukkit.broadcastMessage(str);
    }
}
