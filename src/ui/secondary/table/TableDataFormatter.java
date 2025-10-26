package ui.secondary.table;

import pathfinding.yen.types.PathObject;
import state.InputData;
import input.types.Departure;
import util.DepartureUtility;
import util.Time;
import util.constants.TextConstants;

import java.util.ArrayList;
import java.util.List;

public final class TableDataFormatter {
    private static String lastCriteria = null;
    public static String[] getTableCols(String criteria){
        lastCriteria = criteria;
        return new String[]{ TextConstants.PATH_TABLE_COLUMN_NAMES[0], TextConstants.PATH_TABLE_COLUMN_NAMES[1], criteria };
    }

    public static String[][] getTableData(ArrayList<PathObject> paths){
        String[][] res = new String[paths.size()][3];

        for (int i = 0; i < paths.size(); i++) {
            res[i][0] = String.valueOf(i + 1);
            res[i][1] = getFormattedPath(paths.get(i));

            final double rawValue = paths.get(i).getTotalCost();
            switch (lastCriteria){
                case TextConstants.CRITERIA_PRICE_DISPLAY_NAME        -> res[i][2] = String.valueOf(rawValue);
                case TextConstants.CRITERIA_DURATION_DISPLAY_NAME     -> res[i][2] = Time.minutesToStringTime(rawValue);
                case TextConstants.CRITERIA_NODES_DISPLAY_NAME        -> res[i][2] = String.valueOf((int)rawValue - 1);
            }
        }

        return res;
    }

    private static String getFormattedPath(PathObject pathObject){
        List<String> nodes = pathObject.getNodes();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < nodes.size() - 1; i++){
            final String current = nodes.get(i);
            final String next = nodes.get(i + 1);
            final List<Departure> departureList = InputData.getInstance().getDepartureList();

            var quickestDep = DepartureUtility.getQuickestDepartureBetweenTwoNodes(departureList, current, next, false).getFrom();
            String nextNode = quickestDep + " -> ";

            res.append(nextNode);
        }

        res.delete(res.length() - 3, res.length());
        res.append(" -> ").append(nodes.getLast());

        return res.toString();
    }
}
