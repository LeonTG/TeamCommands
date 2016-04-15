package com.leontg77.teamcommands;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.leontg77.teamcommands.commands.PMOresCommand;
import com.leontg77.teamcommands.commands.TeamChatCommand;
import com.leontg77.teamcommands.commands.TeamCoordsCommand;

/**
 * Main class of the plugin.
 * 
 * @author LeonTG77
 */
public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " is now disabled.");
	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " v" + file.getVersion() + " is now enabled.");

		getCommand("pmores").setExecutor(new PMOresCommand(this));
		getCommand("teamchat").setExecutor(new TeamChatCommand(this));
		getCommand("teamcoords").setExecutor(new TeamCoordsCommand(this));
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}