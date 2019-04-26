package me.hsgamer.nbtcommanditems.commands.subcommands;

import de.tr7zw.itemnbtapi.NBTItem;
import me.hsgamer.nbtcommanditems.Utils;
import me.hsgamer.nbtcommanditems.commands.SubCommand;
import me.hsgamer.nbtcommanditems.enums.ConfigEnums;
import me.hsgamer.nbtcommanditems.enums.NBTEnums;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SetSendAs extends SubCommand {
    public SetSendAs() {
        super(ConfigEnums.DESCRIPTION_SET_SEND_AS, ConfigEnums.USAGE_SET_SEND_AS, ConfigEnums.PERMISSION_SET_SEND_AS, false);
    }

    @Override
    public void onSubCommand(CommandSender sender, String[] args) {
        if (args[1].equalsIgnoreCase("console") || args[1].equalsIgnoreCase("op") || args[1].equalsIgnoreCase("player")) {
            ItemStack item = Utils.getItem((Player) sender);
            if (!item.getType().equals(Material.AIR)) {
                NBTItem nbtItem = new NBTItem(item);
                nbtItem.setString(NBTEnums.SEND_AS.get(), args[1].toLowerCase());
                Utils.setItem((Player) sender, nbtItem.getItem());
                Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
            } else {
                Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
            }
        } else {
            Utils.sendMessage(sender, ConfigEnums.SET_SEND_AS_INVALID_SENDER);
        }
    }

    @Override
    public boolean isProperUsage(CommandSender sender, String[] args) {
        return args.length == 2;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> list = new ArrayList<>();

        if (args.length == 2) {
            list.add("console");
            list.add("player");
            list.add("op");
        }

        return list;
    }
}
