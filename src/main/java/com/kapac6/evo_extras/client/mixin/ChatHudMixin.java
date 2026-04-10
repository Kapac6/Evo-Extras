package com.kapac6.evo_extras.client.mixin;

import com.kapac6.evo_extras.client.chat.ChatTabManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.util.ChatMessages;
import net.minecraft.text.OrderedText;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin {
    @Shadow
    @Final
    private List<ChatHudLine> messages;
    @Shadow @Final private List<ChatHudLine.Visible> visibleMessages;
    @Shadow public abstract int getWidth();
    @Shadow public abstract double getChatScale();

    @Inject(method = "addVisibleMessage", at = @At("HEAD"), cancellable = true)
    private void onAddVisibleMessage(ChatHudLine message, CallbackInfo ci) {
        ChatTabManager manager = ChatTabManager.getInstance();
        if (!manager.getActiveTab().getFilter().isEmpty() && !manager.shouldDisplayMessage(message.content())) {
            ci.cancel();
        }
    }

    @Inject(method = "refresh", at = @At("HEAD"), cancellable = true)
    private void onRefresh(CallbackInfo ci) {
        ci.cancel();
        this.visibleMessages.clear();
        ChatTabManager manager = ChatTabManager.getInstance();

        List<ChatHudLine> reversed = new ArrayList<>(messages);
        Collections.reverse(reversed);

        for (ChatHudLine line : reversed) {
            if (manager.getActiveTab().getFilter().isEmpty() ||
                    manager.shouldDisplayMessage(line.content())) {
                addVisibleMessageUnfiltered(line);
            }
        }
    }

    @Unique
    private void addVisibleMessageUnfiltered(ChatHudLine message) {
        int i = MathHelper.floor((double)this.getWidth() / this.getChatScale());
        MessageIndicator.Icon icon = message.getIcon();
        if (icon != null) {
            i -= icon.width + 4 + 2;
        }
        List<OrderedText> list = ChatMessages.breakRenderedChatMessageLines(message.content(), i, MinecraftClient.getInstance().textRenderer);

        for(int j = 0; j < list.size(); ++j) {
            OrderedText orderedText = list.get(j);
            boolean bl = j == list.size() - 1;
            this.visibleMessages.addFirst(new ChatHudLine.Visible(message.creationTick(), orderedText, message.indicator(), bl));
        }

        while(this.visibleMessages.size() > 1000) {
            this.visibleMessages.removeLast();
        }
    }
}
