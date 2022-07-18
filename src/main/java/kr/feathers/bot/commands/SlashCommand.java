package kr.feathers.bot.commands;

import kr.feathers.mc.MagicPluginMain;
import kr.feathers.utils.DataContainor;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.logging.Logger;

import static kr.feathers.bot.MagicPluginBot.*;

@SuppressWarnings("all")
public class SlashCommand extends ListenerAdapter {
    public static Map<User, String> verifyQueue = new HashMap<>();
    private static final Logger log = MagicPluginMain.getInstance().getLogger();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        if (!e.isFromGuild()) {
            e.getHook().sendMessage("You can't use this command in DMs!");
            return;
        }

        User user = e.getUser();
        TextChannel tc = e.getTextChannel();
        String command = e.getName();

        //TODO
        if (command.equalsIgnoreCase("verify")) {
            if (!tc.getId().equals(DataContainor.getVerifyChannelID())) {
                e.getHook().sendMessage("You can only use this command in the verify channel.").queue();
                return;
            }

            if (e.getOption("code") == null) {
                if (verifyQueue.containsKey(user)) {
                    e.getHook().sendMessage("You are already in the queue!").queue();
                    return;
                }

                String str = (new Random()).ints(48, 123)
                        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                        .limit(6)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

                e.getHook().sendMessage(user.getAsMention() + ", here is your verification code: **" + str + "**\nPlease enter this code here in 30 seconds. `/verify <code>`").queue();
                verifyQueue.put(user, str);

                Bukkit.getScheduler().runTaskLater(MagicPluginMain.getInstance(), () -> {
                    if (verifyQueue.containsKey(user)) {
                        verifyQueue.remove(user);
                        e.getHook().sendMessage("Verification code expired.").queue();
                    }
                }, 30L*20L);
            }
            else {
                if (!tc.getId().equals(VerifyChannelID)) {
                    e.getHook().sendMessage("You can only use this command in the verify channel.").queue();
                    return;
                }

                if (!verifyQueue.containsKey(user)) {
                    e.getHook().sendMessage("You are not in the queue!").queue();
                    return;
                }

                if (verifyQueue.get(user).equals(e.getOption("code").getAsString())) {
                    e.getHook().sendMessage("Verification successful!").queue();
                    verifyQueue.remove(user);
                    tc.getGuild().addRoleToMember(user, tc.getGuild().getRoleById(VerifiedRoleID)).queue();
                }
                else {
                    e.getHook().sendMessage("Verification failed.").queue();
                }
            }
        }
    }

}
