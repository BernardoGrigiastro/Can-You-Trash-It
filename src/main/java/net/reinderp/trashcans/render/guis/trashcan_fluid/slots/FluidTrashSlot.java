package net.reinderp.trashcans.render.guis.trashcan_fluid.slots;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.reinderp.trashcans.TrashcansMod;
import net.reinderp.trashcans.compat.TechRebornCompat;
import org.apache.logging.log4j.Level;

public class FluidTrashSlot extends Slot {
    public FluidTrashSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if (stack.getItem() instanceof BucketItem) {
            return true;
        }
        else if (FabricLoader.getInstance().isModLoaded("techreborn") && TechRebornCompat.instanceOfCell(stack.getItem())) {
            return true;
        }
        return false;
    }

    @Override
    public ItemStack insertStack(ItemStack stack) {
        TrashcansMod.LOGGER.log(Level.INFO, "NO");
        return super.insertStack(stack);
    }
}
