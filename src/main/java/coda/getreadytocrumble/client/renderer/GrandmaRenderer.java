package coda.getreadytocrumble.client.renderer;

import coda.getreadytocrumble.client.model.GingerbreadManModel;
import coda.getreadytocrumble.client.model.GrandmaModel;
import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import coda.getreadytocrumble.common.entities.GrandmaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GrandmaRenderer extends GeoEntityRenderer<GrandmaEntity> {
    public GrandmaEntity entity;
    public MultiBufferSource multiBufferSource;

    public GrandmaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GrandmaModel());
        this.shadowRadius = 1.0F;
    }

    @Override
    public void renderEarly(GrandmaEntity entity, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        this.entity = entity;
        this.multiBufferSource = renderTypeBuffer;
        super.renderEarly(entity, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }
}
