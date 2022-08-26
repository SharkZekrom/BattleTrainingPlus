package be.shark_zekrom;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor, TabExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String string, String[] args) {

        Player player = (Player) commandSender;
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
