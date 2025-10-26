package ui.tertiary.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class DetailedPathTable extends JTable {
    public DetailedPathTable(String[][] data, String[] cols) {
        super(data, cols);

        this.getColumn("Voznja #").setPreferredWidth(60);
        this.getColumn("Polazni grad").setPreferredWidth(230);
        this.getColumn("Vozilo").setPreferredWidth(80);
        this.getColumn("Destinacija").setPreferredWidth(230);

        this.getTableHeader().setReorderingAllowed(false);
        this.setFont(new Font("Roboto", Font.PLAIN, 12));
        this.setRowSelectionAllowed(false);
        this.setColumnSelectionAllowed(false);

        var centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.getColumn("Voznja #").setCellRenderer(centerRenderer);
    }
}
