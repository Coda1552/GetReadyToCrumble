package coda.getreadytocrumble;

import coda.getreadytocrumble.registry.GRTCItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GetReadyToCrumble.MOD_ID)
public class GetReadyToCrumble {
    public static final String MOD_ID = "getreadytocrumble";

    public GetReadyToCrumble() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        GRTCItems.ITEMS.register(bus);
    }
}
