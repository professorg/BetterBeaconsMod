package com.professorg.betterbeaconsmod.init;

import com.professorg.betterbeaconsmod.BetterBeaconsMod;
import com.professorg.betterbeaconsmod.block.BlockBetterBeacon;
import com.professorg.betterbeaconsmod.lib.Names;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by ProfessorG on 2/12/2017.
 */
public class ModBlocks {

    public static BlockBetterBeacon blockBetterBeacon;

    public static void init() {

        ResourceLocation location = new ResourceLocation(BetterBeaconsMod.RESOURCE_PREFIX + Names.BETTER_BEACON);
        blockBetterBeacon = new BlockBetterBeacon();
        blockBetterBeacon.setRegistryName(location);
        GameRegistry.register(blockBetterBeacon);
        GameRegistry.register(new ItemBlock(blockBetterBeacon), location);
    }

    @SideOnly(Side.CLIENT)
    public static void initClient(ItemModelMesher mesher) {

        Item item = Item.getItemFromBlock(blockBetterBeacon);
        ModelResourceLocation model = new ModelResourceLocation(BetterBeaconsMod.RESOURCE_PREFIX + Names.BETTER_BEACON, "inventory");
        ModelLoader.registerItemVariants(item, model);
        mesher.register(item, 0, model);
    }

    public static void initRecipes() {

        blockBetterBeacon.addRecipes();
    }
}
