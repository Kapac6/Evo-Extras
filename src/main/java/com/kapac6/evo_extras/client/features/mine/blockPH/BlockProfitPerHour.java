package com.kapac6.evo_extras.client.features.mine.blockPH;


import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.ConfigMining;
import com.kapac6.evo_extras.client.util.MoneyUtils;
import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockProfitPerHour implements ClientPlayerBlockBreakEvents.After {

    /*
     *  КОНФИГУРАЦИЯ
     */
    private final int maxLatestActionBar = 10*1000; //10*1000 - 10 секунд до инвалидации денег с акшнбара
    private final int maxLatestBreak = 10*1000; //10*1000 - 10 секунд до паузы

    
    public long totalBrokenBlocks = 0;
    public long uptime = 0;
    public long totalMoney = 0;
    public long totalShards = 0;
    private long latestBreak = 1000000;
    private long latestActionBarTime = 100; // как давно показывали деньги в акшнбар, анти-абуз короче
    private long latestActionBar = 0; // последняя цена из акшнбара
    private long latestActionBarTimeout = 100; // за каждый полученный акшнбар больше в 10 раз обычного сюда добавляется 1, анти-абуз бомбочек и прочего
    public boolean paused = true;

    public long BlocksPerHour = 0;
    public long MoneyPerHour = 0;
    public long ShardsPerHour = 0;


    static Pattern moneyPattern = Pattern.compile("(\\d+\\.\\d+\\w)");
    static Pattern shardMultiplierPattern = Pattern.compile("(\\d+)");
    @Override
    public void afterBlockBreak(ClientWorld world, ClientPlayerEntity playerEntity, BlockPos blockPos, BlockState blockState) {
        if(ConfigMining.bphWidgetOnlyBlocks || (latestActionBarTime <= maxLatestActionBar && !paused)) {
            totalBrokenBlocks++;
            latestBreak = 0;
        }
        if(!ConfigMining.bphWidgetOnlyBlocks) {
            addMoney();
        }
    }
    public void reset() {
        totalBrokenBlocks = 0;
        uptime = 0;
        totalMoney = 0;
        totalShards = 0;
        latestBreak = 1000000;
        latestActionBarTime = 100;
        latestActionBarTimeout = 100;
        paused = true;
        BlocksPerHour = 0;
        MoneyPerHour = 0;
        ShardsPerHour = 0;
    }
    public void updateActionBar(Text text) {
        if(text.toString() == null) return;
        if(ConfigMining.bphWidgetOnlyBlocks) return;
        Matcher matchingText = moneyPattern.matcher(text.toString());
        if(matchingText.find()) {

            long price = MoneyUtils.convertFrom(matchingText.group());
            if(price / 10 > latestActionBar && latestActionBarTimeout < 10) { // если цена с акшнбара сказочно высокая
                latestActionBarTimeout++; // забить хуй
            } else {
                latestActionBar = price; // если норм то пушить
                latestActionBarTimeout = 0;
            }
            if(latestBreak <= maxLatestBreak) paused = false; // снятие с паузы только если капнул кеш на счёт
            // TODO: сделать переключение снятия паузы в кфг (снять при копании / снять при деньги) ЧТОБЫ чел без доната мог фиксировать блоки/шарды в час
            latestActionBarTime = 0; // время действия этого акшнбара
        }
    }

    public void getMessage(Text text, boolean overlay) {
        if(overlay) return;
        String msg = text.getString();

        if(msg.startsWith("Вы нашли шард!")) {
            if(msg.length() > 14) { // если Вы нашли шард! x5; если умножается короче
                Matcher matcher = shardMultiplierPattern.matcher(msg);
                if(matcher.find()) {
                    totalShards += Integer.parseInt(matcher.group());
                }
            } else {
                totalShards++;
            }


            latestActionBarTimeout = 0;
            latestBreak = 0;
            paused = false;
        }


        boolean found = isFound(msg);
        if(found) {
            Matcher matchingText = moneyPattern.matcher(text.toString());
            if(matchingText.find()) {
                long price = MoneyUtils.convertFrom(matchingText.group());
                totalMoney += price;

                latestActionBarTimeout = 0;
                latestBreak = 0;
                paused = false;
            }
        }
    }

    private static boolean isFound(String msg) {
        boolean found = false;
        if(ConfigMining.bphWidgetBarrels && (msg.startsWith("Фиолетовая бочка") || msg.startsWith("Красная бочка"))) {
            found = true;

        } else
        if(ConfigMining.bphWidgetRunes && msg.startsWith("Руна")) {
            found = true;

        } else
        if(ConfigMining.bphWidgetBombs && msg.startsWith("Вы взорвали")) {
            found = true;

        } else
        if(ConfigMining.bphWidgetPets && msg.startsWith("Вы сломали")) {
            found = true;

        } else
        if(ConfigMining.bphWidgetMultitool && (
                msg.startsWith("Сокрушающий меч разрубил") ||
                msg.startsWith("Залп стрел"))) {
            found = true;

        }
        return found;
    }

    private void addMoney() {
        if(latestActionBarTime <= maxLatestActionBar && !paused) {
            totalMoney += latestActionBar;
        }
    }

    public static BlockProfitPerHour getInstance() {
        return Evo_extrasClient.eventBlockProfitPerHour;
    }

    public void second() { //добавляет секунду сюда
        if(paused) return;

        latestBreak += 1000;
        latestActionBarTime += 1000;

        if((!ConfigMining.bphWidgetOnlyBlocks && (latestBreak > maxLatestBreak || latestActionBarTime > maxLatestActionBar))
                || (ConfigMining.bphWidgetOnlyBlocks && latestBreak > maxLatestBreak)) {
            paused = true;
        } else {
            uptime += 1000;
        }

        BlocksPerHour = (long) Math.floor(((double) totalBrokenBlocks) / ((double) uptime / (1000 * 60 * 60)));
        MoneyPerHour = (long) Math.floor(((double) totalMoney) / ((double) uptime / (1000 * 60 * 60)));
        ShardsPerHour = (long) Math.floor(((double) totalShards) / ((double) uptime / (1000 * 60 * 60)));


    }


}
