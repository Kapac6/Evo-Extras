package com.kapac6.evo_extras.client.ui.widgets;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.ConfigMining;
import com.kapac6.evo_extras.client.config.Hidden.HudConfig;
import com.kapac6.evo_extras.client.features.mine.blockPH.BlockProfitPerHour;
import com.kapac6.evo_extras.client.ui.WidgetScreen;
import com.kapac6.evo_extras.client.util.ContextBuilder;
import com.kapac6.evo_extras.client.util.MoneyUtils;
import com.kapac6.evo_extras.client.util.TimeUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class WBlockProfitPH extends WWidget {
    private static final Identifier TEXTURE = Identifier.of("minecraft", "textures/item/netherite_pickaxe.png");
    private static final Identifier TEXTURE_PAUSED = Identifier.of("minecraft", "textures/item/barrier.png");
    private static final int TEXTURE_SIZE = 28;
    private static final int PADDING = 10;

    private BlockProfitPerHour blockProfitPH;
    private long lastUpdateTime = 0;
    private static final long UPDATE_COOLDOWN = 50; //50мс

    private final boolean isEditMode;

    public WBlockProfitPH(int x, int y, int width, int height, WidgetScreen widgetScreen) {
        super(x, y, width, height, widgetScreen);
        this.blockProfitPH = BlockProfitPerHour.getInstance();
        this.isEditMode = widgetScreen != null;
        if(this.blockProfitPH == null) {
            this.blockProfitPH = Evo_extrasClient.eventBlockProfitPerHour;
        }
    }

    private void ensureContext() { //хуярит контекст когда уже можно, проверка можно сказать..
        if(contextBuilder == null && blockProfitPH != null) {
            contextBuilder = new ContextBuilder.Builder()
                    .addTexture(TEXTURE, 0, 0, true, width, height, TEXTURE_SIZE)
                    .setPadding(PADDING)
                    .addLine(Text.literal("Время: 0"), 0xFFFFFFFF, TEXTURE_SIZE+1, 1, true)
                    .addLine(Text.literal("В час: §b0§f\uE128 §f/ §a0§f\uE135 §f/ §d0§f\uE365"), 0xFFFFFFFF, TEXTURE_SIZE+1, 1, true)
                    .addLine(Text.literal("Накопано: §b0§f\uE128 §f/ §a0§f\uE135 §f/ §d0§f\uE365"), 0xFFFFFFFF, TEXTURE_SIZE+1, 1, true)
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
        if(ConfigMining.bphWidgetToggle) {
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
        if(blockProfitPH == null || contextBuilder == null) return;

        long now = System.currentTimeMillis();
        if(now - lastUpdateTime > UPDATE_COOLDOWN) {

            updateLine(0, Text.literal(String.format("Время: %s",
                    TimeUtils.asTextTime(blockProfitPH.uptime))));

            updateLine(1, Text.literal(String.format("В час: §b%s§f\uE128 §f/ §a%s§f\uE135 §f/ §d%s§f\uE365",
                    MoneyUtils.convertTo(blockProfitPH.BlocksPerHour),
                    MoneyUtils.convertTo(blockProfitPH.MoneyPerHour),
                    MoneyUtils.convertTo(blockProfitPH.ShardsPerHour))));

            updateLine(2, Text.literal(String.format("Накопано: §b%s§f\uE128 §f/ §a%s§f\uE135 §f/ §d%s§f\uE365",
                    MoneyUtils.convertTo(blockProfitPH.totalBrokenBlocks),
                    MoneyUtils.convertTo(blockProfitPH.totalMoney),
                    MoneyUtils.convertTo(blockProfitPH.totalShards))));

            if(blockProfitPH.paused) {
                updateIcon(0, TEXTURE_PAUSED);
            } else {
                updateIcon(0, TEXTURE);
            }


            lastUpdateTime = now;
        }
    }

    @Override
    protected void savePosition() {
        HudConfig.WidgetBphX = getX();
        HudConfig.WidgetBphY = getY();
    }
}
