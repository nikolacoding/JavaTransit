package util;

import state.InputData;
import input.types.Departure;
import util.constants.GeneralConstants;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class DepartureUtility {
    public static String stationToCity(String station){
        return station.replace("A", "G").replace("Z", "G");
    }

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
