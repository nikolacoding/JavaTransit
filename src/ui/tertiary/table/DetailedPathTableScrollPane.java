package ui.tertiary.table;
import ui.secondary.table.PathTable;
import util.constants.UIConstants;

import javax.swing.*;
import java.awt.*;

public class DetailedPathTableScrollPane extends JScrollPane {
    public DetailedPathTableScrollPane(DetailedPathTable dpTable){
        super(dpTable);

        this.setPreferredSize(UIConstants.DETAILED_PATH_TABLE_SCROLL_PANE_DIMENSION);
    }
}
