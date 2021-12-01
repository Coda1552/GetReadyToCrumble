package coda.getreadytocrumble.registry;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.common.blockentities.OvenBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GRTCBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, GetReadyToCrumble.MOD_ID);

    public static final RegistryObject<BlockEntityType<OvenBlockEntity>> OVEN_BLOCK_ENTITY = BLOCK_ENTITIES.register("oven", () -> BlockEntityType.Builder.of(OvenBlockEntity::new, GRTCBlocks.OVEN.get()).build(null));
}

