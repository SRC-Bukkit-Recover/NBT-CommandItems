package me.hsgamer.nbtcommanditems;

import de.tr7zw.itemnbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PluginCommand implements TabExecutor, CommandExecutor {
    private static final String SET_LEFT_COMMAND = "setleftcommand";
    private static final String SET_RIGHT_COMMAND = "setrightcommand";
    private static final String ADD_LEFT_COMMAND = "addleftcommand";
    private static final String ADD_RIGHT_COMMAND = "addrightcommand";
    private static final String DEL_LEFT_COMMAND = "delleftcommand";
    private static final String DEL_RIGHT_COMMAND = "delrightcommand";
    private static final String SET_ONE_TIME_USE = "setonetimeuse";
    private static final String SET_SEND_AS = "setsendas";
    private static final String GET_COMMAND = "getcommand";
    private static final String HELP = "help";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1 || args[0].equalsIgnoreCase(HELP)) {
            Utils.sendMessage(sender, (List<String>) Utils.getValueFromConfig(ConfigEnums.HELP), false);
            return true;
        }
        if (sender instanceof Player) {
            switch (args[0].toLowerCase()) {
                case ADD_LEFT_COMMAND: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_ADD_LEFT_COMMAND))) {
                        if (args.length > 1) {
                            ItemStack item = getItem((Player) sender);
                            if (!item.getType().equals(Material.AIR)) {
                                NBTItem nbtItem = new NBTItem(item);
                                String s = String.join(" ", new ArrayList<>(Arrays.asList(args).subList(1, args.length)));
                                List<String> sl = new ArrayList<>();
                                if (nbtItem.hasKey(NBTEnums.LEFT_CLICK.get())) {
                                    try {
                                        sl = Utils.toStrings(nbtItem.getByteArray(NBTEnums.LEFT_CLICK.get()));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                sl.add(s);
                                byte[] b = new byte[0];
                                try {
                                    b = Utils.toBytes(sl);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                nbtItem.setByteArray(NBTEnums.LEFT_CLICK.get(), b);
                                setItem((Player) sender, nbtItem.getItem());
                                Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
                            } else {
                                Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
                            }
                        } else {
                            Utils.sendMessage(sender, ConfigEnums.USAGE_ADD_LEFT_COMMAND);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                    }
                    break;
                }
                case ADD_RIGHT_COMMAND: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_ADD_RIGHT_COMMAND))) {
                        if (args.length > 1) {
                            ItemStack item = getItem((Player) sender);
                            if (!item.getType().equals(Material.AIR)) {
                                NBTItem nbtItem = new NBTItem(item);
                                String s = String.join(" ", new ArrayList<>(Arrays.asList(args).subList(1, args.length)));
                                List<String> sl = new ArrayList<>();
                                if (nbtItem.hasKey(NBTEnums.RIGHT_CLICK.get())) {
                                    try {
                                        sl = Utils.toStrings(nbtItem.getByteArray(NBTEnums.RIGHT_CLICK.get()));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                sl.add(s);
                                byte[] b = new byte[0];
                                try {
                                    b = Utils.toBytes(sl);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                nbtItem.setByteArray(NBTEnums.RIGHT_CLICK.get(), b);
                                setItem((Player) sender, nbtItem.getItem());
                                Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
                            } else {
                                Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
                            }
                        } else {
                            Utils.sendMessage(sender, ConfigEnums.USAGE_ADD_RIGHT_COMMAND);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                    }
                    break;
                }
                case DEL_LEFT_COMMAND: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_DEL_LEFT_COMMAND))) {
                        if (args.length == 2) {
                            ItemStack item = getItem((Player) sender);
                            if (!item.getType().equals(Material.AIR)) {
                                NBTItem nbtItem = new NBTItem(item);
                                List<String> sl = new ArrayList<>();
                                if (nbtItem.hasKey(NBTEnums.LEFT_CLICK.get())) {
                                    try {
                                        sl = Utils.toStrings(nbtItem.getByteArray(NBTEnums.LEFT_CLICK.get()));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    int index = Integer.parseInt(args[1]);
                                    if (index >= 0 && index <= sl.size() - 1) {
                                        sl.remove(index);
                                        byte[] b = new byte[0];
                                        try {
                                            b = Utils.toBytes(sl);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        nbtItem.setByteArray(NBTEnums.LEFT_CLICK.get(), b);
                                        setItem((Player) sender, nbtItem.getItem());
                                        Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
                                    } else {
                                        Utils.sendMessage(sender, ConfigEnums.INDEX_OUT_OF_BOUND);
                                    }
                                } catch (NumberFormatException e) {
                                    Utils.sendMessage(sender, ConfigEnums.INVALID_INTEGER);
                                }
                            } else {
                                Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
                            }
                        } else {
                            Utils.sendMessage(sender, ConfigEnums.USAGE_DEL_LEFT_COMMAND);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                    }
                    break;
                }
                case DEL_RIGHT_COMMAND: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_DEL_RIGHT_COMMAND))) {
                        if (args.length == 2) {
                            ItemStack item = getItem((Player) sender);
                            if (!item.getType().equals(Material.AIR)) {
                                NBTItem nbtItem = new NBTItem(item);
                                List<String> sl = new ArrayList<>();
                                if (nbtItem.hasKey(NBTEnums.RIGHT_CLICK.get())) {
                                    try {
                                        sl = Utils.toStrings(nbtItem.getByteArray(NBTEnums.RIGHT_CLICK.get()));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    int index = Integer.parseInt(args[1]);
                                    if (index >= 0 && index <= sl.size() - 1) {
                                        sl.remove(index);
                                        byte[] b = new byte[0];
                                        try {
                                            b = Utils.toBytes(sl);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        nbtItem.setByteArray(NBTEnums.RIGHT_CLICK.get(), b);
                                        setItem((Player) sender, nbtItem.getItem());
                                        Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
                                    } else {
                                        Utils.sendMessage(sender, ConfigEnums.INDEX_OUT_OF_BOUND);
                                    }
                                } catch (NumberFormatException e) {
                                    Utils.sendMessage(sender, ConfigEnums.INVALID_INTEGER);
                                }
                            } else {
                                Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
                            }
                        } else {
                            Utils.sendMessage(sender, ConfigEnums.USAGE_DEL_RIGHT_COMMAND);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                    }
                    break;
                }
                case SET_LEFT_COMMAND: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_SET_LEFT_COMMAND))) {
                        if (args.length > 3) {

                        } else {
                            Utils.sendMessage(sender, ConfigEnums.USAGE_SET_LEFT_COMMAND);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                    }
                    break;
                }
                case SET_RIGHT_COMMAND: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_SET_RIGHT_COMMAND))) {
                        if (args.length > 3) {

                        } else {
                            Utils.sendMessage(sender, ConfigEnums.USAGE_SET_RIGHT_COMMAND);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                    }
                    break;
                }
                case SET_ONE_TIME_USE: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_SET_ONE_TIME_USE))) {
                        if (args.length == 2) {
                            if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false")) {
                                boolean b = Boolean.parseBoolean(args[1].toLowerCase());
                                ItemStack item = getItem((Player) sender);
                                if (!item.getType().equals(Material.AIR)) {
                                    NBTItem nbtItem = new NBTItem(item);
                                    nbtItem.setBoolean(NBTEnums.ONE_TIME_USE.get(), b);
                                    setItem((Player) sender, nbtItem.getItem());
                                    Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
                                } else {
                                    Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
                                }
                            } else {
                                Utils.sendMessage(sender, ConfigEnums.ONE_TIME_USE_INVALID_STATE);
                            }
                        } else {
                            Utils.sendMessage(sender, ConfigEnums.USAGE_SET_ONE_TIME_USE);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                    }
                    break;
                }
                case SET_SEND_AS: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_SET_SEND_AS))) {
                        if (args.length == 2) {
                            if (args[1].equalsIgnoreCase("console") || args[1].equalsIgnoreCase("op") || args[1].equalsIgnoreCase("player")) {
                                ItemStack item = getItem((Player) sender);
                                if (!item.getType().equals(Material.AIR)) {
                                    NBTItem nbtItem = new NBTItem(item);
                                    nbtItem.setString(NBTEnums.SEND_AS.get(), args[1].toLowerCase());
                                    setItem((Player) sender, nbtItem.getItem());
                                    Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
                                } else {
                                    Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
                                }
                            } else {
                                Utils.sendMessage(sender, ConfigEnums.SET_SEND_AS_INVALID_SENDER);
                            }
                        } else {
                            Utils.sendMessage(sender, ConfigEnums.USAGE_SET_SEND_AS);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                    }
                    break;
                }
                case GET_COMMAND: {
                    if (sender.hasPermission((String) Utils.getValueFromConfig(ConfigEnums.PERMISSION_GET_COMMAND))) {
                        ItemStack item = getItem((Player) sender);
                        if (!item.getType().equals(Material.AIR)) {
                            NBTItem nbtItem = new NBTItem(item);
                            if (nbtItem.hasKey(NBTEnums.LEFT_CLICK.get()) || nbtItem.hasKey(NBTEnums.RIGHT_CLICK.get()) || nbtItem.hasKey(NBTEnums.ONE_TIME_USE.get())) {
                                List<String> found = (List<String>) Utils.getValueFromConfig(ConfigEnums.GET_COMMAND_FOUND);
                                List<String> foundCopy = new ArrayList<>();
                                found.forEach(string -> foundCopy.add(string.replace("<left-command>", nbtItem.getString(NBTEnums.LEFT_CLICK.get()))
                                        .replace("<right-command>", nbtItem.getString(NBTEnums.RIGHT_CLICK.get()))
                                        .replace("<one-time-use>", String.valueOf(nbtItem.getBoolean(NBTEnums.ONE_TIME_USE.get())))
                                        .replace("<send-as>", nbtItem.getString(NBTEnums.SEND_AS.get()))));
                                Utils.sendMessage(sender, foundCopy, true);
                            } else {
                                Utils.sendMessage(sender, ConfigEnums.GET_COMMAND_NOT_FOUND);
                            }
                        } else {
                            Utils.sendMessage(sender, ConfigEnums.NO_ITEM_HAND);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.NO_PERMISSION);
                    }
                    break;
                }
                case HELP:
                default: {
                    Utils.sendMessage(sender, (List<String>) Utils.getValueFromConfig(ConfigEnums.HELP), false);
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
            list.add(ADD_LEFT_COMMAND);
            list.add(ADD_RIGHT_COMMAND);
            list.add(DEL_LEFT_COMMAND);
            list.add(DEL_RIGHT_COMMAND);
            list.add(SET_ONE_TIME_USE);
            list.add(SET_SEND_AS);
            list.add(GET_COMMAND);
            list.add(HELP);
        } else if (args.length == 1) {
            if ("set".startsWith(args[0].toLowerCase())) {
                list.add(SET_LEFT_COMMAND);
                list.add(SET_RIGHT_COMMAND);
                list.add(SET_ONE_TIME_USE);
                list.add(SET_SEND_AS);
            } else if ("add".startsWith(args[0].toLowerCase())) {
                list.add(ADD_LEFT_COMMAND);
                list.add(ADD_RIGHT_COMMAND);
            } else if ("del".startsWith(args[0].toLowerCase())) {
                list.add(DEL_LEFT_COMMAND);
                list.add(DEL_RIGHT_COMMAND);
            } else if (GET_COMMAND.startsWith(args[0].toLowerCase())) {
                list.add(GET_COMMAND);
            } else if (SET_LEFT_COMMAND.startsWith(args[0].toLowerCase())) {
                list.add(SET_LEFT_COMMAND);
            } else if (SET_RIGHT_COMMAND.startsWith(args[0].toLowerCase())) {
                list.add(SET_RIGHT_COMMAND);
            } else if (ADD_LEFT_COMMAND.startsWith(args[0].toLowerCase())) {
                list.add(ADD_LEFT_COMMAND);
            } else if (ADD_RIGHT_COMMAND.startsWith(args[0].toLowerCase())) {
                list.add(ADD_RIGHT_COMMAND);
            } else if (DEL_LEFT_COMMAND.startsWith(args[0].toLowerCase())) {
                list.add(DEL_LEFT_COMMAND);
            } else if (DEL_RIGHT_COMMAND.startsWith(args[0].toLowerCase())) {
                list.add(DEL_RIGHT_COMMAND);
            } else if (SET_ONE_TIME_USE.startsWith(args[0].toLowerCase())) {
                list.add(SET_ONE_TIME_USE);
            } else if (SET_SEND_AS.startsWith(args[0].toLowerCase())) {
                list.add(SET_SEND_AS);
            }
        } else if (args.length == 2) {
            if (SET_ONE_TIME_USE.equalsIgnoreCase(args[0])) {
                list.add("true");
                list.add("false");
            } else if (SET_SEND_AS.equalsIgnoreCase(args[0])) {
                list.add("console");
                list.add("player");
                list.add("op");
            }
        }
        return list;
    }

    @SuppressWarnings("deprecation")
    private ItemStack getItem(Player player) {
        if (NBTCommandItems.getInstance().isLegacy()) {
            return player.getItemInHand();
        } else {
            return player.getInventory().getItemInMainHand();
        }
    }

    @SuppressWarnings("deprecation")
    private void setItem(Player player, ItemStack item) {
        if (NBTCommandItems.getInstance().isLegacy()) {
            player.setItemInHand(item);
        } else {
            player.getInventory().setItemInMainHand(item);
        }
    }
}
