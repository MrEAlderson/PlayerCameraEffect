package de.marcely.pcel.versions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public abstract class VersionHandler {
	
	public abstract void forceRespawn(final Player player);
	
	public abstract void sendCameraPacket(final Player player, Entity e);
}