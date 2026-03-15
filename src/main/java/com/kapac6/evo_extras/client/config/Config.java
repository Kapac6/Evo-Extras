package com.kapac6.evo_extras.client.config;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.Hidden.HudConfig;
import com.kapac6.evo_extras.client.ui.WidgetScreen;
import com.teamresourceful.resourcefulconfig.api.annotations.*;

@com.teamresourceful.resourcefulconfig.api.annotations.Config(
        value = Evo_extrasClient.MODID,
        categories = {
                ConfigAutoclicker.class,
                ConfigMining.class,
                ConfigRunes.class,
                HudConfig.class
        }
)
@ConfigInfo(
        icon = "fish",
        title = "§d§lEvo Extras",
        description = "QoL mod for PrisonEvo mode of Diamond World",
        descriptionTranslation = "evoextras.config.info",
        links = {
            @ConfigInfo.Link(
                    value = "https://modrinth.com/mod/evoextras",
                    icon = "modrinth",
                    text = "Modrinth"
            ),
            @ConfigInfo.Link(
                    value = "https://discord.gg/sx4TXM2NX8",
                    icon = "cat",
                    text = "Discord"
            )
        }
)
public class Config {

    @ConfigButton(text = "Открыть", title = "Открыть меню редактирования виджетов")
    public static final Runnable editWidgetsButton = () -> {
        Evo_extrasClient.instance.setScreen(new WidgetScreen());
    }; /////////////////////////////////////


    @ConfigEntry(
            id = "widgetUseBg",
            translation = "Использовать фон в виджетах"
    )
    public static boolean widgetUseBg = true;


    @ConfigEntry(
            id = "widgetBg",
            translation = "Цвет фона виджетов"
    )
    @ConfigOption.Color(
            alpha = true
    )
    public static int widgetBg = 1686801034;

}
