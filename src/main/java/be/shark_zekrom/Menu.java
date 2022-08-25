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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Menu implements Listener {

    public static HashMap<Player, ArmorStand> playerPunchingball = new HashMap<>();
    public static HashMap<ArmorStand, Player> armorstandPunchingball = new HashMap<>();

    public static HashMap<Player, ArmorStand> playereditarmorstand = new HashMap<>();

    public static void punchingball(Player player, ArmorStand armorStand) {

        Inventory inventory = Bukkit.createInventory(null, 54, "Punchingball");

        player.openInventory(inventory);


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

    public static void punchingballEditing(Player player, ArmorStand armorStand) {
        Inventory inventory = Bukkit.createInventory(null, 54, "Punchingball Editing");

        player.openInventory(inventory);

        inventory.setItem(13, armorStand.getHelmet());
        inventory.setItem(22, armorStand.getChestplate());
        inventory.setItem(31, armorStand.getLeggings());
        inventory.setItem(40, armorStand.getBoots());

        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Remove");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(49, itemStack);

        playereditarmorstand.put(player, armorStand);
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

    @EventHandler
    private void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (event.getView().getTitle().equalsIgnoreCase("Punchingball Editing")) {

            ArmorStand armorStand = playereditarmorstand.get(player);

            armorStand.setHelmet(event.getInventory().getItem(13));
            armorStand.setChestplate(event.getInventory().getItem(22));
            armorStand.setLeggings(event.getInventory().getItem(31));
            armorStand.setBoots(event.getInventory().getItem(40));

            playereditarmorstand.remove(player);
        }
    }
}
