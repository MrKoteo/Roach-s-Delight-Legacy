package com.shiver.roachdelight.client;

import com.shiver.roachdelight.common.CommonProxy;
import com.shiver.roachdelight.common.ModItems;
import com.shiver.roachdelight.common.entity.EntityRoachBottle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntityRoachBottle.class,
                manager -> new RenderSnowball<>(manager, ModItems.SPLASH_BOTTLED_ROACH, Minecraft.getMinecraft().getRenderItem()));
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        for (Item item : ModItems.ITEMS.values()) {
            ResourceLocation name = item.getRegistryName();
            if (name != null) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name, "inventory"));
            }
        }
    }
}
