package com.kapac6.evo_extras.client.config;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigOption;

@Category(value = "Автокликер")
public class ConfigAutoclicker {
    @ConfigOption.Hidden
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
    public static ENUMautoclickerButton autoclickerButton = ENUMautoclickerButton.LMB; //////////////////////////////////////
    public enum ENUMautoclickerButton {
        LMB, RMB;
        @Override
        public String toString() {
            return switch (this) {
                case LMB -> "ЛКМ";
                case RMB -> "ПКМ";
            };
        }}


    @ConfigEntry(
            id = "autoclickerActivation",
            translation = "evoextras.config.autoclicker.activation"
    )
    @ConfigOption.Select
    public static ENUMautoclickerActivation autoclickerActivation = ENUMautoclickerActivation.SWITCH; /////////////////////////////
    public enum ENUMautoclickerActivation {
        HOLD, SWITCH;
        @Override
        public String toString() {
            return switch (this) {
                case HOLD -> "Зажатие";
                case SWITCH -> "Переключение";
            };
        }
    }


    @ConfigEntry(
            id = "autoclickerCps",
            translation = "evoextras.config.autoclicker.cps"
    )
    @ConfigOption.Range(min = 1, max = 20)
    @ConfigOption.Slider
    public static int autoclickerCps = 10; ///////////////////////////////////
}
