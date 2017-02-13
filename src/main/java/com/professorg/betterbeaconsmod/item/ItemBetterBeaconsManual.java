package com.professorg.betterbeaconsmod.item;

import com.professorg.betterbeaconsmod.BetterBeaconsMod;
import com.professorg.betterbeaconsmod.block.BlockBetterBeacon;
import com.professorg.betterbeaconsmod.init.ModBlocks;
import com.professorg.betterbeaconsmod.lib.Names;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by ProfessorG on 2/12/2017.
 */
public class ItemBetterBeaconsManual extends Item {

    public ItemBetterBeaconsManual() {

        super();
        setUnlocalizedName(BetterBeaconsMod.RESOURCE_PREFIX + Names.BETTER_BEACONS_MANUAL);
        setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

        // TODO: Open manual
        if(!world.isRemote && !player.isSneaking())
            player.sendMessage(new TextComponentString("*Opens Manual*"));
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (!world.isRemote) {
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock().equals(Blocks.BEACON)) {
                world.setBlockState(pos, ModBlocks.blockBetterBeacon.getDefaultState());
            }
        }
        return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
    }

    public void addRecipes() {

        GameRegistry.addShapelessRecipe(new ItemStack(this), Items.BOOK, Blocks.OBSIDIAN);
        GameRegistry.addShapelessRecipe(new ItemStack(Items.BOOK), new ItemStack(this));
    }
}
