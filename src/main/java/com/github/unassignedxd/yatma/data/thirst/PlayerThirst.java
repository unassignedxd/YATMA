package com.github.unassignedxd.yatma.data.thirst;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerThirst implements IPlayerThirst {

    protected int thirstLevel;
    protected int maxThirstLevel;
    protected PlayerEntity player;

    public PlayerThirst(){}

    public PlayerThirst(PlayerEntity p, int thirstAmount, int maxThirst){
        this.thirstLevel = thirstAmount;
        this.maxThirstLevel = maxThirst;
        this.player = p;
    }

    @Override
    public int getThirst() {
        return this.thirstLevel;
    }

    @Override
    public int getMaxThirst() {
        return this.maxThirstLevel;
    }

    @Override
    public void setThirst(int setter) {
        this.thirstLevel = setter;
    }

    @Override
    public void setMaxThirst(int setter) {
        this.maxThirstLevel = setter;
    }

    @Override
    public int addThirst(int toAdd) {
        this.setThirst(Math.min(this.getMaxThirst(), toAdd+this.getThirst()));
        return (toAdd+this.getThirst()) - this.getMaxThirst();
    }

    @Override
    public int removeThirst(int toRemove) {
        this.setThirst(Math.max(0, this.getThirst()-toRemove));
        return Math.max(toRemove - this.getThirst(), 0);
    }

    @Override
    public PlayerEntity getPlayer() {
        return player;
    }
}
