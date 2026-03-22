package com.kapac6.evo_extras.client.ui;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.Config;
import com.kapac6.evo_extras.client.config.Hidden.HudConfig;
import com.kapac6.evo_extras.client.ui.widgets.WWidget;
import com.kapac6.evo_extras.client.ui.widgets.WiBlockProfitPH;
import com.kapac6.evo_extras.client.ui.widgets.WiBoostCounter;
import com.kapac6.evo_extras.client.ui.widgets.WiRuneDuration;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;


public class WidgetScreen extends Screen {
    private WiBlockProfitPH wiBlockProfitPH;
    private WiRuneDuration wiRuneDuration;
    private WiBoostCounter wiBoostCounter;

    public WidgetScreen() {
        super(Text.literal("Widget screen"));
        initScreenWidgets();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double xAmount, double yAmount) {
        for (Element element : this.children()) {
            Evo_extrasClient.logger.info("element");
            if (element instanceof ClickableWidget widget && widget.isMouseOver(mouseX, mouseY)) {
                Evo_extrasClient.logger.info("instance and over");
                if(widget instanceof WWidget wWidget) {
                    Evo_extrasClient.logger.info(String.format("resize! x: %s, y: %s", xAmount, yAmount));
                    wWidget.resize(yAmount);
                    return true;
                }
            }
        }
        return super.mouseScrolled(mouseX, mouseY, xAmount, yAmount);
    }

    private void initScreenWidgets() {
        wiBlockProfitPH = new WiBlockProfitPH(
                HudConfig.WidgetBphX,
                HudConfig.WidgetBphY,
                230, 31, this
        );

        wiRuneDuration = new WiRuneDuration(
                HudConfig.WidgetRuneDurationX,
                HudConfig.WidgetRuneDurationY,
                200, 16, this
        );

        wiBoostCounter = new WiBoostCounter(
                HudConfig.WidgetBoostCounterX,
                HudConfig.WidgetBoostCounterY,
                HudConfig.WWboostCounterWidth,
                HudConfig.WWboostCounterHeight,
                this
        );

        this.addDrawableChild(wiBlockProfitPH);
        this.addDrawableChild(wiRuneDuration);
        this.addDrawableChild(wiBoostCounter);
    }
    @Override
    public void close() {
        if(wiBlockProfitPH != null) {
            HudConfig.WidgetBphX = wiBlockProfitPH.getX();
            HudConfig.WidgetBphY = wiBlockProfitPH.getY();
        }
        if(wiRuneDuration != null) {
            HudConfig.WidgetRuneDurationX = wiRuneDuration.getX();
            HudConfig.WidgetRuneDurationY = wiRuneDuration.getY();
        }
        if(wiBoostCounter != null) {
            HudConfig.WidgetBoostCounterX = wiBoostCounter.getX();
            HudConfig.WidgetBoostCounterY = wiBoostCounter.getY();
        }


        boolean configurator = Evo_extrasClient.configurator.saveConfig(Config.class);

        Evo_extrasClient.evoClient.initHudWidgets();

        super.close();
    }




}
