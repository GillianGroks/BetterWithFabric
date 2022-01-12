package com.github.gilliangroks.betterwithfabric.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.github.gilliangroks.betterwithfabric.BetterWithFabric.LOGGER;
import static com.github.gilliangroks.betterwithfabric.BetterWithFabric.MOD_ID;

public class ItemRegistry {

    // Define Item Group
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "items"),
            () -> new ItemStack(ItemRegistry.STEEL_INGOT));


    // Define new items
    public static final Item HEMP = registerItem("hemp",
            new Item(new FabricItemSettings().group(ITEM_GROUP)));

    public static final Item HEMP_SEEDS = registerItem("hemp_seeds",
            new AliasedBlockItem(BlockRegistry.HEMP_CROP, new FabricItemSettings().group(ITEM_GROUP)));


    public static final Item STEEL_INGOT = registerItem("steel_ingot",
            new Item(new FabricItemSettings().group(ITEM_GROUP)));

    public static final Item STEEL_NUGGET = registerItem("steel_nugget",
            new Item(new FabricItemSettings().group(ITEM_GROUP)));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MOD_ID, name), item);
    }

    public static void initialize() {
        LOGGER.info("Registering Items for " + MOD_ID);
    }

}
