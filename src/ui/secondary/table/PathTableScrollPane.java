package ui.secondary.table;

import util.constants.UIConstants;

import javax.swing.*;

public class PathTableScrollPane extends JScrollPane {
    public PathTableScrollPane(PathTable pathTable){
        super(pathTable);

        this.setPreferredSize(UIConstants.PATH_TABLE_SCROLL_PANE_DIMENSION);
    }
}
