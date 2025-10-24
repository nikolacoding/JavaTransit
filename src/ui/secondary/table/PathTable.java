package ui.secondary.table;

import javax.swing.*;
import java.awt.*;

public final class PathTable extends JTable {
    public PathTable(String[][] data, String[] cols){
        super(data, cols);

        this.getColumn("Putanja").setPreferredWidth(550);
    }
}
