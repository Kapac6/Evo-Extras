package com.kapac6.evo_extras.client;

import com.kapac6.evo_extras.client.config.HudConfig;
import com.kapac6.evo_extras.client.features.autoclicker.Clicker;
import com.kapac6.evo_extras.client.config.ConfigMenu;
import com.kapac6.evo_extras.client.ui.WidgetScreen;
import gg.essential.universal.UScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Evo_extrasClient implements ClientModInitializer {

    public static final Logger logger = LoggerFactory.getLogger("Evo Extras");

    private static final KeyBinding ClickerBind = new KeyBinding("Автокликер", GLFW.GLFW_KEY_B, "Evo Extras");
    private static final KeyBinding ConfigBind = new KeyBinding("Открыть меню", GLFW.GLFW_KEY_INSERT, "Evo Extras");
    private static final KeyBinding TestBind = new KeyBinding("Тест", GLFW.GLFW_KEY_G, "Evo Extras");

    public static MinecraftClient instance;

    @Override
    public void onInitializeClient() {
        logger.info("Jujzz.z,.x.,xx.x");

        instance = MinecraftClient.getInstance();

        KeyBindingHelper.registerKeyBinding(ConfigBind);
        KeyBindingHelper.registerKeyBinding(ClickerBind);
        KeyBindingHelper.registerKeyBinding(TestBind);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Clicker.AutoClickerTick(instance, true);
            while (ConfigBind.wasPressed()) {
                UScreen.displayScreen(ConfigMenu.instance.gui());
            }
            while (TestBind.wasPressed()) {
                UScreen.displayScreen(HudConfig.instance.gui());
            }

            if(ConfigMenu.autoclickerMethod == 1) {
                ConfigMenu.autoclickerToggle=false;
                if(ClickerBind.isPressed()) {
                    Clicker.AutoClickerTick(instance, false);
                }
            } else {
                if (ClickerBind.wasPressed()) {
                    ConfigMenu.autoclickerToggle = !ConfigMenu.autoclickerToggle;
                }
            }
        });

    }
}
