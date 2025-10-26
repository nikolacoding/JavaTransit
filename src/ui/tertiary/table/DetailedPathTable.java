package ui.tertiary.table;

import ui.shared.GeneralTable;
import util.constants.TextConstants;
import util.constants.UIConstants;

public class DetailedPathTable extends GeneralTable {
    public DetailedPathTable(String[][] data, String[] cols) {
        super(data, cols);

        this.getColumn(TextConstants.DETAILED_PATH_TABLE_COLUMN_NAMES[0]).setPreferredWidth(UIConstants.DETAILED_PATH_TABLE_ID_COLUMN_WIDTH);
        this.getColumn(TextConstants.DETAILED_PATH_TABLE_COLUMN_NAMES[1]).setPreferredWidth(UIConstants.DETAILED_PATH_TABLE_DEPARTURE_COLUMN_WIDTH);
        this.getColumn(TextConstants.DETAILED_PATH_TABLE_COLUMN_NAMES[2]).setPreferredWidth(UIConstants.DETAILED_PATH_TABLE_VEHICLE_COLUMN_WIDTH);
        this.getColumn(TextConstants.DETAILED_PATH_TABLE_COLUMN_NAMES[3]).setPreferredWidth(UIConstants.DETAILED_PATH_TABLE_ARRIVAL_COLUMN_WIDTH);

        this.setRowSelectionAllowed(false);
        this.setColumnSelectionAllowed(false);
    }
}
