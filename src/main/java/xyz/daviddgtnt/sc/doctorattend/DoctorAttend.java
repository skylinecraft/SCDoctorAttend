package xyz.daviddgtnt.sc.doctorattend;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class DoctorAttend implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            Bukkit.getLogger().warning("You cannot use this command from the console!");
            return true;
        }

        if (sender.hasPermission("doctorattend.doctor")) {
            if (args.length < 1) {
                sender.sendMessage("You need to supply which player you are attending to!");
                return true;
            }

            Player player = Bukkit.getPlayer(args[0]);

            if (player == null) {
                sender.sendMessage(ChatColor.RED + args[0] + " isn't online!");
                return true;
            }

            ConsoleCommandSender commandSender = Bukkit.getConsoleSender();
            Bukkit.dispatchCommand(commandSender, "warp hospital " + sender.getName());
            this.notifyDoctors(sender, player);
        } else {
            sender.sendMessage(ChatColor.RED + "You need to be a doctor to attend to patients!");
        }

        return true;
    }

    private void notifyDoctors(CommandSender sender, Player player) {
        String senderName = sender.getName();
        String playerName = player.getName();

        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for (Player p : players) {
            if (p.hasPermission("doctorattend.doctor")) {
                p.sendMessage(ChatColor.RED + senderName + " is attending to " + playerName + ".");
            }
        }
    }
}
