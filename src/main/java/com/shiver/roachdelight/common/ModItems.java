package com.shiver.roachdelight.common;

import com.shiver.roachdelight.RoachDelight;
import com.shiver.roachdelight.common.item.ItemKangfuXinYe;
import com.shiver.roachdelight.common.item.ItemRoachBottle;
import com.shiver.roachdelight.common.item.ItemRoachFood;
import com.shiver.roachdelight.common.item.ItemSplashRoachBottle;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.LinkedHashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = RoachDelight.MOD_ID)
public final class ModItems {
    public static final Map<String, Item> ITEMS = new LinkedHashMap<>();

    public static final Item BOTTLED_ROACH = register("bottled_roach", new ItemRoachBottle());
    public static final Item GOKIBURI_YAKI = register("gokiburi_yaki", ItemRoachFood.gokiburiYaki());
    public static final Item JUICY_ROAST_ROACH = register("juicy_roast_roach", ItemRoachFood.juicyRoastRoach());
    public static final Item PROTEIN_BLOCK = register("protein_block", ItemRoachFood.simple(8));
    public static final Item ROACH_BURGER = register("roach_burger", ItemRoachFood.roachBurger());
    public static final Item ROACH_MEATBALL = register("roach_meatball", ItemRoachFood.roachBall());
    public static final Item ROACH_PATTY = register("roach_patty", ItemRoachFood.roachBall());
    public static final Item ROACH_ROLL = register("roach_roll", ItemRoachFood.roachRoll());
    public static final Item ROACH_SANDWICH = register("roach_sandwich", ItemRoachFood.roachRoll());
    public static final Item ROACH_SALAD_PLATTER = register("roach_salad_platter", ItemRoachFood.roachSaladPlatter());
    public static final Item ROACH_SKEWER = register("roach_skewer", ItemRoachFood.simple(11));
    public static final Item ROACH_WHISKER_CANDY = register("roach_whisker_candy", ItemRoachFood.candy());
    public static final Item SPLASH_BOTTLED_ROACH = register("splash_bottled_roach", new ItemSplashRoachBottle());
    public static final Item KANGFU_XIN_YE = register("kangfu_xin_ye", new ItemKangfuXinYe());

    private ModItems() {
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        for (Item item : ITEMS.values()) {
            event.getRegistry().register(item);
        }
    }

    private static Item register(String path, Item item) {
        item.setRegistryName(new ResourceLocation(RoachDelight.MOD_ID, path));
        item.setTranslationKey(RoachDelight.MOD_ID + "." + path);
        item.setCreativeTab(ModCreativeTab.TAB);
        ITEMS.put(path, item);
        return item;
    }
}
