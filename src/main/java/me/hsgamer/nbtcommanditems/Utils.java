package me.hsgamer.nbtcommanditems;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Utils {
    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', NBTCommandItems.getInstance().getConfig().getString(ConfigEnums.PREFIX.get()) + message));
    }

    public static void sendMessage(CommandSender sender, ConfigEnums configEnums) {
        sendMessage(sender, NBTCommandItems.getInstance().getConfig().getString(configEnums.get()));
    }

    public static void sendMessage(CommandSender sender, List<String> strings) {
        for (String message : strings) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    public static Object getValueFromConfig(String path) {
        return NBTCommandItems.getInstance().getConfig().get(path);
    }
}
