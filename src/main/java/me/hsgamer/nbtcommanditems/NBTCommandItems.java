package me.hsgamer.nbtcommanditems;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class NBTCommandItems extends JavaPlugin {
    private static NBTCommandItems instance;

    public static NBTCommandItems getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        getCommand("commanditems").setExecutor(new PluginCommand());
        getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
        HandlerList.unregisterAll(this);
    }
}
