package com.professorg.betterbeaconsmod;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Random;

// Based on a tutorial series by SilentChaos512 (https://www.youtube.com/channel/UCZm-VfTkVGml1BJRaLYsJ-Q)

@Mod(modid = BetterBeaconsMod.MODID, name = BetterBeaconsMod.NAME, version = BetterBeaconsMod.VERSION, dependencies = BetterBeaconsMod.DEPENDENCIES)
public class BetterBeaconsMod {

    // Constants
    public static final String MODID = "betterbeaconsmod";
    public static final String NAME = "Better Beacons";
    public static final String VERSION = "1.0.0";
    public static final String DEPENDENCIES = "required-after:forge@[13.20.0.2227,);";
    public static final String RESOURCE_PREFIX = MODID.toLowerCase() + ":";

    // Variables
    public static Random random = new Random();
    public static CraftingManager cm = CraftingManager.getInstance();

    @Instance(MODID)
    public static BetterBeaconsMod instance;

    @SidedProxy(clientSide = "com.professorg.betterbeaconsmod.ClientProxy", serverSide = "com.professorg.betterbeaconsmod.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {

        proxy.preInit(event);
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {

        proxy.init(event);
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {

        proxy.postInit(event);
    }
}
