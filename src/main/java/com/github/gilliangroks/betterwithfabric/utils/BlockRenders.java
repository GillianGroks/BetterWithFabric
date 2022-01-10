package com.github.gilliangroks.betterwithfabric.utils;

import com.github.gilliangroks.betterwithfabric.registry.BlockRegistry;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class BlockRenders {

    public static void defineRenders() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.HEMP_CROP, RenderLayer.getCutout());
    }

}
