package ui.secondary.table;

import input.StateManager;
import ui.tertiary.DetailedPathWindow;
import ui.tertiary.table.DetailedPathTable;
import ui.tertiary.table.DetailedPathTableScrollPane;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static pathfinding.DepartureUtility.generateDetailedPathTableData;

public final class PathTable extends JTable {
    public PathTable(String[][] data, String[] cols){
        super(data, cols);

        this.getColumn("#").setPreferredWidth(5);
        this.getColumn("Putanja").setPreferredWidth(1000);
        this.getTableHeader().setReorderingAllowed(false);
        this.setFont(new Font("Roboto", Font.PLAIN, 12));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = PathTable.this.getSelectedRow();

                if (selectedRow != -1){
                    final StateManager smInstance = StateManager.getInstance();
                    String num = PathTable.this.getValueAt(selectedRow, 0).toString();
                    String path = PathTable.this.getValueAt(selectedRow, 1).toString();
                    String value = PathTable.this.getValueAt(selectedRow, 2).toString();

                    smInstance.setSelectedRowNum(num);
                    smInstance.setSelectedRowPath(path);
                    smInstance.setSelectedRowValue(value);

                    smInstance.getBuyLabel().setText("Izabrano: [" + num + "] - Putanja:  " + path + "; " + PathTable.this.getColumnName(2) + ": " + value);
                    smInstance.getBuyButton().setVisible(true);

                    // TODO: generisati tabelu za tercijarni prozor
                    String[][] dptData = new String[][] {{"a", "b", "c", "d"}};
                    String[] dptCols = new String[]{"Voznja #", "Polazni grad", "Vozilo", "Destinacija"};

                    dptData = generateDetailedPathTableData(path);

                    final DetailedPathTable detailedPathTable = new DetailedPathTable(dptData, dptCols);
                    final DetailedPathTableScrollPane detailedPathTableScrollPane = new DetailedPathTableScrollPane(detailedPathTable);
                    DetailedPathWindow.scrollPane = detailedPathTableScrollPane;
                    DetailedPathWindow.startTimeLabel.setText("     Polazak: 16:25");
                    DetailedPathWindow.endTimeLabel.setText("     Dolazak: 21:00");
                    DetailedPathWindow.priceLabel.setText("     Cijena: 1250KM");
                }
            }
        });
    }

    @Override
    public boolean isCellEditable(int r, int c){
        return false;
    }
}
