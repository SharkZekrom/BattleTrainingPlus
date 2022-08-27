package be.shark_zekrom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Menu implements Listener {

    public static HashMap<Player, ArmorStand> playerPunchingball = new HashMap<>();
    public static HashMap<ArmorStand, Player> armorstandPunchingball = new HashMap<>();

    public static HashMap<Player, ArmorStand> playereditarmorstand = new HashMap<>();

    public static void punchingball(Player player, ArmorStand armorStand) {

        Inventory inventory = Bukkit.createInventory(null, 54, Main.getInstance().getConfig().getString("PunchingballMenu"));

        player.openInventory(inventory);
        Utils.border(inventory);


        ItemStack clock15 = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta15 = clock15.getItemMeta();
        clockMeta15.setDisplayName(Main.getInstance().getConfig().getString("Punchingball15seconds"));
        clock15.setItemMeta(clockMeta15);
        inventory.setItem(20, clock15);

        ItemStack clock30 = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta30 = clock30.getItemMeta();
        clockMeta30.setDisplayName(Main.getInstance().getConfig().getString("Punchingball30seconds"));
        clock30.setItemMeta(clockMeta30);
        inventory.setItem(21, clock30);

        ItemStack clock60 = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta60 = clock60.getItemMeta();
        clockMeta60.setDisplayName(Main.getInstance().getConfig().getString("Punchingball60seconds"));
        clock60.setItemMeta(clockMeta60);
        inventory.setItem(22, clock60);

        ItemStack clock90 = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta90 = clock90.getItemMeta();
        clockMeta90.setDisplayName(Main.getInstance().getConfig().getString("Punchingball90seconds"));
        clock90.setItemMeta(clockMeta90);
        inventory.setItem(23, clock90);

        ItemStack clock120 = new ItemStack(Material.CLOCK);
        ItemMeta clockMeta120 = clock120.getItemMeta();
        clockMeta120.setDisplayName(Main.getInstance().getConfig().getString("Punchingball120seconds"));
        clock120.setItemMeta(clockMeta120);
        inventory.setItem(24, clock120);

        playerPunchingball.put(player, armorStand);
        armorstandPunchingball.put(armorStand, player);

    }

    public static void punchingballEditing(Player player, ArmorStand armorStand) {
        Inventory inventory = Bukkit.createInventory(null, 54, Main.getInstance().getConfig().getString("PunchingballEditingMenu"));

        player.openInventory(inventory);
        Utils.border(inventory);

        inventory.setItem(22, armorStand.getHelmet());
        inventory.setItem(31, armorStand.getChestplate());

        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Main.getInstance().getConfig().getString("PunchingballRemove"));
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(49, itemStack);

        playerPunchingball.put(player, armorStand);
        armorstandPunchingball.put(armorStand, player);
        playereditarmorstand.put(player, armorStand);
    }


    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();
        if (event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getConfig().getString("PunchingballEditingMenu"))) {
            if (event.getClickedInventory().getType() != InventoryType.PLAYER) {

                if (!(slot == 22 || slot == 31)) {
                    event.setCancelled(true);
                }

                if (slot == 49) {
                    player.closeInventory();
                    playerPunchingball.get(player).getLocation().getBlock().setType(Material.AIR);
                    playerPunchingball.get(player).remove();
                    armorstandPunchingball.remove(playerPunchingball.get(player));
                    playerPunchingball.remove(player);
                }
            }
        }
        if (event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getConfig().getString("PunchingballMenu"))) {
            if (event.getClickedInventory().getType() != InventoryType.PLAYER) {

                event.setCancelled(true);

                if (slot == 20) {
                    player.closeInventory();
                    Punchingball.startPunchingball(player, playerPunchingball.get(player), 15);
                }
                if (slot == 21) {
                    player.closeInventory();
                    Punchingball.startPunchingball(player, playerPunchingball.get(player), 30);
                }
                if (slot == 22) {
                    player.closeInventory();
                    Punchingball.startPunchingball(player, playerPunchingball.get(player), 60);
                }
                if (slot == 23) {
                    player.closeInventory();
                    Punchingball.startPunchingball(player, playerPunchingball.get(player), 90);
                }
                if (slot == 24) {
                    player.closeInventory();
                    Punchingball.startPunchingball(player, playerPunchingball.get(player), 120);
                }

            }
        }
    }

    @EventHandler
    private void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getConfig().getString("PunchingballEditingMenu"))) {

            ArmorStand armorStand = playereditarmorstand.get(player);

            armorStand.setHelmet(event.getInventory().getItem(22));
            armorStand.setChestplate(event.getInventory().getItem(31));
            playereditarmorstand.remove(player);
        }
    }
}
