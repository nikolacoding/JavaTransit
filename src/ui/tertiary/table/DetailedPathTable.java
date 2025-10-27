package ui.tertiary.table;

import ui.shared.GeneralTable;
import util.constants.TextConstants;
import util.constants.UIConstants;

/**
 * Wrapper klasa za wrapper klasu GeneralTable koja pre-generise tabelu za detaljan prikaz puta koristeci
 * superklasu.
 * @author Nikola Markovic
 */
public class DetailedPathTable extends GeneralTable {
    /**
     * Konstruktor, po pozivu, generise tabelu prosljedjivanjem <code>data</code> i <code>cols</code>
     * u konstruktor superklase. Dodatno, podesava sirine odredjenih kolona kako bi prvi prikaz korisniku
     * bio sto optimalniji i kako u opstem slucaju ne bi zahtijevao korisnika da rucno mijenja velicinu
     * nekih kolona da iscita sve podatke.
     *
     * @param data Dvodimenzionalni niz podataka tabele
     * @param cols Jednodimenzionalni niz naziva headera tabele
     * @author Nikola Markovic
     */
    public DetailedPathTable(String[][] data, String[] cols) {
        super(data, cols);

        this.getColumn(TextConstants.DETAILED_PATH_TABLE_COLUMN_NAMES[0]).setPreferredWidth(UIConstants.DETAILED_PATH_TABLE_ID_COLUMN_WIDTH);
        this.getColumn(TextConstants.DETAILED_PATH_TABLE_COLUMN_NAMES[1]).setPreferredWidth(UIConstants.DETAILED_PATH_TABLE_DEPARTURE_COLUMN_WIDTH);
        this.getColumn(TextConstants.DETAILED_PATH_TABLE_COLUMN_NAMES[2]).setPreferredWidth(UIConstants.DETAILED_PATH_TABLE_VEHICLE_COLUMN_WIDTH);
        this.getColumn(TextConstants.DETAILED_PATH_TABLE_COLUMN_NAMES[3]).setPreferredWidth(UIConstants.DETAILED_PATH_TABLE_ARRIVAL_COLUMN_WIDTH);

        this.setRowSelectionAllowed(false);
        this.setColumnSelectionAllowed(false);
    }
}
