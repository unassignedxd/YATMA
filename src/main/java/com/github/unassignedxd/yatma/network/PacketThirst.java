package com.github.unassignedxd.yatma.network;

import com.github.unassignedxd.yatma.data.CapabilityPlayerThirst;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketThirst {

    private final float thirst;
    private final float maxThirst;

    public PacketThirst(PacketBuffer buf) {
        thirst = buf.readFloat();
        maxThirst = buf.readFloat();
    }

    public PacketThirst(float thirst, float maxThirst) {
        this.thirst = thirst;
        this.maxThirst = maxThirst;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeFloat(thirst);
        buf.writeFloat(maxThirst);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Minecraft.getInstance().player.getCapability(CapabilityPlayerThirst.PLAYER_THIRST_CAPABILITY).ifPresent(h->{
                h.setThirst(thirst);
                h.setMaxThirst(maxThirst);
            });
        });
        return true;
    }
}
