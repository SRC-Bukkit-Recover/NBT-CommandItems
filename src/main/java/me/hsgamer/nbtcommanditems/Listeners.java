package me.hsgamer.nbtcommanditems;

import de.tr7zw.itemnbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        if (!event.hasItem()) return;
        ItemStack item = event.getItem();
        NBTItem nbtitem = new NBTItem(item);
        Player player = event.getPlayer();
        if (!(nbtitem.hasKey(NBTEnums.LEFT_CLICK.get()) && nbtitem.hasKey(NBTEnums.RIGHT_CLICK.get()))) return;
        String leftclick = nbtitem.getString(NBTEnums.LEFT_CLICK.get());
        String rightclick = nbtitem.getString(NBTEnums.RIGHT_CLICK.get());
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (leftclick != null) {
                player.chat("/" + NBTCommandItems.getVariable().getParsed(player, leftclick));
                event.setCancelled(true);
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (rightclick != null) {
                player.chat("/" + NBTCommandItems.getVariable().getParsed(player, rightclick));
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
                    event.getPlayer().getInventory().setItemInMainHand(item);
                } else {
                    event.getPlayer().getInventory().setItemInMainHand(null);
                }
            }
        }
    }
}
