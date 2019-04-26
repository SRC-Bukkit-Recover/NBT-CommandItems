package me.hsgamer.nbtcommanditems.listeners;

import de.tr7zw.itemnbtapi.NBTItem;
import me.hsgamer.nbtcommanditems.NBTCommandItems;
import me.hsgamer.nbtcommanditems.Utils;
import me.hsgamer.nbtcommanditems.enums.NBTEnums;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Listeners implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        if (!event.hasItem()) return;
        ItemStack item = event.getItem();
        NBTItem nbtitem = new NBTItem(item);
        Player player = event.getPlayer();
        if (!(nbtitem.hasKey(NBTEnums.LEFT_CLICK.get()) || nbtitem.hasKey(NBTEnums.RIGHT_CLICK.get()))) return;
        List<String> rightclicklist = new ArrayList<>();
        List<String> leftclicklist = new ArrayList<>();
        try {
            rightclicklist = Utils.toStrings(nbtitem.getByteArray(NBTEnums.RIGHT_CLICK.get()));
            leftclicklist = Utils.toStrings(nbtitem.getByteArray(NBTEnums.LEFT_CLICK.get()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!leftclicklist.isEmpty()) {
                leftclicklist.forEach(s -> sendCommand(player, nbtitem, s));
                event.setCancelled(true);
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!rightclicklist.isEmpty()) {
                rightclicklist.forEach(s -> sendCommand(player, nbtitem, s));
                event.setCancelled(true);
            }
        } else {
            return;
        }
        if (nbtitem.hasKey(NBTEnums.ONE_TIME_USE.get()) != null) {
            boolean onetimeuse = nbtitem.getBoolean(NBTEnums.ONE_TIME_USE.get());
            if (onetimeuse) {
                if (item.getAmount() > 1) {
                    item.setAmount(item.getAmount() - 1);
                    if (NBTCommandItems.getInstance().isLegacy()) {
                        player.setItemInHand(item);
                    } else {
                        Utils.setItem(player, item, event.getHand());
                    }
                } else {
                    if (NBTCommandItems.getInstance().isLegacy()) {
                        player.setItemInHand(null);
                    } else {
                        Utils.setItem(player, null, event.getHand());
                    }
                }
            }
        }
    }

    private void sendCommand(Player player, NBTItem nbtitem, String command) {
        if (!nbtitem.hasKey(NBTEnums.SEND_AS.get()) || nbtitem.getString(NBTEnums.SEND_AS.get()).equalsIgnoreCase("player")) {
            player.performCommand(command);
        } else if (nbtitem.getString(NBTEnums.SEND_AS.get()).equalsIgnoreCase("op")) {
            try {
                player.setOp(true);
                player.performCommand(command);
            } finally {
                player.setOp(false);
            }
        } else if (nbtitem.getString(NBTEnums.SEND_AS.get()).equalsIgnoreCase("console")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }
}
