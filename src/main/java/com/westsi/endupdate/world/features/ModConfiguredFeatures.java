package com.westsi.endupdate.world.features;

import com.westsi.endupdate.Endupdate;
import com.westsi.endupdate.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.HeightmapDecoratorConfig;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEAD_TREE_KEY = registerKey("dead_spawn");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BELLADONNA_KEY = registerKey("belladonna");

    //tree
    public static final ConfiguredFeature<TreeFeatureConfig, ?> DEAD_TREE = register("tree_dead",
            Feature.TREE.configure(new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(ModBlocks.DEAD_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
                    new SimpleBlockStateProvider(Blocks.GRASS_BLOCK.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 2),
                    new TwoLayersFeatureSize(1, 0, 1)).build()));

    //flower
    public static final ConfiguredFeature<?, ?> BELLADONNA_CONFIG = register(Feature.FLOWER.configure(
                    new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.BELLADONNA.getDefaultState()),
                            SimpleBlockPlacer.INSTANCE).tries(64).build())
            .decorate(Decorator.SPREAD_32_ABOVE.configure(NopeDecoratorConfig.INSTANCE)
                    .decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING))
                            .spreadHorizontally().repeat(4))), BELLADONNA_KEY);


    public static final ConfiguredFeature<?, ?> DEAD_TREE_SPAWN = register(DEAD_TREE
            .decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING))
                    .spreadHorizontally().applyChance(10)), DEAD_TREE_KEY);


    private static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(Endupdate.MOD_ID, name));
    }

    private static ConfiguredFeature<TreeFeatureConfig, ?> register(String name,
                                                                    ConfiguredFeature<TreeFeatureConfig, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Endupdate.MOD_ID, name),
                configuredFeature);
    }

    private static ConfiguredFeature<?, ?> register(ConfiguredFeature<?, ?> configuredFeature,
                                                    RegistryKey<ConfiguredFeature<?, ?>> key) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key.getValue(), configuredFeature);
    }

    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + Endupdate.MOD_ID);
    }
}
