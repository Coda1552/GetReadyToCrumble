package coda.getreadytocrumble.common.items;

import coda.getreadytocrumble.common.entities.GingerbreadManEntity;
import coda.getreadytocrumble.registry.GRTCEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GingerBreadManItem extends Item {
    public GingerBreadManItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
            ServerLevel levelIn = (ServerLevel) level;
            GingerbreadManEntity gbm = GRTCEntities.GINGERBREAD_MAN.get().create(levelIn);
            BlockPos blockpos = player.blockPosition().offset(0.0d, 0.1d, 0.0d);
            gbm.moveTo(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            gbm.setNoGravity(false);
            gbm.finalizeSpawn((ServerLevel) levelIn, levelIn.getCurrentDifficultyAt(blockpos), MobSpawnType.MOB_SUMMONED, null, null);
            levelIn.addFreshEntity(gbm);
            return super.use(levelIn, player, hand);
    }
}
