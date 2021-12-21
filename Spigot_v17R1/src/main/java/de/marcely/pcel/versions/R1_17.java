package de.marcely.pcel.versions;

import net.minecraft.network.protocol.game.PacketPlayInClientCommand;
import net.minecraft.network.protocol.game.PacketPlayOutCamera;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class R1_17 extends VersionHandler {

    @Override
    public void forceRespawn(Player player){
        final PacketPlayInClientCommand packet = new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.a);

        ((CraftPlayer) player).getHandle().b.a(packet);
    }

    @Override
    public void sendCameraPacket(Player player, Entity e){
        final PacketPlayOutCamera packet = new PacketPlayOutCamera(((CraftEntity) e).getHandle());

        ((CraftPlayer) player).getHandle().b.sendPacket(packet);
    }
}
