package coda.getreadytocrumble.common.menu;

import coda.getreadytocrumble.registry.GRTCMenus;
import coda.getreadytocrumble.registry.GRTCRecipes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.crafting.RecipeType;

public class OvenMenu extends AbstractFurnaceMenu {

    public OvenMenu(int windowId, Inventory inventory, FriendlyByteBuf data) {
        this(windowId, inventory);
    }

    public OvenMenu(int p_39079_, Inventory p_39080_) {
        super(GRTCMenus.OVEN_MENU.get(), GRTCRecipes.BAKING_TYPE, RecipeBookType.CRAFTING, p_39079_, p_39080_);
    }

    public OvenMenu(int p_40277_, Inventory p_40278_, Container p_40279_, ContainerData p_40280_) {
        super(MenuType.SMOKER, RecipeType.SMOKING, RecipeBookType.SMOKER, p_40277_, p_40278_, p_40279_, p_40280_);
    }
}