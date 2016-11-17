package de.marcely.pcel.versions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import de.marcely.pcel.Console;
import de.marcely.pcel.PlayerCameraEffectPlugin;

public enum Version {
	R1_8(8),
	R2_8(8),
	R3_8(8),
	R1_9(9),
	R2_9(9),
	R1_10(10),
	R1_11(11);
	
	private int selected_number;
	private VersionClass selected_class;
	
	private Version(int number){
		this.selected_number = number;
	}
	
	public int getVersionNumber(){ return this.selected_number; }
	
	public VersionClass getVersionClass(){
		if(this.selected_class == null){
			try{
				selected_class = (VersionClass) Class.forName("de.marcely.pcel.versions." + name()).newInstance();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return this.selected_class;
	}
	
	
	
	public static Version version = null;
	
	public static boolean onEnable(){
		String v = Bukkit.getVersion().split("\\(")[1].replace(")", "").replace("MC: ", "");
		String[] vs = v.split("\\.");
		
		// get version numbers
		int v1 = Integer.valueOf(vs[0]),
			v2 = Integer.valueOf(vs[1]),
			v3 = 0;
		if(vs.length >= 3)
			v3 = Integer.valueOf(vs[2]);
		
		// get version R
		// 1.*.*
		if(v1 == 1){
			
			// 1.8.*
			if(v2 == 8){
				
				if(v3 >= 0 && v3 <= 2) version = R1_8;
				if(v3 >= 3 && v3 <= 5) version = R2_8;
				if(v3 >= 6) version = R3_8;
				
			// 1.9.*
			}else if(v2 == 9){
				
				if(v3 >= 0 && v3 <= 3) version = R1_9;
				if(v3 >= 4) version = R2_9;
			
			// 1.10.*
			}else if(v2 == 10){
				
				if(v3 >= 0) version = R1_10;
				
			// 1.11.*
			}else if(v2 == 11){
				
				if(v3 >= 0) version = R1_11;
				
			}
		}
		
		// msg if no version
		if(version == null){
			Console.printWarn("IMPORTANT: Failed to get your version. (" + v + ")!");
			Console.printWarn("Stopping the plugin...");
			Bukkit.getPluginManager().disablePlugin(PlayerCameraEffectPlugin.plugin);
			return false;
		}
		
		return true;
	}
	
	public static void forceRespawn(Player player){ version.getVersionClass().forceRespawn(player); }
	
	public static void sendCameraPacket(final Player player, Entity e){ version.getVersionClass().sendCameraPacket(player, e); }
	
	public static abstract class VersionClass {
		
		public abstract void forceRespawn(final Player player);
		
		public abstract void sendCameraPacket(final Player player, Entity e);
	}
}
