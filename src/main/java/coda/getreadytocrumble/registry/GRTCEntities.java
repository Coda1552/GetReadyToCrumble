package coda.getreadytocrumble.registry;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GRTCEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, GetReadyToCrumble.MOD_ID);

    public static final RegistryObject<EntityType<GingerbreadManEntity>> GINGERBREAD_MAN = create("gingerbread_man", EntityType.Builder.of(GingerbreadManEntity::new, MobCategory.CREATURE).sized(0.8f, 1.2f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(GetReadyToCrumble.MOD_ID + "." + name));
    }
}
