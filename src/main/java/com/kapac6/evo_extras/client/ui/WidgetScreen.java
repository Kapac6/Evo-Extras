package com.kapac6.evo_extras.client.ui;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.Hidden.HudConfig;
import com.kapac6.evo_extras.client.ui.widgets.WBlockProfitPH;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;


public class WidgetScreen extends Screen {
    private WBlockProfitPH wBlockProfitPH;

    public WidgetScreen() {
        super(Text.literal("Widget screen"));
        initScreenWidgets();
    }

    private void initScreenWidgets() {
        wBlockProfitPH = new WBlockProfitPH(
                HudConfig.WidgetBphX,
                HudConfig.WidgetBphY,
                260, 31, this
        );
        this.addDrawableChild(wBlockProfitPH);
    }
    @Override
    public void close() {
        if(wBlockProfitPH != null) {
            HudConfig.WidgetBphX = wBlockProfitPH.getX();
            HudConfig.WidgetBphY = wBlockProfitPH.getY();
        }

        Evo_extrasClient.configurator.saveConfig(HudConfig.class);

        Evo_extrasClient.evoClient.reloadHudWidgets();

        super.close();
    }




}
