package coda.getreadytocrumble.registry;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.common.crafting.BakingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GRTCRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GetReadyToCrumble.MOD_ID);

//    public static final RegistryObject<RecipeSerializer<?>> BAKING_SERIALIZER = RECIPES.register("baking", new SimpleCookingSerializer<>(BakingRecipe::new, 100));
    public static final RecipeType<BakingRecipe> BAKING_TYPE = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(GetReadyToCrumble.MOD_ID, "baking"), new RecipeType<BakingRecipe>() {
        @Override
        public String toString() {
            return "baking";
        }
    });
}
