package me.hsgamer.nbtcommanditems.enums;

import java.util.Arrays;

public enum ConfigEnums {
    PREFIX("messages.prefix", "&f[&b&lCommandItems&r&f] "),
    SUCCESSFUL("messages.successful", "&aSuccessfully executed"),

    HELP_HEADER("messages.help.header", Arrays.asList(
            "&f&l--------------------------------------------------------",
            ""
    )),
    HELP_INFO("messages.help.info", Arrays.asList(
            "&f&l/commanditems <subcommand>",
            "                &e<description>"
    )),
    HELP_FOOTER("messages.help.footer", Arrays.asList(
            "",
            "&f&l--------------------------------------------------------"
    )),

    DESCRIPTION_SET_COMMAND("messages.description.setcommand", "Set command in the specific index"),
    DESCRIPTION_ADD_COMMAND("messages.description.addcommand", "Add command to the item"),
    DESCRIPTION_DEL_COMMAND("messages.description.delcommand", "Delete command in the specific index"),
    DESCRIPTION_SET_ONE_TIME_USE("messages.description.setonetimeuse", "Toggle one-time-use"),
    DESCRIPTION_SET_SEND_AS("messages.description.setsendas", "Set command sender"),
    DESCRIPTION_GET_COMMAND("messages.description.getcommand", "Get the commands of the item"),
    DESCRIPTION_HELP("messages.description.help", "Help command"),

    USAGE_ADD_COMMAND("messages.usage.addcommand", "&cUsage: /commanditems addcommand <left/right> <command>"),
    USAGE_DEL_COMMAND("messages.usage.delcommand", "&cUsage: /commanditems delcommand <left/right> <index>"),
    USAGE_GET_COMMAND("messages.usage.getcommand", "&cUsage: /commanditems getcommand"),
    USAGE_SET_COMMAND("messages.usage.setcommand", "&cUsage: /commanditems setcommand <left/right> <index> <command>"),
    USAGE_SET_ONE_TIME_USE("messages.usage.setonetimeuse", "&cUsage: /commanditems setonetimeuse <true/false>"),
    USAGE_SET_SEND_AS("messages.usage.setsendas", "&cUsage: /commanditems setsendas <console/player/op>"),

    PLAYER_ONLY("messages.error.player-only", "&cThis command is for players only"),
    INDEX_OUT_OF_BOUND("messages.error.index-out-of-bound", "&cThe index is out of bound"),
    INVALID_INTEGER("messages.error.invalid-integer", "&cThat should be a positive number"),
    INVALID_ACTION("messages.error.invalid-integer", "&cThe click-action should be either 'left' or 'right'"),
    ONE_TIME_USE_INVALID_STATE("messages.error.one-time-use-invalid-state", "&cThe state should be either 'true' or 'false'"),
    SET_SEND_AS_INVALID_SENDER("messages.error.set-send-as-invalid-sender", "&cThe sender should be either 'console', 'player' or 'op'"),
    NO_PERMISSION("messages.no-permission", "&cYou don't have permission to do this"),
    NO_ITEM_HAND("messages.no-item-on-hand", "&cYou must have an item on your hand to do this"),

    PERMISSION_SET_COMMAND("permissions.setcommand", "commanditems.setcommand"),
    PERMISSION_ADD_COMMAND("permissions.addcommand", "commanditems.addcommand"),
    PERMISSION_DEL_COMMAND("permissions.delcommand", "commanditems.delcommand"),
    PERMISSION_SET_ONE_TIME_USE("permissions.setonetimeuse", "commanditems.setonetimeuse"),
    PERMISSION_SET_SEND_AS("permissions.setsendas", "commanditems.setsendas"),
    PERMISSION_GET_COMMAND("permissions.getcommand", "commanditems.getcommand"),

    GET_COMMAND_FOUND_LEFT("messages.getcommand.found.left-click", "&b&lLeft-Click:"),
    GET_COMMAND_FOUND_RIGHT("messages.getcommand.found.right-click", "&b&lRight-Click:"),
    GET_COMMAND_FOUND_COMMAND("messages.getcommand.found.command", "&f- <command>"),
    GET_COMMAND_FOUND_SEND_AS("messages.getcommand.found.send-as", "&b&lSend-As: &f"),
    GET_COMMAND_FOUND_ONE_TIME_USE("messages.getcommand.found.one-time-use", "&b&lOne-Time-Use: &f"),
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
