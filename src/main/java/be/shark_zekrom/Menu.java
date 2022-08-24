package be.shark_zekrom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Menu implements Listener {

    public static HashMap<Player, ArmorStand> playerPunchingball = new HashMap<>();
    public static HashMap<ArmorStand, Player> armorstandPunchingball = new HashMap<>();


    public static void Punchingball(Player player, ArmorStand armorStand) {

        Inventory inventory = Bukkit.createInventory(null, 54, "Punchingball");

        player.openInventory(inventory);

        ItemStack itemStack = new ItemStack(Material.BARRIER);
        inventory.setItem(49, itemStack);

        ItemStack clock15 = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta15 = clock15.getItemMeta();
        clockMeta15.setDisplayName("15 secondes");
        clock15.setItemMeta(clockMeta15);
        inventory.setItem(0, clock15);

        ItemStack clock30 = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta30 = clock30.getItemMeta();
        clockMeta30.setDisplayName("30 secondes");
        clock30.setItemMeta(clockMeta30);
        inventory.setItem(1, clock30);

        ItemStack clock60 = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta60 = clock60.getItemMeta();
        clockMeta60.setDisplayName("60 secondes");
        clock60.setItemMeta(clockMeta60);
        inventory.setItem(2, clock60);

        ItemStack clock90 = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta90 = clock90.getItemMeta();
        clockMeta90.setDisplayName("90 secondes");
        clock90.setItemMeta(clockMeta90);
        inventory.setItem(3, clock90);

        ItemStack clock120 = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta120 = clock120.getItemMeta();
        clockMeta120.setDisplayName("120 secondes");
        clock120.setItemMeta(clockMeta120);
        inventory.setItem(4, clock120);


        playerPunchingball.put(player, armorStand);
        armorstandPunchingball.put(armorStand, player);

    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        if (event.getView().getTitle().equalsIgnoreCase("Punchingball")) {
            event.setCancelled(true);

            if (Punchingball.punchingball.containsKey(player)) {
                player.sendMessage("you are in battle");


            } else {

                if (slot == 0) {
                    player.closeInventory();
                    Punchingball.startPunchingball(player, playerPunchingball.get(player), 15);
                }
                if (slot == 1) {
                    player.closeInventory();
                    Punchingball.startPunchingball(player, playerPunchingball.get(player), 30);
                }
                if (slot == 2) {
                    player.closeInventory();
                    Punchingball.startPunchingball(player, playerPunchingball.get(player), 60);
                }
                if (slot == 3) {
                    player.closeInventory();
                    Punchingball.startPunchingball(player, playerPunchingball.get(player), 90);
                }
                if (slot == 4) {
                    player.closeInventory();
                    Punchingball.startPunchingball(player, playerPunchingball.get(player), 120);
                }

                if (slot == 49) {
                    player.closeInventory();
                    playerPunchingball.get(player).remove();
                    armorstandPunchingball.remove(playerPunchingball.get(player));
                    playerPunchingball.remove(player);
                }

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
