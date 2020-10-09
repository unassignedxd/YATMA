package com.github.unassignedxd.yatma.client.events;

import com.github.unassignedxd.yatma.YetAnotherThirstModAttempt;
import com.github.unassignedxd.yatma.data.CapabilityPlayerThirst;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = YetAnotherThirstModAttempt.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {

    private static Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }

        mc.player.getCapability(CapabilityPlayerThirst.PLAYER_THIRST_CAPABILITY).ifPresent(h->{
            mc.fontRenderer.drawString("Current Thirst: " + h.getThirst(), 20, 20, 0);
        });
    }
}
