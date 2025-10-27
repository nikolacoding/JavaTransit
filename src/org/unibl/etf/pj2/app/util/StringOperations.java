package org.unibl.etf.pj2.app.util;

/**
 * Klasa koja sadrzi metode za slozene operacije sa Stringovima.
 * @author Nikola Markovic
 */
public final class StringOperations {
    /**
     * Metoda koja vraca broj pojavljivanja znaka <code>c</code> u stringu <code>s</code>.
     * @param s String
     * @param c Znak za brojanje
     * @return Broj pojavljivanja znaka <code>c</code> u stringu <code>s</code>.
     * @author Nikola Markovic
     */
    public static int countChars(String s, char c){
        final int[] total = {0};

        s.chars().forEach(ch -> {
            if (ch == c)
                total[0]++;
        });

        return total[0];
    }

    /**
     * Metoda koja pretvara niz Stringova sadrzan u Stringu u standardan niz Stringova. Na primjer, String <code>"["jedan", "dva"]"</code>
     * zadan kao <code>arr</code> se parsira i vraca kao <code>String[2] {"jedan", "dva"}</code>.
     * @param arr Niz Stringova sadrzan u Stringu
     * @return Standardan niz Stringova koji nije sadrzan u Stringu
     * @author Nikola Markovic
     */
    public static String[] parseStringArray(String arr){
        arr = arr.substring(0, arr.length() - 1);
        arr = arr.replace("[", "");
        arr = arr.replace("]", "");
        arr = arr.replace("\"", "");

        return arr.split(",");
    }
}
