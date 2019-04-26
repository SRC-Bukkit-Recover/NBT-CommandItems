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

public class AddCommand extends SubCommand {
    public AddCommand() {
        super(ConfigEnums.DESCRIPTION_ADD_COMMAND, ConfigEnums.USAGE_ADD_COMMAND, ConfigEnums.PERMISSION_ADD_COMMAND, false);
    }

    @Override
    public void onSubCommand(CommandSender sender, String[] args) {
        ItemStack item = Utils.getItem((Player) sender);
        if (!item.getType().equals(Material.AIR)) {
            NBTItem nbtItem = new NBTItem(item);
            String s = String.join(" ", new ArrayList<>(Arrays.asList(args).subList(2, args.length)));
            if (args[1].equalsIgnoreCase("left")) {
                addCommand((Player) sender, nbtItem, s, NBTEnums.LEFT_CLICK);
            } else if (args[1].equalsIgnoreCase("right")) {
                addCommand((Player) sender, nbtItem, s, NBTEnums.RIGHT_CLICK);
            } else {
                Utils.sendMessage(sender, ConfigEnums.INVALID_ACTION);
            }
        } else {
            Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
        }
    }

    private void addCommand(Player player, NBTItem nbtItem, String command, NBTEnums nbtEnums) {
        List<String> sl = new ArrayList<>();
        if (nbtItem.hasKey(nbtEnums.get())) {
            try {
                sl = Utils.toStrings(nbtItem.getByteArray(NBTEnums.LEFT_CLICK.get()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sl.add(command);
        byte[] b = new byte[0];
        try {
            b = Utils.toBytes(sl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        nbtItem.setByteArray(nbtEnums.get(), b);
        Utils.setItem(player, nbtItem.getItem());
        Utils.sendMessage(player, ConfigEnums.SUCCESSFUL);
    }

    @Override
    public boolean isProperUsage(CommandSender sender, String[] args) {
        return args.length > 2;
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
