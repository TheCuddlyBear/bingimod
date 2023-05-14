package me.thecuddlybear.bingi.entity;

import me.thecuddlybear.bingi.Bingi;
import me.thecuddlybear.bingi.entity.custom.RuizEntity;
import me.thecuddlybear.bingi.entity.custom.TigerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Bingi.MODID);

    public static final RegistryObject<EntityType<TigerEntity>> TIGER = ENTITY_TYPES.register("tiger", () -> EntityType.Builder.of(TigerEntity::new, MobCategory.CREATURE).sized(1.5f, 1.75f).build(new ResourceLocation(Bingi.MODID, "tiger").toString()));
    public static final RegistryObject<EntityType<RuizEntity>> RUIZ = ENTITY_TYPES.register("ruiz", () -> EntityType.Builder.of(RuizEntity::new, MobCategory.CREATURE).sized(1.5f, 1.75f).build(new ResourceLocation(Bingi.MODID, "ruiz").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
