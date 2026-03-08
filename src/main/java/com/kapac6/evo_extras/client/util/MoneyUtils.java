package com.kapac6.evo_extras.client.util;

import java.util.Locale;

public class MoneyUtils {
    public static long convertFrom(String msg) {
        char type = msg.charAt(msg.length() - 1);
        double startmoney = Double.parseDouble(msg.substring(0, msg.length() - 1));
        long money = switch (type) {
            case 'K' -> Math.round(startmoney * 1000);
            case 'M' -> Math.round(startmoney * 1000000);
            case 'B' -> Math.round(startmoney * 1000000000);
            case 'T' -> Math.round(startmoney * 1000000000000L);
            default -> Math.round(startmoney);
        };

        return money;
    }

    public static String convertTo(long msg) {
        long length = length(msg);
        String money;

        if (length >= 1 && length <= 3) {
            money = String.format( "%s", msg);
        } else if (length >= 4 && length <= 6) {
            money = String.format( "%sK", (double)Math.round((float) msg / 1000 * 10)/10);
        } else if (length >= 7 && length <= 9) {
            money = String.format( "%sM", (double)Math.round((float) msg / 1000000* 10)/10);
        } else if (length >= 10 && length <= 12) {
            money = String.format( "%sB", (double)Math.round((float) msg / 1000000000L* 10)/10);
        } else if (length >= 13 && length <= 15) {
            money = String.format( "%sT", (double)Math.round((float) msg / 1000000000000L* 10)/10);
        } else if (length >= 16 && length <= 18) {
            money = String.format( "%sQ", (double)Math.round((float) msg / 1000000000000000L* 10)/10);
        } else {
            money = String.format( "%s", msg);
        }

        return money;
    }

    public static long length(long value) {
        if (value == 0L) {
            return 1;
        }
        return (long) (Math.log10(Math.abs((double) value))) + 1;
    }
}
