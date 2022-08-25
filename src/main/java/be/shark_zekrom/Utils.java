package be.shark_zekrom;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    public static void border(Inventory inventory) {

        for (int i = 0; i < 53; i++) {
            ItemStack wheat = new ItemStack(Material.WHEAT);
            ItemMeta wheatMeta = wheat.getItemMeta();
            wheatMeta.setDisplayName(" ");
            wheat.setItemMeta(wheatMeta);
            inventory.setItem(i, wheat);
        }

        int[] border = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};

        ItemStack itemStack = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemStack.setItemMeta(itemMeta);

        for (
                int slot : border) {
            inventory.setItem(slot, itemStack);
        }


    }
}

