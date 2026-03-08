package com.kapac6.evo_extras.client;

import com.kapac6.evo_extras.client.config.Config;
import com.kapac6.evo_extras.client.config.ConfigAutoclicker;
import com.kapac6.evo_extras.client.config.Hidden.HudConfig;
import com.kapac6.evo_extras.client.event.ChatGameEvent;
import com.kapac6.evo_extras.client.features.autoclicker.Clicker;
import com.kapac6.evo_extras.client.features.mine.blockPH.BlockProfitPerHour;
import com.kapac6.evo_extras.client.ui.WidgetScreen;
import com.kapac6.evo_extras.client.ui.widgets.WBlockProfitPH;
import com.kapac6.evo_extras.client.ui.widgets.WWidget;
import com.kapac6.evo_extras.client.util.ContextBuilder;
import com.teamresourceful.resourcefulconfig.api.client.ResourcefulConfigScreen;
import com.teamresourceful.resourcefulconfig.api.loader.Configurator;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;


public class Evo_extrasClient implements ClientModInitializer {

    public static final Logger logger = LoggerFactory.getLogger("Evo Extras");
    public static final String MODID = "evo_extras";
    public static final Configurator configurator = new Configurator(MODID);

    private static final Identifier WIDGET_LAYER = Identifier.of(MODID, "hud-widget-layer");

    private static final KeyBinding ClickerBind = new KeyBinding("Автокликер", GLFW.GLFW_KEY_UNKNOWN, "Evo Extras");
    private static final KeyBinding ConfigBind = new KeyBinding("Открыть меню", GLFW.GLFW_KEY_INSERT, "Evo Extras");
    //private static final KeyBinding TestBind = new KeyBinding("Тест", GLFW.GLFW_KEY_G, "Evo Extras");

    public static MinecraftClient instance;
    public static Evo_extrasClient evoClient;

    private WWidget wBlockProfitPH;
    private boolean widgetsInitialized = false;
    public static BlockProfitPerHour eventBlockProfitPerHour;
    public static ChatGameEvent eventChatGame;
    @Override
    public void onInitializeClient() {
        logger.info("🎈🎈🎉\nEvo Extras initialized");

        instance = MinecraftClient.getInstance();
        evoClient = this;

        configurator.register(Config.class);

        /*
        * БИНДЫ
         */

        KeyBindingHelper.registerKeyBinding(ConfigBind);
        KeyBindingHelper.registerKeyBinding(ClickerBind);
        //KeyBindingHelper.registerKeyBinding(TestBind);



        /*
        * РЕГИСТРАЦИЯ СОБЫТИЙ
         */

        eventBlockProfitPerHour = new BlockProfitPerHour();
        eventChatGame = new ChatGameEvent();
        ClientPlayerBlockBreakEvents.AFTER.register(eventBlockProfitPerHour);
        ClientReceiveMessageEvents.GAME.register(eventChatGame);




        AtomicLong latestTick = new AtomicLong(System.currentTimeMillis());
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(System.currentTimeMillis() - latestTick.get() >= 1000) { // раз в секунду вызывает всякое
                eventBlockProfitPerHour.second();
                latestTick.set(System.currentTimeMillis());
            }


            if(!widgetsInitialized && client.player != null) { // инициализация виджетов
                initHudWidgets();
                widgetsInitialized = true;
            }


            while (ConfigBind.wasPressed()) { // открытие меню по бинду
                instance.setScreen(ResourcefulConfigScreen.getFactory(MODID).apply(null));
            }


            Clicker.AutoClickerTick(instance, true); // кликер

            if(ConfigAutoclicker.autoclickerActivation.equals(ConfigAutoclicker.ENUMautoclickerActivation.Зажатие)) {
                ConfigAutoclicker.autoclickerToggle = false;
                if(ClickerBind.isPressed()) {
                    Clicker.AutoClickerTick(instance, false);
                }
            } else {
                if (ClickerBind.wasPressed()) {
                    ConfigAutoclicker.autoclickerToggle = !ConfigAutoclicker.autoclickerToggle;
                }
            }
        });

        HudLayerRegistrationCallback.EVENT.register(layeredDrawerWrapper -> {
            layeredDrawerWrapper.attachLayerBefore(IdentifiedLayer.CROSSHAIR, WIDGET_LAYER, ((context, tickCounter) -> {
                if(wBlockProfitPH != null) {
                    wBlockProfitPH.render(context, 0, 0, tickCounter.getTickDelta(false));
                }
            }));
        });

    }

    public void initHudWidgets() {
        wBlockProfitPH = new WBlockProfitPH(
                HudConfig.WidgetBphX,
                HudConfig.WidgetBphY,
                260, //190
                31,
                null
        );
    }

    public void reloadHudWidgets() {
        if(wBlockProfitPH != null) {
            wBlockProfitPH.setX(HudConfig.WidgetBphX);
            wBlockProfitPH.setY(HudConfig.WidgetBphY);
            wBlockProfitPH.applyPos();
        } else {
            initHudWidgets();
        }


    }
}
