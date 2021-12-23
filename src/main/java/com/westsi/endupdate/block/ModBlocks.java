package com.westsi.endupdate.block;

import com.westsi.endupdate.Endupdate;
import com.westsi.endupdate.block.sapling.DeadSaplingBlock;
import com.westsi.endupdate.world.features.tree.DeadSaplingGenerator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    //new end valuables
    public static final Block ENDERITE_ORE = registerBlock("enderite_ore",
            new Block(FabricBlockSettings.of(Material.METAL).strength(30f, 1200).requiresTool()));

    public static final Block ENDERITE_BLOCK = registerBlock("enderite_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(50f, 1200).requiresTool()));

    //end woods
    public static final Block DEAD_LOG = registerBlock("dead_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)));

    public static final Block DEAD_LOG_PLANKS = registerBlock("dead_log_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)));

    public static final Block DEAD_WOOD = registerBlock("dead_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)));

    public static final Block STRIPPED_DEAD_LOG = registerBlock("stripped_dead_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final Block STRIPPED_DEAD_WOOD = registerBlock("stripped_dead_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD)));

    //end stone variants
    public static final Block END_STONE_POLISHED = registerBlock("polished_end_stone",
            new Block(FabricBlockSettings.of(Material.STONE).strength(3f, 9).requiresTool()));

    public static final Block END_STONE_STAIRS = registerBlock("end_stone_stairs",
            new ModStairsBlock(Blocks.END_STONE.getDefaultState(),
                    FabricBlockSettings.of(Material.STONE).strength(3f, 9).requiresTool()));

    public static final Block POLISHED_END_STONE_STAIRS = registerBlock("polished_end_stone_stairs",
            new ModStairsBlock(ModBlocks.END_STONE_POLISHED.getDefaultState(),
                    FabricBlockSettings.of(Material.STONE).strength(3f, 9).requiresTool()));

    public static final Block END_STONE_SLAB = registerBlock("end_stone_slab",
            new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f, 9).requiresTool()));

    public static final Block POLISHED_END_STONE_SLAB = registerBlock("polished_end_stone_slab",
            new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f, 9).requiresTool()));

    public static final Block END_STONE_WALL = registerBlock("end_stone_wall",
            new WallBlock(FabricBlockSettings.of(Material.STONE).strength(3f, 9).requiresTool()));

    //new end nature blocks
    public static final Block END_DIRT = registerBlock("end_dirt",
            new Block(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.GRASS).strength(0.5f, 1)));

    public static final Block END_GRASS = registerBlock("end_grass",
            new Block(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.GRASS).strength(0.5f, 1)));



    //dead tree sapling

    public static final Block DEAD_SAPLING = registerBlock("dead_sapling",
            new DeadSaplingBlock(new DeadSaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING).nonOpaque()));


    //end glass
    public static final Block END_GLASS = registerBlock("end_glass",
            new GlassBlock(FabricBlockSettings.of(Material.GLASS).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(50f, 1200).requiresTool()));

    //reciever
    public static final Block RECIEVER_BLOCK = registerBlock("reciever_block",
            new RecieverBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.METAL).strength(50f, 1200).requiresTool()));

    //flower
    public static final Block BELLADONNA = registerBlock("belladonna",
            new FlowerBlock(StatusEffects.POISON, 200, FabricBlockSettings.copy(Blocks.DANDELION).nonOpaque().noCollision()));

    //skull
    public static final Block ENDERMAN_HEAD = registerBlock("enderman_head",
            new ModSkullBlock(FabricBlockSettings.of(Material.STONE).nonOpaque().strength(1, 1).luminance(7)));


    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(Endupdate.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(Endupdate.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }

    public static void registerModBlocks() {
        System.out.println("Registering ModBlocks for " + Endupdate.MOD_ID);
    }
}
