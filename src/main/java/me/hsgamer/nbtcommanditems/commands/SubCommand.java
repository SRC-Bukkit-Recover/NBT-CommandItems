package me.hsgamer.nbtcommanditems.commands;

import me.hsgamer.nbtcommanditems.Utils;
import me.hsgamer.nbtcommanditems.enums.ConfigEnums;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public abstract class SubCommand implements ISubCommand {
    private String permission;
    private String usage;
    private String description;
    private boolean isConsoleAllowed;

    public SubCommand(String description, String usage, String permission, boolean isConsoleAllowed) {
        this.permission = permission;
        this.description = description;
        this.usage = usage;
        this.isConsoleAllowed = isConsoleAllowed;
    }

    public SubCommand(ConfigEnums description, ConfigEnums usage, ConfigEnums permission, boolean isConsoleAllowed) {
        this.description = (String) Utils.getValueFromConfig(description);
        this.usage = (String) Utils.getValueFromConfig(usage);
        this.permission = (String) Utils.getValueFromConfig(permission);
        this.isConsoleAllowed = isConsoleAllowed;
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        if (sender instanceof ConsoleCommandSender && !isConsoleAllowed) {
            Utils.sendMessage(sender, ConfigEnums.PLAYER_ONLY);
        } else {
            if (sender.hasPermission(permission)) {
                if (isProperUsage(sender, args)) {
                    onSubCommand(sender, args);
                } else {
                    Utils.sendMessage(sender, usage);
                }
            } else {
                Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
            }
        }
        return true;
    }

    public String getDescription() {
        return description;
    }
}
