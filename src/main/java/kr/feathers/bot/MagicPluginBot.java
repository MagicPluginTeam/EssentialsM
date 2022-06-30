package kr.feathers.bot;

import kr.feathers.bot.listener.Commands;
import kr.feathers.bot.utils.RichPresenceUtils;
import kr.feathers.mc.utils.DataContainor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
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
        initVars();

        jda = JDABuilder.createDefault(BotToken).build();

        jda.addEventListener(new Commands());
        RichPresenceUtils.setStatus(OnlineStatus.ONLINE);
        RichPresenceUtils.setActivity(Activity.playing("FEATHER SERVER"), true);
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
