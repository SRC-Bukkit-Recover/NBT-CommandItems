package me.hsgamer.nbtcommanditems;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Variable {
    private boolean isPlaceholderAPI;

    public Variable() {
        isPlaceholderAPI = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    public String getParsed(Player player, String string) {
        String stringCopy = string;
        if (isPlaceholderAPI) {
            stringCopy = PlaceholderAPI.setPlaceholders(player, stringCopy);
        }
        // TODO: variables
        stringCopy = stringCopy
                .replace("<player>", player.getName())
        ;

        return stringCopy;
    }
}
