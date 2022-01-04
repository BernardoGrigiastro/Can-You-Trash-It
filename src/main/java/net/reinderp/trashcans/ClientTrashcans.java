package net.reinderp.trashcans;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.reinderp.trashcans.init.ModScreenHandlers;

@Environment(EnvType.CLIENT)
public class ClientTrashcans implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ModScreenHandlers.ClientInitialize();

    }
}
