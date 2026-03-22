package com.kapac6.evo_extras.client.ui.widgets;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.Hidden.HudConfig;
import com.kapac6.evo_extras.client.config.ConfigRunes;
import com.kapac6.evo_extras.client.features.runes.RuneDurationBar;
import com.kapac6.evo_extras.client.ui.WidgetScreen;
import com.kapac6.evo_extras.client.ui.elements.ContextBuilder;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class WiRuneDuration extends WWidget {
    private long lastUpdateTime = 0;
    private RuneDurationBar runeDurationBar;
    private static final long UPDATE_COOLDOWN = 20; //50мс

    public WiRuneDuration(int x, int y, int width, int height, WidgetScreen widgetScreen) {
        super(x, y, width, height, widgetScreen, HudConfig.WidgetRuneDurationScale);
        this.runeDurationBar = RuneDurationBar.getInstance();
        if(runeDurationBar == null) {
            this.runeDurationBar = Evo_extrasClient.runeDurationBar;
        }
    }

    private void ensureContext() {
        if(contextBuilder == null) {
            contextBuilder = new ContextBuilder.Builder()
                    .addBar(2, 2, width - 2 -2, height - 2 -2,
                            ConfigRunes.runeDurationWidgetBackColor, ConfigRunes.runeDurationWidgetFrontColor)
                    .addLine(Text.literal(""), 0xCAFEEDFF, 2+2, 2+2, false)
                    .setX(getX())
                    .setY(getY())
                    .setScale(1)
                    .setWidth(width)
                    .setHeight(height)
                    .background(true)
                    .build();
        }
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (ConfigRunes.runeDurationWidgetToggle) {
            hide(false);
        } else {
            hide(true);
            return;
        }

        ensureContext();
        if(contextBuilder == null) {
            renderBg(context);
            return;
        }

        contextBuilder.getBar(0).setPos(getX()+2, getY()+2);


        update();

        super.renderWidget(context, mouseX, mouseY, delta);
    }

    private void update() {
        if(contextBuilder == null) return;

        long now = System.currentTimeMillis();
        if(now - lastUpdateTime > UPDATE_COOLDOWN) {
            if(runeDurationBar.value > 0) {
                if(this.hudHidden) this.hideHud(false);

                updateBar(0, runeDurationBar.value, 0, runeDurationBar.max);
                if(ConfigRunes.runeDurationWidgetName) {
                    updateLine(0, Text.literal(runeDurationBar.name));
                } else updateLine(0, Text.literal(""));
                contextBuilder.getBar(0).setBackColor(ConfigRunes.runeDurationWidgetBackColor);
                contextBuilder.getBar(0).setFrontColor(ConfigRunes.runeDurationWidgetFrontColor);


            } else {
                if(!this.hudHidden) this.hideHud(true);
            }

            lastUpdateTime = now;


        }
    }

    @Override
    protected void savePosition() {
        HudConfig.WidgetRuneDurationX = getX();
        HudConfig.WidgetRuneDurationY = getY();
    }

    @Override
    protected void saveScale(double scale) {
        HudConfig.WidgetRuneDurationScale = scale;
    }

    @Override
    protected double loadScale() {
        return HudConfig.WidgetRuneDurationScale;
    }
}
