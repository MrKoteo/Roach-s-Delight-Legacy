package com.shiver.roachdelight.common;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import net.minecraft.entity.EntityLivingBase;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class RoachData {
    private static final Random RANDOM = new Random();
    private static final List<Potion> COCKROACH_CLUSTER = Arrays.asList(
            MobEffects.STRENGTH,
            MobEffects.NAUSEA,
            MobEffects.WEAKNESS,
            MobEffects.SPEED,
            MobEffects.SLOWNESS
    );
    private static final List<List<Potion>> COCKROACH_BALL = Arrays.asList(
            Arrays.asList(MobEffects.STRENGTH, MobEffects.WEAKNESS),
            Arrays.asList(MobEffects.SPEED, MobEffects.SLOWNESS),
            Arrays.asList(MobEffects.REGENERATION, MobEffects.POISON)
    );

    private RoachData() {
    }

    public static Potion randomClusterEffect() {
        return COCKROACH_CLUSTER.get(RANDOM.nextInt(COCKROACH_CLUSTER.size()));
    }

    public static void addBallEffects(EntityLivingBase entity) {
        for (List<Potion> pair : COCKROACH_BALL) {
            entity.addPotionEffect(new PotionEffect(pair.get(RANDOM.nextInt(pair.size())), 20 * 30));
        }
    }

    public static void removeEffectIf(EntityLivingBase entity, int level, Potion potion) {
        PotionEffect effect = entity.getActivePotionEffect(potion);
        if (effect != null && effect.getAmplifier() < level) {
            entity.removePotionEffect(potion);
        }
    }
}
