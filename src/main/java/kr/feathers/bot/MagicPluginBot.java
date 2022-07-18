package kr.feathers.bot;

import kr.feathers.bot.commands.SlashCommand;
import kr.feathers.bot.utils.RichPresenceUtils;
import kr.feathers.mc.listener.MinecraftChatSyncListener;
import kr.feathers.mc.utils.InitUtils;
import kr.feathers.utils.DataContainor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;

import javax.security.auth.login.LoginException;
import java.util.logging.Logger;

@SuppressWarnings("all")
public class MagicPluginBot {
    public static JDA jda;
    private static Boolean isBotRunning;
    private static Logger log = Bukkit.getLogger();
    public static String BotToken,
            WelcomeByeChannelID,
            VerifyChannelID,
            ChatSyncChannelID,
            VerifiedRoleID;

    public static Boolean isBotRunning() {
        return isBotRunning;
    }

    public static void initJDA() throws LoginException {
        /* [ <-- Init Variables --> ] */
        initVars();

        if (BotToken.equals("YOUR_BOT_TOKEN")) {
            log.warning("Bot Token is empty, please set it in config.yml");
            log.warning("Disabling JDA!");
            isBotRunning = false;
            return;
        }

        /* [ <-- Build JDA --> ] */
        jda = JDABuilder.createDefault(BotToken).build();

        /* [ <-- Add Listener --> ] */
        jda.addEventListener(new SlashCommand());
        jda.addEventListener(new MinecraftChatSyncListener());

        /* [ <-- Init Slash Commands --> ] */
        InitUtils.updateBotCommands();

        /* [ <-- Set Rich Presence --> ] */
        RichPresenceUtils.setStatus(DataContainor.getBotStatus());
        RichPresenceUtils.setActivity(Activity.playing(DataContainor.getBotRichPresence()), true);

        /* [ <-- Other Inits... --> ] */
        isBotRunning = true;
    }

    public static void initVars() {
        BotToken = DataContainor.getBotToken();
        VerifyChannelID = DataContainor.getVerifyChannelID();
        ChatSyncChannelID = DataContainor.getChatSyncChannelID();
        VerifiedRoleID = DataContainor.getVerifiedRoleID();
    }
}
