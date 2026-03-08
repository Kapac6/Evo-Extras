package com.kapac6.evo_extras.client.config;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.features.mine.blockPH.BlockProfitPerHour;
import com.kapac6.evo_extras.client.ui.WidgetScreen;
import com.teamresourceful.resourcefulconfig.api.annotations.*;

@Category("Шахта")
public class ConfigMining {

    @ConfigOption.Separator(
            value = "Счетчик денег в час",
            description = "Считает примерное количество блоков/денег в час"
    )
    @Comment(
            value = "§cСейчас работает только с /autosell"
    )
    @ConfigEntry(
            id = "bphWidgetToggle",
            translation = "Отображение виджета"
    )
    public static boolean bphWidgetToggle = false;


    @ConfigEntry(
            id = "bphWidgetBarrels",
            translation = "Учитывать бочки"
    )
    public static boolean bphWidgetBarrels = false;


    @ConfigEntry(
            id = "bphWidgetRunes",
            translation = "Учитывать руны"
    )
    public static boolean bphWidgetRunes = false;


    @ConfigEntry(
            id = "bphWidgetBombs",
            translation = "Учитывать бомбочки"
    )
    public static boolean bphWidgetBombs = false;


    @ConfigEntry(
            id = "bphWidgetPets",
            translation = "Учитывать питомцев и посох шахтёра"
    )
    public static boolean bphWidgetPets = false;


    @ConfigEntry(
            id = "bphWidgetMultitool",
            translation = "Учитывать зачарования на мультитуле"
    )
    public static boolean bphWidgetMultitool = false;



    @ConfigButton(text = "Сбросить", title = "Сбросить виджет")
    public static final Runnable bphWidgetReset = () -> {
        BlockProfitPerHour.getInstance().reset();
    }; /////////////////////////////////////

}
