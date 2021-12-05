package coda.getreadytocrumble.common.events;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import coda.getreadytocrumble.registry.GRTCEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GetReadyToCrumble.MOD_ID)
public class EntityEvents {

    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent event) {
        event.put(GRTCEntities.GINGERBREAD_MAN.get(), GingerbreadManEntity.createAttributes().build());
    }

}
