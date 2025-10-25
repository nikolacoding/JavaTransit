package util;

public final class Time {
    public static String FormatMinutes(double minutes){
        int mins = (int)minutes % 60;
        int hrs = ((int)minutes / 60) % 24;
        int days = (int)minutes / 60 / 24;

        String minsStr = (mins + "m");
        String hrsStr = (hrs != 0) ? (hrs + "h ") : "";
        String daysStr = (days != 0) ? (days + "d ") : "";

        return daysStr + hrsStr + minsStr;
    }
}
