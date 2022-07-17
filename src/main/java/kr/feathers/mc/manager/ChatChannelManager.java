package kr.feathers.mc.manager;

import kr.feathers.mc.MagicPluginMain;
import kr.feathers.utils.DataContainor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static kr.feathers.mc.MagicPluginMain.prefix;
import static kr.feathers.mc.MagicPluginMain.data;

@SuppressWarnings("all")
public class ChatChannelManager {
    private static final MagicPluginMain plugin = MagicPluginMain.getInstance();
    private static final Map<Integer, Set<Player>> chatChannels = new HashMap<>();

    public static void joinChannel(Player p, int ChannelID) {
        int id;

        if (!chatChannels.containsKey(ChannelID)) {
            p.sendMessage(prefix + "§7Channel §cnot §7found.");

            joinDefault(p);

            return;
        }

        leaveChannel(p);

        if (chatChannels.containsKey(ChannelID)) {
            p.sendMessage(prefix + " §7Channel §cnot §7found. please §cgenerate chat channel §7before join.");
        }
        id = ChannelID;

        setChannel(p, id);
        p.sendMessage(prefix + " §7Your chat channel is set to §c" + id + "§7.");
    }

    public static void joinDefault(Player p) {
        chatChannels.get(0).add(p);
        setChannel(p, 0);
        p.sendMessage(prefix + " §7Your chat channel is set to §cdefault§7.");

        if (chatChannels.get(0).size() == 1) {
            return;
        }

        for (Player ccp : chatChannels.get(0)) {
            ccp.sendMessage(prefix + " §7" + p.getName() + " §7joined the chat channel.");
        }
    }

    public static void createChannel(Player p, int ChannelID) {
        if (chatChannels.containsKey(ChannelID)) {
            p.sendMessage(prefix + " §7Channel §c" + ChannelID + " §7already exists.");
            return;
        }

        chatChannels.put(ChannelID, Set.of(p));

        p.sendMessage(prefix + " §7Chat channel §c" + ChannelID + "§7 created.");

        joinChannel(p, ChannelID);
    }

    public static void removeChannel(int ChannelID) {
        chatChannels.remove(ChannelID);

        Bukkit.broadcastMessage(prefix + " §7Chat channel §c" + ChannelID + "§7 removed.");
    }

    public static void leaveChannel(Player p) {
        int id = getChannel(p);

        chatChannels.get(id).remove(p);

        if (chatChannels.get(id).isEmpty()) {
            removeChannel(id);
            return;
        }

        for (Player ccp : chatChannels.get(getChannel(p))) {
            ccp.sendMessage(prefix + " §7Player §c" + p.getName() + "§7 left chat channel.");
        }
    }

    public static int getChannel(Player p) {
        return data.getInt(p.getUniqueId().toString() + ".channel");
    }

    public static void setChannel(Player p, int ChannelID) {
        data.set(p.getUniqueId().toString() + ".channel", ChannelID);
    }

    public static void sendMessage(Player p, String Message) {
        for (Player ccp : chatChannels.get(getChannel(p))) {
            ccp.sendMessage(DataContainor.getChatMessage()
                    .replace("%player%", p.getName())
                    .replace("%message%", Message)
                    .replace("%channel%", String.valueOf(getChannel(p))));
        }
    }

    public static void initPlayer(Player p) {
        if (!chatChannels.containsKey(0)) {
            chatChannels.put(0, Set.of());
        }

        if (!data.contains(p.getUniqueId().toString() + ".channel")) {
            joinDefault(p);
            return;
        }

        joinChannel(p, getChannel(p));
    }
}