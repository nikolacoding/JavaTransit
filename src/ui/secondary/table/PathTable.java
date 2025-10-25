package ui.secondary.table;

import javax.swing.*;
import java.awt.*;

public final class PathTable extends JTable {
    public PathTable(String[][] data, String[] cols){
        super(data, cols);

        this.getColumn("#").setPreferredWidth(5);
        this.getColumn("Putanja").setPreferredWidth(550);
        this.getTableHeader().setReorderingAllowed(false);
        this.setFont(new Font("Roboto", Font.PLAIN, 12));
    }

    @Override
    public boolean isCellEditable(int r, int c){
        return false;
    }
}
