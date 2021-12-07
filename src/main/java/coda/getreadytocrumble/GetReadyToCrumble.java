package coda.getreadytocrumble;

import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import coda.getreadytocrumble.registry.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(GetReadyToCrumble.MOD_ID)
public class GetReadyToCrumble {
    public static final String MOD_ID = "getreadytocrumble";

    public GetReadyToCrumble() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        GRTCItems.ITEMS.register(bus);
        GRTCBlocks.BLOCKS.register(bus);
        GRTCMenus.MENUS.register(bus);
        GRTCBlockEntities.BLOCK_ENTITIES.register(bus);
        GRTCRecipes.RECIPES.register(bus);
        GRTCEntities.ENTITIES.register(bus);

        GeckoLib.initialize();
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
    }
}
