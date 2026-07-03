package com.kapac6.evo_extras.client.config;

import com.teamresourceful.resourcefulconfig.api.annotations.Category;
import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigOption;

@Category("Чат")
public class ConfigChat {
    @ConfigOption.Separator(
            value = "§bВкладки чатов",
            description = "Вкладки над полем чата, позволяющие отображать только нужные чаты, Shift+ЛКМ для мута вкладки"
    )
    @ConfigEntry(
            id = "chatTabsToggle",
            translation = "Отображение вкладок чата"
    )
    public static boolean chatTabsToggle = true;
    @ConfigOption.Range(min = 0, max = 9999)
    @ConfigEntry(
            id = "chatTabsXOffset",
            translation = "Положение вкладок чата по горизонтали"
    )
    public static int chatTabsXOffset = 0;
}
