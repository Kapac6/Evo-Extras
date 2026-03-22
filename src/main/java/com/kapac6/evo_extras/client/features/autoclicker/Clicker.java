package com.kapac6.evo_extras.client.features.autoclicker;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.ConfigAutoclicker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class Clicker {
    public static boolean toggled() {
        return ConfigAutoclicker.autoclickerToggle;
    }

    private static long lastClickTime = 123;

    public static void AutoClickerTick(MinecraftClient mc, boolean toggle) {
        long currentTime = System.currentTimeMillis();
        if (!toggled() && toggle) return;
        int clickCooldown = Math.round((float) 1000 / ConfigAutoclicker.autoclickerCps);

        if((currentTime - lastClickTime) >= clickCooldown) {
            if(mc.world != null && mc.player != null && mc.player.isAlive()) {
                if(ConfigAutoclicker.autoclickerButton.equals(ConfigAutoclicker.ENUMautoclickerButton.RMB)) { //если пкм
                    KeyBinding.onKeyPressed(mc.options.useKey.getDefaultKey());
                } else {

                    KeyBinding.onKeyPressed(mc.options.attackKey.getDefaultKey());
                }

                lastClickTime = System.currentTimeMillis();
            }
        }

    }
}
