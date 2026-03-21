package com.kapac6.evo_extras.client.ui.widgets;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.ConfigMining;
import com.kapac6.evo_extras.client.config.Hidden.HudConfig;
import com.kapac6.evo_extras.client.features.mine.boostCounter.Boost;
import com.kapac6.evo_extras.client.features.mine.boostCounter.BoostCounter;
import com.kapac6.evo_extras.client.ui.WidgetScreen;
import com.kapac6.evo_extras.client.ui.elements.ContextBuilder;
import com.kapac6.evo_extras.client.util.TimeUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WiBoostCounter extends WWidget {
    private long lastUpdateTime = 0;
    private static final long UPDATE_COOLDOWN = 1000;
    private static final int PADDING = 10;
    private BoostCounter boostCounter;
    private int boosts = 0;
    private double totalMoneyBoost = 0;
    private double totalShardBoost = 0;

    public WiBoostCounter(int x, int y, int width, int height, WidgetScreen widgetScreen) {
        super(x, y, width, height, widgetScreen);
        this.boostCounter = Evo_extrasClient.boostCounter;
    }

    private void ensureContext() {
        if(contextBuilder == null) {
            contextBuilder = new ContextBuilder.Builder()
                    .setPadding(PADDING)
                    .addLine(Text.literal("Бусты: §f\uE135[§a0.0§f] / \uE365[§d0.0§f]"), 0xCAFEEDFF, 1, 1, true)
                    .setX(getX())
                    .setY(getY())
                    .setScale(1)
                    .setWidth(width)
                    .setHeight(height)
                    .background(true)
                    .build();
        }
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (ConfigMining.boostCounterWidgetToggle) {
            hide(false);
        } else {
            hide(true);
            return;
        }

        ensureContext();
        if(contextBuilder == null) {
            renderBg(context);
            return;
        }

        update();

        super.renderWidget(context, mouseX, mouseY, delta);
    }

    private void update() {
        if(contextBuilder == null) return;

        long now = System.currentTimeMillis();
        if(now - lastUpdateTime > UPDATE_COOLDOWN) {
            if(boostCounter.boostList.size() != boosts) {
                boostCounter.boostList.sort(Comparator.comparing(Boost::getTimeLeft)); // сортировка по длительности
                ContextBuilder.Builder builder = new ContextBuilder.Builder()
                        .setPadding(PADDING)
                        .setX(getX())
                        .setY(getY())
                        .setScale(1)
                        .background(true)
                        .addLine(Text.literal("Бусты: §f\uE135[§a0.0§f] / \uE365[§d0.0§f]"), 0xCAFEEDFF, 1, 1, true);

                List<Boost> clonedList = new ArrayList<>(boostCounter.boostList);
                boosts = 0;
                totalMoneyBoost = 0;
                totalShardBoost = 0;
                this.width = HudConfig.WWboostCounterWidth;
                this.height = HudConfig.WWboostCounterHeight;

                for(Boost boost : clonedList) {
                    boost.tick();
                    if(boost.nullable) {
                        boostCounter.boostList.remove(boost);
                    }
                    height += 10;

                    if(boost.type.equals("\uE135")) {
                        totalMoneyBoost += boost.boost;
                    } else {
                        totalShardBoost += boost.boost;
                    }

                    builder.addLine(Text.literal(String.format("§f%s §e[%s]§f %s", boost.type, boost.boost, TimeUtils.asShortTextTime(boost.getTimeLeft())))
                    , 0xFFFFFFFF, 1, 1, true);
                    boosts++;
                }
                this.contextBuilder = builder.setWidth(width).setHeight(height).build();
            } else {
                int index = 1;
                List<Boost> clonedList = new ArrayList<>(boostCounter.boostList);
                for(Boost boost : clonedList) {
                    boost.tick();
                    if(boost.nullable) boostCounter.boostList.remove(boost);
                    updateLine(index, Text.literal(String.format("§f%s §e[%s]§f %s", boost.type, boost.boost, TimeUtils.asShortTextTime(boost.getTimeLeft()))));
                    index++;
                }
            }

            updateLine(0, Text.literal(String.format("§f\uE135§a[%s] §f/ \uE365§d[%s]§f", totalMoneyBoost, totalShardBoost)));
            lastUpdateTime = now;
        }
    }


    @Override
    protected void savePosition() {
        HudConfig.WidgetBoostCounterX = getX();
        HudConfig.WidgetBoostCounterY = getY();
    }
}
