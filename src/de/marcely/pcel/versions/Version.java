package de.marcely.pcel.versions;

import org.bukkit.Bukkit;

import de.marcely.pcel.Console;
import de.marcely.pcel.PlayerCameraEffectPlugin;

public enum Version {
	
	R1_8(8, R1_8.class),
	R2_8(8, R2_8.class),
	R3_8(8, R3_8.class),
	R1_9(9, R1_9.class),
	R2_9(9, R2_9.class),
	
	R1_10(10, R1_10.class),
	
	R1_11(11, R1_11.class),
	
	R1_12(12, R1_12.class),
	
	R1_13(13, R1_13.class),
	R2_13(13, R2_13.class),
	
	R1_14(14, R1_14.class);
	
	public static Version current;
	
	private final int number;
	private final Class<? extends VersionHandler> handlerClass;
	
	private VersionHandler handler;
	
	private Version(int number, Class<? extends VersionHandler> handlerClass){
		this.number = number;
		this.handlerClass = handlerClass;
	}
	
	public int getVersionNumber(){ return this.number; }
	
	public VersionHandler getHandler(){
		if(this.handler == null){
			try{
				this.handler = (VersionHandler) handlerClass.newInstance();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return this.handler;
	}
	
	public static boolean onEnable(){
		final String PATH = Bukkit.getServer().getClass().getPackage().getName();
		final String VERSION = PATH.substring(PATH.lastIndexOf(".")+1, PATH.length());
		String[] vs = VERSION.split("_");
		
		String bwVersionName = vs[2] + "_" + vs[1];
		
		try{
			Version.current = valueOf(bwVersionName);
			// version.raw = Bukkit.getVersion().split("-")[0];
		}catch(IllegalArgumentException e){
			// msg if no version
			if(Version.current  == null){
				Console.printWarn("IMPORTANT: Failed to work with your version. (" + VERSION + ")! Maybe it's unsupported?");
				Console.printWarn("Stopping the plugin...");
				Bukkit.getPluginManager().disablePlugin(PlayerCameraEffectPlugin.plugin);
				return false;
			}
		}
		
		return true;
	}
}
