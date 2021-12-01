package coda.getreadytocrumble.common.blocks;

import coda.getreadytocrumble.common.blockentities.OvenBlockEntity;
import coda.getreadytocrumble.registry.GRTCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class OvenBlock extends AbstractFurnaceBlock {

    public OvenBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return GRTCBlockEntities.OVEN_BLOCK_ENTITY.get().create(p_153215_, p_153216_);
    }

    @Override
    protected void openContainer(Level world, BlockPos pos, Player player) {
        if (!world.isClientSide) {
            BlockEntity te = world.getBlockEntity(pos);
            if (te instanceof OvenBlockEntity) {
                NetworkHooks.openGui((ServerPlayer) player, (OvenBlockEntity) te, pos);
                player.awardStat(Stats.INTERACT_WITH_SMOKER);
            }
        }
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_154640_, BlockState p_154641_, BlockEntityType<T> p_154642_) {
        return createFurnaceTicker(p_154640_, p_154642_, GRTCBlockEntities.OVEN_BLOCK_ENTITY.get());
    }
}
