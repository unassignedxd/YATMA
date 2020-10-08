package com.github.unassignedxd.yatma.data;

import com.github.unassignedxd.yatma.data.thirst.IPlayerThirst;
import com.github.unassignedxd.yatma.data.thirst.PlayerThirst;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityPlayerThirst {

    @CapabilityInject(IPlayerThirst.class)
    public static Capability<IPlayerThirst> PLAYER_THIRST_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IPlayerThirst.class, new Storage(), PlayerThirst::new);
    }

    public static class Storage implements Capability.IStorage<IPlayerThirst> {
        @Nullable
        @Override
        public INBT writeNBT(Capability<IPlayerThirst> capability, IPlayerThirst instance, Direction side) {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("thirstlevel", instance.getThirst());
            tag.putInt("maxthirstlevel", instance.getMaxThirst());
            return tag;
        }

        @Override
        public void readNBT(Capability<IPlayerThirst> capability, IPlayerThirst instance, Direction side, INBT nbt) {
            instance.setThirst(((CompoundNBT)nbt).getInt("thirstlevel"));
            instance.setMaxThirst(((CompoundNBT)nbt).getInt("maxthirstlevel"));
        }
    }
}
