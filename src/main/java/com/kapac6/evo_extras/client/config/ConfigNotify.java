package com.kapac6.evo_extras.client.config;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigOption;

@Category("Уведомления")
public class ConfigNotify {

    @ConfigOption.Separator(
            value = "Звуковое уведомление при респавне босса",
            description = "§cТребует включенные уведомления о боссе в EvoPlus\n§cНа данный момент не работает с модом c2me"
    )
    @ConfigEntry(
            id = "bossSoundNotifyToggle",
            translation = "Звуковое уведомление"
    )
    public static boolean bossSoundNotifyToggle = false;

    @Comment(value = "Адекватные значения: 1-100")
    @ConfigOption.Range(min = 0, max = 1000)
    @ConfigEntry(
            id = "bossSoundNotifyVolume",
            translation = "Громкость уведомления"
    )
    public static int bossSoundNotifyVolume = 100;

}
