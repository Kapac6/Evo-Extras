package com.kapac6.evo_extras.client.ui.widgets;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.HudConfig;
import com.kapac6.evo_extras.client.ui.WidgetScreen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import java.awt.*;

public class CustomWidget extends ClickableWidget {
    double x;
    double y;
    int width;
    int height;
    WidgetScreen widgetScreen;
    public CustomWidget(int x, int y, int width, int height, WidgetScreen widgetScreen) {
        super(x, y, width, height, Text.empty());
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.widgetScreen = widgetScreen;

    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        int bgColor = 0x808a8a8a;
        if(isHovered()) bgColor = 0x80bebebe;
        int rx = (int) Math.round(x);
        int ry = (int) Math.round(y);
        this.setX(rx);
        this.setY(ry);
        HudConfig.WTest_X = rx;
        HudConfig.WTest_Y = ry;


        Evo_extrasClient.logger.info("rx: " + HudConfig.WTest_X + " | ry: " + HudConfig.WTest_Y);

        context.fill(rx, ry, rx+width, ry+height, bgColor);
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {}
}
