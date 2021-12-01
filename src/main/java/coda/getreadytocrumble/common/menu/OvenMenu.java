package coda.getreadytocrumble.common.menu;

import coda.getreadytocrumble.registry.GRTCMenus;
import coda.getreadytocrumble.registry.GRTCRecipes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.RecipeBookType;

public class OvenMenu extends AbstractFurnaceMenu {

    public OvenMenu(int windowId, Inventory inventory, FriendlyByteBuf data) {
        this(windowId, inventory);
    }

    public OvenMenu(int p_39079_, Inventory p_39080_) {
        super(GRTCMenus.OVEN_MENU.get(), GRTCRecipes.BAKING_TYPE, RecipeBookType.CRAFTING, p_39079_, p_39080_);
    }
}