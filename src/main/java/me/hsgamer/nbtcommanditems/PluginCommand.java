package me.hsgamer.nbtcommanditems;

import de.tr7zw.itemnbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PluginCommand implements TabExecutor, CommandExecutor {
    private static final String SET_LEFT_COMMAND = "setleftcommand";
    private static final String SET_RIGHT_COMMAND = "setrightcommand";
    private static final String SET_ONE_TIME_USE = "setonetimeuse";
    private static final String GET_COMMAND = "getcommand";
    private static final String HELP = "help";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1 || args[0].equalsIgnoreCase("help")) {
            Utils.sendMessage(sender, (List<String>) Utils.getValueFromConfig(ConfigEnums.HELP.get()));
            return true;
        }
        if (sender instanceof Player) {
            switch (args[0].toLowerCase()) {
                case SET_LEFT_COMMAND: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_SET_LEFT_COMMAND.get()))) {
                        if (args.length > 1) {
                            ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
                            if (!item.getType().equals(Material.AIR)) {
                                NBTItem nbtItem = new NBTItem(item);
                                List<String> s = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
                                nbtItem.setString(NBTEnums.LEFT_CLICK.get(), String.join(" ", s));
                                ((Player) sender).getInventory().setItemInMainHand(nbtItem.getItem());
                                Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
                            } else {
                                Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
                            }
                        } else {
                            Utils.sendMessage(sender, ConfigEnums.USAGE_SET_LEFT_COMMAND);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                        return true;
                    }
                    break;
                }
                case SET_RIGHT_COMMAND: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_SET_RIGHT_COMMAND.get()))) {
                        if (args.length > 1) {
                            ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
                            if (!item.getType().equals(Material.AIR)) {
                                NBTItem nbtItem = new NBTItem(item);
                                List<String> s = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
                                nbtItem.setString(NBTEnums.RIGHT_CLICK.get(), String.join(" ", s));
                                ((Player) sender).getInventory().setItemInMainHand(nbtItem.getItem());
                            } else {
                                Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
                            }
                        } else {
                            Utils.sendMessage(sender, ConfigEnums.USAGE_SET_RIGHT_COMMAND);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                        return true;
                    }
                    break;
                }
                case SET_ONE_TIME_USE: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_SET_ONE_TIME_USE.get()))) {
                        if (args.length == 2) {
                            boolean b;
                            switch (args[1].toLowerCase()) {
                                case "true": {
                                    b = true;
                                    break;
                                }
                                case "false": {
                                    b = false;
                                    break;
                                }
                                default: {
                                    Utils.sendMessage(sender, ConfigEnums.ONE_TIME_USE_INVALID_STATE);
                                    return true;
                                }
                            }
                            ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
                            if (!item.getType().equals(Material.AIR)) {
                                NBTItem nbtItem = new NBTItem(item);
                                nbtItem.setBoolean(NBTEnums.ONE_TIME_USE.get(), b);
                                ((Player) sender).getInventory().setItemInMainHand(nbtItem.getItem());
                                Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
                            } else {
                                Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
                            }
                        } else {
                            Utils.sendMessage(sender, ConfigEnums.USAGE_SET_ONE_TIME_USE);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                        return true;
                    }
                    break;
                }
                case GET_COMMAND: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_GET_COMMAND.get()))) {
                        ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
                        if (!item.getType().equals(Material.AIR)) {
                            NBTItem nbtItem = new NBTItem(item);
                            if (nbtItem.hasKey(NBTEnums.LEFT_CLICK.get()) || nbtItem.hasKey(NBTEnums.RIGHT_CLICK.get()) || nbtItem.hasKey(NBTEnums.ONE_TIME_USE.get())) {
                                List<String> found = NBTCommandItems.getInstance().getConfig().getStringList(ConfigEnums.GET_COMMAND_FOUND.get());
                                List<String> foundCopy = new ArrayList<>();
                                found.forEach((string) -> foundCopy.add(string.replace("<left-command>", nbtItem.getString(NBTEnums.LEFT_CLICK.get()))
                                        .replace("<right-command>", nbtItem.getString(NBTEnums.RIGHT_CLICK.get()))
                                        .replace("<one-time-use>", String.valueOf(nbtItem.getBoolean(NBTEnums.ONE_TIME_USE.get())))));
                                Utils.sendMessage(sender, foundCopy);
                            } else {
                                Utils.sendMessage(sender, ConfigEnums.GET_COMMAND_NOT_FOUND);
                            }
                        } else {
                            Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                        return true;
                    }
                    break;
                }
                default: {
                    Utils.sendMessage(sender, (List<String>) Utils.getValueFromConfig(ConfigEnums.HELP.get()));
                    break;
                }
            }
        } else {
            Utils.sendMessage(sender, ConfigEnums.PLAYER_ONLY);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length < 1 || args[0].equals("")) {
            list.add(SET_LEFT_COMMAND);
            list.add(SET_RIGHT_COMMAND);
            list.add(SET_ONE_TIME_USE);
            list.add(GET_COMMAND);
            list.add(HELP);
        } else if (args.length == 1) {
            if ("set".startsWith(args[0].toLowerCase())) {
                list.add(SET_LEFT_COMMAND);
                list.add(SET_RIGHT_COMMAND);
                list.add(SET_ONE_TIME_USE);
            } else if (GET_COMMAND.startsWith(args[0].toLowerCase())) {
                list.add(GET_COMMAND);
            } else if (SET_LEFT_COMMAND.startsWith(args[0].toLowerCase())) {
                list.add(SET_LEFT_COMMAND);
            } else if (SET_RIGHT_COMMAND.startsWith(args[0].toLowerCase())) {
                list.add(SET_RIGHT_COMMAND);
            } else if (SET_ONE_TIME_USE.startsWith(args[0].toLowerCase())) {
                list.add(SET_ONE_TIME_USE);
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase(SET_ONE_TIME_USE)) {
            list.add("true");
            list.add("false");
        }
        return list;
    }
}
