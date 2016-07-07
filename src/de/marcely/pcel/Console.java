package de.marcely.pcel;

import org.bukkit.Bukkit;

public class Console {
	public static void printWarn(String warn){
		Bukkit.getLogger().warning("[MBedwars] " + warn);
	}
	
	public static void printInfo(String info){
		Bukkit.getLogger().info("[MBedwars] " + info);
	}
}
