package com.kapac6.evo_extras.client.ui.elements.chat;

import com.kapac6.evo_extras.client.chat.ChatPrefix;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class PrefixButton extends ButtonWidget {
    private final ChatPrefix prefix;
    private boolean active;

    public PrefixButton(int x, int y, ChatPrefix prefix, PressAction onPress) {
        super(x, y, 0, 12, Text.literal(prefix.getText()), onPress, DEFAULT_NARRATION_SUPPLIER);
        this.prefix = prefix;
        this.width = MinecraftClient.getInstance().textRenderer.getWidth(prefix.getText()) + 6;
    }

    public ChatPrefix getPrefix() { return prefix; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        int color = active ? 0xAAAAAA : (isHovered() ? 0xFFFF55 : 0xFFFFFF);
        context.fill(getX(), getY(), getX() + width, getY() + height, 0x80000000);
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        context.drawCenteredTextWithShadow(textRenderer, getMessage(),
                getX() + width / 2, getY() + (height - 8) / 2, color);
    }
}
