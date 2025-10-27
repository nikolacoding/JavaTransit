package ui.shared;

import util.constants.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Wrapper klasa osnovnog JLabel UI elementa sa ustaljenim stilom, ali potencijalno drugacijim bojama u zavisnosti od pozadine.
 * @author Nikola Markovic
 */
public final class GeneralLabel extends JLabel {
    /**
     * Konstruktor sa parametrizovanim tekstom, ali podrazumijevanom bojom teksta (bijelom).
     * @param text Tekstualni sadrzaj JLabel-a
     * @author Nikola Markovic
     */
    public GeneralLabel(String text){
        super(text);

        this.setForeground(Color.white);
        this.setFont(new Font(UIConstants.PRIMARY_FONT_NAME, Font.PLAIN, UIConstants.GENERAL_LABEL_FONT_SIZE));
    }

    /**
     * Konstruktor sa parametrizovanim tekstom i bojom teksta, ali podrazumijevanim horizontalnim poravnanjem.
     * @param text Tekstualni sadrzaj JLabel-a
     * @param color Boja teksta
     * @author Nikola Markovic
     */
    public GeneralLabel(String text, Color color){
        super(text);

        this.setForeground(color);
        this.setFont(new Font(UIConstants.PRIMARY_FONT_NAME, Font.PLAIN, UIConstants.GENERAL_LABEL_FONT_SIZE));
    }

    /**
     * Konstruktor sa parametrizovanim tekstom, bojom teksta i horizontalnim poravnanjem.
     * @param text Tekstualni sadrzaj JLabel-a
     * @param color Boja teksta
     * @param horizonalAlignment Horizontalno poravnanje
     * @author Nikola Markovic
     */
    public GeneralLabel(String text, Color color, int horizonalAlignment){
        super(text);

        this.setForeground(color);
        this.setHorizontalAlignment(horizonalAlignment);
        this.setFont(new Font(UIConstants.PRIMARY_FONT_NAME, Font.PLAIN, UIConstants.GENERAL_LABEL_FONT_SIZE));
    }
}
