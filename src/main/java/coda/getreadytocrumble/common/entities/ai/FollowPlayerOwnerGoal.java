package coda.getreadytocrumble.common.entities.ai;

import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

public class FollowPlayerOwnerGoal extends Goal {
    public GingerbreadManEntity entity;

    public FollowPlayerOwnerGoal(GingerbreadManEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return this.entity.getOwner() != null && !this.entity.getOwner().isSpectator() && !this.entity.getSitting();
    }


    @Override
    public void start() {
        super.start();
        this.entity.getNavigation().moveTo(this.entity.getOwner(), 1.0f);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.entity.distanceToSqr(this.entity.getOwner()) > 10.0f) {
            if (this.entity.distanceToSqr(this.entity.getOwner()) < 55.0f) {
                this.entity.getNavigation().moveTo(this.entity.getOwner(), 1.0f);
                this.entity.getLookControl().setLookAt(this.entity.getOwner());
            } else {
                this.teleportToOwner();
            }
        }
        else{
            this.entity.getNavigation().stop();
        }
    }

    public void stop() {
        super.stop();
        this.entity.getNavigation().stop();
    }

    private void teleportToOwner() {
        BlockPos blockpos = this.entity.getOwner().blockPosition();

        for(int i = 0; i < 10; ++i) {
            int j = this.randomIntInclusive(-3, 3);
            int k = this.randomIntInclusive(-1, 1);
            int l = this.randomIntInclusive(-3, 3);
            boolean flag = this.maybeTeleportTo(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l);
            if (flag) {
                return;
            }
        }

    }

    private boolean maybeTeleportTo(int p_25304_, int p_25305_, int p_25306_) {
        if (Math.abs((double)p_25304_ - this.entity.getOwner().getX()) < 2.0D && Math.abs((double)p_25306_ - this.entity.getOwner().getZ()) < 2.0D) {
            return false;
        } else if (!this.canTeleportTo(new BlockPos(p_25304_, p_25305_, p_25306_))) {
            return false;
        } else {
            this.entity.moveTo((double)p_25304_ + 0.5D, (double)p_25305_, (double)p_25306_ + 0.5D, this.entity.getYRot(), this.entity.getXRot());
            this.entity.getNavigation().stop();
            return true;
        }
    }

    private boolean canTeleportTo(BlockPos p_25308_) {
        BlockPathTypes blockpathtypes = WalkNodeEvaluator.getBlockPathTypeStatic(this.entity.level, p_25308_.mutable());
        if (blockpathtypes != BlockPathTypes.WALKABLE) {
            return false;
        } else {
            BlockState blockstate = this.entity.level.getBlockState(p_25308_.below());
            if (blockstate.getBlock() instanceof LeavesBlock) {
                return false;
            } else {
                BlockPos blockpos = p_25308_.subtract(this.entity.blockPosition());
                return this.entity.level.noCollision(this.entity, this.entity.getBoundingBox().move(blockpos));
            }
        }
    }


    private int randomIntInclusive(int p_25301_, int p_25302_) {
        return this.entity.getRandom().nextInt(p_25302_ - p_25301_ + 1) + p_25301_;
    }
}
