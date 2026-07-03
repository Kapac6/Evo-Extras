package com.kapac6.evo_extras.client.features.clan.clanpoints;

import com.kapac6.evo_extras.client.Evo_extrasClient;
import com.kapac6.evo_extras.client.config.ConfigMining;
import com.kapac6.evo_extras.client.util.MoneyUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ClanPoints {

    // меня мигрень уже пятый день мучает сорян
    // скоро доделаю


//    private static File file = null;
//    private static final ZoneId msk = ZoneId.of("Europe/Moscow");
//    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//    /**
//     * инициализация файла
//     * @param MODID айди мода
//     */
//    public static void init(String MODID) {
//        file = FabricLoader.getInstance().getConfigDir().resolve(MODID + "/points.txt").toFile();
//    }
//
//
//
//    /**
//     * добавить клановые очки в статистику к.о. за сутки
//     * @param points добавляемые клановые очки
//     */
//    public static void add(int points) {
//        List<String> lines = getActualLines();
//        String line = String.format("%s;%s\n", ZonedDateTime.now(msk).format(format), points);
//        lines.add(line);
//        try {
//            Files.write(file.toPath(), lines);
//        } catch(Exception e) {
//            Evo_extrasClient.logger.info("exception at add()");
//        }
//    }
//
//
//    /**
//     * возвращает актуальные линии (которые сегодня по мск добавились)
//     * @return List< String >
//     */
//    private static List<String> getActualLines() {
//        try {
//            if(!file.exists()) return new ArrayList<>();
//
//            String now = ZonedDateTime.now(msk).format(format);
//            return Files.readAllLines(file.toPath()).stream().filter(line ->
//                    line.length() > 10 && line.startsWith(now))
//                    .collect(Collectors.toList());
//
//        } catch (Exception e) {
//            Evo_extrasClient.logger.info("exception at getActualLines()");
//            return new ArrayList<>();
//        }
//    }
//
//    public static int getPoints() {
//        try {
//            return getActualLines().stream()
//                    .mapToInt(line -> Integer.parseInt(line.split(";")[1].trim())).sum();
//        } catch (Exception e) {
//            Evo_extrasClient.logger.info("exception at getPoints()");
//            return 0;
//        }
//    }
//
//
//
//    static Pattern pointPattern = Pattern.compile("\\+ (\\d+) очк.+ клана");
//    public static void getMessage(Text text, boolean overlay) {
//        if(overlay) return;
//        String msg = text.getString();
//
//        if(msg.startsWith("+ ")) {
//            Matcher matcher = pointPattern.matcher(msg);
//            add(Integer.parseInt(matcher.group()));
//        }
//    }

}
