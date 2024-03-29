package com.climpy.essentials;

import com.climpy.profile.ProfilePlugin;
import com.climpy.profile.rank.RankType;
import com.climpy.profile.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldTeleportCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        User user = ProfilePlugin.getInstance().getUserManager().getUser(sender.getName());
        Player player = (Player) sender;

        if (sender instanceof Player && !player.isOp() && !user.getRankType().isAboveOrEqual(RankType.MOD)) {
            sender.sendMessage(ChatColor.RED + "Bu komutu gerçekleştirmek için " + RankType.MOD.getDisplayName() + ChatColor.RED + " veya üzeri rütbe gerekiyor.");
            return true;
        }

            if (args.length == 0) {
                player.sendMessage("§cDoğru Kullan§m: /world <Dünya Adı..>");
                return false;
            }

            String worldName = "";
            for (String arg : args)
                worldName = arg;
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                player.teleport(new Location(world, 0.0D, 80.0D, 0.0D));
                Bukkit.broadcastMessage("§e" + player.getName() + "§9oyuncusu §a" + world.getName() + " §9dünyasına ışınlandı.");
            }
            else if (world == null) {
                player.sendMessage("§cBöyle bir dünya bulunamadı.");
            }
            return true;
    }
}
