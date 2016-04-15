package com.leontg77.teamcommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Joiner;
import com.leontg77.teamcommands.Main;
import com.leontg77.teamcommands.Utils;

/**
 * Team chat command class.
 * 
 * @author LeonTG77
 */
public class TeamChatCommand implements CommandExecutor {
	private final Main plugin;
	
	/**
	 * Team chat class constructor.
	 * 
	 * @param plugin The main class.
	 */
	public TeamChatCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can send private messages.");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <message>");
			return true;
		}
		
		Team team = Utils.getTeam(player);
		
		if (team == null) {
			sender.sendMessage(ChatColor.RED + "You are not on a team.");
			return true;
		}
		
		String prefix = plugin.getConfig().getString("team chat.prefix");
		String format = plugin.getConfig().getString("team chat.format");
		
		String message = Joiner.on(' ').join(args);

		format = format.replace("{name}", player.getName());
		format = format.replace("{message}", message);
		
		Utils.broadcastToTeam(team, ChatColor.translateAlternateColorCodes('&', prefix + format));
		return true;
	}
}