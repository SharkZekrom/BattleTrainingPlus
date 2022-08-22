package be.shark_zekrom;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance = this;
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new Listeners(), this);

        this.getCommand("BattleTraining+").setExecutor(new Commands());



        Bukkit.getLogger().info("BattleTraining+ enabled !");

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("BattleTraining+ disabled !");

    }
}
