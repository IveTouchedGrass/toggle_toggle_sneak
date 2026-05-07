package ivetouchedgrass.toggle_toggle_sneak.mixin;

import ivetouchedgrass.toggle_toggle_sneak.ModKeybinds;
import ivetouchedgrass.toggle_toggle_sneak.ToggleToggleSneak;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Shadow
    @Final
    public Options options;

    @Shadow
    @Nullable
    public LocalPlayer player;

    @Inject(method = "handleKeybinds", at = @At("HEAD"))
    private void handleKeybinds(CallbackInfo ci) {
        if (player == null)
            return;
        if (ModKeybinds.TOGGLE_MOD.isDown() && !ToggleToggleSneak.hasModToggleKeyBeenPressed) {
            options.toggleCrouch().set(!options.toggleCrouch().get());
            player.sendOverlayMessage(options.toggleCrouch().get() ? Component.translatable("toggle_toggle_sneak.enabled_mod").withColor(0x00FF00) : Component.translatable("toggle_toggle_sneak.disabled_mod").withColor(0xFF0000));
        }
        ToggleToggleSneak.hasModToggleKeyBeenPressed = ModKeybinds.TOGGLE_MOD.isDown();
    }
}