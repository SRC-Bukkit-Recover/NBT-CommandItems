package me.hsgamer.nbtcommanditems;

public enum ConfigEnums {
    PREFIX("messages.prefix"),
    PLAYER_ONLY("messages.player-only"),
    SUCCESSFUL("messages.successful"),
    HELP("messages.help"),
    USAGE_SET_LEFT_COMMAND("messages.usage.setleftcommand"),
    USAGE_SET_RIGHT_COMMAND("messages.usage.setrightcommand"),
    USAGE_SET_ONE_TIME_USE("messages.usage.setonetimeuse"),
    ONE_TIME_USE_INVALID_STATE("messages.one-time-use-invalid-state"),
    NO_PERMISSION("messages.no-permission"),
    NO_ITEM_HAND("messages.no-item-on-hand"),

    PERMISSION_SET_LEFT_COMMAND("permissions.setleftcommand"),
    PERMISSION_SET_RIGHT_COMMAND("permissions.setrightcommand"),
    PERMISSION_SET_ONE_TIME_USE("permissions.setonetimeuse"),
    PERMISSION_GET_COMMAND("permissions.getcommand"),
    ;
    String path;
    ConfigEnums(String path) {
        this.path = path;
    }

    public String get() {
        return path;
    }
}
