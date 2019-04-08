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
    NO_PERMISSION("messages.no-permission")
    ;
    String path;
    ConfigEnums(String path) {
        this.path = path;
    }

    public String get() {
        return path;
    }
}
