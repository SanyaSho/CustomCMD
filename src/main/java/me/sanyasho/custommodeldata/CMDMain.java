package me.sanyasho.custommodeldata;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.io.*;

public class CMDMain extends JavaPlugin {

    Logger log = getLogger();

    public static FileConfiguration config;
    File cfgfile;

    @Override
    public void onEnable() {
	Metrics metrics = new Metrics(this, 15155);

        config = getConfig();
	config.options().copyDefaults(true);
	saveConfig();
	cfgfile = new File(getDataFolder(), "config.yml");

	log.info("Require permission: " + getConfig().getBoolean("plugin.require-cmd-command-permission"));
        log.info("Add Lore to item: " + getConfig().getBoolean("plugin.add-item-lore"));

        getCommand("custommodeldata").setExecutor(new SetCMD(this));
    }

    @Override
    public void onDisable()
    {
    }

    // config reload command
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args)
    {
	if(!cs.hasPermission("cmd.reload"))
	{
	    cs.sendMessage(ChatColor.RED + "[ОШИБКА] Недостаточно прав на выполнение этой команды");
	    return true;
	}

	if(cmd.getName().equalsIgnoreCase("cmdreload"))
	{
	    config = YamlConfiguration.loadConfiguration(cfgfile);
	    cs.sendMessage(ChatColor.GREEN + "Конфигурация перезагружена");
	    return true;
	}

	return true;
    }

}
