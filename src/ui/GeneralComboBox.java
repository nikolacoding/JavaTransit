package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public final class GeneralComboBox extends JComboBox<String> {
    public GeneralComboBox(){
        super();

        this.setPreferredSize(new Dimension(200, 30));
        this.setBackground(Color.darkGray);
        this.setForeground(Color.white);
        this.setFocusable(false);
    }

    public void setItems(String[] items){
        Arrays.stream(items).forEach(this::addItem);
    }
}
