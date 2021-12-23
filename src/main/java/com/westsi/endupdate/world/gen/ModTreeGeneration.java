package com.westsi.endupdate.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import com.westsi.endupdate.world.features.ModConfiguredFeatures;

public class ModTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),GenerationStep.Feature.VEGETAL_DECORATION, ModConfiguredFeatures.DEAD_TREE_KEY);
    }
}
