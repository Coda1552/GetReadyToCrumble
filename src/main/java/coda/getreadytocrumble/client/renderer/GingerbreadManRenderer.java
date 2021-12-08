package coda.getreadytocrumble.client.renderer;

import coda.getreadytocrumble.client.model.GingerbreadManModel;
import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
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
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

public class GingerbreadManRenderer extends GeoEntityRenderer<GingerbreadManEntity> {
    public GingerbreadManEntity entity;
    public MultiBufferSource multiBufferSource;

    public GingerbreadManRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GingerbreadManModel());
        this.shadowRadius = 0.5F;
    }

    @Override
    public void renderEarly(GingerbreadManEntity entity, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        this.entity = entity;
        this.multiBufferSource = renderTypeBuffer;
        super.renderEarly(entity, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("item")) {
            stack.pushPose();
            stack.translate(0.35D, 0.15D, -0.1D);
            stack.mulPose(Vector3f.XP.rotationDegrees(-70.0F));
            stack.scale(0.8f, 0.8f, 0.8f);
            ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.MAINHAND);
            Minecraft.getInstance().getItemRenderer().renderStatic(itemstack, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 0);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
