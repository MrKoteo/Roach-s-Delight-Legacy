package com.shiver.roachdelight;

import com.shiver.roachdelight.common.ModEntities;
import com.shiver.roachdelight.common.ModItems;
import com.shiver.roachdelight.common.ModRecipes;
import com.shiver.roachdelight.common.CommonProxy;
import com.shiver.roachdelight.common.RoachEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = RoachDelight.MOD_ID,
        name = RoachDelight.MOD_NAME,
        version = Tags.VERSION,
        acceptedMinecraftVersions = "[1.12.2]",
        dependencies = "required-after:alexsmobs;required-after:farmersdelight"
)
public class RoachDelight {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String MOD_NAME = Tags.MOD_NAME;
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    @Mod.Instance(MOD_ID)
    public static RoachDelight INSTANCE;

    @SidedProxy(
            clientSide = "com.shiver.roachdelight.client.ClientProxy",
            serverSide = "com.shiver.roachdelight.common.CommonProxy"
    )
    public static CommonProxy PROXY;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModEntities.registerAll();
        PROXY.preInit();
        MinecraftForge.EVENT_BUS.register(new RoachEvents());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.registerAll();
        LOGGER.info("{} loaded {} items.", MOD_NAME, ModItems.ITEMS.size());
    }
}
