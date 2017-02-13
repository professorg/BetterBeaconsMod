package com.professorg.betterbeaconsmod.block;

import com.professorg.betterbeaconsmod.BetterBeaconsMod;
import com.professorg.betterbeaconsmod.init.ModItems;
import com.professorg.betterbeaconsmod.lib.Names;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

import static com.professorg.betterbeaconsmod.BetterBeaconsMod.cm;

/**
 * Created by ProfessorG on 2/12/2017.
 */
public class BlockBetterBeacon extends BlockBeacon {

    public BlockBetterBeacon() {

        super();
        setUnlocalizedName(BetterBeaconsMod.RESOURCE_PREFIX + Names.BETTER_BEACON);
        setLightLevel(1f);
    }

    public void addRecipes() {
        // Remove vanilla beacon recipe
        cm.getRecipeList().removeIf((recipe) -> recipe.getRecipeOutput().getItem().equals(Item.getItemFromBlock(Blocks.BEACON)));
        // Better Beacon from regular Beacon
        GameRegistry.addShapelessRecipe(new ItemStack(this), Blocks.BEACON);
        // Vanilla beacon recipe - overhauled
        GameRegistry.addRecipe(new ItemStack(this), "GGG", "GSG", "OOO", 'G', Blocks.GLASS, 'S', Items.NETHER_STAR, 'O', Blocks.OBSIDIAN);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (player.getHeldItem(hand).getItem().equals(Items.BOOK)) {
            int num = player.getHeldItem(hand).getCount();
            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModItems.itemBetterBeaconsManual, num));
            return true;
        }
        return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
    }
}
