package com.westsi.endupdate.entity.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class EndSlimeEntity extends PathAwareEntity {

    public EndSlimeEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

}
