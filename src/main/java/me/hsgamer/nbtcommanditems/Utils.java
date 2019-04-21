package me.hsgamer.nbtcommanditems;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.io.*;
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
        switch (slot) {
            case HAND: {
                player.getInventory().setItemInMainHand(item);
                break;
            }
            case OFF_HAND: {
                player.getInventory().setItemInOffHand(item);
                break;
            }
            case FEET: {
                player.getInventory().setBoots(item);
                break;
            }
            case HEAD: {
                player.getInventory().setHelmet(item);
                break;
            }
            case LEGS: {
                player.getInventory().setLeggings(item);
                break;
            }
            case CHEST: {
                player.getInventory().setChestplate(item);
                break;
            }
        }
    }

    public static byte[] toBytes(String[] strings) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(strings);
        objectOutputStream.flush();
        objectOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static String[] toStrings(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        String[] stringArray2 = (String[]) objectInputStream.readObject();
        objectInputStream.close();

        return stringArray2;
    }
}
