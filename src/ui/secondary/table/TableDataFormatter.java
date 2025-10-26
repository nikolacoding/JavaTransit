package ui.secondary.table;

import input.InputData;
import input.types.Departure;
import pathfinding.DepartureUtility;
import pathfinding.YenKShortestPaths;
import util.Time;

import java.util.ArrayList;
import java.util.List;

public final class TableDataFormatter {
    private static String lastCriteria = null;
    public static String[] GetTableCols(String criteria){
        lastCriteria = criteria;
        return new String[]{"#", "Putanja", criteria};
    }

    public static String[][] GetTableData(ArrayList<YenKShortestPaths.PathObject> paths){
        String[][] res = new String[paths.size()][3];

        for (int i = 0; i < paths.size(); i++) {
            res[i][0] = String.valueOf(i + 1);
            res[i][1] = getFormattedPath(paths.get(i));

            final double rawValue = paths.get(i).getTotalCost();
            switch (lastCriteria){
                case "Cijena"           -> res[i][2] = String.valueOf(rawValue);
                case "Trajanje"         -> res[i][2] = Time.minutesToStringTime(rawValue);
                case "Broj presjedanja" -> res[i][2] = String.valueOf((int)rawValue - 1);
            }
        }

        return res;
    }

    private static String getFormattedPath(YenKShortestPaths.PathObject pathObject){
        List<String> nodes = pathObject.getNodes();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < nodes.size() - 1; i++){
            final String current = nodes.get(i);
            final String next = nodes.get(i + 1);
            final List<Departure> departureList = InputData.getInstance().getDepartureList();

            String nextNode = DepartureUtility.getQuickestDepartureBetweenTwoNodes(departureList, current, next, false).getFrom() + " -> ";

            res.append(nextNode);
        }

        res.delete(res.length() - 3, res.length());
        res.append(" -> ").append(nodes.getLast());

        return res.toString();
    }
}
