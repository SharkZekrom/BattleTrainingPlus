package be.shark_zekrom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Menu implements Listener {

    public static HashMap<Player, ArmorStand> playerPunchingball = new HashMap<>();
    public static HashMap<ArmorStand, Player> armorstandPunchingball = new HashMap<>();


    public static void Punchingball(Player player, ArmorStand armorStand) {

        Inventory inventory = Bukkit.createInventory(null, 54, "Punchingball");

        player.openInventory(inventory);

        ItemStack itemStack = new ItemStack(Material.BARRIER);
        inventory.setItem(49, itemStack);

        playerPunchingball.put(player, armorStand);
        armorstandPunchingball.put(armorStand, player);

    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        if (event.getView().getTitle().equalsIgnoreCase("Punchingball")) {
            event.setCancelled(true);
            if (slot == 49) {
                player.closeInventory();
                playerPunchingball.get(player).remove();
                armorstandPunchingball.remove(playerPunchingball.get(player));
                playerPunchingball.remove(player);
            }

        }
    }

  //  @EventHandler
    //    private void onInventoryClose(InventoryCloseEvent event) {
    //        Player player = (Player) event.getPlayer();
    //
    //        if (event.getView().getTitle().equalsIgnoreCase("Punchingball")) {
    //            armorstandPunchingball.remove(playerPunchingball.get(player));
    //            playerPunchingball.remove(player);
    //        }
    //    }
}
