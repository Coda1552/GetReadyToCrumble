package coda.getreadytocrumble.common.items;

import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import coda.getreadytocrumble.registry.GRTCEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GingerBreadManItem extends Item {
    public GingerBreadManItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        ItemStack itemstack = player.getItemInHand(hand);
        BlockState blockstate = level.getBlockState(pos);
        GingerbreadManEntity gbm = GRTCEntities.GINGERBREAD_MAN.get().create(level);

        if (!level.isClientSide && gbm != null) {
            BlockPos blockpos1;
            if (blockstate.getCollisionShape(level, pos).isEmpty()) {
                blockpos1 = pos;
            } else {
                blockpos1 = pos.relative(direction);
            }
            gbm.moveTo(blockpos1.getX() + 0.5F, blockpos1.getY() + 0.5F, blockpos1.getZ() + 0.5F);
            gbm.tame(player);
            gbm.finalizeSpawn((ServerLevel) level, level.getCurrentDifficultyAt(player.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            level.addFreshEntity(gbm);
            player.getItemInHand(hand).shrink(1);
            level.playSound(player, player.blockPosition(), SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.NEUTRAL, 1.0F, 1.0F);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.isCreative()) {
            itemstack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        tooltip.add(new TranslatableComponent("item.tooltip.gingerbreadman").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY).withItalic(true)));
    }
}
