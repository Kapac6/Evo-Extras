package com.kapac6.evo_extras.client.config;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigOption;

@Category("Визуал")
public class ConfigVisual {
    @ConfigOption.Separator(
            value = "§bПрозрачность окна игры",
            description = "Позволяет менять прозрачность окна с игрой для сквозного просмотра чего-нибудь"
    )
    @ConfigEntry(
            id = "seeThroughWindowToggle",
            translation = "Включение прозрачности окна"
    )
    public static boolean seeThroughWindowToggle = false;
    @ConfigOption.Range(min = 5, max = 100)
    @ConfigOption.Slider
    @ConfigEntry(
            id = "seeThroughAlpha",
            translation = "Непрозрачность окна"
    )
    public static byte seeThroughAlpha = 100;
}