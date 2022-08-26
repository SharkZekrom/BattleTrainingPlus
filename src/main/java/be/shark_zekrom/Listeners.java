package be.shark_zekrom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.HashMap;

import static java.lang.String.format;

public class Listeners implements Listener {


    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();

            if (mainHand.getType() == Material.ARMOR_STAND ) {
                if (mainHand.hasItemMeta()) {
                    if (mainHand.getItemMeta().hasDisplayName()) {
                        if (mainHand.getItemMeta().getDisplayName().equals("§fPunching ball")) {
                            if (event.getPlayer().hasPermission("BattleTraining+.spawn.punchingball")) {
                                event.setCancelled(true);
                                Punchingball.spawnPunchingball(event.getPlayer(), event.getClickedBlock().getLocation().add(0.5, 1, 0.5));
                            }
                        }
                    }

                }
            }
        }

    }

    @EventHandler
    public void onRightClickEntity(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ArmorStand armorStand) {
            if (armorStand.getScoreboardTags().contains("Punching ball")) {
                if (event.getPlayer().isSneaking()) {
                    Menu.punchingballEditing(event.getPlayer(), armorStand);

                } else {
                    if (!Punchingball.punchingball.containsKey(event.getPlayer())) {
                        Menu.punchingball(event.getPlayer(), armorStand);

                    }
                }
            }

        }

    }

    @EventHandler
    public void entityDeathEvent(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof ArmorStand armorStand && event.getDamager() instanceof Player player) {
            if (armorStand.getScoreboardTags().contains("Punching ball")) {
                event.setCancelled(true);
                Punchingball.animationPunchingball(armorStand);

                if (Punchingball.punchingball.containsKey(player)) {
                    if (Punchingball.punchingball.get(player) == armorStand) {
                        Punchingball.totalDamage.put(player, Punchingball.totalDamage.get(player) + event.getDamage());
                        Punchingball.lastDamage.put(player, event.getDamage());
                        Punchingball.hits.put(player, Punchingball.hits.get(player) + 1);

                        if (event.getDamage() > Punchingball.maxDamage.get(player)) {
                            Punchingball.maxDamage.put(player, event.getDamage());
                        }

                        if (Punchingball.minDamage.get(player) == null) {
                            Punchingball.minDamage.put(player, event.getDamage());
                        } else {
                            if (event.getDamage() < Punchingball.minDamage.get(player)) {
                                Punchingball.minDamage.put(player, event.getDamage());
                            }
                        }
                    }
                } else {
                    if (!Punchingball.punchingball.containsValue(armorStand)) {
                        double number = event.getDamage();
                        DecimalFormat format = new DecimalFormat("0.00");

                        armorStand.setCustomName("§e-" + format.format(number) + "");
                        armorStand.setCustomNameVisible(true);
                        if (Punchingball.punchingballhit.containsKey(armorStand)) {
                            Punchingball.punchingballhit.put(armorStand, Punchingball.punchingballhit.get(armorStand) + 3);
                        } else {
                            Punchingball.punchingballhit.put(armorStand, 3);

                        }
                        final int[] countdown = {3};
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                countdown[0]--;
                                Punchingball.punchingballhit.put(armorStand, Punchingball.punchingballhit.get(armorStand) - 1);

                                if (Punchingball.punchingballhit.get(armorStand) == 0) {
                                    if (!Punchingball.punchingball.containsValue(armorStand)) {

                                        armorStand.setCustomNameVisible(false);
                                    }
                                }

                                if (countdown[0] == 0) {
                                    cancel();
                                }
                            }
                        }.runTaskTimer(Main.getInstance(), 0L, 20L);
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerArmorStandManipulateEvent(PlayerArmorStandManipulateEvent event) {

        ArmorStand armorStand = event.getRightClicked();
        if (armorStand.getScoreboardTags().contains("Punching ball")) {
            event.setCancelled(true);
        }
    }
}
