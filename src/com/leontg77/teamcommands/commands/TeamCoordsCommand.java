package com.leontg77.teamcommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import com.leontg77.teamcommands.Main;
import com.leontg77.teamcommands.Utils;

/**
 * Team coords command class.
 * 
 * @author LeonTG77
 */
public class TeamCoordsCommand implements CommandExecutor {
	private final Main plugin;
	
	/**
	 * Team coords class constructor.
	 * 
	 * @param plugin The main class.
	 */
	public TeamCoordsCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can send their coords.");
			return true;
		}
		
		Player player = (Player) sender;
		Team team = Utils.getTeam(player);
		
		if (team == null) {
			sender.sendMessage(ChatColor.RED + "You are not on a team.");
			return true;
		}
		
		String prefix = plugin.getConfig().getString("team coords.prefix");
		String format = plugin.getConfig().getString("team coords.format");
		
		Location loc = player.getLocation();

		format = format.replace("{name}", player.getName());
		format = format.replace("{x}", "" + loc.getBlockX());
		format = format.replace("{y}", "" + loc.getBlockY());
		format = format.replace("{z}", "" + loc.getBlockZ());
		format = format.replace("{dimention}", environment(player.getWorld()));
		
		Utils.broadcastToTeam(team, ChatColor.translateAlternateColorCodes('&', prefix + format));
		return true;
	}

	/**
	 * Get the String version of the given world's environment type.
	 * 
	 * @param world The world checking.
	 * @return The string version of the type.
	 */
	private String environment(World world) {
		switch (world.getEnvironment()) {
		case NORMAL:
			return "Overworld";
		case NETHER:
			return "Nether";
		case THE_END:
			return "The End";
		default:
			return "Unknown";
		}
	}
}