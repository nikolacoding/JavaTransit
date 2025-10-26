package ui.secondary.table;

import pathfinding.reconstruction.ReconstructionUtility;
import state.UIManager;
import state.InputData;
import state.StateManager;
import ui.shared.GeneralTable;
import util.DepartureUtility;
import ui.tertiary.DetailedPathWindow;
import ui.tertiary.table.DetailedPathTable;
import ui.tertiary.table.DetailedPathTableScrollPane;
import util.Time;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import util.constants.TextConstants;

public final class PathTable extends GeneralTable {
    public PathTable(String[][] data, String[] cols){
        super(data, cols);

        this.getColumn(TextConstants.PATH_TABLE_COLUMN_NAMES[0]).setPreferredWidth(5);
        this.getColumn(TextConstants.PATH_TABLE_COLUMN_NAMES[1]).setPreferredWidth(1000);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = PathTable.this.getSelectedRow();

                if (selectedRow != -1){
                    final UIManager uimInstance = UIManager.getInstance();
                    String num = PathTable.this.getValueAt(selectedRow, 0).toString();
                    String path = PathTable.this.getValueAt(selectedRow, 1).toString();
                    String value = PathTable.this.getValueAt(selectedRow, 2).toString();

                    uimInstance.setSelectedRowNum(num);
                    uimInstance.setSelectedRowPath(path);
                    uimInstance.setSelectedRowValue(value);

                    uimInstance.getBuyLabel().setText("Izabrano: [" + num + "] - Putanja:  " + path + "; " + PathTable.this.getColumnName(2) + ": " + value);
                    uimInstance.getBuyButton().setVisible(true);

                    generateDetailedPathWindowTable(selectedRow, path, value);
                }
            }
        });
    }

    private void generateDetailedPathWindowTable(int selectedRow, String selectedPath, String selectedValue){
        var smInstance = StateManager.getInstance();

        String[] dptCols = TextConstants.DETAILED_PATH_TABLE_COLUMN_NAMES;
        String[][] dptData = ReconstructionUtility.generateDetailedPathTableData(selectedPath);

        final DetailedPathTable detailedPathTable = new DetailedPathTable(dptData, dptCols);
        final DetailedPathTableScrollPane detailedPathTableScrollPane = new DetailedPathTableScrollPane(detailedPathTable);

        String departureTimeString = TextConstants.DETAILED_PATH_TABLE_DEPARTURE_TIME_PLACEHOLDER;
        String arrivalTimeString = TextConstants.DETAILED_PATH_TABLE_ARRIVAL_TIME_PLACEHOLDER;
        String priceString = TextConstants.DETAILED_PATH_TABLE_PRICE_PLACEHOLDER;

        final String selectedRowFirstNode = dptData[selectedRow][1];
        final String selectedRowSecondNode = dptData[selectedRow][3];

        try {
            departureTimeString = DepartureUtility.getQuickestDepartureBetweenTwoNodes(
                    InputData.getInstance().getDepartureList(), selectedRowFirstNode, selectedRowSecondNode, false
            ).getDepartureTime();
        } catch (NullPointerException npe){
            departureTimeString = TextConstants.DEFAULT_DEPARTURE_TIME;
        }

        if (smInstance.getCriteriaTableName().equals(TextConstants.CRITERIA_PRICE_DISPLAY_NAME)){
            priceString = String.valueOf(selectedValue);

            int totalPathTime = ReconstructionUtility.getMinPathTime(selectedPath);
            arrivalTimeString = Time.addStringTime24(departureTimeString, Time.minutesToStringTime24(totalPathTime));
        }
        else if (smInstance.getCriteriaTableName().equals(TextConstants.CRITERIA_DURATION_DISPLAY_NAME)){
            priceString = String.valueOf((double)ReconstructionUtility.getMinPathPrice(selectedPath, true));

            //                                          8:00                  16h 2m
            arrivalTimeString = Time.addStringTime24(departureTimeString, Time.minutesToStringTime24(Time.stringTimeToMinutes(selectedValue)));
        }
        else if (smInstance.getCriteriaTableName().equals(TextConstants.CRITERIA_NODES_DISPLAY_NAME)){
            priceString = String.valueOf((double)ReconstructionUtility.getMinPathPrice(selectedPath, true));

            int totalPathTime = ReconstructionUtility.getMinPathTime(selectedPath);
            arrivalTimeString = Time.addStringTime24(departureTimeString, Time.minutesToStringTime24(totalPathTime));
        }

        final String departureTimeStringFormatted = TextConstants.DETAILED_PATH_TABLE_DEPARTURE_TIME_PREFIX + departureTimeString;
        final String arrivalTimeStringFormatted = TextConstants.DETAILED_PATH_TABLE_ARRIVAL_TIME_PREFIX + arrivalTimeString;
        final String priceStringFormatted = TextConstants.DETAILED_PATH_TABLE_PRICE_PREFIX + priceString;

        DetailedPathWindow.scrollPane = detailedPathTableScrollPane;
        DetailedPathWindow.departureTimeLabel.setText(departureTimeStringFormatted);
        DetailedPathWindow.arrivalTimeLabel.setText(arrivalTimeStringFormatted);
        DetailedPathWindow.priceLabel.setText(priceStringFormatted);
    }
}
