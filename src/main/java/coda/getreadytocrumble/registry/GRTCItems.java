package coda.getreadytocrumble.registry;

import coda.getreadytocrumble.GetReadyToCrumble;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GRTCItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GetReadyToCrumble.MOD_ID);
    public static final CreativeModeTab TAB = new CreativeModeTab(GetReadyToCrumble.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MAGICAL_DOUGH.get());
        }
    };

    public static final RegistryObject<Item> MAGICAL_DOUGH = ITEMS.register("magical_dough", () -> new Item(new Item.Properties().tab(TAB).rarity(Rarity.RARE)) {
        @Override
        public boolean isFoil(ItemStack stack) {
            return true;
        }
    });
}
