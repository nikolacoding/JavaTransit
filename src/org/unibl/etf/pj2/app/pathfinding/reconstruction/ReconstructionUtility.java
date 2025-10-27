package org.unibl.etf.pj2.app.pathfinding.reconstruction;

import org.unibl.etf.pj2.app.state.InputData;
import org.unibl.etf.pj2.app.util.DepartureUtility;
import org.unibl.etf.pj2.app.util.constants.GeneralConstants;

/**
 * Pomocna klasa za dodatne operacije pri rekonstrukciji putanje.
 */
public class ReconstructionUtility {
    /**
     * Metoda koja generise detaljnu tabelu svih voznji od prvog do zadnjeg cvora.
     * @param pathString String putanje gdje je svaki cvor delimitiran sa " -> ".
     * @return Dvodimenzionalni niz koji se dalje upotrebljuje za generisanje tabele kao njen data parametar.
     * @author Nikola Markovic
     */
    public static String[][] generateDetailedPathTableData(String pathString){
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

    /**
     * Metoda za pronalazak najkraceg moguceg puta izmedju prvog i posljednjeg cvora neke putanje.
     * @param pathString String putanje gdje je svaki cvor delimitiran sa " -> ".
     * @return Najkrace vrijeme za prelazak date putanje
     * @author Nikola Markovic
     */
    public static int getMinPathTime(String pathString){
        int res = 0;
        String[] nodes = pathString.split(" -> ");

        for (int i = 0; i < nodes.length - 1; i++) {
            var departure = DepartureUtility.getQuickestDepartureBetweenTwoNodes(InputData.getInstance().getDepartureList(), nodes[i], nodes[i + 1], false);
            res += (departure == null) ? 0 : departure.getDuration();
        }

        return res;
    }

    /**
     * Metoda za pronalazak najjeftinijeg moguceg puta izmedju prvog i posljednjeg cvora neke putanje.
     * @param pathString String putanje gdje je svaki cvor delimitiran sa " -> ".
     * @param invert Vratiti najskuplju umjesto najjeftinije
     * @return Najmanja cijena za prelazak date putanje
     * @author Nikola Markovic
     */
    public static int getMinPathPrice(String pathString, boolean invert){
        int res = 0;
        String[] nodes = pathString.split(" -> ");

        for (int i = 0; i < nodes.length - 1; i++) {
            var departure = DepartureUtility.getQuickestDepartureBetweenTwoNodes(InputData.getInstance().getDepartureList(), nodes[i], nodes[i + 1], invert);
            res += (departure == null) ? 0 : departure.getDuration() * GeneralConstants.YEN_DENOMINATOR;
        }

        return res;
    }
}
