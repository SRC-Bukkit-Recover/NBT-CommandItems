package me.hsgamer.nbtcommanditems;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Variable {
    private boolean isPlaceholderAPI;

    public Variable(JavaPlugin plugin) {
        isPlaceholderAPI = plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    public String getParsed(Player player, String string) {
        String stringCopy = string;
        if (isPlaceholderAPI) {
            stringCopy = PlaceholderAPI.setPlaceholders(player, stringCopy);
        }
        // TODO: local variables

        return stringCopy;
    }
}
