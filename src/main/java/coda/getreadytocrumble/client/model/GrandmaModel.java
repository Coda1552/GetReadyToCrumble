package coda.getreadytocrumble.client.model;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.common.entities.GrandmaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class GrandmaModel extends AnimatedTickingGeoModel<GrandmaEntity> {

    @Override
    public ResourceLocation getModelLocation(GrandmaEntity object) {
        return new ResourceLocation(GetReadyToCrumble.MOD_ID, "geo/entity/grandma.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GrandmaEntity object) {
        return new ResourceLocation(GetReadyToCrumble.MOD_ID, "textures/entity/grandma/grandma.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GrandmaEntity animatable) {
        return null;
    }

    @Override
    public void setLivingAnimations(GrandmaEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 350F));
    }
}
