package me.sanyasho.custommodeldata;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CMDMain extends JavaPlugin {
    
    Logger log = getLogger();
    
    @Override
    public void onEnable() {
        log.info(ChatColor.GREEN + "Запуск");
        
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
