package ui.secondary.table;

import input.InputData;
import input.StateManager;
import input.types.Departure;
import pathfinding.DepartureUtility;
import ui.tertiary.DetailedPathWindow;
import ui.tertiary.table.DetailedPathTable;
import ui.tertiary.table.DetailedPathTableScrollPane;
import util.Time;

import javax.swing.*;
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
                    String[] dptCols = new String[]{"Voznja #", "Polazni grad", "Vozilo", "Destinacija"};
                    String[][] dptData = generateDetailedPathTableData(path);

                    final DetailedPathTable detailedPathTable = new DetailedPathTable(dptData, dptCols);
                    final DetailedPathTableScrollPane detailedPathTableScrollPane = new DetailedPathTableScrollPane(detailedPathTable);

                    String departureTimeString = "";
                    String arrivalTimeString = "ph";
                    String priceString = "";

                    var currentYenRes = smInstance.getCurrentYenResult();
                    var pathObject = currentYenRes.get(selectedRow);

                    final String selectedRowFirstNode = dptData[selectedRow][1];
                    final String selectedRowSecondNode = dptData[selectedRow][3];

                    try {
                        departureTimeString = DepartureUtility.getQuickestDepartureBetweenTwoNodes(
                                InputData.getInstance().getDepartureList(), selectedRowFirstNode, selectedRowSecondNode, false
                        ).getDepartureTime();
                    } catch (NullPointerException npe){
                        departureTimeString = "8:00";
                    }

                    if (smInstance.getCriteriaTableName().equals("Cijena")){
                        priceString = String.valueOf(value);

                        int totalPathTime = DepartureUtility.getMinPathTime(path);
                        arrivalTimeString = Time.addStringTime24(departureTimeString, Time.minutesToStringTime24(totalPathTime));
                    }
                    else if (smInstance.getCriteriaTableName().equals("Trajanje")){
                        priceString = String.valueOf((double)DepartureUtility.getMinPathPrice(path, true));

                        //                                          8:00                  16h 2m
                        arrivalTimeString = Time.addStringTime24(departureTimeString, Time.minutesToStringTime24(Time.stringTimeToMinutes(value)));
                    }
                    else if (smInstance.getCriteriaTableName().equals("Broj presjedanja")){
                        priceString = String.valueOf((double)DepartureUtility.getMinPathPrice(path, true));

                        int totalPathTime = DepartureUtility.getMinPathTime(path);
                        arrivalTimeString = Time.addStringTime24(departureTimeString, Time.minutesToStringTime24(totalPathTime));
                    }

                    final String departureTimeStringFormatted = "     Polazak: " + departureTimeString;
                    final String arrivalTimeStringFormatted = "     Dolazak: " + arrivalTimeString;
                    final String priceStringFormatted = "     Cijena: " + priceString;

                    DetailedPathWindow.scrollPane = detailedPathTableScrollPane;
                    DetailedPathWindow.departureTimeLabel.setText(departureTimeStringFormatted);
                    DetailedPathWindow.arrivalTimeLabel.setText(arrivalTimeStringFormatted);
                    DetailedPathWindow.priceLabel.setText(priceStringFormatted);
                }
            }
        });
    }

    @Override
    public boolean isCellEditable(int r, int c){
        return false;
    }
}
