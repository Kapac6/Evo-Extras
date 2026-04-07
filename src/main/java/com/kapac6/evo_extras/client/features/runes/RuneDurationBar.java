package com.kapac6.evo_extras.client.features.runes;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.ConfigRunes;
import net.minecraft.text.Text;

public class RuneDurationBar {
    public int value = -1;
    public int max = 0;
    public String name = "";

    public void getMessage(Text text, boolean b) {
        String msg = text.getString();
        if(msg.startsWith("Способность активировалась")) {
            value = ConfigRunes.runeDuration * 1000;
            max = ConfigRunes.runeDuration * 1000;
        }
//        if(msg.startsWith("Способность \"Невидимость\" активировалась")) {
//            applyValue(ConfigRunes.runeNinjaLevel, 3000, 5000, 6000);
//            name = "Ниндзя";
//        } else
//        if(msg.startsWith("Способность \"Шипы\" активировалась")) {
//            applyValue(ConfigRunes.runeSadistLevel, 3000, 4000, 5000);
//            name = "Садист";
//        } else
//        if(msg.startsWith("Способность \"Побег\" активировалась")) {
//            applyValue(ConfigRunes.runeBesLevel, 2000, 3000, 4000);
//            name = "Бес";
//        } else
//        if(msg.startsWith("Способность \"Мор\" активировалась")) {
//            applyValue(ConfigRunes.runeMortusLevel, 5000, 7000, 9000);
//            name = "Мортус";
//        } else
//        if(msg.startsWith("Способность \"Часовщик\" активировалась")) {
//            applyValue(ConfigRunes.runeTempusLevel, 6000, 8000, 10000);
//            name = "Темпус";
//        } else
//        if(msg.startsWith("Способность \"Безмолвие\" активировалась")) {
//            applyValue(ConfigRunes.runeSilenceLevel, 3000, 5000, 7000);
//            name = "Тишина";
//        }
    }
    public void tick() {
        if(value >= 0) {
            value -= 50;
        }
    }

    public static RuneDurationBar getInstance() {
        return Evo_extrasClient.runeDurationBar;
    }
}
