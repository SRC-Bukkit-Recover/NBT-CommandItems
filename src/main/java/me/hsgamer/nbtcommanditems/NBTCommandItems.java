package me.hsgamer.nbtcommanditems;

import org.bstats.bukkit.Metrics;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class NBTCommandItems extends JavaPlugin {
    private static NBTCommandItems instance;
    private static Variable variable;
    private static Metrics metrics;

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
        metrics = new Metrics(this);
        PluginCommand command = new PluginCommand();
        getCommand("commanditems").setExecutor(command);
        getCommand("commanditems").setTabCompleter(command);
        getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
        variable = null;
        metrics = null;
        HandlerList.unregisterAll(this);
    }
}
