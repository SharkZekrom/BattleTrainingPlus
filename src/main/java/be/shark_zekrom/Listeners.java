package be.shark_zekrom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
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
    public void projectileHitEvent(ProjectileHitEvent event) {


    }

}
