package be.shark_zekrom;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.HologramLine;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
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

    public static HashMap<ArmorStand, Integer> punchingballhit = new HashMap<>();

    public static Hologram hologram;





    public static void givePunchingball(Player player) {

        ItemStack itemStack = new ItemStack(Material.ARMOR_STAND);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Punching ball");
        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(itemStack);
    }

    public static void spawnPunchingball(Location location) {

        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCanPickupItems(false);
        armorStand.setGravity(false);
        armorStand.addScoreboardTag("Punching ball");

        armorStand.setHelmet(new ItemStack(Material.HAY_BLOCK));

        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta chestplateMeta = chestplate.hasItemMeta() ? chestplate.getItemMeta() : Bukkit.getItemFactory().getItemMeta(chestplate.getType());
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) chestplateMeta;
        leatherArmorMeta.setColor(Color.fromRGB(254, 216, 61));
        chestplate.setItemMeta(leatherArmorMeta);
        armorStand.setChestplate(chestplate);

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta leggingsMeta = leggings.hasItemMeta() ? leggings.getItemMeta() : Bukkit.getItemFactory().getItemMeta(leggings.getType());
        LeatherArmorMeta leggingsArmorMeta = (LeatherArmorMeta) leggingsMeta;
        leggingsArmorMeta.setColor(Color.fromRGB(254, 216, 61));
        leggings.setItemMeta(leggingsArmorMeta);
        armorStand.setLeggings(leggings);

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta bootsMeta = boots.hasItemMeta() ? boots.getItemMeta() : Bukkit.getItemFactory().getItemMeta(boots.getType());
        LeatherArmorMeta bootsArmorMeta = (LeatherArmorMeta) bootsMeta;
        bootsArmorMeta.setColor(Color.fromRGB(254, 216, 61));
        boots.setItemMeta(bootsArmorMeta);
        armorStand.setBoots(boots);


    }

    public static void startPunchingball(Player player,ArmorStand armorStand , Integer time) {

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

                    player.sendMessage(Main.getInstance().getConfig().getString("PunchingballChatTotalTime").replace("{TotalTime}", String.valueOf(time)));
                    player.sendMessage(Main.getInstance().getConfig().getString("PunchingballChatTotalDamage").replace("{TotalDamage}", format.format(totalDamage.get(player))));
                    player.sendMessage(Main.getInstance().getConfig().getString("PunchingballChatMaxDamage").replace("{MaxDamage}", format.format(maxDamage.get(player))));
                    minDamage.putIfAbsent(player, 0.0);
                    player.sendMessage(Main.getInstance().getConfig().getString("PunchingballChatMinDamage").replace("{MinDamage}", format.format(minDamage.get(player))));
                    player.sendMessage(Main.getInstance().getConfig().getString("PunchingballChatDamagePerSeconds").replace("{DamagePerSeconds}", format.format((totalDamage.get(player)) / time)));
                    player.sendMessage(Main.getInstance().getConfig().getString("PunchingballChatAverageDamage").replace("{AverageDamage}", format.format(totalDamage.get(player) / (double) hits.get(player))));
                    player.sendMessage(Main.getInstance().getConfig().getString("PunchingballChatHits").replace("{Hits}", String.valueOf(hits.get(player))));

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


    public static void spawnHologram() {
        hologram.clearLines();
        hologram.appendTextLine("§bLeaderboard §7| §eDamage per seconds");
        hologram.appendTextLine("");
        hologram.appendTextLine("§6Total Time §7| §e15 seconds ");
        hologram.appendTextLine("");
        hologram.appendTextLine("§c#1 §7|");
        hologram.appendTextLine("§c#2 §7|");
        hologram.appendTextLine("§c#3 §7|");
        hologram.appendTextLine("§c#4 §7|");
        hologram.appendTextLine("§c#5 §7|");
        new BukkitRunnable() {
            @Override
            public void run() {
                hologram.clearLines();
                hologram.appendTextLine("§bLeaderboard §7| §eDamage per seconds");
                hologram.appendTextLine("");
                hologram.appendTextLine("§6Total Time §7| §e30 seconds ");
                hologram.appendTextLine("");
                hologram.appendTextLine("§c#1 §7|");
                hologram.appendTextLine("§c#2 §7|");
                hologram.appendTextLine("§c#3 §7|");
                hologram.appendTextLine("§c#4 §7|");
                hologram.appendTextLine("§c#5 §7|");



            }
        }.runTaskLater(Main.getInstance(), 100L);

        new BukkitRunnable() {
            @Override
            public void run() {
                spawnHologram();


            }
        }.runTaskLater(Main.getInstance(), 200L);
    }
}
