package me.hsgamer.nbtcommanditems.commands.subcommands;

import me.hsgamer.nbtcommanditems.NBTCommandItems;
import me.hsgamer.nbtcommanditems.Utils;
import me.hsgamer.nbtcommanditems.commands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AboutCommand extends SubCommand {
    public AboutCommand() {
        super("About the author", "", "", true);
    }

    @Override
    public void onSubCommand(CommandSender sender, String[] args) {
        List<String> messages = Arrays.asList(
                "&b&lPlugin &f:" + NBTCommandItems.getInstance().getDescription().getName(),
                "&b&lAuthor &f:" + NBTCommandItems.getInstance().getDescription().getAuthors(),
                "&b&lVersion &f:" + NBTCommandItems.getInstance().getDescription().getVersion()
        );
        Utils.sendMessage(sender, messages, true);
    }

    @Override
    public boolean isProperUsage(CommandSender sender, String[] args) {
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
