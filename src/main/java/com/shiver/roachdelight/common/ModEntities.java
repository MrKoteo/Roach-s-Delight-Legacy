package com.shiver.roachdelight.common;

import com.shiver.roachdelight.RoachDelight;
import com.shiver.roachdelight.common.entity.EntityRoachBottle;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class ModEntities {
    private ModEntities() {
    }

    public static void registerAll() {
        EntityRegistry.registerModEntity(new ResourceLocation(RoachDelight.MOD_ID, "roach_bottle"),
                EntityRoachBottle.class, RoachDelight.MOD_ID + ".roach_bottle", 0, RoachDelight.INSTANCE,
                64, 10, true);
    }
}
