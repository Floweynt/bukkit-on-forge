package com.floweytf.forgebukkit.potion;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import net.minecraft.potion.EffectInstance;
import org.bukkit.potion.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ForgeBukkitPotionBrewer implements PotionBrewer {
    private static final Map<PotionType, Collection<PotionEffect>> cache = Maps.newHashMap();

    @Override
    public Collection<PotionEffect> getEffects(PotionType damage, boolean upgraded, boolean extended) {
        if (cache.containsKey(damage))
            return cache.get(damage);

        List<EffectInstance> mcEffects = net.minecraft.potion.Potion.getPotionTypeForName(ForgeBukkitPotionUtil.fromBukkit(new PotionData(damage, extended, upgraded))).getEffects();

        ImmutableList.Builder<PotionEffect> builder = new ImmutableList.Builder<PotionEffect>();
        for (EffectInstance effect : mcEffects) {
            builder.add(ForgeBukkitPotionUtil.toBukkit(effect));
        }

        cache.put(damage, builder.build());

        return cache.get(damage);
    }

    @Override
    public Collection<PotionEffect> getEffectsFromDamage(int damage) {
        return new ArrayList<PotionEffect>();
    }

    @Override
    public PotionEffect createEffect(PotionEffectType potion, int duration, int amplifier) {
        return new PotionEffect(potion, potion.isInstant() ? 1 : (int) (duration * potion.getDurationModifier()), amplifier);
    }
}
