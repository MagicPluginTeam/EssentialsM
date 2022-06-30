package kr.feathers.bot.listener;

import kr.feathers.mc.MagicPluginMain;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.*;
import java.util.logging.Logger;

import static kr.feathers.bot.MagicPluginBot.*;

@SuppressWarnings("all")
public class Commands extends ListenerAdapter {
    public static Map<User, String> verifyQueue = new HashMap<>();
    private static final Timer timer = new Timer();
    private static final Logger log = MagicPluginMain.getInstance().getLogger();

    public void onMessageReceived(MessageReceivedEvent e) {
        if (!e.getMessage().getContentRaw().startsWith(BotCommandPrefix)) { return; }

        if (e.getAuthor().isBot()) { return; }

        if (!e.isFromGuild()) {
            e.getMessage().reply("You can't use this command in DMs!").queue();
            return;
        }

        User user = e.getAuthor();
        TextChannel tc = e.getTextChannel();
        Message msg = e.getMessage();
        String[] args = msg.getContentDisplay().split(" ");

        if (args[0].equalsIgnoreCase(BotCommandPrefix + "hello")) {
            tc.sendMessage("Hello, " + user.getAsMention() + "!").queue();
        }
        else if (args[0].equalsIgnoreCase(BotCommandPrefix + "verify")) {
            if (!tc.getId().equals(VerifyChannelID)) {
                tc.sendMessage("You can only use this command in the verify channel.").queue();
                return;
            }

            if (args.length == 1) {
                if (verifyQueue.containsKey(user)) {
                    tc.sendMessage("You are already in the queue!").queue();
                    return;
                }

                String str = (new Random()).ints(48, 123)
                        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                        .limit(6)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

                tc.sendMessage(user.getAsMention() + ", here is your verification code: **" + str + "**\nPlease enter this code here in 30 seconds. `!verify <code>`").queue();
                verifyQueue.put(user, str);

                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (verifyQueue.containsKey(user)) {
                            verifyQueue.remove(user);
                            tc.sendMessage("Verification code expired.").queue();
                        }
                    }
                };
                timer.schedule(timerTask, 30 * 1000);
            }
            else if (args.length == 2) {
                if (!e.isFromGuild()) {
                    msg.reply("You can't use this command in DMs!").queue();
                    return;
                }

                if (!tc.getId().equals(VerifyChannelID)) {
                    tc.sendMessage("You can only use this command in the verify channel.").queue();
                    return;
                }

                if (!verifyQueue.containsKey(user)) {
                    tc.sendMessage("You are not in the queue!").queue();
                    return;
                }

                if (verifyQueue.get(user).equals(args[1])) {
                    tc.sendMessage("Verification successful!").queue();
                    verifyQueue.remove(user);
                    tc.getGuild().addRoleToMember(user, tc.getGuild().getRoleById(VerifiedRoleID)).queue();
                }
                else {
                    tc.sendMessage("Verification failed.").queue();
                }
            }
        }
    }
}
