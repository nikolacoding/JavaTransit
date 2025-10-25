package util;

public final class Time {
    public static String FormatMinutes(int minutes){
        String mins = String.valueOf(minutes % 60);
        String hrs = String.valueOf((minutes / 60));

        return hrs + "h " + mins + " min";
    }

    public static String FormatMinutes(double minutes){

        String mins = String.valueOf((int)minutes % 60);
        String hrs = String.valueOf(((int)minutes / 60));

        return hrs + "h " + mins + " min";
    }
}
