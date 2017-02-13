package com.professorg.betterbeaconsmod.init;

import com.professorg.betterbeaconsmod.BetterBeaconsMod;
import com.professorg.betterbeaconsmod.item.ItemBetterBeaconsManual;
import com.professorg.betterbeaconsmod.lib.Names;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    public static ItemBetterBeaconsManual itemBetterBeaconsManual;
    public static void init() {

        itemBetterBeaconsManual = new ItemBetterBeaconsManual();
        itemBetterBeaconsManual.setRegistryName(new ResourceLocation(BetterBeaconsMod.RESOURCE_PREFIX + Names.BETTER_BEACONS_MANUAL));
        GameRegistry.register(itemBetterBeaconsManual);
    }


    @SideOnly(Side.CLIENT)
    public static void initClient(ItemModelMesher mesher) {

        ModelResourceLocation model = new ModelResourceLocation(BetterBeaconsMod.RESOURCE_PREFIX + Names.BETTER_BEACONS_MANUAL, "inventory");
        ModelLoader.registerItemVariants(itemBetterBeaconsManual, model);
        mesher.register(itemBetterBeaconsManual, 0, model);
    }

    public static void initRecipes() {

        itemBetterBeaconsManual.addRecipes();
    }
}
