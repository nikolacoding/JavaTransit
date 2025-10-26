package ui.shared;

import javax.swing.*;
import java.awt.*;

public final class GeneralButton extends JButton {
    public GeneralButton(String text){
        super(text);

        this.setFocusable(false);
        this.setBackground(Color.white);
    }
}
