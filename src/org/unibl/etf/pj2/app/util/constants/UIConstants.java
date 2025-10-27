package org.unibl.etf.pj2.app.util.constants;

import java.awt.*;

/**
 * Data-holder klasa statickih apsolutnih konstanti koje se koriste u svrhe prvobitnog renderovanja UI elemenata.
 * Sadrzi razne dimenzije i tehnicke konstante UI-ja sakrivene od prvog korisnickog utiska.
 * @author Nikola Markovic
 */
public final class UIConstants {
    private UIConstants() {} // prevencija instanciranja

    public static final int WINDOW_WIDTH_MAX = 1280;
    public static final int BOTTOM_PANEL_HEIGHT = 160;
    public static final int TITLED_PANEL_HEIGHT = 80;
    public static final int MAIN_PANEL_HEIGHT = 700;

    public static final int DETAILED_PATH_TABLE_ID_COLUMN_WIDTH = 75;
    public static final int DETAILED_PATH_TABLE_DEPARTURE_COLUMN_WIDTH = 220;
    public static final int DETAILED_PATH_TABLE_VEHICLE_COLUMN_WIDTH = 85;
    public static final int DETAILED_PATH_TABLE_ARRIVAL_COLUMN_WIDTH = 220;

    public static final int SEARCH_RESULT_PANEL_RIGHT_SUBPANEL_SIZE = 300;

    public static final Dimension GENERAL_COMBO_BOX_DIMENSION = new Dimension(200, 30);
    public static final Dimension BUY_PANEL_DIMENSION = new Dimension(1180, 50);
    public static final Dimension PATH_TABLE_SCROLL_PANE_DIMENSION = new Dimension(1180, 110);
    public static final Dimension DETAILED_PATH_TABLE_SCROLL_PANE_DIMENSION = new Dimension(600, 300);

    public static final int GENERAL_LABEL_FONT_SIZE = 14;
    public static String PRIMARY_FONT_NAME = "Roboto";
}
