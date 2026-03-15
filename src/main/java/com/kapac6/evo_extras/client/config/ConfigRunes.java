package com.kapac6.evo_extras.client.config;

import com.teamresourceful.resourcefulconfig.api.annotations.*;

@Category("Руны")
public class ConfigRunes {
    @ConfigOption.Separator(
            value = "Длительность активных рун",
            description = "Полоса, отображающая оставшееся время действия руны"
    )
    @ConfigEntry(
            id = "runeDurationWidgetToggle",
            translation = "Отображение виджета"
    )
    public static boolean runeDurationWidgetToggle = false;


    @ConfigEntry(
            id = "runeDurationWidgetName",
            translation = "Отображение названия руны в виджете"
    )
    public static boolean runeDurationWidgetName = true;


    @ConfigEntry(
            id = "runeDurationWidgetBackColor",
            translation = "Задний цвет полоски"
    )
    @ConfigOption.Color(
            alpha = true
    )
    public static int runeDurationWidgetBackColor = -7237231;

    @ConfigEntry(
            id = "runeDurationWidgetFrontColor",
            translation = "Передний цвет полоски"
    )
    @ConfigOption.Color(
            alpha = true
    )
    public static int runeDurationWidgetFrontColor = -53455108;


    @Comment("0 - отключение")
    @ConfigOption.Range(min = 0, max = 3)
    @ConfigEntry(
            id = "runeNinjaLevel",
            translation = "Уровень руны \"Ниндзя\""
    )
    public static int runeNinjaLevel = 0;


    @Comment("0 - отключение")
    @ConfigOption.Range(min = 0, max = 3)
    @ConfigEntry(
            id = "runeSadistLevel",
            translation = "Уровень руны \"Садист\""
    )
    public static int runeSadistLevel = 0;


    @Comment("0 - отключение")
    @ConfigOption.Range(min = 0, max = 3)
    @ConfigEntry(
            id = "runeBesLevel",
            translation = "Уровень руны \"Бес\""
    )
    public static int runeBesLevel = 0;


    @Comment("0 - отключение")
    @ConfigOption.Range(min = 0, max = 3)
    @ConfigEntry(
            id = "runeMortusLevel",
            translation = "Уровень руны \"Мортус\""
    )
    public static int runeMortusLevel = 0;


    @Comment("0 - отключение")
    @ConfigOption.Range(min = 0, max = 3)
    @ConfigEntry(
            id = "runeTempusLevel",
            translation = "Уровень руны \"Темпус\""
    )
    public static int runeTempusLevel = 0;


    @Comment("0 - отключение")
    @ConfigOption.Range(min = 0, max = 3)
    @ConfigEntry(
            id = "runeSilenceLevel",
            translation = "Уровень руны \"Тишина\""
    )
    public static int runeSilenceLevel = 0;

}
