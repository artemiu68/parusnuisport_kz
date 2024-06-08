package clubdev.autorestart.utils;

import java.util.concurrent.TimeUnit;

public class TimeFormatter {
    
    public static String format(Long millis) {

        if (millis <= 60000L) {
            return getSeconds(millis).trim();
        }
        if (millis <= 3600000L) {
            return getMinutes(millis).trim();
        }
        if (millis <= 86400000L) {
            return getHours(millis).trim();
        }
        if (millis <= 86400000L * 365) {
            return getDays(millis).trim();
        }
        return "Больше года";
    }
    
    private static String getDays(Long millis) {
        String hms = String.format("%2d дней, %2d часов, %2d минут и %2d секунд", TimeUnit.MILLISECONDS.toDays(millis), TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millis)), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return hms;
    }
    
    private static String getHours(Long millis) {
        String hms = String.format("%2d часов, %2d минут и %2d секунд", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return hms;
    }
    
    private static String getMinutes(Long millis) {
        String hms = String.format("%2d минут и %2d секунд", TimeUnit.MILLISECONDS.toMinutes(millis), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return hms;
    }
    
    private static String getSeconds(Long millis) {
        return String.valueOf(millis / 1000L + " секунд");
    }
}