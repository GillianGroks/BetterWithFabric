package com.github.gilliangroks.betterwithfabric;

import com.github.gilliangroks.betterwithfabric.registry.BlockRegistry;
import com.github.gilliangroks.betterwithfabric.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterWithFabric implements ModInitializer {
	public static final String MOD_ID = "betterwithfabric";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing " + MOD_ID);
		BlockRegistry.initialize();
		ItemRegistry.initialize();
	}
}
