package me.sanyasho.custommodeldata;

import java.util.logging.Logger;
import static org.bukkit.Bukkit.getLogger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.*;
import me.sanyasho.custommodeldata.CMDMain;

public class SetCMD implements CommandExecutor
{
    Logger log = getLogger();

    private CMDMain cmd;
    public SetCMD(final CMDMain instance) {
        cmd = instance;
    }

    private int cmddata;

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args)
    {
	// check for permission
	if(CMDMain.needperm)
	{
	    if(!cs.hasPermission("cmd.use"))
	    {
		cs.sendMessage(ChatColor.RED + "[ОШИБКА] Недостаточно прав на выполнение этой команды.");
                return true;
	    }
	}

	// check sender instance
        if(!(cs instanceof Player))
	{
	    cs.sendMessage(ChatColor.RED + "[ОШИБКА] Эта команда может быть использована только в игре.");
            return true;
        }

	// prepare variables
        Player executor = (Player) cs;
        String username = executor.getName();
	ItemStack item = executor.getInventory().getItemInMainHand();
	ItemMeta itemmeta = item.getItemMeta();

	// check item type
        if(item.getType() == Material.AIR)
        {
            cs.sendMessage(ChatColor.RED + "[ОШИБКА] Вы должны держать предмет в основной руке.");
            return true;
        }
	else
	{
	    log.info("(" + username +") Selected item: " + item.getI18NDisplayName());
	}

	// error if length of args is 0
        if(args.length == 0)
        {
            cs.sendMessage(ChatColor.RED + "[ОШИБКА] Пропущено значение CustomModelData.");
            return true;
        }

	// error if length or args > 1
	if(args.length > 1)
	{
	    cs.sendMessage(ChatColor.RED + "[ОШИБКА] Использование: /custommodeldata [int]");
            return true;
	}

	// try to parse integer from args[0]
	try
	{
	    if(args[0].length() > 9)
	    {
		log.info("(" + username + ") CMD Data: > 9");
		cs.sendMessage(ChatColor.RED + "[ОШИБКА] Максисально можно ввести только 9 цифр. Вы ввели " + args[0].length() + " цифр."); 
		return true;
	    }
	    else
	    {
	    	cmddata = Integer.parseInt(args[0]);
	    }
	}
	catch (NumberFormatException e)
	{
	    cs.sendMessage(ChatColor.RED + "[ОШИБКА] Ожидалось числовое значение в аргументе. (" + e + ")");
            return true;
	}

	// set custommodeldata
        itemmeta.setCustomModelData(cmddata);

	// add lore to item
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "CustomModelData:" + cmddata);

        if(CMDMain.addlore)
        {
            itemmeta.setLore(lore);
        }

	// set itemmeta for selected item
        item.setItemMeta(itemmeta);

	log.info("(" + username + ") CMD Data: " + cmddata);
        cs.sendMessage(ChatColor.GREEN + "[УСПЕХ] Значение CustomModelData для " + ChatColor.GRAY + item.getI18NDisplayName() + ChatColor.GREEN + " установлено на: " + ChatColor.GRAY + cmddata);
        return true;
    }
}

