package com.github.unassignedxd.yatma.setup;

import com.github.unassignedxd.yatma.data.CapabilityPlayerThirst;
import com.github.unassignedxd.yatma.network.ModNetwork;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

    public static final ItemGroup ITEM_GROUP = new ItemGroup("yatma") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.APPLE);
        }
    };

    public static void init(final FMLCommonSetupEvent event) {
        ModNetwork.registerMessages();
        CapabilityPlayerThirst.register();
    }
}
