package me.thecuddlybear.bingi.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import me.thecuddlybear.bingi.Bingi;
import me.thecuddlybear.bingi.entity.custom.RuizEntity;
import me.thecuddlybear.bingi.entity.custom.TigerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RuizEntityRenderer extends GeoEntityRenderer<RuizEntity> {
    public RuizEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RuizModel());
    }

    @Override
    public ResourceLocation getTextureLocation(RuizEntity animatable) {
        return new ResourceLocation(Bingi.MODID, "textures/entity/ruiz_mob.png");
    }

    @Override
    public void render(RuizEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }else{
            poseStack.scale(1.5f, 1.5f, 1.5f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}
