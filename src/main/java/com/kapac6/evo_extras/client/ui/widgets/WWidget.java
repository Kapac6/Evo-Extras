package com.kapac6.evo_extras.client.ui.widgets;

import com.kapac6.evo_extras.client.ui.WidgetScreen;
import com.kapac6.evo_extras.client.ui.elements.ContextBuilder;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class WWidget extends ClickableWidget {
    protected final WidgetScreen widgetScreen;
    protected ContextBuilder contextBuilder;
    protected boolean isDragging = false;

    private double x;
    private double y;
    protected boolean hidden;
    protected boolean hudHidden;



    public WWidget(int x, int y, int width, int height, WidgetScreen widgetScreen) {
        super(x, y, width, height, Text.empty());
        this.widgetScreen = widgetScreen;
        this.x = x;
        this.y = y;
        this.hidden = true;
        this.hudHidden = false;
    }

    public void hide(boolean value) {
        hidden = value;
    }
    public void hideHud(boolean value) {
        hudHidden = value;
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if(hidden) return;
        super.onClick(mouseX, mouseY);
        isDragging = true;
    }


    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if(hidden) return;
        if(hudHidden && widgetScreen == null) return;
        int rx = (int) Math.round(x);
        int ry = (int) Math.round(y);

        this.setX(rx);
        this.setY(ry);

        renderBg(context);
        if(contextBuilder != null) {
            contextBuilder.setPosition(rx, ry);
            contextBuilder.draw(context);

        }

        //
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        if(hidden) return;
        this.x += deltaX;
        this.y += deltaY;
        if(widgetScreen != null) {
            this.x = Math.max(0, Math.min(this.x, widgetScreen.width - width));
            this.y = Math.max(0, Math.min(this.y, widgetScreen.height - height));
        }
    }

    protected abstract void savePosition();

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {}

    protected void renderBg(DrawContext context) {
        if(hidden) return;
        if(widgetScreen == null) return;
        int bgColor = isHovered() ? 0x80bebebe : 0x808a8a8a;
        context.fill(getX(), getY(), getX()+width, getY()+height, bgColor);
    }


    public void applyPos() {
        if(contextBuilder != null) {
            this.x = getX();
            this.y = getY();
            contextBuilder.setPosition(getX(), getY());
        }
    }
    protected ContextBuilder getContextBuilder() {
        return contextBuilder;
    }
    protected void updateLine(int index, Text newText) {
        if(contextBuilder != null) {
            contextBuilder.updateLine(index, newText);
        }
    }
    protected void updateColor(int index, int newColor) {
        if(contextBuilder != null) {
            contextBuilder.updateColor(index, newColor);
        }
    }
    protected void updateIcon(int index, Identifier icon) {
        if(contextBuilder != null) {
            contextBuilder.updateIcon(index, icon);
        }
    }
    protected void updateBar(int index, int value, int min, int max) {
        if(contextBuilder != null) {
            contextBuilder.updateBar(index, value, min, max);
        }
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }




}
