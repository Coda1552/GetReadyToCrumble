package coda.getreadytocrumble.client.model;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class GingerbreadManModel extends AnimatedTickingGeoModel<GingerbreadManEntity> {

    @Override
    public ResourceLocation getModelLocation(GingerbreadManEntity object) {
        return new ResourceLocation(GetReadyToCrumble.MOD_ID, "geo/gingerbreadmen/gbm_1.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GingerbreadManEntity object) {
        if(object.getEntityClass() == 1) {
            return new ResourceLocation(GetReadyToCrumble.MOD_ID, "textures/entity/gingerbreadmen/gbm_1_knight.png");
        }
        if(object.getEntityClass() == 2){
            return new ResourceLocation(GetReadyToCrumble.MOD_ID, "textures/entity/gingerbreadmen/gbm_1_mage.png");
        }
        return new ResourceLocation(GetReadyToCrumble.MOD_ID, "textures/entity/gingerbreadmen/gbm_1.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GingerbreadManEntity animatable) {
        return new ResourceLocation(GetReadyToCrumble.MOD_ID, "animations/gingerbreadmen/gbm_1.animations.json");
    }

    @Override
    public void setLivingAnimations(GingerbreadManEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 350F));
    }
}
