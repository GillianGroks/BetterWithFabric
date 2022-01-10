package com.github.gilliangroks.betterwithfabric;

import com.github.gilliangroks.betterwithfabric.utils.BlockRenders;
import net.fabricmc.api.ClientModInitializer;

import static com.github.gilliangroks.betterwithfabric.BetterWithFabric.LOGGER;
import static com.github.gilliangroks.betterwithfabric.BetterWithFabric.MOD_ID;

public class BetterWithFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        BlockRenders.defineRenders();

        LOGGER.info("Initializing Client for " + MOD_ID);
    }
}
