package util;

import java.util.Arrays;

public final class Time {
    public static String minutesToStringTime(double minutes){
        int mins = (int)minutes % 60;
        int hrs = ((int)minutes / 60) % 24;
        int days = (int)minutes / 60 / 24;

        String minsStr = (mins + "m");
        String hrsStr = (hrs != 0) ? (hrs + "h ") : "";
        String daysStr = (days != 0) ? (days + "d ") : "";

        return daysStr + hrsStr + minsStr;
    }

    public static String minutesToStringTime24(int minutes){
        int mins = minutes % 60;
        int hrs = (minutes / 60) % 24;

        final String minsString = (mins < 10) ? ("0" + mins) : String.valueOf(mins);
        final String hoursString = (hrs < 10) ? ("0" + hrs) : String.valueOf(hrs);

        return hoursString + ":" + minsString;
    }


    public static String addStringTime24(String t1, String t2){
        int m1 = stringTime24ToMinutes(t1);
        int m2 = stringTime24ToMinutes(t2);

        int m = m1 + m2;

        return minutesToStringTime24(m);
    }

    public static int stringTimeToMinutes(String time){
        int[] res = {0};
        String[] args = time.split(" ");

        Arrays.stream(args).forEach(arg -> {
            if (arg.contains("d")){
                String r = arg.replace("d", "");
                int days = Integer.parseInt(r) * 24 * 60;

                res[0] += days;
            }

            if (arg.contains("h")){
                String r = arg.replace("h", "");
                int hours = Integer.parseInt(r) * 60;

                res[0] += hours;
            }

            if (arg.contains("m")){
                String r = arg.replace("m", "");
                int mins = Integer.parseInt(r);

                res[0] += mins;
            }
        });

        return res[0];
    }

    public static int stringTime24ToMinutes(String time){
        int res = 0;
        String[] args = time.replace(" ", "").split(":");
        res += Integer.parseInt(args[0]) * 60;
        res += Integer.parseInt(args[1]);

        return res;
    }
}
