package com.leontg77.teamcommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

import com.leontg77.teamcommands.Main;
import com.leontg77.teamcommands.Utils;

/**
 * PMOres command class.
 * 
 * @author LeonTG77
 */
public class PMOresCommand implements CommandExecutor {
	private final Main plugin;
	
	/**
	 * PMOres class constructor.
	 * 
	 * @param plugin The main class.
	 */
	public PMOresCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can send team ores.");
			return true;
		}
		
		Player player = (Player) sender;
		Team team = Utils.getTeam(player);
		
		if (team == null) {
			sender.sendMessage(ChatColor.RED + "You are not on a team.");
			return true;
		}
		
		String prefix = plugin.getConfig().getString("pm ores.prefix");
		String format = plugin.getConfig().getString("pm ores.format");
		
		int iron = 0, gold = 0, dias = 0;
		
		for (ItemStack item : player.getInventory().getContents()) {
			if (item == null) {
				continue;
			}
			
			switch (item.getType()) {
			case IRON_ORE:
			case IRON_INGOT:
				iron += item.getAmount();
				break;
			case GOLD_ORE:
			case GOLD_INGOT:
				gold += item.getAmount();
				break;
			case DIAMOND_ORE:
			case DIAMOND:
				dias += item.getAmount();
				break;
			default:
				break;
			}
		}

		format = format.replace("{name}", player.getName());
		format = format.replace("{iron}", "" + iron);
		format = format.replace("{gold}", "" + gold);
		format = format.replace("{diamonds}", "" + dias);
		
		Utils.broadcastToTeam(team, ChatColor.translateAlternateColorCodes('&', prefix + format));
		return true;
	}
}