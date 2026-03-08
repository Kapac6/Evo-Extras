package com.kapac6.evo_extras.client.config;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigOption;

@Category(value = "Автокликер")
public class ConfigAutoclicker {
    @ConfigEntry(
            id = "autoclickerToggle",
            translation = "evoextras.config.autoclicker.toggle"
    )
    @Comment(
            value = "Не рекомендуется включать, заставляет автокликер работать без вашего участия"
    )
    public static boolean autoclickerToggle = false; /////////////////////////////////////


    @ConfigEntry(
            id = "autoclickerButton",
            translation = "evoextras.config.autoclicker.button"
    )
    @ConfigOption.Select
    public static ENUMautoclickerButton autoclickerButton = ENUMautoclickerButton.ЛКМ; //////////////////////////////////////
    public enum ENUMautoclickerButton {ЛКМ, ПКМ}


    @ConfigEntry(
            id = "autoclickerActivation",
            translation = "evoextras.config.autoclicker.activation"
    )
    @ConfigOption.Select
    public static ENUMautoclickerActivation autoclickerActivation = ENUMautoclickerActivation.Переключение; /////////////////////////////
    public enum ENUMautoclickerActivation {Зажатие, Переключение}


    @ConfigEntry(
            id = "autoclickerCps",
            translation = "evoextras.config.autoclicker.cps"
    )
    @ConfigOption.Range(min = 1, max = 20)
    @ConfigOption.Slider
    public static int autoclickerCps = 10; ///////////////////////////////////
}
