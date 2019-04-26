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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetCommand extends SubCommand {
    public SetCommand() {
        super(ConfigEnums.DESCRIPTION_SET_COMMAND, ConfigEnums.USAGE_SET_COMMAND, ConfigEnums.PERMISSION_SET_COMMAND, false);
    }

    @Override
    public void onSubCommand(CommandSender sender, String[] args) {
        ItemStack item = Utils.getItem((Player) sender);
        if (!item.getType().equals(Material.AIR)) {
            NBTItem nbtItem = new NBTItem(item);
            String s = String.join(" ", new ArrayList<>(Arrays.asList(args).subList(3, args.length)));
            if (args[1].equalsIgnoreCase("left")) {
                setCommand((Player) sender, nbtItem, args[2], s, NBTEnums.LEFT_CLICK);
            } else if (args[1].equalsIgnoreCase("right")) {
                setCommand((Player) sender, nbtItem, args[2], s, NBTEnums.RIGHT_CLICK);
            } else {
                Utils.sendMessage(sender, ConfigEnums.INVALID_ACTION);
            }
        } else {
            Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
        }
    }

    private void setCommand(Player player, NBTItem nbtItem, String indexStr, String command, NBTEnums nbtEnums) {
        List<String> sl = new ArrayList<>();
        if (nbtItem.hasKey(nbtEnums.get())) {
            try {
                sl = Utils.toStrings(nbtItem.getByteArray(NBTEnums.LEFT_CLICK.get()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            int index = Integer.parseInt(indexStr);
            if (index >= 0 && index <= sl.size() - 1) {
                sl.set(index, command);
                byte[] b = new byte[0];
                try {
                    b = Utils.toBytes(sl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                nbtItem.setByteArray(nbtEnums.get(), b);
                Utils.setItem(player, nbtItem.getItem());
                Utils.sendMessage(player, ConfigEnums.SUCCESSFUL);
            } else {
                Utils.sendMessage(player, ConfigEnums.INDEX_OUT_OF_BOUND);
            }
        } catch (NumberFormatException e) {
            Utils.sendMessage(player, ConfigEnums.INVALID_INTEGER);
        }
    }

    @Override
    public boolean isProperUsage(CommandSender sender, String[] args) {
        return args.length > 3;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> list = new ArrayList<>();

        if (args.length == 2) {
            list.add("left");
            list.add("right");
        }

        return list;
    }
}
