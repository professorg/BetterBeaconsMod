package com.professorg.betterbeaconsmod.block;

import com.professorg.betterbeaconsmod.BetterBeaconsMod;
import com.professorg.betterbeaconsmod.init.ModItems;
import com.professorg.betterbeaconsmod.lib.Names;
import com.professorg.betterbeaconsmod.tileentity.TileEntityBetterBeacon;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.professorg.betterbeaconsmod.BetterBeaconsMod.cm;

/**
 * Created by ProfessorG on 2/12/2017.
 */
public class BlockBetterBeacon extends BlockContainer {

    public BlockBetterBeacon() {

        super(Material.GLASS, MapColor.DIAMOND);
        setHardness(3.0f);
        setCreativeTab(CreativeTabs.MISC);
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

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (player.getHeldItem(hand).getItem().equals(Items.BOOK)) {
            int num = player.getHeldItem(hand).getCount();
            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModItems.itemBetterBeaconsManual, num));
            return true;
        }
        if (world.isRemote) return true;
        else {

            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityBetterBeacon)
            {
                player.displayGUIChest((TileEntityBetterBeacon)tileentity);
                player.addStat(StatList.BEACON_INTERACTION);
            }

            return true;
        }
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        super.onBlockPlacedBy(world, pos, state, placer, stack);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityBetterBeacon)
            {
                ((TileEntityBetterBeacon)tileentity).setName(stack.getDisplayName());
            }
        }
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor){
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity instanceof TileEntityBetterBeacon) {
            ((TileEntityBetterBeacon)tileEntity).updateBetterBeacon();
            if (world instanceof World) ((World)world).addBlockEvent(pos, this, 1, 0);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityBetterBeacon();
    }

    public static void updateColorAsync(final World world, final BlockPos glassPos)
    {
        HttpUtil.DOWNLOADER_EXECUTOR.submit(new Runnable()
        {
            public void run()
            {
                Chunk chunk = world.getChunkFromBlockCoords(glassPos);

                for (int i = glassPos.getY() - 1; i >= 0; --i)
                {
                    final BlockPos blockpos = new BlockPos(glassPos.getX(), i, glassPos.getZ());

                    if (!chunk.canSeeSky(blockpos))
                    {
                        break;
                    }

                    IBlockState iblockstate = world.getBlockState(blockpos);

                    if (iblockstate.getBlock() == Blocks.BEACON)
                    {
                        ((WorldServer)world).addScheduledTask(new Runnable()
                        {
                            public void run()
                            {
                                TileEntity tileentity = world.getTileEntity(blockpos);

                                if (tileentity instanceof TileEntityBetterBeacon)
                                {
                                    ((TileEntityBetterBeacon)tileentity).updateBetterBeacon();
                                    world.addBlockEvent(blockpos, Blocks.BEACON, 1, 0);
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
