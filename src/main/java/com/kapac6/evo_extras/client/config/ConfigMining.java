package com.kapac6.evo_extras.client.config;

import com.kapac6.evo_extras.client.features.mine.blockPH.BlockProfitPerHour;
import com.teamresourceful.resourcefulconfig.api.annotations.*;

@Category("Шахта")
public class ConfigMining {

    @ConfigOption.Separator(
            value = "Счетчик денег в час",
            description = "Считает примерное количество блоков/денег в час"
    )
    @ConfigEntry(
            id = "bphWidgetToggle",
            translation = "Отображение виджета"
    )
    public static boolean bphWidgetToggle = false;


    @Comment(
            value = "§cВключать только если у вас нет /autosell\n§7Не будет считать деньги за вскопанные блоки"
    )
    @ConfigEntry(
            id = "bphWidgetOnlyBlocks",
            translation = "Считать только блоки"
    )
    public static boolean bphWidgetOnlyBlocks = false;

    @ConfigEntry(
            id = "bphWidgetAllowed",
            translation = "Учитывать в счетчике"
    )
    @ConfigOption.Select("Выбрать")
    public static bphAllowEnum[] bphWidgetAllowed = bphAllowEnum.values();

    public enum bphAllowEnum {
        BARRELS, RUNES, BOMBS, PETS, WANDS, MULTITOOL;

        @Override
        public String toString() {
            return switch (this) {
                case BARRELS -> "Бочки";
                case RUNES -> "Руны";
                case BOMBS -> "Бомбочки";
                case PETS -> "Питомцы";
                case WANDS -> "Посохи";
                case MULTITOOL -> "Мультитул";
            };
        }

    }



    @ConfigButton(text = "Сбросить", title = "Сбросить виджет")
    public static final Runnable bphWidgetReset = () -> {
        BlockProfitPerHour.getInstance().reset();
    };

    @ConfigOption.Separator(
            value = "Список локальных бустеров",
            description = "Показывает активные локальные бустеры в виде списка"
    )
    @ConfigEntry(
            id = "boostCounterWidgetToggle",
            translation = "Отображение виджета"
    )
    public static boolean boostCounterWidgetToggle = false;

}
