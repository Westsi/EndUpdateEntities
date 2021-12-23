package com.westsi.endupdate.client.renderer;

import com.westsi.endupdate.entity.mob.SuperiorityEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;

public class SuperiorityEntityRenderer extends BipedEntityRenderer<SuperiorityEntity, SkeletonEntityModel<SuperiorityEntity>> {

    public SuperiorityEntityRenderer(EntityRenderDispatcher entityRenderdispatcher) {
        super(entityRenderdispatcher, new SkeletonEntityModel<>(), 0.5f);
    }

    @Override
    public Identifier getTexture(SuperiorityEntity entity) {
        return new Identifier("endupdate", "textures/entity/skeleton/skelly.png");
    }

}
