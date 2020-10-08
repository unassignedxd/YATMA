package com.github.unassignedxd.yatma.setup;

import com.github.unassignedxd.yatma.block.TestBlock;
import com.github.unassignedxd.yatma.item.TestItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.github.unassignedxd.yatma.YetAnotherThirstModAttempt.MODID;

public class ModRegistration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<TestBlock> TESTBLOCK = BLOCKS.register("testblock", TestBlock::new);
    public static final RegistryObject<Item> TESTBLOCK_ITEM = ITEMS.register("testblock", () -> new BlockItem(TESTBLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<TestItem> TESTITEM = ITEMS.register("testitem", TestItem::new);
}
