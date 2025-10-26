package ui.shared;

import util.constants.UIConstants;

import javax.swing.*;
import java.awt.*;

public final class GeneralLabel extends JLabel {
    public GeneralLabel(String text){
        super(text);

        this.setForeground(Color.white);
        this.setFont(new Font(UIConstants.PRIMARY_FONT_NAME, Font.PLAIN, UIConstants.GENERAL_LABEL_FONT_SIZE));
    }

    public GeneralLabel(String text, Color color){
        super(text);

        this.setForeground(color);
        this.setFont(new Font(UIConstants.PRIMARY_FONT_NAME, Font.PLAIN, UIConstants.GENERAL_LABEL_FONT_SIZE));
    }

    public GeneralLabel(String text, Color color, int horizonalAlignment){
        super(text);

        this.setForeground(color);
        this.setHorizontalAlignment(horizonalAlignment);
        this.setFont(new Font(UIConstants.PRIMARY_FONT_NAME, Font.PLAIN, UIConstants.GENERAL_LABEL_FONT_SIZE));
    }
}
