package net.reinderp.trashcans.common.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.reinderp.trashcans.config.TrashConfig;
import net.reinderp.trashcans.init.ModBlockEntities;
import net.reinderp.trashcans.render.guis.trashcan_energy.EnergyTrashcanScreenHandler;
import net.reinderp.trashcans.util.ImplementedInventory;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleBatteryItem;
import team.reborn.energy.api.base.SimpleEnergyStorage;
import team.reborn.energy.api.base.SimpleSidedEnergyContainer;


public class EnergyTrashcanBlockEntity extends BlockEntity implements BlockEntityTicker<EnergyTrashcanBlockEntity>, NamedScreenHandlerFactory, ImplementedInventory {

    protected DefaultedList<ItemStack> inventory;

    private final SimpleSidedEnergyContainer energyContainer = new SimpleSidedEnergyContainer() {
        @Override
        public long getCapacity() {
            return 10000;
        }

        @Override
        public long getMaxInsert(@Nullable Direction side) {
            return TrashConfig.getConfig().trashSettings.energyTrashcanInput;
        }

        @Override
        public long getMaxExtract(@Nullable Direction side) {
            return 0;
        }
    };

    public EnergyTrashcanBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENERGY_TRASHCAN_BLOCK_ENTITY, pos, state);

        this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, EnergyTrashcanBlockEntity blockEntity) {
        if (!world.isClient && energyContainer.amount > 0) {
            energyContainer.amount = 0;
        }

        ItemStack itemStack = this.inventory.get(0);
        if (!itemStack.isEmpty()) {
            if (itemStack.getItem() instanceof SimpleBatteryItem) {
                if (TrashConfig.getConfig().trashSettings.oneTickItemEnergyDepletion) {
                    ((SimpleBatteryItem) itemStack.getItem()).setStoredEnergy(itemStack, 0);
                } else {
                    ((SimpleBatteryItem) itemStack.getItem()).setStoredEnergy(itemStack, Math.max(0, ((SimpleBatteryItem) itemStack.getItem()).getStoredEnergy(itemStack) - 1024));
                }
            }
        }
    }

    public EnergyStorage getEnergyContainer(@Nullable Direction side) {
        return energyContainer.getSideStorage(side);
    }


    @Override
    public Text getDisplayName() {
        return new TranslatableText("block.trashcans.trashcan_energy");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new EnergyTrashcanScreenHandler(syncId, inv, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }
}
