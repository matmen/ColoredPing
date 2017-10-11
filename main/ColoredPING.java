package main;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.metrics.Metrics;

import listener.PingListener;

public class ColoredPING extends JavaPlugin {
	public static ColoredPING INSTANCE;
	public ProtocolManager protocolManager;
	private PingListener listener;
	public String eventMOTD = "";
	public String prefix = "§7[§cColored§lPING§7] ";

	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("event")) {
			if (sender.hasPermission("coloredping.event")) {
				if (args.length == 0) {
					sender.sendMessage(this.prefix + "§cUsage: /event <MOTD / clear>");
				} else if (args[0].equalsIgnoreCase("clear")) {
					sender.sendMessage(this.prefix + "§cMOTD gelöscht!");
					this.eventMOTD = "";
				} else {
					int a = args.length;
					int i = 0;
					StringBuilder emotd = new StringBuilder();
					this.eventMOTD = "";
					while (i < a) {
						emotd.append(args[i].replace("&", "§") + " ");
						i++;
					}

					this.eventMOTD = emotd.toString();
					sender.sendMessage(this.prefix + "§cEvent- MOTD gesetzt: §f'§r§7" + this.eventMOTD + "§r§f'");
				}
			} else {
				sender.sendMessage(this.prefix + "§cKeine Rechte!");
			}

			return true;
		}
		return false;
	}

	public void onEnable() {
		INSTANCE = this;
		this.protocolManager = ProtocolLibrary.getProtocolManager();
		this.listener = new PingListener();
		this.listener.addPingResponsePacketListener();
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException localIOException) {
		}
	}
}