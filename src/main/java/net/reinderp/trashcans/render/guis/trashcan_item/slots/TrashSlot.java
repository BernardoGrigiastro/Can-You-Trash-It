package net.reinderp.trashcans.render.guis.trashcan_item.slots;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.FurnaceFuelSlot;
import net.minecraft.screen.slot.Slot;
import net.reinderp.trashcans.TrashcansMod;
import org.apache.logging.log4j.Level;

public class TrashSlot extends Slot {

    public TrashSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
}
