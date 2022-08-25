package be.shark_zekrom;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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

        config.addDefault("EditingMenu", "Punching ball editing");
        config.addDefault("PunchingballMenu", "Punching ball");

        config.options().copyDefaults(true);
        saveConfig();


        Bukkit.getLogger().info("BattleTraining+ enabled !");

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("BattleTraining+ disabled !");
        //Punchingball.hologram.delete();
    }
}
