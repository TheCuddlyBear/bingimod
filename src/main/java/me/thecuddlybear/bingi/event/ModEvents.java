package me.thecuddlybear.bingi.event;

import me.thecuddlybear.bingi.Bingi;
import me.thecuddlybear.bingi.entity.ModEntities;
import me.thecuddlybear.bingi.entity.custom.RuizEntity;
import me.thecuddlybear.bingi.entity.custom.TigerEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Bingi.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){
        event.put(ModEntities.TIGER.get(), TigerEntity.setAttributes());
        event.put(ModEntities.RUIZ.get(), RuizEntity.setAttributes());
    }

}
