package com.kapac6.evo_extras.client.ui.elements.chat;

import com.kapac6.evo_extras.client.chat.ChatTab;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class TabButton extends ButtonWidget {
    private final ChatTab tab;
    private boolean active;

    public TabButton(int x, int y, ChatTab tab, PressAction onPress) {
        super(x, y, 0, 12, Text.literal(tab.name()), onPress, DEFAULT_NARRATION_SUPPLIER);
        this.tab = tab;
        this.width = MinecraftClient.getInstance().textRenderer.getWidth(tab.name()) + 6;
    }

    public ChatTab getTab() { return tab; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        int color = active ? tab.selectedColor() : (isHovered() ? tab.hoveredColor() : tab.color());
        context.fill(getX(), getY(), getX() + width, getY() + height, 0x80000000);
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        context.drawCenteredTextWithShadow(textRenderer, getMessage(),
                getX() + width / 2, getY() + (height - 8) / 2, color);
    }
}