package com.shiver.roachdelight.common;

import com.shiver.roachdelight.RoachDelight;
import com.wdcftgg.farmersdelightlegacy.common.recipe.CookingPotRecipeManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public final class ModRecipes {
    private static final int COOK_TIME = 200;
    private static final float EXPERIENCE = 1.0F;

    private ModRecipes() {
    }

    public static void registerAll() {
        registerBrewingRecipes();
        registerCookingRecipes();
    }

    private static void registerBrewingRecipes() {
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Items.POTIONITEM, ModItems.BOTTLED_ROACH,
                new ItemStack(ModItems.KANGFU_XIN_YE)));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModItems.BOTTLED_ROACH, Items.GUNPOWDER,
                new ItemStack(ModItems.SPLASH_BOTTLED_ROACH)));
    }

    private static void registerCookingRecipes() {
        cooking("gokiburi_yaki",
                stack(ModItems.GOKIBURI_YAKI),
                item("roach_meatball"), item("roach_meatball"), "farmersdelight:onion");
        cooking("juicy_roast_roach",
                stack(ModItems.JUICY_ROAST_ROACH),
                item("bottled_roach"));
        cooking("roach_burger",
                stack(ModItems.ROACH_BURGER),
                item("roach_patty"), "ore:listAllveggie", "farmersdelight:tomato", "farmersdelight:onion", "minecraft:bread");
        cooking("roach_meatball",
                new ItemStack(ModItems.ROACH_MEATBALL, 2),
                item("roach_patty"), item("roach_patty"));
        cooking("protein_block",
                stack(ModItems.PROTEIN_BLOCK),
                item("roach_patty"), item("roach_patty"), item("roach_patty"), item("roach_patty"), item("roach_patty"), item("roach_patty"));
        cooking("roach_patty",
                new ItemStack(ModItems.ROACH_PATTY, 2),
                item("bottled_roach"), item("bottled_roach"), item("bottled_roach"), "farmersdelight:wheat_dough");
        cooking("roach_sandwich_patty",
                stack(ModItems.ROACH_SANDWICH),
                item("roach_patty"), "ore:listAllveggie", "minecraft:carrot", "minecraft:bread");
        cooking("roach_sandwich",
                stack(ModItems.ROACH_SANDWICH),
                item("bottled_roach"), item("bottled_roach"), item("bottled_roach"), "ore:listAllveggie", "minecraft:carrot", "minecraft:bread");
        cooking("roach_skewer",
                stack(ModItems.ROACH_SKEWER),
                item("bottled_roach"), item("bottled_roach"), item("bottled_roach"), item("bottled_roach"));
        cookingWithContainer("roach_salad_platter",
                stack(ModItems.ROACH_SALAD_PLATTER),
                new ItemStack(Items.BOWL),
                item("roach_patty"), item("roach_meatball"), "farmersdelight:tomato", "farmersdelight:onion", "ore:listAllveggie");
        cooking("roach_whisker_candy",
                new ItemStack(ModItems.ROACH_WHISKER_CANDY, 2),
                item("bottled_roach"), item("bottled_roach"), item("bottled_roach"), "minecraft:sugar");
    }

    private static void cooking(String key, ItemStack result, String... ingredients) {
        CookingPotRecipeManager.registerScriptRecipe(RoachDelight.MOD_ID + ":" + key, ingredients,
                result, ItemStack.EMPTY, COOK_TIME, EXPERIENCE, true);
    }

    private static void cookingWithContainer(String key, ItemStack result, ItemStack container, String... ingredients) {
        CookingPotRecipeManager.registerScriptRecipe(RoachDelight.MOD_ID + ":" + key, ingredients,
                result, container, COOK_TIME, EXPERIENCE, true);
    }

    private static String item(String path) {
        return RoachDelight.MOD_ID + ":" + path;
    }

    private static ItemStack stack(net.minecraft.item.Item item) {
        return new ItemStack(item);
    }

    private static final class ItemBrewingRecipe extends BrewingRecipe {
        private final Item input;
        private final Item ingredient;

        private ItemBrewingRecipe(Item input, Item ingredient, ItemStack output) {
            super(new ItemStack(input), new ItemStack(ingredient), output);
            this.input = input;
            this.ingredient = ingredient;
        }

        @Override
        public boolean isInput(ItemStack stack) {
            return !stack.isEmpty() && stack.getItem() == this.input;
        }

        @Override
        public boolean isIngredient(ItemStack stack) {
            return !stack.isEmpty() && stack.getItem() == this.ingredient;
        }
    }
}
