package com.github.unassignedxd.yatma.data.thirst;

import com.github.unassignedxd.yatma.YetAnotherThirstModAttempt;
import com.github.unassignedxd.yatma.data.CapabilityPlayerThirstProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = YetAnotherThirstModAttempt.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ThirstEventHandler {

    @SubscribeEvent
    public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            CapabilityPlayerThirstProvider provider = new CapabilityPlayerThirstProvider((PlayerEntity)event.getObject(), 20, 20);
            event.addCapability(new ResourceLocation(YetAnotherThirstModAttempt.MODID, "thirst"), provider);
        }
    }

}
