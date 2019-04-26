package me.hsgamer.nbtcommanditems.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface ISubCommand {
    void onSubCommand(CommandSender sender, String[] args);

    boolean isProperUsage(CommandSender sender, String[] args);

    List<String> onTabComplete(CommandSender sender, String[] args);
}
