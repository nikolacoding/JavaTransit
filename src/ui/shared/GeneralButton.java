package ui.shared;

import javax.swing.*;
import java.awt.*;

/**
 * Wrapper klasa osnovnog JButton UI elementa sa ustaljenim stilom.
 * @author Nikola Markovic
 */
public final class GeneralButton extends JButton {
    public GeneralButton(String text){
        super(text);

        this.setFocusable(false);
        this.setBackground(Color.white);
    }
}
