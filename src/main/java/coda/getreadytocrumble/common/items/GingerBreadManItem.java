package coda.getreadytocrumble.common.items;

import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import coda.getreadytocrumble.registry.GRTCEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GingerBreadManItem extends Item {
    public GingerBreadManItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && player.isCrouching()) {
            GingerbreadManEntity gbm = GRTCEntities.GINGERBREAD_MAN.get().create(level);
            Vec3 offset = player.getViewVector(1.0f);
            gbm.moveTo(player.getX(), player.getY(), player.getZ());
            gbm.setOwner(player);
            gbm.finalizeSpawn((ServerLevel) level, level.getCurrentDifficultyAt(player.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            level.addFreshEntity(gbm);
            player.getItemInHand(hand).shrink(1);
            return InteractionResultHolder.consume(player.getItemInHand(hand));
        }
            return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, tooltip, p_41424_);
        tooltip.add(new TextComponent("Made with love, ginger, and a few ingredients not allowed by the FDA...").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY).withItalic(true)));
    }
}
