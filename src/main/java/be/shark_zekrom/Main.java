package be.shark_zekrom;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }
    //public boolean useHolographicDisplays;
    @Override
    public void onEnable() {
        instance = this;
        PluginManager pm = getServer().getPluginManager();

        // if (Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
        //    useHolographicDisplays = Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays");
        //    Punchingball.hologram = HologramsAPI.createHologram(Main.getInstance(), new Location(Bukkit.getWorld("world2"),0,91,0));
        //    Punchingball.spawnHologram();

        //} else {
        //    getLogger().severe("*** HolographicDisplays is not installed or not enabled. You can't use leaderboard ***");

        // }

        pm.registerEvents(new Listeners(), this);
        pm.registerEvents(new Menu(), this);

        this.getCommand("BattleTraining+").setExecutor(new Commands());


        FileConfiguration config = getConfig();

        config.addDefault("PunchingballShowArmorstandNameWhenNotUse", true);
        config.addDefault("PunchingballArmorstandName", "§6Dummy");
        config.addDefault("PunchingballEditingMenu", "Punching ball editing");
        config.addDefault("PunchingballMenu", "Punching ball");
        config.addDefault("Punchingball15seconds", "§e15 seconds");
        config.addDefault("Punchingball30seconds", "§e30 seconds");
        config.addDefault("Punchingball60seconds", "§e60 seconds");
        config.addDefault("Punchingball90seconds", "§e90 seconds");
        config.addDefault("Punchingball120seconds", "§e120 seconds");
        config.addDefault("PunchingballRemove", "&cRemove");
        config.addDefault("PunchingballCountdown", "§c{Damage} | {Countdown}");

        config.addDefault("PunchingballChatScore", List.of(new String[]{
                "§b=====[BattleTrainingPlus]=====",
                " ",
                "§6Total time §7» §e{TotalTime}",
                "§6Total damage §7» §e{TotalDamage}",
                "§6Max damage §7» §e{MaxDamage}",
                "§6Min damage §7» §e{MinDamage}",
                "§6Damage per seconds §7» §e{DamagePerSeconds}",
                "§6Average damage §7» §e{AverageDamage}",
                "§6Hits §7» §e{Hits}",
                " "}));


        config.options().copyDefaults(true);
        saveConfig();

        if (config.getBoolean("PunchingballShowArmorstandNameWhenNotUse")) {
            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity instanceof ArmorStand armorStand) {
                        if (armorStand.getScoreboardTags().contains("Punching ball")) {
                            armorStand.setCustomName(config.getString("PunchingballArmorstandName"));
                            armorStand.setCustomNameVisible(true);
                        }
                    }
                }
            }
        }
        Bukkit.getLogger().info("BattleTraining+ enabled !");

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("BattleTraining+ disabled !");
        //Punchingball.hologram.delete();

        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof ArmorStand armorStand) {
                    if (armorStand.getScoreboardTags().contains("Punching ball")) {
                        armorStand.setCustomNameVisible(false);
                    }
                }
            }
        }
    }
}
