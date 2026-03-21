package com.kapac6.evo_extras.client.features.mine.boostCounter;

import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoostCounter {
    public List<Boost> boostList = new ArrayList<>();
    static Pattern boostPattern = Pattern.compile("бустер (.+) x(\\d+\\.\\d+) на (\\d+) минут");
    public void getMessage(Text text, boolean overlay) {
        if(overlay) return;
        String msg = text.getString();
        if(msg.startsWith("Вы получили локальный бустер")) {
            Matcher matcher = boostPattern.matcher(msg);
            if(matcher.find()) {
                String type = matcher.group(1).equals("денег") ? "\uE135" : "\uE365";
                boostList.add(new Boost(type, Integer.parseInt(matcher.group(3))*60*1000, Double.parseDouble(matcher.group(2))));
            }
        }
    }
}
