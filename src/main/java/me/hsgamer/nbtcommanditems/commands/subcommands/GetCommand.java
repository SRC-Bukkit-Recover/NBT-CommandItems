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
import java.util.List;

public class GetCommand extends SubCommand {
    public GetCommand() {
        super(ConfigEnums.DESCRIPTION_GET_COMMAND, ConfigEnums.USAGE_GET_COMMAND, ConfigEnums.PERMISSION_GET_COMMAND, false);
    }

    @Override
    public void onSubCommand(CommandSender sender, String[] args) {
        ItemStack item = Utils.getItem((Player) sender);
        if (!item.getType().equals(Material.AIR)) {
            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.hasKey(NBTEnums.LEFT_CLICK.get())
                    || nbtItem.hasKey(NBTEnums.RIGHT_CLICK.get())
                    || nbtItem.hasKey(NBTEnums.ONE_TIME_USE.get())
                    || nbtItem.hasKey(NBTEnums.SEND_AS.get())) {
                List<String> found = new ArrayList<>();
                String command = (String) Utils.getValueFromConfig(ConfigEnums.GET_COMMAND_FOUND_COMMAND);
                // LEFT COMMAND
                found.add((String) Utils.getValueFromConfig(ConfigEnums.GET_COMMAND_FOUND_LEFT));
                if (nbtItem.hasKey(NBTEnums.LEFT_CLICK.get())) {
                    try {
                        for (String string : Utils.toStrings(nbtItem.getByteArray(NBTEnums.LEFT_CLICK.get()))) {
                            found.add(command.replace("<command>", string));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    found.add(command.replace("<command>", ""));
                }
                // RIGHT COMMAND
                found.add((String) Utils.getValueFromConfig(ConfigEnums.GET_COMMAND_FOUND_RIGHT));
                if (nbtItem.hasKey(NBTEnums.RIGHT_CLICK.get())) {
                    try {
                        for (String string : Utils.toStrings(nbtItem.getByteArray(NBTEnums.RIGHT_CLICK.get()))) {
                            found.add(command.replace("<command>", string));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    found.add(command.replace("<command>", ""));
                }
                // ONE-TIME-USE
                found.add(Utils.getValueFromConfig(ConfigEnums.GET_COMMAND_FOUND_ONE_TIME_USE) + String.valueOf(nbtItem.getBoolean(NBTEnums.ONE_TIME_USE.get())));
                // SEND-AS
                found.add(Utils.getValueFromConfig(ConfigEnums.GET_COMMAND_FOUND_SEND_AS) + nbtItem.getString(NBTEnums.SEND_AS.get()));
                Utils.sendMessage(sender, found, true);
            } else {
                Utils.sendMessage(sender, ConfigEnums.GET_COMMAND_NOT_FOUND);
            }
        } else {
            Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
        }
    }

    @Override
    public boolean isProperUsage(CommandSender sender, String[] args) {
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
