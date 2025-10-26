package ui.tertiary.table;
import ui.secondary.table.PathTable;

import javax.swing.*;
import java.awt.*;

public class DetailedPathTableScrollPane extends JScrollPane {
    public DetailedPathTableScrollPane(DetailedPathTable dpTable){
        super(dpTable);

        this.setPreferredSize(new Dimension());
        this.setPreferredSize(new Dimension(600, 300));
    }
}
