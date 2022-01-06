package coda.getreadytocrumble.common.entities.ai;

import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class GingerBreadManAttackGoal extends Goal {
    protected GingerbreadManEntity entity;
    private int timer;
    private final int COOLDOWN = 20;
    private int cooldownTimer;

    public GingerBreadManAttackGoal(GingerbreadManEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return this.entity.getTarget() != null && this.entity.getEntityClass() == 1;
    }

    @Override
    public void start() {
        super.start();
        this.timer = 0;
        this.entity.getNavigation().moveTo(this.entity.getTarget(), 1.0f);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.entity.getTarget() !=  null) {
            if (this.cooldownTimer < COOLDOWN) {
                this.cooldownTimer++;
                this.entity.getNavigation().moveTo(this.entity.getTarget(), 1.0f);
                this.entity.setAttacking(false);
            } else {
                if (this.timer < 20) {
                    this.timer++;
                    this.entity.getNavigation().stop();
                    this.entity.setAttacking(true);
                    if (this.timer == 14) {
                        this.tryHurtTarget(this.entity, this.entity.distanceToSqr(this.entity.getTarget()));
                    }
                } else {
                    this.timer = 0;
                    this.entity.setAttacking(false);
                    this.cooldownTimer = 0;
                }
            }
        }
        else{
            this.entity.setAttacking(false);
        }
    }

    protected void tryHurtTarget(GingerbreadManEntity entity, double distanceTo) {
        if (distanceTo < this.getAttackReachSqr(entity)) {
            this.entity.doHurtTarget(this.entity.getTarget());
        }
    }

    protected double getAttackReachSqr(LivingEntity entity) {
        return entity.getBbWidth() * 1.2F * entity.getBbWidth() * 1.2F + entity.getBbWidth();
    }
}
