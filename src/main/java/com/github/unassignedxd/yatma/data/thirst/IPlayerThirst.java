package com.github.unassignedxd.yatma.data.thirst;

import net.minecraft.entity.player.PlayerEntity;

public interface IPlayerThirst {
    float getThirst();
    float getMaxThirst();
    float getOverThirst();

    void setOverThirst(float setter);
    void setThirst(float setter);
    void setMaxThirst(float setter);
    float addThirst(float toAdd);           //returns amount not added
    float removeThirst(float toRemove);     //returns amount to removed

    PlayerEntity getPlayer();
    void update();
}
