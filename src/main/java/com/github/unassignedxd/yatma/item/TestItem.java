package com.github.unassignedxd.yatma.item;

import com.github.unassignedxd.yatma.data.CapabilityPlayerThirst;
import com.github.unassignedxd.yatma.setup.ModSetup;
import javafx.animation.TranslateTransition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.UUID;

public class TestItem extends Item {

    public TestItem() {
        super(new Properties()
                .maxStackSize(8)
                .group(ModSetup.ITEM_GROUP));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if(!world.isRemote) {
           if(player != null) {
               player.getCapability(CapabilityPlayerThirst.PLAYER_THIRST_CAPABILITY, null).ifPresent(h -> {
                   player.sendMessage(new StringTextComponent("current thirst: " + h.getThirst()));
                   if(player.isSneaking()) h.removeThirst(2);
               });
           }
        }
        return super.onItemRightClick(world, player, hand);
    }
}
