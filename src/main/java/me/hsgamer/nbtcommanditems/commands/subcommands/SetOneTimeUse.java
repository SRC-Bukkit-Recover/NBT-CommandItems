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

public class SetOneTimeUse extends SubCommand {
    public SetOneTimeUse() {
        super(ConfigEnums.DESCRIPTION_SET_ONE_TIME_USE, ConfigEnums.USAGE_SET_ONE_TIME_USE, ConfigEnums.PERMISSION_SET_ONE_TIME_USE, false);
    }

    @Override
    public void onSubCommand(CommandSender sender, String[] args) {
        if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false")) {
            boolean b = Boolean.parseBoolean(args[1].toLowerCase());
            ItemStack item = Utils.getItem((Player) sender);
            if (!item.getType().equals(Material.AIR)) {
                NBTItem nbtItem = new NBTItem(item);
                nbtItem.setBoolean(NBTEnums.ONE_TIME_USE.get(), b);
                Utils.setItem((Player) sender, nbtItem.getItem());
                Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
            } else {
                Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
            }
        } else {
            Utils.sendMessage(sender, ConfigEnums.ONE_TIME_USE_INVALID_STATE);
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
            list.add("true");
            list.add("false");
        }

        return list;
    }
}
