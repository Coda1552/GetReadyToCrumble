package coda.getreadytocrumble.client.renderer;

import coda.getreadytocrumble.client.model.GingerbreadManModel;
import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GingerbreadManRenderer extends GeoEntityRenderer<GingerbreadManEntity> {

    public GingerbreadManRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GingerbreadManModel());
        this.shadowRadius = 0.5F;
    }
}