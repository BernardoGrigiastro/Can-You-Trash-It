package net.reinderp.trashcans.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "trashcans")
public
class TrashConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public TrashSettings trashSettings = new TrashSettings();

    public static class TrashSettings {
        @ConfigEntry.Gui.Tooltip
        public boolean directBucketClearing = true;

        @ConfigEntry.Gui.Tooltip
        public boolean shiftClearingBucket = true;

        @ConfigEntry.Gui.Tooltip
        public boolean oneTickItemEnergyDepletion = false;

        @ConfigEntry.Gui.Tooltip
        public int energyTrashcanInput = 1024;
    }

    public static TrashConfig getConfig() {
        return AutoConfig.getConfigHolder(TrashConfig.class).getConfig();
    }
}