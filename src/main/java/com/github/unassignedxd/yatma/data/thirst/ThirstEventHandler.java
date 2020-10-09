package com.github.unassignedxd.yatma.data.thirst;

import com.github.unassignedxd.yatma.YetAnotherThirstModAttempt;
import com.github.unassignedxd.yatma.data.CapabilityPlayerThirst;
import com.github.unassignedxd.yatma.data.CapabilityPlayerThirstProvider;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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

    @SubscribeEvent
    public static void onPlayerInteractEvent(PlayerInteractEvent event) {
        World w = event.getWorld();
        PlayerEntity p = event.getPlayer();
        if(event.getHand() == Hand.MAIN_HAND) {
            if(!w.isRemote) {
                if(p.isSneaking()) {
                    System.out.println(event.getPos());
                    if(w.getBlockState(event.getPos().offset(Direction.UP)).getBlock() == Blocks.WATER) {
                        p.getCapability(CapabilityPlayerThirst.PLAYER_THIRST_CAPABILITY).ifPresent(h->{
                            h.addThirst(2);
                        });
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        player.getCapability(CapabilityPlayerThirst.PLAYER_THIRST_CAPABILITY).ifPresent(IPlayerThirst::update);
    }

}
