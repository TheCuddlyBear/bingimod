package me.thecuddlybear.bingi.sound;

import me.thecuddlybear.bingi.Bingi;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Bingi.MODID);

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }


}
