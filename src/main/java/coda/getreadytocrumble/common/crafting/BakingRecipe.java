package coda.getreadytocrumble.common.crafting;

import coda.getreadytocrumble.registry.GRTCBlocks;
import coda.getreadytocrumble.registry.GRTCRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BakingRecipe extends AbstractCookingRecipe {
    public BakingRecipe(ResourceLocation p_43793_, String p_43794_, Ingredient p_43795_, ItemStack p_43796_, float p_43797_, int p_43798_) {
        super(GRTCRecipes.BAKING_TYPE, p_43793_, p_43794_, p_43795_, p_43796_, p_43797_, p_43798_);
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(GRTCBlocks.OVEN.get());
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializer.BLASTING_RECIPE;
    }
}