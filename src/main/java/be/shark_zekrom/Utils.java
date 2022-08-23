package be.shark_zekrom;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {


    public static void givePunchingball(Player player) {

        ItemStack itemStack = new ItemStack(Material.ARMOR_STAND);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Punchingball");
        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(itemStack);
    }

    public static void spawnPunchingball(Location location) {

        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCustomName("Punchingball");
        armorStand.setCanPickupItems(false);
        armorStand.setGravity(false);


    }
}
