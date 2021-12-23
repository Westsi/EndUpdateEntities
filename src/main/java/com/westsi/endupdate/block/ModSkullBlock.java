package com.westsi.endupdate.block;


import net.minecraft.block.SkullBlock;

import static net.minecraft.block.SkullBlock.Type.WITHER_SKELETON;

public class ModSkullBlock extends SkullBlock {
    public ModSkullBlock(Settings settings) {
        super(WITHER_SKELETON, settings);
    }
}