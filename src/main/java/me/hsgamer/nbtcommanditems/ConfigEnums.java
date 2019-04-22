package me.hsgamer.nbtcommanditems;

import java.util.Arrays;

public enum ConfigEnums {
    PREFIX("messages.prefix", "&f[&b&lCommandItems&r&f] "),
    PLAYER_ONLY("messages.player-only", "&cThis command is for players only"),
    SUCCESSFUL("messages.successful", "&aSuccessfully executed"),
    HELP("messages.help", Arrays.asList(
            "&f&l--------------------------------------------------------",
            "",
            "   &f&l/commanditems setleftcommand",
            "                &eAdd left-click command to the item",
            "   &f&l/commanditems setrightcommand",
            "                &eAdd right-click command to the item",
            "   &f&l/commanditems setonetimeuse",
            "                &eToggle one-time-use",
            "   &f&l/commanditems setsendas",
            "                &eSet command sender",
            "   &f&l/commanditems getcommand",
            "                &eGet command of the item",
            "   &f&l/commanditems help",
            "                &eHelp command",
            "",
            "&f&l--------------------------------------------------------"
    )),
    USAGE_ADD_LEFT_COMMAND("messages.usage.addleftcommand", "&cUsage: /commanditems addleftcommand <command>"),
    USAGE_ADD_RIGHT_COMMAND("messages.usage.addrightcommand", "&cUsage: /commanditems addrightcommand <command>"),
    USAGE_DEL_LEFT_COMMAND("messages.usage.delleftcommand", "&cUsage: /commanditems delleftcommand <index>"),
    USAGE_DEL_RIGHT_COMMAND("messages.usage.delrightcommand", "&cUsage: /commanditems delrightcommand <index>"),
    USAGE_SET_LEFT_COMMAND("messages.usage.setleftcommand", "&cUsage: /commanditems setleftcommand <index> <command>"),
    USAGE_SET_RIGHT_COMMAND("messages.usage.setrightcommand", "&cUsage: /commanditems setrightcommand <index> <command>"),
    USAGE_SET_ONE_TIME_USE("messages.usage.setonetimeuse", "&cUsage: /commanditems setonetimeuse <true/false>"),
    USAGE_SET_SEND_AS("messages.usage.setsendas", "&cUsage: /commanditems setsendas <console/player/op>"),
    INDEX_OUT_OF_BOUND("messages.index-out-of-bound", "&cThe index is out of bound"),
    INVALID_INTEGER("messages.invalid-integer", "&cThat should be a positive number"),
    ONE_TIME_USE_INVALID_STATE("messages.one-time-use-invalid-state", "&cThe state should be 'true' or 'false'"),
    SET_SEND_AS_INVALID_SENDER("messages.set-send-as-invalid-sender", "&cThe sender should be 'console', 'player' or 'op'"),
    NO_PERMISSION("messages.no-permission", "&cYou don't have permission to do this"),
    NO_ITEM_HAND("messages.no-item-on-hand", "&cYou must have an item on your hand to do this"),

    PERMISSION_SET_LEFT_COMMAND("permissions.setleftcommand", "commanditems.setleftcommand"),
    PERMISSION_SET_RIGHT_COMMAND("permissions.setrightcommand", "commanditems.setrightcommand"),
    PERMISSION_ADD_LEFT_COMMAND("permissions.addleftcommand", "commanditems.addleftcommand"),
    PERMISSION_ADD_RIGHT_COMMAND("permissions.addrightcommand", "commanditems.addrightcommand"),
    PERMISSION_DEL_LEFT_COMMAND("permissions.delleftcommand", "commanditems.delleftcommand"),
    PERMISSION_DEL_RIGHT_COMMAND("permissions.delrightcommand", "commanditems.delrightcommand"),
    PERMISSION_SET_ONE_TIME_USE("permissions.setonetimeuse", "commanditems.setonetimeuse"),
    PERMISSION_SET_SEND_AS("permissions.setsendas", "commanditems.setsendas"),
    PERMISSION_GET_COMMAND("permissions.getcommand", "commanditems.getcommand"),

    GET_COMMAND_FOUND("messages.getcommand.found", Arrays.asList(
            "&b&lLeft-Click: &f<left-command>",
            "&b&lRight-Click: &f<right-command>",
            "&b&lSend-As: &f<send-as>",
            "&b&lOne-Time-Use: &f<one-time-use>"
    )),
    GET_COMMAND_NOT_FOUND("messages.getcommand.not-found", "&cThis item does not contain commands");
    String path;
    Object def;

    ConfigEnums(String path, Object def) {
        this.path = path;
        this.def = def;
    }

    public String getPath() {
        return path;
    }

    public Object getDef() {
        return def;
    }
}
