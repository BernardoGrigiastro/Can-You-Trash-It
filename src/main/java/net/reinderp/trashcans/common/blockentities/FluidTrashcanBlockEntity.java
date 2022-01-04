package net.reinderp.trashcans.common.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.reinderp.trashcans.compat.TechRebornCompat;
import net.reinderp.trashcans.init.ModBlockEntities;
import net.reinderp.trashcans.render.guis.trashcan_fluid.FluidTrashCanScreenHandler;
import net.reinderp.trashcans.util.fluid.FluidContainerProvider;
import net.reinderp.trashcans.util.ImplementedInventory;
import net.reinderp.trashcans.util.fluid.FluidContainer;
import net.reinderp.trashcans.util.fluid.FluidValue;
import net.reinderp.trashcans.util.fluid.VoidFluidContainer;
import org.jetbrains.annotations.Nullable;

public class FluidTrashcanBlockEntity extends BlockEntity implements BlockEntityTicker<FluidTrashcanBlockEntity>, FluidContainerProvider, NamedScreenHandlerFactory, ImplementedInventory {

    protected DefaultedList<ItemStack> inventory;

    private VoidFluidContainer fluidContainer = new VoidFluidContainer("FluidTrashcanFluidContainer", FluidValue.BUCKET);

    public FluidTrashcanBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUID_TRASHCAN_BLOCK_ENTITY, pos, state);

        this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    }

    @Override
    public FluidContainer getFluidContainer(BlockState var1, WorldAccess var2, BlockPos var3) {
        return this.fluidContainer;
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText("block.trashcans.trashcan_fluid");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new FluidTrashCanScreenHandler(syncId, inv, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, FluidTrashcanBlockEntity blockEntity) {
        ItemStack itemStack = this.inventory.get(0);
        if (!itemStack.isEmpty()) {
            if (itemStack.getItem() instanceof BucketItem) {
                this.inventory.set(0, new ItemStack(Items.BUCKET, itemStack.getCount()));
            }
            else if (TechRebornCompat.instanceOfCell(itemStack.getItem())) {
                this.inventory.set(0, TechRebornCompat.getEmptyStack(itemStack));
            }
        }
    }
}
