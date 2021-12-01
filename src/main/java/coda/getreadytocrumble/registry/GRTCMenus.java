package coda.getreadytocrumble.registry;

import coda.getreadytocrumble.GetReadyToCrumble;
import coda.getreadytocrumble.common.menu.OvenMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GRTCMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, GetReadyToCrumble.MOD_ID);

    public static final RegistryObject<MenuType<OvenMenu>> OVEN_MENU = MENUS.register("oven", () -> IForgeMenuType.create(OvenMenu::new));
}