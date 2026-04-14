package com.kapac6.evo_extras.client.mixin;

import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public abstract class ChatFocusMixin extends Screen {
    @Shadow
    protected TextFieldWidget chatField;

    protected ChatFocusMixin(Text title) { super(title); }

    @Inject(method = "mouseClicked", at = @At("RETURN"))
    private void afterMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        // я ебал это говно!!!!!!!!!!!!!!!!!!! надеюсь оно ниче в других модах не поломает кстати..
        if (!chatField.isMouseOver(mouseX, mouseY)) {
            this.setFocused(chatField);
            chatField.setFocused(true);
        }
    }

    @Inject(method = "keyPressed", at = @At("RETURN"))
    private void afterKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (chatField != null && this.getFocused() != chatField) {
            this.setFocused(chatField);
            chatField.setFocused(true);
        }
    }
}
