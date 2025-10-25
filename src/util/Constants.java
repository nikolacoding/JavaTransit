package util;

public final class Constants {
    private Constants () {} // prevencija instanciranja

    public static final int WINDOW_WIDTH_MAX = 1280;
    public static final int BOTTOM_PANEL_HEIGHT = 160;
    public static final int TITLED_PANEL_HEIGHT = 80;
    public static final int MAIN_PANEL_HEIGHT = 700;

    public static final String[] OPTIMIZATION_CRITERIA = {"Najnizoj cijeni", "Najkracem vremenu puta", "Najmanjem broju presjedanja"};

    public static final String GRAPH_BACKGROUND_COLOR_CSS = "rgb(33, 32, 28)";
    public static final String DEFAULT_NODE_COLOR_CSS = "gray";
    public static final String DEFAULT_EDGE_COLOR_CSS = "gray";
}
