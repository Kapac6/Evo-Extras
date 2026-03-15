package com.kapac6.evo_extras.client.ui;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.Hidden.HudConfig;
import com.kapac6.evo_extras.client.ui.widgets.WiBlockProfitPH;
import com.kapac6.evo_extras.client.ui.widgets.WiRuneDuration;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;


public class WidgetScreen extends Screen {
    private WiBlockProfitPH wiBlockProfitPH;
    private WiRuneDuration wiRuneDuration;

    public WidgetScreen() {
        super(Text.literal("Widget screen"));
        initScreenWidgets();
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

        this.addDrawableChild(wiBlockProfitPH);
        this.addDrawableChild(wiRuneDuration);
    }
    @Override
    public void close() {
        if(wiBlockProfitPH != null) {
            HudConfig.WidgetBphX = wiBlockProfitPH.getX();
            HudConfig.WidgetBphY = wiBlockProfitPH.getY();
            Evo_extrasClient.logger.info(String.format("Saved BPH at x: %s, y: %s", HudConfig.WidgetBphX, HudConfig.WidgetBphY));
        }
        if(wiRuneDuration != null) {
            HudConfig.WidgetRuneDurationX = wiRuneDuration.getX();
            HudConfig.WidgetRuneDurationY = wiRuneDuration.getY();
            Evo_extrasClient.logger.info(String.format("Saved Rune at x: %s, y: %s", HudConfig.WidgetRuneDurationX, HudConfig.WidgetRuneDurationY));
        }

        Evo_extrasClient.configurator.saveConfig(HudConfig.class);
        Evo_extrasClient.logger.info("configurator");

        Evo_extrasClient.evoClient.initHudWidgets();

        super.close();
    }




}
