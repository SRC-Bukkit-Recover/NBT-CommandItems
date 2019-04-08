package me.hsgamer.nbtcommanditems;

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
        String leftclick = (String) NBTEditor.getItemTag(item, NBTEnums.LEFT_CLICK.get());
        String rightclick = (String) NBTEditor.getItemTag(item, NBTEnums.RIGHT_CLICK.get());
        if (leftclick == null && rightclick == null) return;
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (leftclick != null) {
                event.getPlayer().chat("/" + leftclick);
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (rightclick != null) {
                event.getPlayer().chat("/" + rightclick);
            }
        }
        if (NBTEditor.getItemTag(item, NBTEnums.ONE_TIME_USE.get()) != null) {
            boolean onetimeuse = (boolean) NBTEditor.getItemTag(item, NBTEnums.ONE_TIME_USE.get());
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
