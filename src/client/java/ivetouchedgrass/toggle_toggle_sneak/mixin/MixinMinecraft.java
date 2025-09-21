package ivetouchedgrass.toggle_toggle_sneak.mixin;

import ivetouchedgrass.toggle_toggle_sneak.ModKeybinds;
import ivetouchedgrass.toggle_toggle_sneak.ToggleToggleSneak;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraft {
    @Shadow
    @Final
    public GameOptions options;

    @Shadow
    @Nullable
    public ClientPlayerEntity player;

    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    private void handleKeybinds(CallbackInfo ci) {
        if (player == null)
            return;
        if (ModKeybinds.TOGGLE_MOD.isPressed() && !ToggleToggleSneak.hasModToggleKeyBeenPressed) {
            options.getSneakToggled().setValue(!options.getSneakToggled().getValue());
            player.sendMessage(options.getSneakToggled().getValue() ? Text.translatable("toggle_toggle_sneak.enabled_mod").withColor(0x00FF00) : Text.translatable("toggle_toggle_sneak.disabled_mod").withColor(0xFF0000), true);
        }
        ToggleToggleSneak.hasModToggleKeyBeenPressed = ModKeybinds.TOGGLE_MOD.isPressed();
    }
}