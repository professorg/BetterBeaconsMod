package com.professorg.betterbeaconsmod;

import com.professorg.betterbeaconsmod.init.ModBlocks;
import com.professorg.betterbeaconsmod.init.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

        // Blocks/Items init
        ModItems.init();
        ModBlocks.init();
    }

    public void init(FMLInitializationEvent event) {

        ModBlocks.initRecipes();
        ModItems.initRecipes();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
