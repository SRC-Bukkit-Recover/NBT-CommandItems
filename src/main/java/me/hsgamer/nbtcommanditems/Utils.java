package me.hsgamer.nbtcommanditems;

import me.hsgamer.nbtcommanditems.enums.ConfigEnums;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', NBTCommandItems.getInstance().getConfigFile().getConfig().getString(ConfigEnums.PREFIX.getPath(), (String) ConfigEnums.PREFIX.getDef()) + message));
    }

    public static void sendMessage(CommandSender sender, ConfigEnums configEnums) {
        sendMessage(sender, NBTCommandItems.getInstance().getConfigFile().getConfig().getString(configEnums.getPath(), (String) configEnums.getDef()));
    }

    public static void sendMessage(CommandSender sender, List<String> strings, boolean prefix) {
        for (String message : strings) {
            if (prefix) {
                sendMessage(sender, message);
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        }
    }

    public static Object getValueFromConfig(ConfigEnums configEnums) {
        return NBTCommandItems.getInstance().getConfigFile().getConfig().get(configEnums.getPath(), configEnums.getDef());
    }

    public static void setItem(Player player, ItemStack item, EquipmentSlot slot) {
        if (slot == null) return;
        if (slot == EquipmentSlot.HAND) {
            player.getInventory().setItemInMainHand(item);
        } else if (slot == EquipmentSlot.OFF_HAND) {
            player.getInventory().setItemInOffHand(item);
        } else if (slot == EquipmentSlot.FEET) {
            player.getInventory().setBoots(item);
        } else if (slot == EquipmentSlot.HEAD) {
            player.getInventory().setHelmet(item);
        } else if (slot == EquipmentSlot.LEGS) {
            player.getInventory().setLeggings(item);
        } else if (slot == EquipmentSlot.CHEST) {
            player.getInventory().setChestplate(item);
        }
    }

    public static byte[] toBytes(List<String> list) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        for (String element : list) {
            out.writeUTF(element);
        }
        out.flush();
        return baos.toByteArray();
    }

    public static List<String> toStrings(byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        DataInputStream in = new DataInputStream(bais);
        List<String> list = new ArrayList<>();
        while (in.available() > 0) {
            list.add(in.readUTF());
        }

        return list;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack getItem(Player player) {
        if (NBTCommandItems.getInstance().isLegacy()) {
            return player.getItemInHand();
        } else {
            return player.getInventory().getItemInMainHand();
        }
    }

    @SuppressWarnings("deprecation")
    public static void setItem(Player player, ItemStack item) {
        if (NBTCommandItems.getInstance().isLegacy()) {
            player.setItemInHand(item);
        } else {
            player.getInventory().setItemInMainHand(item);
        }
    }
}
