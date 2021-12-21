package de.marcely.pcel.versions;

import net.minecraft.server.v1_8_R1.EnumClientCommand;
import net.minecraft.server.v1_8_R1.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R1.PacketPlayOutCamera;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class R1_8 extends VersionHandler {

	@Override
	public void forceRespawn(Player player){
		final PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);

		((CraftPlayer) player).getHandle().playerConnection.a(packet);
	}

	@Override
	public void sendCameraPacket(Player player, Entity e){
		final PacketPlayOutCamera packet = new PacketPlayOutCamera(((CraftEntity) e).getHandle());

		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
}