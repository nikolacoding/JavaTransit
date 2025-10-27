package org.unibl.etf.pj2.app.util.constants;

/**
 * Data-holder klasa statickih apsolutnih konstanti koje se koriste u svrhe renderovanja UI elemenata sa tekstualnim sadrzajem kao
 * i opste provjere stanja nekih UI objekata u odredjenim trenucima izvrsavanja.
 * @author Nikola Markovic
 */
public final class TextConstants {
    private TextConstants() {} // prevencija instanciranja

    public static final String OPTIMIZATION_CRITERIA_COMBO_BOX_BY_PRICE = "Najnizoj cijeni";
    public static final String OPTIMIZATION_CRITERIA_COMBO_BOX_BY_DURATION = "Najkracem vremenu puta";
    public static final String OPTIMIZATION_CRITERIA_COMBO_BOX_BY_NODE_COUNT = "Najmanjem broju presjedanja";
    public static final String[] OPTIMIZATION_CRITERIA = { OPTIMIZATION_CRITERIA_COMBO_BOX_BY_PRICE, OPTIMIZATION_CRITERIA_COMBO_BOX_BY_DURATION, OPTIMIZATION_CRITERIA_COMBO_BOX_BY_NODE_COUNT };

    public static final String DEFAULT_DEPARTURE_TIME = "08:00";

    public static final String MAIN_WINDOW_TITLE = "JavaTransit";
    public static final String PATHS_WINDOW_TITLE = "Rezultat pretrage";
    public static final String DETAILED_PATHS_WINDOW_TITLE = "Kupovina karte";

    public static final String CRITERIA_PRICE_DISPLAY_NAME = "Cijena";
    public static final String CRITERIA_DURATION_DISPLAY_NAME = "Trajanje";
    public static final String CRITERIA_NODES_DISPLAY_NAME = "Broj presjedanja";

    public static final String SELECT_PATH_LABEL_TEXT = "Izaberi put za kupovinu karte";
    public static final String BUY_TICKET_BUTTON_TEXT = "Kupi kartu";

    public static final String[] PATH_TABLE_COLUMN_NAMES = {"#", "Putanja"};
    public static final String[] DETAILED_PATH_TABLE_COLUMN_NAMES = {"Voznja #", "Polazni grad", "Vozilo", "Destinacija"};

    public static final String DETAILED_PATH_TABLE_DEPARTURE_TIME_PLACEHOLDER = "[departureTime]";
    public static final String DETAILED_PATH_TABLE_ARRIVAL_TIME_PLACEHOLDER = "[arrivalTime]";
    public static final String DETAILED_PATH_TABLE_PRICE_PLACEHOLDER = "[price]";

    public static final String DETAILED_PATH_TABLE_DEPARTURE_TIME_PREFIX = "     Polazak: ";
    public static final String DETAILED_PATH_TABLE_ARRIVAL_TIME_PREFIX = "     Dolazak: ";
    public static final String DETAILED_PATH_TABLE_PRICE_PREFIX = "     Cijena: ";
    public static final String CURRENCY = "¤";

    public static final String GRAPH_GENERAL_LABEL_TEXT = "Grad";

    public static final String OPTIONS_GENERAL_LABEL_TEXT = "Opcije";
    public static final String DEPARTURE_COMBO_BOX_LABEL_TEXT = "Polazak  ";
    public static final String ARRIVAL_COMBO_BOX_LABEL_TEXT = "Destinacija  ";
    public static final String CRITERIA_COMBO_BOX_LABEL_TEXT = "Optimizuj po  ";
    public static final String FIND_BUTTON_TEXT = "Pronadji";
    public static final String EXTRA_BUTTON_TEXT = "Detaljno";

    public static final String CONFIRM_PURCHASE_BUTTON_TEXT = "Potvrdi kupovinu";

    public static final String RESULT_GENERAL_LABEL_TEXT = "Rezultat";
    public static final String RESULT_LABEL_NO_PATHS = "Ne postoji put izmedju dva zadata grada.";
    public static final String RESULT_LABEL_SAME_NODES = "Izabrana su dva ista grada.";

    public static final String RECEIPT_SUCCESS_TEXT = "Racun je uspjesno kupljen i sacuvan u ";
    public static final String SERIALIZATION_ERROR_LABEL_TEXT = "Greska pri serijalizaciji.";
    public static final String SERIALIZATION_LABEL_LOG_TEXT = "Greska pri serijalizaciji (PrintWriter neuspjesno instanciran).";
}
