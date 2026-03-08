package com.kapac6.evo_extras.client.config.Hidden;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.Config;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigOption;

@Category(value = "Debug")
public class HudConfig {

    @ConfigOption.Hidden
    @ConfigEntry(
            id = "bphWidgetX",
            translation = "bph widget X"
    )
    public static int WidgetBphX = 0;

    @ConfigOption.Hidden
    @ConfigEntry(
            id = "bphWidgetY",
            translation = "bph widget Y"
    )
    public static int WidgetBphY = 0;
}
