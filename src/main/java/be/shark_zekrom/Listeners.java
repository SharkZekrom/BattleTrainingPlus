package be.shark_zekrom;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.HashMap;

import static java.lang.String.format;

public class Listeners implements Listener {

    public static HashMap<Player, Integer> punchingballhit = new HashMap<>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();

            if (mainHand.getType() == Material.ARMOR_STAND ) {
                if (mainHand.hasItemMeta()) {
                    if (mainHand.getItemMeta().hasDisplayName()) {
                        if (mainHand.getItemMeta().getDisplayName().equals("BattleTrainingPlus")) {
                            event.setCancelled(true);
                            Utils.spawnPunchingball(event.getPlayer(), event.getClickedBlock().getLocation().add(0.5,1,0.5));
                        }
                    }

                }
            }
        }

    }

    @EventHandler
    public void onRightClickEntity(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ArmorStand armorStand) {
            if (armorStand.getScoreboardTags().contains("Punchingball")) {
                Menu.Punchingball(event.getPlayer(), armorStand);
            }

        }

    }

    @EventHandler
    public void entityDeathEvent(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof ArmorStand armorStand && event.getDamager() instanceof Player player) {
            if (armorStand.getScoreboardTags().contains("Punchingball")) {
                event.setCancelled(true);
                Utils.animationPunchingball(armorStand);

                double number = event.getDamage();
                DecimalFormat format = new DecimalFormat("0.00");

                armorStand.setCustomName("-" + format.format(number) + "");
                armorStand.setCustomNameVisible(true);
                if (punchingballhit.containsKey(player)) {
                    punchingballhit.put(player, punchingballhit.get(player) + 3);
                } else {
                    punchingballhit.put(player, 3);

                }
                final int[] countdown = {3};
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        countdown[0]--;

                        punchingballhit.put(player ,punchingballhit.get(player) - 1);

                        if (punchingballhit.get(player) == 0) {
                            armorStand.setCustomNameVisible(false);

                        }

                        if (countdown[0] == 0) {
                            cancel();
                        }



                    }
                }.runTaskTimer(Main.getInstance(),0L,20L);
            }
        }
    }

}
