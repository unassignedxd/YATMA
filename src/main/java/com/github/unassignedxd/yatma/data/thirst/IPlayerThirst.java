package com.github.unassignedxd.yatma.data.thirst;

import net.minecraft.entity.player.PlayerEntity;

public interface IPlayerThirst {
    int getThirst();
    int getMaxThirst();
    void setThirst(int setter);
    void setMaxThirst(int setter);
    int addThirst(int toAdd);           //returns amount not added
    int removeThirst(int toRemove);     //returns amount to removed

    PlayerEntity getPlayer();
}
