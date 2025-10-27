package util;

import input.types.Departure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Pomocna klasa koja sadrzi nekategorizovane metode za rad sa polascima.
 * @author Nikola Markovic
 */
public final class DepartureUtility {
    /**
     * Metoda koja pretvara naziv autobuske/zeljeznicke stanice u naziv grada u kojem se ona nalazi.
     * @param station Naziv stanice
     * @return Naziv grada u kom se nalazi stanica
     * @author Nikola Markovic
     */
    public static String stationToCity(String station){
        return station.replace("A", "G").replace("Z", "G");
    }

    /**
     * Metoda koja pronalazi vremenski najkraci put izmedju dva cvora.
     * @param departures Lista svih polazaka
     * @param n1 Cvor polaska
     * @param n2 Cvor destinacije
     * @param invert Da li obrnuti rezultat (vratiti vremenski naj<u>duzi</u> umjesto naj<u>kraceg</u>)
     * @return Objekat tipa <code>Departure</code> koji reprezentuje polazak sa vremenski najkracim putem izmedju dva zadata cvora
     * @author Nikola Markovic
     */
    public static Departure getQuickestDepartureBetweenTwoNodes(List<Departure> departures, String n1, String n2, boolean invert){
        List<Departure> sorted = new ArrayList<>();

        String n1a = new StringBuilder(n1).delete(0, 1).toString();
        String n2a = new StringBuilder(n2).delete(0, 1).toString();

        departures.stream()
                .filter(d -> d.getFrom().endsWith(n1a))
                .filter(d -> d.getTo().endsWith(n2a))
                .sorted(Comparator.comparingInt(Departure::getDuration))
                .forEach(sorted::add);

        if (sorted.isEmpty())
            return null;

        return invert ? sorted.getLast() : sorted.getFirst();
    }

    /**
     * Metoda koja pronalazi najjeftiniji put izmedju dva cvora.
     * @param departures Lista svih polazaka
     * @param n1 Cvor polaska
     * @param n2 Cvor destinacije
     * @param invert Da li obrnuti rezultat (vratiti naj<u>skuplji</u> umjesto naj<u>jeftinijeg</u> puta)
     * @return Objekat tipa <code>Departure</code> koji reprezentuje polazak sa najjeftinijim putem izmedju dva zadata cvora
     * @author Nikola Markovic
     * @deprecated
     */
    public static Departure getCheapestDepartureBetweenTwoNodes(List<Departure> departures, String n1, String n2, boolean invert){
        List<Departure> sorted = new ArrayList<>();

        String n1a = new StringBuilder(n1).delete(0, 1).toString();
        String n2a = new StringBuilder(n2).delete(0, 1).toString();

        departures.stream()
                .filter(d -> d.getFrom().endsWith(n1a))
                .filter(d -> d.getTo().endsWith(n2a))
                .sorted(Comparator.comparingInt(Departure::getPrice))
                .forEach(sorted::add);

        if (sorted.isEmpty())
            return null;

        return invert ? sorted.getLast() : sorted.getFirst();
    }
}
