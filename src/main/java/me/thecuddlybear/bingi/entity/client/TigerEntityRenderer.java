package me.thecuddlybear.bingi.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import me.thecuddlybear.bingi.Bingi;
import me.thecuddlybear.bingi.entity.custom.TigerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TigerEntityRenderer extends GeoEntityRenderer<TigerEntity> {
    public TigerEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TigerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(TigerEntity animatable) {
        return new ResourceLocation(Bingi.MODID, "textures/entity/tiger.png");
    }

    @Override
    public void render(TigerEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
