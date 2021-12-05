package coda.getreadytocrumble.registry;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import coda.getreadytocrumble.common.items.GingerBreadManItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GRTCEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, GetReadyToCrumble.MOD_ID);

    public static final RegistryObject<EntityType<GingerbreadManEntity>> GINGERBREAD_MAN = create("gingerbread_man", EntityType.Builder.of(GingerbreadManEntity::new, MobCategory.CREATURE).sized(1.0f, 1.5f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(GetReadyToCrumble.MOD_ID + "." + name));
    }
}
