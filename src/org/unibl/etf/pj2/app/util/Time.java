package org.unibl.etf.pj2.app.util;

import java.util.Arrays;

/**
 * Klasa koja sadrzi metode za rukovanje razlicitim vremenskim formatima. U svim metodama se minut uzima kao osnovna jedinica vremena.
 * @author Nikola Markovic
 */
public final class Time {
    /**
     * Metoda koja uzima broj minuta i pretvara u formatirano vrijeme.
     * Na primjer: ako je <code>minutes</code> argument zadan kao 5312, povratni rezultat je String objekat <code>"3d 16h 32m"</code>.
     * Obrnuta operacija se vrsi koristeci <code>stringTimeToMinutes(String time)</code>
     *
     * @param minutes Broj minuta
     * @return Formatirano vrijeme (u danima, satima i minutama)
     * @author Nikola Markovic
     */
    public static String minutesToStringTime(double minutes){
        int mins = (int)minutes % 60;
        int hrs = ((int)minutes / 60) % 24;
        int days = (int)minutes / 60 / 24;

        String minsStr = (mins + "m");
        String hrsStr = (hrs != 0) ? (hrs + "h ") : "";
        String daysStr = (days != 0) ? (days + "d ") : "";

        return daysStr + hrsStr + minsStr;
    }

    /**
     * Metoda koja uzima broj minuta i pretvara u formatirano vrijeme po 24-satnom formatu.
     * Na primjer: ako je <code>minutes</code> argument zadan kao 442, povratni rezultat je String objekat <code>07:22</code>.
     * Formatiranje dodaje vodecu nulu ukoliko je broj sati/minuta jednocifren.
     * Obrnuta operacija se vrsi koristeci <code>stringTime24ToMinutes(String time)</code>
     *
     * @param minutes Broj minuta
     * @return Formatirano vrijeme (u 0-23h formatu)
     * @author Nikola Markovic
     */
    public static String minutesToStringTime24(int minutes){
        int mins = minutes % 60;
        int hrs = (minutes / 60) % 24;

        final String minsString = (mins < 10) ? ("0" + mins) : String.valueOf(mins);
        final String hoursString = (hrs < 10) ? ("0" + hrs) : String.valueOf(hrs);

        return hoursString + ":" + minsString;
    }


    /**
     * Metoda koja uzima dva String objekta koja reprezentuju 24-casovne satnice i sabira ih.
     * Na primjer: ako je <code>t1</code> argument zadan kao 7:14, a <code>t2</code> kao 2:46, povratni rezultat je String objekat <code>10:00</code>.
     * Dodatni primjer: ako je <code>t1</code> jednak 16:25, a <code>t2</code> jednak 8:15, povratni rezultat je 00:40.
     *
     * @param t1 Prvi vremenski sabirak
     * @param t2 Drugi vremenski sabirak
     * @return Zbir dva 24-casovna vremena u obliku rezultantnog 24-casovnog vremena
     * @author Nikola Markovic
     */
    public static String addStringTime24(String t1, String t2){
        int m1 = stringTime24ToMinutes(t1);
        int m2 = stringTime24ToMinutes(t2);

        int m = m1 + m2;

        return minutesToStringTime24(m);
    }

    /**
     * Metoda koja pretvara String reprezentaciju vremena u minute.
     * Obrnuta operacija se vrsi koristeci <code>minutesToStringTime(double minutes)</code>
     *
     * @param time String reprezentacija vremena (npr. 2d 4h 51m).
     * @return Argumentom-navedeno vrijeme pretvoreno u minute
     * @author Nikola Markovic
     */
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

    /**
     * Metoda koja pretvara String reprezentaciju 24-casovnog vremena u minute.
     * Obrnuta operacija se vrsi koristeci <code>minutesToStringTime24(int minutes)</code>
     *
     * @param time String reprezentacija 24-casovnog vremena (npr. 14:15)
     * @return Argumentom-navedeno 24-casovno vrijeme pretvoreno u minute
     * @author Nikola Markovic
     */
    public static int stringTime24ToMinutes(String time){
        int res = 0;
        String[] args = time.replace(" ", "").split(":");
        res += Integer.parseInt(args[0]) * 60;
        res += Integer.parseInt(args[1]);

        return res;
    }
}
