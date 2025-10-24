package ui.primary;

import javax.swing.*;
import java.awt.*;

public final class GeneralLabel extends JLabel {
    public GeneralLabel(String text){
        super(text);

        this.setForeground(Color.white);
        this.setFont(new Font("Roboto", Font.PLAIN, 14));
    }

    public GeneralLabel(String text, Color color){
        super(text);

        this.setForeground(color);
        this.setFont(new Font("Roboto", Font.PLAIN, 14));
    }

    public GeneralLabel(String text, Color color, int horizonalAlignment){
        super(text);

        this.setForeground(color);
        this.setHorizontalAlignment(horizonalAlignment);
        this.setFont(new Font("Roboto", Font.PLAIN, 14));
    }
}
