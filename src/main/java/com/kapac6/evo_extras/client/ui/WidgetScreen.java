package com.kapac6.evo_extras.client.ui;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.HudConfig;
import com.kapac6.evo_extras.client.ui.widgets.CustomWidget;
import gg.essential.elementa.WindowScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;


public class WidgetScreen extends WindowScreen {

    public WidgetScreen() {

        Evo_extrasClient.logger.info("init()");
        this.addDrawableChild(new CustomWidget(HudConfig.WTest_X, HudConfig.WTest_Y, 90, 30, this));


    }

}
