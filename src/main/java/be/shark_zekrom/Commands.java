package be.shark_zekrom;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String string, String[] args) {

        Player player = (Player) commandSender;
        if (args.length == 2) {

            if (args[0].equalsIgnoreCase("give")) {
                if (args[1].equalsIgnoreCase("Punchingball")) {
                    Utils.givePunchingball(player);
                }
            }
        }

        return false;

    }
}
