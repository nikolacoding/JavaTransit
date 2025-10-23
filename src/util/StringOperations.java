package util;

public final class StringOperations {
    public static int CountChars(String s, char c){
        final int[] total = {0};

        s.chars().forEach(ch -> {
            if (ch == c)
                total[0]++;
        });

        return total[0];
    }

    public static String[] ParseStringArray(String sa){
        sa = sa.substring(0, sa.length() - 1);
        sa = sa.replace("[", "");
        sa = sa.replace("]", "");
        sa = sa.replace("\"", "");

        return sa.split(",");
    }
}
