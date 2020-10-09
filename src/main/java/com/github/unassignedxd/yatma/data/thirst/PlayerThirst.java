package com.github.unassignedxd.yatma.data.thirst;

import com.github.unassignedxd.yatma.network.ModNetwork;
import com.github.unassignedxd.yatma.network.PacketThirst;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

public class PlayerThirst implements IPlayerThirst {

    protected float thirstLevel;
    protected float maxThirstLevel;
    protected float overThirst;
    protected PlayerEntity player;

    private boolean needsSync = false;
    private int ticker = 0;

    public PlayerThirst(){}

    public PlayerThirst(PlayerEntity p, float thirstAmount, float maxThirst){
        this.thirstLevel = thirstAmount;
        this.maxThirstLevel = maxThirst;
        this.player = p;
    }

    @Override
    public float getThirst() {
        return this.thirstLevel;
    }

    @Override
    public float getMaxThirst() {
        return this.maxThirstLevel;
    }

    @Override
    public float getOverThirst() {
        return this.overThirst;
    }

    @Override
    public void setOverThirst(float setter) {
        this.overThirst = setter;
        this.needsSync = true;
    }

    @Override
    public void setThirst(float setter) {
        this.thirstLevel = setter;
        this.needsSync = true;
    }

    @Override
    public void setMaxThirst(float setter) {
        this.maxThirstLevel = setter;
        this.needsSync = true;
    }

    @Override
    public float addThirst(float toAdd) {
        this.setThirst(Math.min(this.getMaxThirst(), toAdd+this.getThirst()));
        return (toAdd+this.getThirst()) - this.getMaxThirst();
    }

    @Override
    public float removeThirst(float toRemove) {
        this.setThirst(Math.max(0, this.getThirst()-toRemove));
        return Math.max(toRemove - this.getThirst(), 0);
    }

    @Override
    public PlayerEntity getPlayer() {
        return this.player;
    }

    @Override
    public void update() {
        this.ticker++;

        if(this.player.isSprinting()) {
            this.ticker++;
        }

        if(this.ticker >= 120){
            this.removeThirst(.25f);
            this.ticker = 0;
        }

        if(this.needsSync) {
            if(!this.getPlayer().world.isRemote) ModNetwork.sendToClient(new PacketThirst(this.getThirst(), this.getMaxThirst()), (ServerPlayerEntity)this.getPlayer());
        }
    }
}
