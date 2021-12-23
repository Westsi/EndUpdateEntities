package com.westsi.endupdate.world.dimension;

import com.westsi.endupdate.Endupdate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {
    private static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(Registry.DIMENSION_KEY,
            new Identifier(Endupdate.MOD_ID, "finalitydim"));
    public static RegistryKey<World> FINALITYDIM_KEY = RegistryKey.of(Registry.WORLD_KEY, DIMENSION_KEY.getValue());
    private static final RegistryKey<DimensionType> DIMENSION_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            new Identifier(Endupdate.MOD_ID, "finalitydim_type"));

    public static void register() {
        System.out.println("Registering ModDimensions for " + Endupdate.MOD_ID);
    }
}
