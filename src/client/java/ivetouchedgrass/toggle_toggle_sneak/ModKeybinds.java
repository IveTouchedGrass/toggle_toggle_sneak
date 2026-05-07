package ivetouchedgrass.toggle_toggle_sneak;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static KeyMapping TOGGLE_MOD;
    public static void registerKeybinds() {
        TOGGLE_MOD = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.toggle_toggle_sneak.toggle",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_BACKSLASH,
                KeyMapping.Category.MISC
        ));
    }
}