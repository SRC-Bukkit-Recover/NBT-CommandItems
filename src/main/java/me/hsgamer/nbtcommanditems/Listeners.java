package me.hsgamer.nbtcommanditems;

import de.tr7zw.itemnbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        if (!event.hasItem()) return;
        EquipmentSlot slot = event.getHand();
        if (slot == null) return;
        ItemStack item = event.getItem();
        NBTItem nbtitem = new NBTItem(item);
        Player player = event.getPlayer();
        if (!(nbtitem.hasKey(NBTEnums.LEFT_CLICK.get()) || nbtitem.hasKey(NBTEnums.RIGHT_CLICK.get()))) return;
        String leftclick = NBTCommandItems.getVariable().getParsed(player, nbtitem.getString(NBTEnums.LEFT_CLICK.get()));
        String rightclick = NBTCommandItems.getVariable().getParsed(player, nbtitem.getString(NBTEnums.RIGHT_CLICK.get()));
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!leftclick.equals("")) {
                if (!nbtitem.hasKey(NBTEnums.SEND_AS.get()) || nbtitem.getString(NBTEnums.SEND_AS.get()).equalsIgnoreCase("player")) {
                    player.performCommand(leftclick);
                } else if (nbtitem.getString(NBTEnums.SEND_AS.get()).equalsIgnoreCase("op")) {
                    try {
                        player.setOp(true);
                        player.performCommand(leftclick);
                    } finally {
                        player.setOp(false);
                    }
                } else if (nbtitem.getString(NBTEnums.SEND_AS.get()).equalsIgnoreCase("console")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), leftclick);
                }
                event.setCancelled(true);
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!rightclick.equals("")) {
                if (!nbtitem.hasKey(NBTEnums.SEND_AS.get()) || nbtitem.getString(NBTEnums.SEND_AS.get()).equalsIgnoreCase("player")) {
                    player.performCommand(rightclick);
                } else if (nbtitem.getString(NBTEnums.SEND_AS.get()).equalsIgnoreCase("op")) {
                    try {
                        player.setOp(true);
                        player.performCommand(rightclick);
                    } finally {
                        player.setOp(false);
                    }
                } else if (nbtitem.getString(NBTEnums.SEND_AS.get()).equalsIgnoreCase("console")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rightclick);
                }
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
                    Utils.setItem(player, item, slot);
                } else {
                    Utils.setItem(player, null, slot);
                }
            }
        }
    }
}
