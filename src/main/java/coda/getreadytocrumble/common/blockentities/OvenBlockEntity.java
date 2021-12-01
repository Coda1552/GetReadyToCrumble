package coda.getreadytocrumble.common.blockentities;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.common.menu.OvenMenu;
import coda.getreadytocrumble.registry.GRTCBlockEntities;
import coda.getreadytocrumble.registry.GRTCRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OvenBlockEntity extends AbstractFurnaceBlockEntity {

    public OvenBlockEntity(BlockPos pos, BlockState state) {
        super(GRTCBlockEntities.OVEN_BLOCK_ENTITY.get(), pos, state, GRTCRecipes.BAKING_TYPE);
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + GetReadyToCrumble.MOD_ID + ".oven");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new OvenMenu(id, player, this, this.dataAccess);
    }
}
