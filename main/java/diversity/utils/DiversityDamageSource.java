package diversity.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import diversity.entity.EntityDart;

public class DiversityDamageSource {
    /**
     * returns EntityDamageSourceIndirect of an arrow
     */
    public static DamageSource causeDartDamage(EntityDart p_76353_0_, Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("dart", p_76353_0_, p_76353_1_)).setProjectile();
    }
}
