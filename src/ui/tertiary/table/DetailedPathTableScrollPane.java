package ui.tertiary.table;
import util.constants.UIConstants;

import javax.swing.*;

/**
 * Jednostavna wrapper klasa za JScrollPane koja radi konkretno sa DetailedPathTable-om i postavlja
 * odgovarajucu defaultnu velicinu prikaza.
 * @author Nikola Markovic
 */
public class DetailedPathTableScrollPane extends JScrollPane {
    public DetailedPathTableScrollPane(DetailedPathTable dpTable){
        super(dpTable);

        this.setPreferredSize(UIConstants.DETAILED_PATH_TABLE_SCROLL_PANE_DIMENSION);
    }
}
