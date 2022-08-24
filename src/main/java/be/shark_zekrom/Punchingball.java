package be.shark_zekrom;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Punchingball {

    public static HashMap<Player, ArmorStand> punchingball = new HashMap<>();
    public static HashMap<Player, Double> totalDamage = new HashMap<>();
    public static HashMap<Player, Double> lastDamage = new HashMap<>();
    public static HashMap<Player, Double> maxDamage = new HashMap<>();
    public static HashMap<Player, Double> minDamage = new HashMap<>();
    public static HashMap<Player, Integer> hits = new HashMap<>();

    public static HashMap<Player, Integer> punchingballhit = new HashMap<>();





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

    public static void startPunchingball(Player player,ArmorStand armorStand , Integer time) {

        player.sendMessage("start");
        punchingball.put(player,armorStand);
        totalDamage.put(player,0.0);
        lastDamage.put(player, 0.0);
        maxDamage.put(player, 0.0);
        hits.put(player,0);
        double number = lastDamage.get(player);
        DecimalFormat format = new DecimalFormat("0.00");
        armorStand.setCustomName("§c"+ format.format(number) + " | " + time);
        armorStand.setCustomNameVisible(true);
        final int[] countdown = {time};
        new BukkitRunnable() {
            @Override
            public void run() {
                double number = lastDamage.get(player);
                armorStand.setCustomName("§c"+ format.format(number) + " | " + (countdown[0]));

                if (countdown[0] == 0) {
                    cancel();
                    armorStand.setCustomNameVisible(false);


                    player.sendMessage("Total time : " + time);
                    player.sendMessage("Total damage : " + format.format(totalDamage.get(player)));
                    player.sendMessage("Max damage : " + format.format(maxDamage.get(player)));
                    player.sendMessage("Min damage : " + format.format(minDamage.get(player)));
                    player.sendMessage("Damage per seconds : " + format.format((totalDamage.get(player)) / time));
                    player.sendMessage("Average damage : " + format.format(totalDamage.get(player) / (double) hits.get(player)));
                    player.sendMessage("Hits : " + hits.get(player));

                    punchingball.remove(player);
                }

                countdown[0]--;
            }
        }.runTaskTimer(Main.getInstance(),0L, 20L);

    }



    public static void animationPunchingball(ArmorStand armorStand) {


        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() - Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),0L);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() - Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),1L);
        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() + Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),2L);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() + Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),3L);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() - Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),4L);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() - Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),5L);


        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() + Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),6L);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setHeadPose(armorStand.getHeadPose().setX(armorStand.getHeadPose().getX() + Math.toRadians(3)));

            }
        }.runTaskLater(Main.getInstance(),7L);
    }
}
