package coda.getreadytocrumble.registry;

import coda.getreadytocrumble.GetReadyToCrumble;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GetReadyToCrumble.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GRTCEntityAttributes {

    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(GRTCEntities.GINGERBREAD_MAN.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,15.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.3d)
                .add(Attributes.KNOCKBACK_RESISTANCE,0.1d)
                .add(Attributes.ARMOR, 0.35d)
                .add(Attributes.ATTACK_DAMAGE, 1.2d)
                .add(Attributes.ATTACK_DAMAGE, 1.2d)
                .add(Attributes.FOLLOW_RANGE, 28.0d)
                .build());
    }
}