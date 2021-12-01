package coda.getreadytocrumble.client;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.client.screen.OvenScreen;
import coda.getreadytocrumble.registry.GRTCMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = GetReadyToCrumble.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(GRTCMenus.OVEN_MENU.get(), OvenScreen::new);
    }
}