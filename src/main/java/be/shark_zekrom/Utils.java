package be.shark_zekrom;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class Utils {


    public static void givePunchingball(Player player) {

        ItemStack itemStack = new ItemStack(Material.ARMOR_STAND);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Punchingball");
        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(itemStack);
    }

    public static void spawnPunchingball(Player player, Location location) {

        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCustomName("Punchingball");
        armorStand.setCanPickupItems(false);
        armorStand.setGravity(false);
        armorStand.addScoreboardTag("Punchingball");

        armorStand.setHelmet(new ItemStack(Material.STONE));




    }


    public static void animationPunchingball(ArmorStand armorStand) {


        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() + Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),0L);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() + Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),1L);
        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() - Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),2L);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() - Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),3L);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() + Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),4L);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() + Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),5L);


        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() - Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),6L);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() - Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),7L);
    }
}
