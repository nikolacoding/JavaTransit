package ui.shared;

import util.constants.UIConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Arrays;

public class GeneralTable extends JTable {
    public GeneralTable(String[][] data, String[] cols){
        super(data, cols);

        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setFont(new Font(UIConstants.PRIMARY_FONT_NAME, Font.BOLD, 14));
        this.setFont(new Font(UIConstants.PRIMARY_FONT_NAME, Font.PLAIN, 12));

        var centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        Arrays.stream(cols).forEach(col -> {
            this.getColumn(col).setCellRenderer(centerRenderer);
        });
    }

    @Override
    public boolean isCellEditable(int r, int c){
        return false;
    }
}
