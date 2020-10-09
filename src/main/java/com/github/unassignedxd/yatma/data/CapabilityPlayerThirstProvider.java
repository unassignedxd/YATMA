package com.github.unassignedxd.yatma.data;

import com.github.unassignedxd.yatma.data.thirst.IPlayerThirst;
import com.github.unassignedxd.yatma.data.thirst.PlayerThirst;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityPlayerThirstProvider implements ICapabilitySerializable<CompoundNBT> {

    private final PlayerThirst thirst;
    private final LazyOptional<IPlayerThirst> thirstOptional;

    public CapabilityPlayerThirstProvider(PlayerEntity player, int curThirst, int maxThirst){
        this.thirst = new PlayerThirst(player, curThirst, maxThirst);
        this.thirstOptional = LazyOptional.of(()->thirst);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return thirstOptional.cast();
    }

    @Override
    public CompoundNBT serializeNBT() {
        if (CapabilityPlayerThirst.PLAYER_THIRST_CAPABILITY == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityPlayerThirst.PLAYER_THIRST_CAPABILITY.writeNBT(thirst, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (CapabilityPlayerThirst.PLAYER_THIRST_CAPABILITY != null) {
            CapabilityPlayerThirst.PLAYER_THIRST_CAPABILITY.readNBT(thirst, null, nbt);
        }
    }
}
