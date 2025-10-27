package ui.primary;

import util.constants.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Klasa koja se izvodi iz JComboBox sa tipom podatka String sa predefinisanm stilom i osnovnim elementima ponasanja.
 * @author Nikola Markovic
 */
public class GeneralComboBox extends JComboBox<String> {
    public GeneralComboBox(){
        super();

        this.setPreferredSize(UIConstants.GENERAL_COMBO_BOX_DIMENSION);
        this.setBackground(Color.darkGray);
        this.setForeground(Color.white);
        this.setFocusable(false);
    }

    /**
     * Metoda za postavljanje stavki u ComboBox.
     * @param items Niz stavki koje ce se postaviti na ComboBox pri prvom renderovanju
     * @author Nikola Markovic
     */
    public void setItems(String[] items){
        Arrays.stream(items).forEach(this::addItem);
    }
}
