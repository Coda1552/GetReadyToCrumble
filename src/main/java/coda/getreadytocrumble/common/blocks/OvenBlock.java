package coda.getreadytocrumble.common.blocks;

import coda.getreadytocrumble.common.blockentities.OvenBlockEntity;
import coda.getreadytocrumble.registry.GRTCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
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


    // attempt at the beeping, but it does not work
/*    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {

        if (world.getBlockEntity(pos) instanceof OvenBlockEntity be) {
            int cookingProgress = be.dataAccess.get(2);
            int litTime = be.dataAccess.get(0);

            // check if its lit
            if (litTime > 0) {
                world.playLocalSound(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, SoundEvents.GHAST_SCREAM, SoundSource.BLOCKS, 1.0F, 1.0F, true);

            }

            System.out.println(be.dataAccess.get(0));
        }

        super.animateTick(state, world, pos, rand);
    }*/

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_154640_, BlockState p_154641_, BlockEntityType<T> p_154642_) {
        return createTickerHelper(p_154642_, GRTCBlockEntities.OVEN_BLOCK_ENTITY.get(), (level, pos, state, be) -> AbstractFurnaceBlockEntity.serverTick(level, pos, level.getBlockState(pos), be));
    }

}
