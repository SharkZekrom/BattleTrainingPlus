package be.shark_zekrom;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Menu {

    public static void Punchingball(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 54,"Punchingball");

        player.openInventory(inventory);



    }
}
