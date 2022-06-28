package kr.feathers.utils;

@SuppressWarnings("all")
public class JoinQuitMessageManager {
    private static String joinMessage = "&e%player% joined the game";
    private static String quitMessage = "&e%player% left the game";

    public static String getJoinMessage() {
        return joinMessage;
    }

    public static String getQuitMessage() {
        return quitMessage;
    }

    public static void setJoinMessage(String str) {
        joinMessage = str;
    }

    public static void setQuitMessage(String str) {
        quitMessage = str;
    }

    public static String getSerializedJoinMessage() {
        return ColorUtils.applyColor(joinMessage);
    }

    public static String getSerializedQuitMessage() {
        return ColorUtils.applyColor(quitMessage);
    }
}
