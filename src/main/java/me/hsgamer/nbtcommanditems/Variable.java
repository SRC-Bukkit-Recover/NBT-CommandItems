package me.hsgamer.nbtcommanditems;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getParsed(Player player, List<String> list) {
        List<String> listCopy = new ArrayList<>();

        for (String string : list) {
            listCopy.add(getParsed(player, string));
        }

        return listCopy;
    }
}
