package de.marcely.pcel.versions;

import net.minecraft.server.v1_9_R2.PacketPlayInClientCommand;
import net.minecraft.server.v1_9_R2.PacketPlayInClientCommand.EnumClientCommand;
import net.minecraft.server.v1_9_R2.PacketPlayOutCamera;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import de.marcely.pcel.PlayerCameraEffectPlugin;

public class R2_9 extends VersionHandler {
	
	public void forceRespawn(final Player player){
		Bukkit.getScheduler().scheduleSyncDelayedTask(PlayerCameraEffectPlugin.plugin, new Runnable(){
			@Override
			public void run() {
				if(player.isDead()){
	        		PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
	        		((CraftPlayer) player).getHandle().playerConnection.a(packet);
				}
			}
		}, 2);
	}

	public void sendCameraPacket(Player player, Entity e){
		PacketPlayOutCamera packet = new PacketPlayOutCamera(((CraftEntity) e).getHandle());
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
}