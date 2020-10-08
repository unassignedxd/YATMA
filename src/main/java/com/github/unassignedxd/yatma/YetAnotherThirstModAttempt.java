package com.github.unassignedxd.yatma;

import com.github.unassignedxd.yatma.setup.ModRegistration;
import com.github.unassignedxd.yatma.setup.ModSetup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(YetAnotherThirstModAttempt.MODID)
public class YetAnotherThirstModAttempt {

    public static final String MODID = "yatma";

    private static final Logger LOGGER = LogManager.getLogger();

    public YetAnotherThirstModAttempt() {
        ModRegistration.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        LOGGER.info("YATMA Initialization Complete!");
    }

}
