package com.kapac6.evo_extras.client.ui.elements.chat;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.chat.ChatTab;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class TabButton extends ButtonWidget {
    private final ChatTab tab;
    private boolean activetab;
    private boolean banned = false;
    private final PressAction leftClick;


    public TabButton(int x, int y, ChatTab tab, PressAction leftClick) {
        super(x, y, 0, 12, Text.literal(tab.name()), leftClick, DEFAULT_NARRATION_SUPPLIER);
        this.tab = tab;
        this.width = MinecraftClient.getInstance().textRenderer.getWidth(tab.name()) + 6;
        this.leftClick = leftClick;

        this.setFocused(false);
    }

    public ChatTab getTab() { return tab; }
    public void setActive(boolean active) { this.activetab = active; }
    public void setBanned(boolean banned) { this.banned = banned; }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        int textColor = (activetab ?
                tab.selectedColor() : (banned ?
                tab.bannedColor() : (isHovered() ?
                tab.hoveredColor() : tab.color())));

        context.fill(getX(), getY(), getX() + width, getY() + height, 0x80000000);
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        context.drawCenteredTextWithShadow(textRenderer, getMessage(),
                getX() + width / 2, getY() + (height - 8) / 2, textColor);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible && this.isMouseOver(mouseX, mouseY)) {

            if (button == 0 && leftClick != null) { //лкм
                this.playDownSound(MinecraftClient.getInstance().getSoundManager());
                this.leftClick.onPress(this);
                return true;
            }
        }
        return false;
    }
}