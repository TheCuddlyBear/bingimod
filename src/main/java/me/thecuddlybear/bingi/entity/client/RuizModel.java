package me.thecuddlybear.bingi.entity.client;

import me.thecuddlybear.bingi.Bingi;
import me.thecuddlybear.bingi.entity.custom.RuizEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class RuizModel extends GeoModel<RuizEntity> {
    @Override
    public ResourceLocation getModelResource(RuizEntity animatable) {
        return new ResourceLocation(Bingi.MODID, "geo/ruiz.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RuizEntity animatable) {
        return new ResourceLocation(Bingi.MODID, "textures/entity/ruiz_mob.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RuizEntity animatable) {
        return new ResourceLocation(Bingi.MODID, "geo/ruiz.animation.json");
    }

    @Override
    public void setCustomAnimations(RuizEntity animatable, long instanceId, AnimationState<RuizEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if(head != null ){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }

    }
}
