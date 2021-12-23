package com.westsi.endupdate.world.features.tree;

import com.westsi.endupdate.Endupdate;
import com.westsi.endupdate.world.features.ModConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;
import java.util.Random;

public class DeadSaplingGenerator extends SaplingGenerator {


    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
        return ModConfiguredFeatures.DEAD_TREE;
    }
}
