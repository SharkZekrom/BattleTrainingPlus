package be.shark_zekrom;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor, TabExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String string, String[] args) {

        Player player = (Player) commandSender;
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (player.hasPermission("BattleTraining+.reload")) {
                    try {
                        Main.getInstance().getConfig().load(new File(Main.getInstance().getDataFolder(), "config.yml"));
                    } catch (IOException | InvalidConfigurationException e) {
                        throw new RuntimeException(e);
                    }

                    Main.punchingballShowArmorstandNameWhenNotUse = Main.getInstance().getConfig().getBoolean("PunchingballShowArmorstandNameWhenNotUse");
                    Bukkit.broadcastMessage( Main.punchingballShowArmorstandNameWhenNotUse + "");
                    if (Main.punchingballShowArmorstandNameWhenNotUse) {
                        for (World world : Bukkit.getWorlds()) {
                            for (Entity entity : world.getEntities()) {
                                if (entity instanceof ArmorStand armorStand) {
                                    if (armorStand.getScoreboardTags().contains("Punching ball")) {
                                        armorStand.setCustomName(Main.getInstance().getConfig().getString("PunchingballArmorstandName"));
                                        armorStand.setCustomNameVisible(true);
                                    }
                                }
                            }
                        }
                    } else {
                        for (World world : Bukkit.getWorlds()) {
                            for (Entity entity : world.getEntities()) {
                                if (entity instanceof ArmorStand armorStand) {
                                    if (armorStand.getScoreboardTags().contains("Punching ball")) {
                                        armorStand.setCustomName(Main.getInstance().getConfig().getString("PunchingballArmorstandName"));
                                        armorStand.setCustomNameVisible(false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (args.length == 2) {

            if (args[0].equalsIgnoreCase("give")) {
                if (args[1].equalsIgnoreCase("punchingball")) {
                    if (player.hasPermission("BattleTraining+.give.punchingball")) {
                        Punchingball.givePunchingball(player);
                    }
                }
            }
        }
        return false;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        List<String> arguments = new ArrayList<>();
        if (args.length == 1) {
            arguments.add("give");
        }
        if (args[0].equals("give")) {
            arguments.add("punchingball");
        }

            return arguments;
    }

}
