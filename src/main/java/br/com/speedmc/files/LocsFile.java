package br.com.speedmc.files;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static br.com.speedmc.SpeedPvP.*;

public class LocsFile {

    private File file;
    private FileConfiguration fileConfiguration;

    public LocsFile() {
        file = new File(getInstance().getDataFolder(), "locs.yml");
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            try {
                file.createNewFile();
                loadConfig();
                Bukkit.getConsoleSender().sendMessage("§aArquivo " + file.getName() + " criado com sucesso.");
            } catch (Exception e) {
                e.printStackTrace();
                Bukkit.getConsoleSender().sendMessage("§cOcorreu um erro ao criar o arquivo:" + file.getName());
            }
        }
    }

    public FileConfiguration getConfig() {
        return fileConfiguration;
    }

    public void saveConfig() {
        try {

            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§cOcorreu um erro ao salvar o arquivo:" + file.getName());
        }
    }

    private void loadConfig() {
        getConfig().set("Locs", new HashMap<String, Location>());
        saveConfig();
    }

}
