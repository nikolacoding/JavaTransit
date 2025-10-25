package ui.secondary.table;

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
                case "Trajanje"         -> res[i][2] = Time.FormatMinutes(rawValue);
                case "Broj presjedanja" -> res[i][2] = String.valueOf((int)rawValue - 1);
            }
        }

        return res;
    }

    private static String getFormattedPath(YenKShortestPaths.PathObject pathObject){
        List<String> nodes = pathObject.getNodes();
        StringBuilder res = new StringBuilder();

        nodes.forEach(node -> res.append(node).append(" -> "));

        res.delete(res.length() - 3, res.length());

        return res.toString();
    }
}
