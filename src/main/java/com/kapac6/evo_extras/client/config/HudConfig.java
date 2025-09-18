package com.kapac6.evo_extras.client.config;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

import java.io.File;

public class HudConfig extends Vigilant {

    /*
     *  РАСПОЛОЖЕНИЕ ВИДЖЕТОВ (HIDDEN)
     */
    @Property(type = PropertyType.NUMBER, hidden = true, name = "WBlockProfitPerHour_X", category = "pos", min = -1000, max = 10000)
    public static int WBlockProfitPerHour_X = 0;
    @Property(type = PropertyType.NUMBER, hidden = true, name = "WBlockProfitPerHour_Y", category = "pos", min = -1000, max = 10000)
    public static int WBlockProfitPerHour_Y = 0;

    @Property(type = PropertyType.NUMBER,  name = "WTest_X", category = "pos", min = -1000, max = 10000)
    public static int WTest_X = 0;
    @Property(type = PropertyType.NUMBER,  name = "WTest_Y", category = "pos", min = -1000, max = 10000)
    public static int WTest_Y = 0;

    public static HudConfig instance = new HudConfig();
    public HudConfig() {
        super(new File("./config/evoextras/hudconfig.toml"), "§d§lEvo Extras Widgets");
        initialize();
    }
}
