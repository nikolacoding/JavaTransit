package ui.secondary.table;

import javax.swing.*;
import java.awt.*;

public class PathTableScrollPane extends JScrollPane {
    public PathTableScrollPane(PathTable pathTable){
        super(pathTable);

        this.setPreferredSize(new Dimension());
        this.setPreferredSize(new Dimension(800, 110));
    }
}
