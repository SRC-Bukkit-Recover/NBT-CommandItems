package me.hsgamer.nbtcommanditems;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class NBTCommandItems extends JavaPlugin {
    private static NBTCommandItems instance;
    private static Variable variable;

    public static NBTCommandItems getInstance() {
        return instance;
    }

    public static Variable getVariable() {
        return variable;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        variable = new Variable();
        getCommand("commanditems").setExecutor(new PluginCommand());
        getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
        variable = null;
        HandlerList.unregisterAll(this);
    }
}
