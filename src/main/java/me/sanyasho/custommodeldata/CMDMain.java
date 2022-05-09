package me.sanyasho.custommodeldata;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.concurrent.*;
import org.bukkit.Bukkit;
import java.util.HashMap;
import java.util.Map;

public class CMDMain extends JavaPlugin {

    Logger log = getLogger();

    @Override
    public void onEnable() {
        log.info(ChatColor.GREEN + "Запуск");

	int ID = 15155;
	Metrics metrics = new Metrics(this, ID);

	// player count char
	metrics.addCustomChart(new Metrics.SingleLineChart("players", new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return Bukkit.getOnlinePlayers().size();
            }
        }));

	// used java version char
	metrics.addCustomChart(new Metrics.DrilldownPie("java_version", () -> {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        String javaVersion = System.getProperty("java.version");
        Map<String, Integer> entry = new HashMap<>();
        entry.put(javaVersion, 1);
            map.put(javaVersion, entry);
            return map;
        }));

        PluginCommand cmd = getCommand("custommodeldata");
        cmd.setExecutor(new SetCMD());
    }

    @Override
    public void onLoad() {
        log.info(ChatColor.GREEN + "Загрузка");
        
    }
    
    @Override
    public void onDisable() {
        log.info(ChatColor.RED + "Выключение");
        
    }
    
}
