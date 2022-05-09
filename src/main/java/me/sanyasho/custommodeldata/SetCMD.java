package me.sanyasho.custommodeldata;

import java.util.ArrayList;
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

public class SetCMD implements CommandExecutor {

    Logger log = getLogger();

    private int cmddata;

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {

        if(!(cs instanceof Player)) {
            cs.sendMessage(ChatColor.RED + "[ОШИБКА] Эта команда может быть использована только в игре.");
            return true;
        }

        Player executor = (Player) cs;
        ItemStack item = executor.getInventory().getItemInMainHand();
	ItemMeta itemmeta = item.getItemMeta();

        log.info("(" + executor.getName() +") Selected item: " + item.getI18NDisplayName());

        if(item.getType() == Material.AIR)
        {
            cs.sendMessage(ChatColor.RED + "[ОШИБКА] Вы должны держать предмет в основной руке.");
            return true;
        }

        if(args.length == 0)
        {
            cs.sendMessage(ChatColor.RED + "[ОШИБКА] Пропущено значение CustomModelData.");
            return true;
        }

	try {
	    cmddata = Integer.parseInt(args[0]);
	} catch (NumberFormatException e) {
	    cs.sendMessage(ChatColor.RED + "[ОШИБКА] Ожидалось числовое значение в аргументе. (" + e + ")");
            return true;
	}

	// lore message
        String itemlore = ChatColor.GRAY + "CustomModelData:" + cmddata;

	// set custommodeldata
        itemmeta.setCustomModelData(cmddata);

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(itemlore);
        itemmeta.setLore(lore);

        item.setItemMeta(itemmeta);

        cs.sendMessage(ChatColor.GREEN + "[УСПЕХ] Значение CustomModelData для " + ChatColor.GRAY + item.getI18NDisplayName() + ChatColor.GREEN + " установлено на: " + ChatColor.GRAY + cmddata);

        return true;
    }
}

