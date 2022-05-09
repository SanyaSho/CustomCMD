package me.sanyasho.custommodeldata;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.concurrent.*;
import org.bukkit.Bukkit;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.file.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.io.*;

public class CMDMain extends JavaPlugin {

    Logger log = getLogger();

    public static FileConfiguration config;
    File cfgfile;

    @Override
    public void onEnable() {
	int ID = 15155;
	Metrics metrics = new Metrics(this, ID);

        config = getConfig();
	config.options().copyDefaults(true);
	saveConfig();
	cfgfile = new File(getDataFolder(), "config.yml");

	log.info("Require permission: " + getConfig().getString("plugin.require-cmd-command-permission"));

        getCommand("custommodeldata").setExecutor(new SetCMD(this));
    }

    @Override
    public void onDisable()
    {
    }

    // config reload command
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args)
    {
	if(!cs.hasPermission("cmd.reload"))
	{
	    cs.sendMessage(ChatColor.RED + "[ОШИБКА] Недостаточно прав на выполнение этой команды.");
	    return true;
	}

	if(cmd.getName().equalsIgnoreCase("cmdreload"))
	{
	    config = YamlConfiguration.loadConfiguration(cfgfile);
	    cs.sendMessage("[" + ChatColor.GOLD + "CustomCMD" + ChatColor.RESET + "] " + ChatColor.GREEN + "Reload complete");
	    return true;
	}

	return true;
    }

}
