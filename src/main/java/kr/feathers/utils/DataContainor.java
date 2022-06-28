package kr.feathers.utils;

import static kr.feathers.MagicPlugin.config;

@SuppressWarnings("all")
public class DataContainor {
    public static Boolean isCustomJoinMessageEnabled() {
        return config.getBoolean("CustomJoinMessage.enable");
    }

    public static Boolean getCustomQuitMessage() {
        return config.getBoolean("CustomQuitMessage.message");
    }
}
