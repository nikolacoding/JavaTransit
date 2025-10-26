package ui.primary;

import util.constants.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GeneralComboBox extends JComboBox<String> {

    public GeneralComboBox(){
        super();

        this.setPreferredSize(UIConstants.GENERAL_COMBO_BOX_DIMENSION);
        this.setBackground(Color.darkGray);
        this.setForeground(Color.white);
        this.setFocusable(false);
    }

    public void setItems(String[] items){
        Arrays.stream(items).forEach(this::addItem);
    }
}
