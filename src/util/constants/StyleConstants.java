package util.constants;

import java.awt.*;

/**
 * Data-holder klasa statickih apsolutnih konstanti koje se koriste za stilizovanje UI elemenata koji podrzavaju CSS-styling.
 * @author Nikola Markovic
 */
public final class StyleConstants {
    private StyleConstants() {} // prevencija instanciranja

    public static final String GRAPH_STYLESHEET = "graph { fill-color: rgb(33, 32, 28); }";

    public static final String NODE_STYLE_DEFAULT = "fill-color:  gray; size: 10px;";
    public static final String NODE_STYLE_SELECTED_A = "fill-color: red; size: 10px;";
    public static final String NODE_STYLE_SELECTED_B = "fill-color: blue; size: 10px;";
    public static final String NODE_STYLE_SELECTED_C = "fill-color: yellow; size: 10px;";

    public static final String EDGE_STYLE_DEFAULT = "fill-color: gray; size: 1px;";
    public static final String EDGE_STYLE_SELECTED = "fill-color: yellow; size: 5px; ";

    public static final Color GRAPH_GENERAL_LABEL_BACKGROUND_COLOR = new Color(33, 32, 28);
    public static final Color GRAPH_GENERAL_LABEL_COLOR = Color.white;

}
