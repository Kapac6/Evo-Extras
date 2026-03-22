package com.kapac6.evo_extras.client.config.Hidden;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
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

    @ConfigOption.Hidden
    @ConfigEntry(
            id = "bphWidgetScale",
            translation = "bph widget scale"
    )
    public static double WidgetBphScale = 1;



    @ConfigOption.Hidden
    @ConfigEntry(
            id = "RuneDurationWidgetX",
            translation = "rune duration widget X"
    )
    public static int WidgetRuneDurationX = 0;

    @ConfigOption.Hidden
    @ConfigEntry(
            id = "RuneDurationWidgetY",
            translation = "rune duration widget Y"
    )
    public static int WidgetRuneDurationY = 0;

    @ConfigOption.Hidden
    @ConfigEntry(
            id = "RuneDurationWidgetScale",
            translation = "rune duration widget scale"
    )
    public static double WidgetRuneDurationScale = 1;


    @ConfigOption.Hidden
    @ConfigEntry(
            id = "BoostCounterWidgetX",
            translation = "boost counter widget X"
    )
    public static int WidgetBoostCounterX = 0;

    @ConfigOption.Hidden
    @ConfigEntry(
            id = "BoostCounterWidgetY",
            translation = "boost counter widget Y"
    )
    public static int WidgetBoostCounterY = 0;

    @ConfigOption.Hidden
    @ConfigEntry(
            id = "BoostCounterWidgetScale",
            translation = "boost counter widget scale"
    )
    public static double WidgetBoostCounterScale = 1;

    public static int WWboostCounterWidth = 160;
    public static int WWboostCounterHeight = 10;
}
