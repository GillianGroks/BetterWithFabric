package com.github.gilliangroks.betterwithfabric.registry;



import com.github.gilliangroks.betterwithfabric.block.HempBlock;
import com.github.gilliangroks.betterwithfabric.block.SteelDoorBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.github.gilliangroks.betterwithfabric.BetterWithFabric.LOGGER;
import static com.github.gilliangroks.betterwithfabric.BetterWithFabric.MOD_ID;
import static com.github.gilliangroks.betterwithfabric.registry.ItemRegistry.ITEM_GROUP;

public class BlockRegistry {



    // Define new Blocks
    public static final Block STEEL_BLOCK = registerBlock("steel_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()));

    public static final Block STEEL_DOOR = registerBlock("steel_door",
            new SteelDoorBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()));

    public static final Block HEMP_CROP = registerBlockWithoutBlockItem("hemp_crop",
            new HempBlock(FabricBlockSettings.of(Material.PLANT).nonOpaque().noCollision().breakInstantly().ticksRandomly().sounds(BlockSoundGroup.CROP)));

    // Helper methods
    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(ITEM_GROUP)));
    }

    public static void initialize() {
        LOGGER.info("Registering Blocks for " + MOD_ID);
    }
}
