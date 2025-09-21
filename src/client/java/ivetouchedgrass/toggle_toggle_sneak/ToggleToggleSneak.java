package ivetouchedgrass.toggle_toggle_sneak;

import net.fabricmc.api.ModInitializer;

public class ToggleToggleSneak implements ModInitializer {
    public static boolean hasModToggleKeyBeenPressed = false;

    @Override
    public void onInitialize() {
        ModKeybinds.registerKeybinds();
    }
}
