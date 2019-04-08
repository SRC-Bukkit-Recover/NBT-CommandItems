package me.hsgamer.nbtcommanditems;

import org.bukkit.ChatColor;
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
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1 || args[0].equalsIgnoreCase("help")) {
            sendHelpCommand(sender);
            return true;
        }
        if (sender instanceof Player) {
            switch (args[0].toLowerCase()) {
                case "setleftcommand": {
                    if (args.length > 1) {
                        ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
                        if (!item.getType().equals(Material.AIR)) {
                            List<String> s = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
                            ((Player) sender).getInventory().setItemInMainHand(NBTEditor.setItemTag(item, String.join(" ", s), NBTEnums.LEFT_CLICK.get()));
                            Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.USAGE_SET_LEFT_COMMAND);
                    }
                    break;
                }
                case "setrightcommand": {
                    if (args.length > 1) {
                        ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
                        if (!item.getType().equals(Material.AIR)) {
                            List<String> s = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
                            ((Player) sender).getInventory().setItemInMainHand(NBTEditor.setItemTag(item, String.join(" ", s), NBTEnums.RIGHT_CLICK.get()));
                            Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.USAGE_SET_RIGHT_COMMAND);
                    }
                    break;
                }
                case "setonetimeuse": {
                    if (args.length == 2) {
                        boolean b;
                        switch (args[1].toLowerCase()) {
                            case "true" : {
                                b = true;
                                break;
                            }
                            case "false" : {
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
                            ((Player) sender).getInventory().setItemInMainHand(NBTEditor.setItemTag(item, b, NBTEnums.ONE_TIME_USE.get()));
                            Utils.sendMessage(sender, ConfigEnums.SUCCESSFUL);
                        }
                    } else {
                        Utils.sendMessage(sender, ConfigEnums.USAGE_SET_ONE_TIME_USE);
                    }
                    break;
                }
                case "getcommand": {
                    // get command of an item

                    break;
                }
                default: {
                    sendHelpCommand(sender);
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
        return null;
    }

    private void sendHelpCommand(CommandSender sender) {
        Utils.sendMessage(sender, NBTCommandItems.getInstance().getConfig().getStringList(ConfigEnums.HELP.get()));
    }
}
