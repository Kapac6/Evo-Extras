package com.kapac6.evo_extras.client.mixin;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.features.mine.blockPH.BlockProfitPerHour;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class ActionBarMixin {
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/client/gui/hud/InGameHud;setOverlayMessage(Lnet/minecraft/text/Text;Z)V")
    private void actionBar(Text message, boolean tinted, CallbackInfo info) {
        //Evo_extrasClient.logger.info("||||||||||||    " + message.toString());
        BlockProfitPerHour.getInstance().updateActionBar(message);
    }
}
