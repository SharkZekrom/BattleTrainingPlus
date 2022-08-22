package be.shark_zekrom;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {


    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();

            if (mainHand.getType() == Material.ARMOR_STAND ) {
                if (mainHand.hasItemMeta()) {
                    if (mainHand.getItemMeta().hasDisplayName()) {
                        if (mainHand.getItemMeta().getDisplayName().equals("BattleTrainingPlus")) {
                            event.setCancelled(true);

                            //TODO spawn
                        }
                    }

                }
            }
        }

    }

}
