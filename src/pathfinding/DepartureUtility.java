package pathfinding;

import input.types.Departure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class DepartureUtility {
    public static String stationToCity(String station){
        return station.replace("A", "G").replace("Z", "G");
    }

    public static Departure getQuickestDeparture(List<Departure> departures){
        List<Departure> sorted = new ArrayList<>();

        departures.stream()
                .sorted(Comparator.comparingInt(Departure::getDuration))
                .forEach(sorted::add);

        return sorted.getFirst();
    }

    public static Departure getQuickestDepartureBetweenTwoNodes(List<Departure> departures, String n1, String n2){
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

        return sorted.getFirst();
    }

    // TODO: vjv premjestiti negdje drugo
    public static String[][] generateDetailedPathTableData(String pathString){
        // A_0_0 -> A_0_1 -> Z_0_2 -> A_0_3 -> A_0_4 -> A_1_4 -> A_2_4 -> G_2_5

        //{{"1", "G_0_0", "Autobus", "G_0_1"},
        // {"2", "G_0_1", "Autobus", "G_0_2"},
        // {"3", "G_0_2", "Autobus", "G_0_3"},
        // {"4", "G_0_3", "Autobus", "G_0_4"},
        // {"5", "G_0_4", "Autobus", "G_1_4"},
        // {"6", "G_1_4", "Autobus", "G_2_4"},
        // {"7", "G_2_4", "Autobus", "G_2_5"}};

        String[] nodes = pathString.split(" -> ");
        String[][] res = new String[nodes.length - 1][4];

        for (int j = 0; j < nodes.length - 2; j++){
            res[j][0] = String.valueOf(j + 1);
            res[j][1] = nodes[j].replace("A", "G").replace("Z", "G");
            res[j][2] = nodes[j].startsWith("A") ? "Autobus" : "Voz";
            res[j][3] = nodes[j + 1].replace("A", "G").replace("Z", "G");
        }

        res[nodes.length - 2][0] = String.valueOf(nodes.length - 1);
        res[nodes.length - 2][1] = nodes[nodes.length - 2].replace("A", "G").replace("Z", "G");
        res[nodes.length - 2][2] = nodes[nodes.length - 2].startsWith("A") ? "Autobus" : "Voz";
        res[nodes.length - 2][3] = nodes[nodes.length - 1].replace("A", "G").replace("Z", "G");

        return res;
    }
}
