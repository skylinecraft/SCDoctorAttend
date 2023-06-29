package xyz.daviddgtnt.sc.doctorattend;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class NeedDoctorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        if (args.length < 1) {
            if (sender instanceof Player) {
                player = (Player) sender;
                player.sendMessage(ChatColor.RED + "You can't use that command! Go to the hospital if you need a doctor!");
            } else {
                Bukkit.getLogger().warning("You need to supply a player!");
            }
            return true;
        }

        player = Bukkit.getPlayer(args[0]);

        if (player != null) {
            if (sender instanceof ConsoleCommandSender) {
                player.sendMessage(ChatColor.RED + "Calling all online doctors, please wait.");
                Bukkit.getLogger().info(args[0] + " called online doctors for help.");
                this.callDoctors(player);
            } else {
                player.sendMessage(ChatColor.RED + "You can't use that command! Go to the hospital if you need a doctor!");
                return true;
            }
        } else {
            Bukkit.getLogger().warning("Player is not online.");
            return true;
        }

        return true;
    }

    private void callDoctors(Player player) {
        String playerName = player.getName();

        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for (Player p : players) {
            if (p.hasPermission("doctorattend.doctor")) {
                p.sendMessage(ChatColor.RED + playerName + " needs help at the hospital!");
            }
        }
    }
}
