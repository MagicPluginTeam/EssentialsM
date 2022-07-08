package kr.feathers.bot;

import kr.feathers.bot.listener.Commands;
import kr.feathers.bot.utils.RichPresenceUtils;
import kr.feathers.mc.listener.ChatSync;
import kr.feathers.utils.DataContainor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

@SuppressWarnings("all")
public class MagicPluginBot {
    public static JDA jda;
    public static String BotToken,
            BotCommandPrefix,
            WelcomeByeChannelID,
            VerifyChannelID,
            ChatSyncChannelID,
            VerifiedRoleID;

    public static void initJDA() throws LoginException {
        /* [ <-- Init Variables --> ] */
        initVars();

        /* [ <-- Build JDA --> ] */
        jda = JDABuilder.createDefault(BotToken).build();

        /* [ <-- Add Listener --> ] */
        jda.addEventListener(new Commands());
        jda.addEventListener(new ChatSync());

        /* [ <-- Set Rich Presence --> ] */
        RichPresenceUtils.setStatus(DataContainor.getBotStatus());
        RichPresenceUtils.setActivity(Activity.playing(DataContainor.getBotRichPresence()), true);

        /* [ <-- Other Inits... --> ] */

    }

    public static void initVars() {
        BotToken = DataContainor.getBotToken();
        BotCommandPrefix = DataContainor.getBotCommandPrefix();
        WelcomeByeChannelID = DataContainor.getWelcomeByeChannelID();
        VerifyChannelID = DataContainor.getVerifyChannelID();
        ChatSyncChannelID = DataContainor.getChatSyncChannelID();
        VerifiedRoleID = DataContainor.getVerifiedRoleID();
    }
}
