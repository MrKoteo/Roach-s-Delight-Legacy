package com.shiver.roachdelight.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemKangfuXinYe extends ItemFood {
    public ItemKangfuXinYe() {
        super(0, 0.0F, false);
        this.setMaxStackSize(1);
        this.setContainerItem(Items.GLASS_BOTTLE);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (worldIn.isRemote) {
            return;
        }

        player.extinguish();
        List<Potion> harmful = new ArrayList<>();
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getPotion().isBadEffect()) {
                harmful.add(effect.getPotion());
            }
        }
        for (Potion potion : harmful) {
            player.removePotionEffect(potion);
        }
        player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 20 * 10));
        player.heal(2.0F);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        ItemStack result = super.onItemUseFinish(stack, worldIn, entityLiving);
        if (entityLiving instanceof EntityPlayer && !((EntityPlayer) entityLiving).capabilities.isCreativeMode) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
            if (!player.inventory.addItemStackToInventory(bottle)) {
                player.dropItem(bottle, false);
            }
        }
        return result;
    }
}
