package com.westsi.endupdate;

import com.westsi.endupdate.block.ModBlocks;
import com.westsi.endupdate.block.Reciever;
import com.westsi.endupdate.enchants.EndSlayer;
//import com.westsi.endupdate.entity.mob.EndSlimeEntity;
//import com.westsi.endupdate.entity.mob.SuperiorityEntity;
import com.westsi.endupdate.entity.mob.SuperiorityEntity;
import com.westsi.endupdate.fluids.BloodFluid;
import com.westsi.endupdate.item.ModItems;
import com.westsi.endupdate.util.ModLootTableModifiers;
import com.westsi.endupdate.world.dimension.ModDimensions;
import com.westsi.endupdate.world.dimension.ModPortals;
import com.westsi.endupdate.world.features.ModConfiguredFeatures;
import com.westsi.endupdate.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.heightprovider.TrapezoidHeightProvider;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;;

import static com.westsi.endupdate.block.ModBlocks.DEAD_SAPLING;


public class Endupdate implements ModInitializer {

    public static final String MOD_ID = "endupdate";

    //ore gen
    private static ConfiguredFeature<?, ?> ORE_ENDERITE_END = Feature.ORE
            .configure(new OreFeatureConfig(
                    new BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.ENDERITE_ORE.getDefaultState(),
                    3)) // Vein size
            .range(new RangeDecoratorConfig(
                    // You can also use one of the other height providers if you don't want a uniform distribution
                    TrapezoidHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64), 30))) // Inclusive min and max height
            .spreadHorizontally()
            .repeat(2); // Number of veins per chunk

    //enchant
    public static Enchantment ENDSLAYER = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("endupdate", "end_slayer"),
            new EndSlayer()
    );

    //surface builder for new dimension
    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> BASALT_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
            .withConfig(new TernarySurfaceConfig(
                    Blocks.BASALT.getDefaultState(),
                    Blocks.BASALT.getDefaultState(),
                    Blocks.CRYING_OBSIDIAN.getDefaultState()));

    //biome creation for new dim biome

    private static final Biome BASALTMOUNTAINS = createBasaltMountains();

    private static Biome createBasaltMountains() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        //   custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(BASALT_SURFACE_BUILDER);

        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.EXTREME_HILLS)
                .depth(1.0F)
                .scale(0.5F)
                .temperature(0.8F)
                .downfall(0.4F)
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x3f76e4)
                        .waterFogColor(0x050533)
                        .fogColor(0x0d0d0d)
                        .skyColor(0x000000)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
    public static final RegistryKey<Biome> BASALTMOUNTAINS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("endupdate", "basaltmountains"));

    //surface builder for new end biome
    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> ENDGRASS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
            .withConfig(new TernarySurfaceConfig(
                    ModBlocks.END_GRASS.getDefaultState(),
                    ModBlocks.END_DIRT.getDefaultState(),
                    Blocks.END_STONE.getDefaultState()));

    //biome creation for new end biome
    private static final Biome ENDPLAINS = createEndPlains();

    private static Biome createEndPlains() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        //   custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addEndMobs(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(ENDGRASS_SURFACE_BUILDER);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_ENDERITE_END);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);

        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.THEEND)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(0.8F)
                .downfall(0.4F)
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x3f76e4)
                        .waterFogColor(0x050533)
                        .fogColor(0xc0d8ff)
                        .skyColor(0x77adff)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
    public static final RegistryKey<Biome> ENDPLAINS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("endupdate", "endplains"));




    //reciever and sender
    //reciever
    public static BlockEntityType<Reciever> RECIEVER;


    //fluids
    public static FlowableFluid STILL_BLOOD;
    public static FlowableFluid FLOWING_BLOOD;
    public static Item BLOOD_BUCKET;
    public static Block BLOOD;



    //custom entity
    public static final EntityType<SuperiorityEntity> SUPERIORITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("endupdate", "superiority"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SuperiorityEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.99f)).build()
    );
    //public static final EntityType<EndSlimeEntity> ENDSLIME = Registry.register(
   //         Registry.ENTITY_TYPE,
    //        new Identifier("endupdate", "endslime"),
    //        FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EndSlimeEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
   // );





    @Override
    public void onInitialize() {




        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModLootTableModifiers.modifyLootTables();
        ModConfiguredFeatures.registerConfiguredFeatures();

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.END_GLASS , RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BELLADONNA , RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(DEAD_SAPLING , RenderLayer.getTranslucent());


        RegistryKey<ConfiguredFeature<?, ?>> oreEnderiteEnd = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("endupdate", "ore_enderite_end"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreEnderiteEnd.getValue(), ORE_ENDERITE_END);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, oreEnderiteEnd);

        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier("endupdate", "endgrass"), ENDGRASS_SURFACE_BUILDER);
        Registry.register(BuiltinRegistries.BIOME, ENDPLAINS_KEY.getValue(), ENDPLAINS);
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier("endupdate", "basalt"), BASALT_SURFACE_BUILDER);
        Registry.register(BuiltinRegistries.BIOME, BASALTMOUNTAINS_KEY.getValue(), BASALTMOUNTAINS);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),GenerationStep.Feature.VEGETAL_DECORATION, ModConfiguredFeatures.DEAD_TREE_KEY);

        //biome adding to zones
        TheEndBiomes.addHighlandsBiome(ENDPLAINS_KEY, 2D);
        TheEndBiomes.addMidlandsBiome(ENDPLAINS_KEY, ENDPLAINS_KEY,2D);
        TheEndBiomes.addSmallIslandsBiome(ENDPLAINS_KEY,2D);
        TheEndBiomes.addBarrensBiome(ENDPLAINS_KEY, ENDPLAINS_KEY,2D);




        //reciever
        RECIEVER = Registry.register(Registry.BLOCK_ENTITY_TYPE, "endupdate:reciever", FabricBlockEntityTypeBuilder.create(Reciever::new, ModBlocks.RECIEVER_BLOCK).build(null));

        //fluids
        STILL_BLOOD = Registry.register(Registry.FLUID, new Identifier(MOD_ID, "blood"), new BloodFluid.Still());
        FLOWING_BLOOD = Registry.register(Registry.FLUID, new Identifier(MOD_ID, "flowing_blood"), new BloodFluid.Flowing());
        BLOOD_BUCKET = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "blood_bucket"),
                new BucketItem(STILL_BLOOD, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(ItemGroup.MISC)));

        BLOOD = Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "blood"), new FluidBlock(STILL_BLOOD, FabricBlockSettings.of(Material.WATER).noCollision().strength(100.0F).dropsNothing()){});


        ModWorldGen.generateModWorldGen();
        ModDimensions.register();

        ModPortals.registerPortals();

        StrippableBlockRegistry.register(ModBlocks.DEAD_WOOD, ModBlocks.STRIPPED_DEAD_WOOD);
        StrippableBlockRegistry.register(ModBlocks.DEAD_LOG, ModBlocks.STRIPPED_DEAD_LOG);


        //custom entity
        FabricDefaultAttributeRegistry.register(SUPERIORITY, SuperiorityEntity.createSuperiorityAttributes());
        //FabricDefaultAttributeRegistry.register(ENDSLIME, EndSlimeEntity.createMobAttributes());

    }
}
