package ui.secondary.table;

import pathfinding.yen.types.PathObject;
import state.InputData;
import input.types.Departure;
import util.DepartureUtility;
import util.Time;
import util.constants.TextConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa koja sadrzi metode za formatiranje podataka u tabeli optimalnih putanja.
 * @author Nikola Markovic
 */
public final class TableDataFormatter {
    private static String lastCriteria = null;

    /**
     * Metoda koja generise kolone tabele optimalnih putanja u zavisnosti od odabranog kriterijuma.
     *
     * @param criteria Odabrani kriterijum trazenja optimalne putanje
     * @return Jednodimenzionalni niz kolona tabele sa nazivom odabranog kriterijumom kao trecim elementom
     * @author Nikola Markovic
     */
    public static String[] getTableCols(String criteria){
        lastCriteria = criteria;
        return new String[]{ TextConstants.PATH_TABLE_COLUMN_NAMES[0], TextConstants.PATH_TABLE_COLUMN_NAMES[1], criteria };
    }

    /**
     * Metoda koja generise sadrzaj tabele optimalnih putanja u zavisnosti od optimalnih
     * putanja generisanih Jenovim algoritmom.
     *
     * @param paths Lista <code>PathObject</code> objekata koji opisuju optimalne putanje za prikaz
     * @return Podaci za prikaz na tabeli
     * @author Nikola Markovic
     */
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

    /**
     * Metoda koja generise formatiran String prikaz putanje. Na primjer, ako <code>pathObject</code> argument
     * reprezentuje putanju [G_0_1, G_0_2, G_0_3] u svojoj listi cvorova, metoda vraca String sadrzaja
     * <code>"G_0_1 -> G_0_2 -> G_0_3"</code>.
     *
     * @param pathObject Jedan <code>PathObject</code> objekat
     * @return Formatiran String putanje
     * @author Nikola Markovic
     */
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
