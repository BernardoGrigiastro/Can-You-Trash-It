package net.reinderp.trashcans.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import net.reinderp.trashcans.common.blocks.EnergyTrashcanBlock;
import net.reinderp.trashcans.common.blocks.FluidTrashcanBlock;
import net.reinderp.trashcans.common.blocks.ItemTrashcanBlock;
import net.reinderp.trashcans.util.Identifiers;

public class ModBlocks {

    public static final ItemTrashcanBlock TRASHCAN_ITEM = new ItemTrashcanBlock(FabricBlockSettings.of(Material.METAL).strength(3.0F, 4.8F).sounds(BlockSoundGroup.METAL).requiresTool().breakByTool(FabricToolTags.PICKAXES).nonOpaque());
    public static final FluidTrashcanBlock TRASHCAN_FLUID = new FluidTrashcanBlock(FabricBlockSettings.of(Material.METAL).strength(3.0F, 4.8F).sounds(BlockSoundGroup.METAL).requiresTool().breakByTool(FabricToolTags.PICKAXES).nonOpaque());
    public static final EnergyTrashcanBlock TRASHCAN_ENERGY = new EnergyTrashcanBlock(FabricBlockSettings.of(Material.METAL).strength(3.0F, 4.8F).sounds(BlockSoundGroup.METAL).requiresTool().breakByTool(FabricToolTags.PICKAXES).nonOpaque());

    public static void Initialize() {
        Registry.register(Registry.BLOCK, Identifiers.TRASHCAN_ITEM, TRASHCAN_ITEM);
        Registry.register(Registry.BLOCK, Identifiers.TRASHCAN_FLUID, TRASHCAN_FLUID);
        Registry.register(Registry.BLOCK, Identifiers.TRASHCAN_ENERGY, TRASHCAN_ENERGY);
    }
}