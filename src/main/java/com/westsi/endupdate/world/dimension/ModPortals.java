package com.westsi.endupdate.world.dimension;



import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;

public class ModPortals {
    public static void registerPortals() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.END_STONE)
                .lightWithItem(Items.ARROW)
                .destDimID(ModDimensions.FINALITYDIM_KEY.getValue())
                .tintColor(0, 0, 0)
                .registerPortal();
    }
}
