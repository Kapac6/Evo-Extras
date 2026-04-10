package com.kapac6.evo_extras.client.mixin;

import com.kapac6.evo_extras.client.chat.ChatPrefix;
import com.kapac6.evo_extras.client.chat.ChatTab;
import com.kapac6.evo_extras.client.chat.ChatTabManager;
import com.kapac6.evo_extras.client.ui.elements.chat.PrefixButton;
import com.kapac6.evo_extras.client.ui.elements.chat.TabButton;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin extends Screen {
    @Shadow
    protected TextFieldWidget chatField;

    @Unique
    private final List<TabButton> tabButtons = new ArrayList<>();
    @Unique
    private final List<PrefixButton> prefixButtons = new ArrayList<>();

    protected ChatScreenMixin(Text title) { super(title); }

    @Inject(method = "init", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        ChatTabManager manager = ChatTabManager.getInstance();
        int x = 2;
        int y = this.height - 26;

        // вкладки
        for (ChatTab tab : manager.getTabs()) {
            TabButton btn = new TabButton(x, y, tab,
                    (button) -> {
                        manager.setActiveTab(tab);
                        updateTabs();
                    });
            addDrawableChild(btn);
            tabButtons.add(btn);
            x += btn.getWidth() + 2;
        }


        // префиксы
        x += 15;
        ChatPrefix prefix = manager.getCurrentPrefix();
        for (ChatPrefix iprefix : ChatPrefix.getAll()) {
            PrefixButton btn = new PrefixButton(x, y, iprefix,
                    (button) -> {
                        manager.setPrefix(iprefix);
                        chatField.setText(iprefix.get());
                        updatePrefixes();
                    });
            addDrawableChild(btn);
            prefixButtons.add(btn);
            x += btn.getWidth() + 2;
        }

        updateTabs();
        updatePrefixes();

        if (chatField.getText().isEmpty() && prefix != ChatPrefix.NONE) {
            chatField.setText(prefix.get());
        }
    }

    @Inject(method = "sendMessage", at = @At("HEAD"), cancellable = true)
    private void onSendMessage(String chatText, boolean addToHistory, CallbackInfo ci) {
        ChatPrefix prefix = ChatTabManager.getInstance().getCurrentPrefix();
        if (prefix != ChatPrefix.NONE) {
            String prefixStr = prefix.get();
            if (chatText.equals(prefixStr)) {
                ci.cancel(); // ниче не отправлять если чел ничего не ввел после префикса
            }
        }
    }

    @Unique
    private void updateTabs() {
        ChatTab active = ChatTabManager.getInstance().getActiveTab();
        for (TabButton btn : tabButtons) {
            btn.setActive(btn.getTab() == active);
        }
    }

    @Unique
    private void updatePrefixes() {
        ChatPrefix prefix = ChatTabManager.getInstance().getCurrentPrefix();
        for (PrefixButton btn : prefixButtons) {
            btn.setActive(btn.getPrefix() == prefix);
        }
    }
}
