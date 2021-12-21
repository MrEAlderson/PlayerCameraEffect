package de.marcely.pcel.versions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public abstract class VersionHandler {
	
	public abstract void forceRespawn(Player player);
	
	public abstract void sendCameraPacket(Player player, Entity e);
}