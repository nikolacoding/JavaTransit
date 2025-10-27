package ui.secondary.table;

import util.constants.UIConstants;

import javax.swing.*;

/**
 * Jednostavna wrapper klasa za JScrollPane koja radi konkretno sa PathTable-om i postavlja
 * odgovarajucu defaultnu velicinu prikaza.
 * @author Nikola Markovic
 */
public class PathTableScrollPane extends JScrollPane {
    public PathTableScrollPane(PathTable pathTable){
        super(pathTable);

        this.setPreferredSize(UIConstants.PATH_TABLE_SCROLL_PANE_DIMENSION);
    }
}
