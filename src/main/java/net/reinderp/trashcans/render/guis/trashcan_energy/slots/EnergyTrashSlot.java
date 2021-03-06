package net.reinderp.trashcans.render.guis.trashcan_energy.slots;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import team.reborn.energy.api.base.SimpleBatteryItem;

public class EnergyTrashSlot extends Slot {
    public EnergyTrashSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() instanceof SimpleBatteryItem;
    }
}
