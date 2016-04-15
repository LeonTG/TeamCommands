package com.leontg77.teamcommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Utilities class.
 * 
 * @author LeonTG77
 */
@SuppressWarnings("deprecation")
public class Utils {
	private static final Scoreboard BOARD = Bukkit.getScoreboardManager().getMainScoreboard();
	
	/**
	 * Sends a message to everyone on the given team.
	 * 
	 * @param team The team to use.
	 * @param message The message to send.
	 */
	public static void broadcastToTeam(Team team, String message) {
		for (OfflinePlayer teammate : team.getPlayers()) {
			Player online = teammate.getPlayer();
			
			if (online == null) {
				continue;
			}
			
			online.sendMessage(message);
		}
	}

	/**
	 * Gets the team of the given player player.
	 * 
	 * @param player the player in the team.
	 * @return The team, null if the player isn't on a team.
	 */
	public static Team getTeam(OfflinePlayer player) {
		return BOARD.getPlayerTeam(player);
	}
}