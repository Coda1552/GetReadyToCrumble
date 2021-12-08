package coda.getreadytocrumble.common.entities;

import coda.getreadytocrumble.common.entities.ai.FollowPlayerOwnerGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GingerbreadManEntity extends PathfinderMob implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Integer> CLASS   = SynchedEntityData.defineId(GingerbreadManEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT   = SynchedEntityData.defineId(GingerbreadManEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SITTING   = SynchedEntityData.defineId(GingerbreadManEntity.class, EntityDataSerializers.BOOLEAN);
    private Player owner;

    public GingerbreadManEntity(EntityType<? extends PathfinderMob> type, Level world) {
        super(type, world);
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
        if(this.getSitting()){
            this.getNavigation().stop();
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new FollowPlayerOwnerGoal(this));
        this.goalSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0f));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
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
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(player.getItemInHand(hand).is(Items.IRON_SWORD) || player.getItemInHand(hand).is(Items.IRON_AXE)){
            this.setItemInHand(InteractionHand.MAIN_HAND,player.getItemInHand(hand));
            this.setEntityClass(1);
            return InteractionResult.SUCCESS;
        }
        if(player.getItemInHand(hand).isEmpty()) {
            if (!this.getSitting()) {
                this.setSitting(true);
                return InteractionResult.SUCCESS;
            } else {
                this.setSitting(false);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
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

    public void setOwner(Player owner){
        this.owner = owner;
    }

    public Player getOwner(){
        return this.owner;
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
}
