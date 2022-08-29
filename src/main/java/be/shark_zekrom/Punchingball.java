package be.shark_zekrom;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Punchingball implements Listener {

    public static HashMap<Player, ArmorStand> punchingball = new HashMap<>();
    public static HashMap<Player, Double> totalDamage = new HashMap<>();
    public static HashMap<Player, Double> lastDamage = new HashMap<>();
    public static HashMap<Player, Double> maxDamage = new HashMap<>();
    public static HashMap<Player, Double> minDamage = new HashMap<>();
    public static HashMap<Player, Integer> hits = new HashMap<>();

    public static HashMap<ArmorStand, Integer> punchingballhit = new HashMap<>();






    public static void givePunchingball(Player player) {

        ItemStack itemStack = new ItemStack(Material.ARMOR_STAND);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§fPunching ball");
        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(itemStack);
    }

    public static void spawnPunchingball(Player player,Location location) {

        if (location.getWorld().getNearbyEntities(location,0.5,0.5,0.5).isEmpty()) {

            location.getBlock().setType(Material.OAK_FENCE);
            if (Utils.getDirection(player).equals("N") || Utils.getDirection(player).equals("NE")) {
                location.setYaw(270);
            } else if (Utils.getDirection(player).equals("E") || Utils.getDirection(player).equals("SE")) {
                location.setYaw(0);

            } else if (Utils.getDirection(player).equals("S") || Utils.getDirection(player).equals("SW")) {
                location.setYaw(90);

            } else if (Utils.getDirection(player).equals("W") || Utils.getDirection(player).equals("NW")) {
                location.setYaw(180);

            }

            ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            armorStand.setCanPickupItems(false);
            armorStand.setGravity(false);
            armorStand.setVisible(false);
            armorStand.addScoreboardTag("Punching ball");

            armorStand.setHelmet(new ItemStack(Material.HAY_BLOCK));

            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemMeta chestplateMeta = chestplate.hasItemMeta() ? chestplate.getItemMeta() : Bukkit.getItemFactory().getItemMeta(chestplate.getType());
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) chestplateMeta;
            leatherArmorMeta.setColor(Color.fromRGB(254, 216, 61));
            chestplate.setItemMeta(leatherArmorMeta);
            armorStand.setChestplate(chestplate);
        }

    }

    public static void startPunchingball(Player player,ArmorStand armorStand , Integer time) {
        punchingball.put(player, armorStand);


        final int[] countdown = {3};
        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setCustomName("§c" + countdown[0]);
                if (countdown[0] == 0) {
                    cancel();


                    totalDamage.put(player, 0.0);
                    lastDamage.put(player, 0.0);
                    maxDamage.put(player, 0.0);
                    hits.put(player, 0);
                    double number = lastDamage.get(player);
                    DecimalFormat format = new DecimalFormat("0.00");
                    armorStand.setCustomName("§c" + format.format(number) + " | " + time);
                    armorStand.setCustomNameVisible(true);
                    final int[] newtime = {time};
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            double number = lastDamage.get(player);
                            armorStand.setCustomName(Main.getInstance().getConfig().getString("PunchingballCountdown").replace("{Damage}", format.format(number)).replace("{Countdown}", String.valueOf(newtime[0])));

                            if (newtime[0] == 0) {
                                cancel();

                                if (Main.getInstance().getConfig().getBoolean("PunchingballShowArmorstandNameWhenNotUse")) {
                                    armorStand.setCustomName(Main.getInstance().getConfig().getString("PunchingballArmorstandName"));
                                    armorStand.setCustomNameVisible(true);

                                } else {
                                    armorStand.setCustomNameVisible(false);
                                }


                                minDamage.putIfAbsent(player, 0.0);
                                for (String string : Main.getInstance().getConfig().getStringList("PunchingballChatScore")) {

                                    String output = string.replace("{TotalTime}", String.valueOf(time)).replace("{TotalDamage}", format.format(totalDamage.get(player))).replace("{MaxDamage}", format.format(maxDamage.get(player))).replace("{MinDamage}", format.format(minDamage.get(player))).replace("{DamagePerSeconds}", format.format((totalDamage.get(player)) / time)).replace("{AverageDamage}", format.format(totalDamage.get(player) / (double) hits.get(player))).replace("{Hits}", String.valueOf(hits.get(player)));
                                    player.sendMessage(output);
                                }
                                punchingball.remove(player);
                            }

                            newtime[0]--;
                        }
                    }.runTaskTimer(Main.getInstance(), 0L, 20L);
                }
                countdown[0]--;
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L);

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

                                        if (Main.getInstance().getConfig().getBoolean("PunchingballShowArmorstandNameWhenNotUse")) {

                                            armorStand.setCustomName(Main.getInstance().getConfig().getString("PunchingballArmorstandName"));
                                            armorStand.setCustomNameVisible(true);

                                        } else {
                                            armorStand.setCustomNameVisible(false);
                                        }
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
