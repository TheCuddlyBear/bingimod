package me.thecuddlybear.bingi.item;

import me.thecuddlybear.bingi.Bingi;
import me.thecuddlybear.bingi.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Bingi.MODID);

    // Ores
    public static final RegistryObject<Item> BLACK_OPAL = ITEMS.register("black_opal",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_BLACK_OPAL = ITEMS.register("raw_black_opal",
            () -> new Item(new Item.Properties()));
    // Spawn eggs
    public static final RegistryObject<Item> TIGER_SPAWN_EGG = ITEMS.register("tiger_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.TIGER, 0xD57E36, 0x1D0D00, new Item.Properties()));
    public static final RegistryObject<Item> RUIZ_SPAWN_EGG = ITEMS.register("ruiz_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.RUIZ, 0xD57E36, 0x1D0D00, new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
