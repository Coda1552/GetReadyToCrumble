package coda.getreadytocrumble.registry;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.common.items.GingerBreadManItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GRTCItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GetReadyToCrumble.MOD_ID);
    public static final CreativeModeTab TAB = new CreativeModeTab(GetReadyToCrumble.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(GINGERBREAD_MAN.get());
        }
    };

    public static final RegistryObject<Item> GINGERBREAD_MAN = ITEMS.register("gingerbread_man", () -> new GingerBreadManItem(new Item.Properties().tab(TAB).stacksTo(1)));
    public static final RegistryObject<Item> MAGICAL_DOUGH = ITEMS.register("magical_dough", () -> new Item(new Item.Properties().tab(TAB).rarity(Rarity.RARE)) {
        @Override
        public boolean isFoil(ItemStack stack) {
            return true;
        }
    });
    public static final RegistryObject<Item> GRANDMA_SPAWN_EGG = ITEMS.register("grandma_spawn_egg", () -> new ForgeSpawnEggItem(GRTCEntities.GRANDMA, 0xc6c7b3, 0x5b4d6d, new Item.Properties().tab(TAB)));
}
