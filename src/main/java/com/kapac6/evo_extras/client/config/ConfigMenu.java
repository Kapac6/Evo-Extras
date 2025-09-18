package com.kapac6.evo_extras.client.config;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.ui.WidgetScreen;
import gg.essential.universal.UScreen;
import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

import java.io.File;

public class ConfigMenu extends Vigilant {

    /*
     *  ВИДЖЕТЫ
     */
    @Property(
            type = PropertyType.BUTTON,
            name = "Редактор позиций виджетов",
            placeholder = "Открыть",
            category = "Виджеты"
    )
    public void openWidgetEditor() {
        UScreen.displayScreen(new WidgetScreen());
    }
    /*
    *  КЛИКЕР
     */

    @Property(
            type = PropertyType.SWITCH,
            name = "Автокликер",
            description = "Рекомендуется использовать клавишу из настроек управления",
            category = "Кликер"
    )
    public static boolean autoclickerToggle = false;

    @Property(
            type = PropertyType.SLIDER,
            name = "Количество кликов в секунду",
            //description = "",
            category = "Кликер",
            min = 1,
            max = 20
    )
    public static int autoclickerAmount = 10;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Кнопка мыши",
            category = "Кликер",
            options = {"ЛКМ", "ПКМ"}
    )
    public static int autoclickerButton = 0;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Способ активации",
            options = {"Переключение", "Зажатие"},
            category = "Кликер"
    )
    public static int autoclickerMethod = 0;

    /*
     *  ЕСП
     */

    /*
     *  ТАЙМЕР БОССОВ
     */

    /*
     *  ШАХТА
     */

    /*
     *  ИНТЕРФЕЙС
     */




//    @Property(
//            type = PropertyType.COLOR,
//            name = "светяшки",
//            description = "ргб срака z z",
//            category = "тест"
//    )
//    public static Color testColor = Color.white;
    public static ConfigMenu instance = new ConfigMenu();
    public ConfigMenu() {
        super(new File("./config/evoextras/config.toml"), "§d§lEvo Extras");
        initialize();
    }
}
