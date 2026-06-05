package com.shiver.roachdelight.common;

import com.github.alexthe666.alexsmobs.entity.EntityCockroach;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RoachEvents {
    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof EntityCockroach)) {
            return;
        }

        EntityPlayer player = event.getEntityPlayer();
        ItemStack held = player.getHeldItem(event.getHand());
        if (held.isEmpty() || held.getItem() != Items.GLASS_BOTTLE || player.world.isRemote) {
            return;
        }

        if (!player.capabilities.isCreativeMode) {
            held.shrink(1);
        }

        ItemStack result = new ItemStack(ModItems.BOTTLED_ROACH);
        if (!player.inventory.addItemStackToInventory(result)) {
            player.world.spawnEntity(new EntityItem(player.world, player.posX, player.posY + 0.5D, player.posZ, result));
        }

        event.getTarget().setDead();
        event.setCanceled(true);
    }
}
