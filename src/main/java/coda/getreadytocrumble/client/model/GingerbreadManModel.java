package coda.getreadytocrumble.client.model;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class GingerbreadManModel extends AnimatedTickingGeoModel<GingerbreadManEntity> {

    @Override
    public ResourceLocation getModelLocation(GingerbreadManEntity object) {
        return new ResourceLocation(GetReadyToCrumble.MOD_ID, "geo/entity/gingerbread_man.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GingerbreadManEntity object) {
        return new ResourceLocation(GetReadyToCrumble.MOD_ID, "textures/entity/gingerbread_man/gingerbread_man.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GingerbreadManEntity animatable) {
        return new ResourceLocation(GetReadyToCrumble.MOD_ID, "animations/entity/gingerbread_man.animation.json");
    }

    @Override
    public void setLivingAnimations(GingerbreadManEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
    }
}
