package coda.getreadytocrumble.common.entities;

import coda.getreadytocrumble.common.entities.ai.FollowPlayerOwnerGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GingerbreadManEntity extends TamableAnimal implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Integer> CLASS   = SynchedEntityData.defineId(GingerbreadManEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT   = SynchedEntityData.defineId(GingerbreadManEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SITTING   = SynchedEntityData.defineId(GingerbreadManEntity.class, EntityDataSerializers.BOOLEAN);

    public GingerbreadManEntity(EntityType<? extends TamableAnimal> type, Level world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0f));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        super.registerGoals();
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(this.getSitting()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gingerbreadman.sit", true));
            return PlayState.CONTINUE;
        }
        if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gingerbreadman.walk", true));
            return PlayState.CONTINUE;
        }
        else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gingerbreadman.idle", true));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 10, this::predicate));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // only the owner can interact
        if (getOwner() != null && getOwner().is(player)) {
            // can only be interacted with if it has no class
            if (this.getEntityClass() == 0) {
                // knight stuff
                if (player.getItemInHand(hand).is(Items.IRON_SWORD)) {
                    this.setEntityClass(1);
                    stack.shrink(1);
                }
            }

            // sitting
            if (stack.isEmpty()) {
                this.setSitting(!getSitting());
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return this.tickCount;
    }

    public int getVariant(){
        return this.entityData.get(VARIANT);
    }

    public void setSitting(boolean sitting){
        this.entityData.set(SITTING, sitting);
    }

    public boolean getSitting(){
        return this.entityData.get(SITTING);
    }

    public void setEntityClass(int classIn){
        this.entityData.set(CLASS, classIn);
    }

    public int getEntityClass(){
        return this.entityData.get(CLASS);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CLASS, 0);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(SITTING, false);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getSitting()){
            this.getNavigation().stop();
        }
    }
}
