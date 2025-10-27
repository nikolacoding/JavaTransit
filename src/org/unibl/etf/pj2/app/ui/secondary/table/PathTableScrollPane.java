package org.unibl.etf.pj2.app.ui.secondary.table;

import org.unibl.etf.pj2.app.util.constants.UIConstants;

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
