package net.reinderp.trashcans.common.blockentities;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.tick.TickScheduler;
import net.reinderp.trashcans.TrashcansMod;
import net.reinderp.trashcans.init.ModBlockEntities;
import net.reinderp.trashcans.render.guis.trashcan_item.ItemTrashcanScreenHandler;
import net.reinderp.trashcans.util.ImplementedInventory;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;

public class ItemTrashcanBlockEntity extends BlockEntity implements BlockEntityTicker<ItemTrashcanBlockEntity>, NamedScreenHandlerFactory, ImplementedInventory {

    protected DefaultedList<ItemStack> inventory;

    public ItemTrashcanBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ITEM_TRASHCAN_BLOCK_ENTITY, pos, state);
        this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new ItemTrashcanScreenHandler(syncId, inv, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, ItemTrashcanBlockEntity blockEntity) {
        if (!world.isClient) {
            if (!inventory.get(0).isEmpty()) {
                inventory.set(0, ItemStack.EMPTY);
            }
        }
    }
}
