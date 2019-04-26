package me.hsgamer.nbtcommanditems;

import me.hsgamer.nbtcommanditems.enums.ConfigEnums;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigFile {
    private FileConfiguration config;
    private File configFile;
    private JavaPlugin plugin;
    private String filename = "config.yml";

    public ConfigFile(JavaPlugin plugin) {
        this.plugin = plugin;
        setUpConfig();
        setDefault();
    }

    private void setUpConfig() {
        configFile = new File(plugin.getDataFolder(), filename);
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            config = YamlConfiguration.loadConfiguration(configFile);
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Created " + filename);
        } else {
            config = YamlConfiguration.loadConfiguration(configFile);
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loaded " + filename);
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            setUpConfig();
        }
        return config;
    }

    private void setDefault() {
        for (ConfigEnums configEnum : ConfigEnums.class.getEnumConstants()) {
            getConfig().addDefault(configEnum.getPath(), configEnum.getDef());
        }
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
