package ui.primary;

import state.InputData;
import state.StateManager;
import state.UIManager;
import ui.shared.GeneralButton;
import ui.shared.GeneralLabel;
import ui.shared.TitledPanel;
import util.constants.TextConstants;
import ui.shared.Listeners.ButtonListeners;
import ui.shared.Listeners.ComboBoxListeners;

import javax.swing.*;
import java.awt.*;

public final class OptionsPanel extends TitledPanel {
    private final CityComboBox startComboBox = new CityComboBox("A");
    private final CityComboBox destinationComboBox = new CityComboBox("B");
    private final GeneralComboBox optimizationCriteriaComboBox = new GeneralComboBox();

    public OptionsPanel(){
        super(TextConstants.OPTIONS_GENERAL_LABEL_TEXT, Color.darkGray, Color.white);
        final StateManager smInstance = StateManager.getInstance();
        final UIManager uimInstance = UIManager.getInstance();

        uimInstance.setStartComboBox(startComboBox);
        uimInstance.setDestinationComboBox(destinationComboBox);
        uimInstance.setOptimizationCriteriaComboBox(optimizationCriteriaComboBox);

        final JPanel comboBoxPanel = new JPanel();

        final String[] cityNames = InputData.getInstance().getCountryMap().getCityNames();

        startComboBox.addItem("");
        startComboBox.setItems(cityNames);
        comboBoxPanel.setBackground(Color.darkGray);
        comboBoxPanel.add(new GeneralLabel(TextConstants.DEPARTURE_COMBO_BOX_LABEL_TEXT, Color.white));
        comboBoxPanel.add(startComboBox);

        destinationComboBox.addItem("");
        destinationComboBox.setItems(cityNames);
        comboBoxPanel.add(new GeneralLabel(TextConstants.ARRIVAL_COMBO_BOX_LABEL_TEXT, Color.white));
        comboBoxPanel.add(destinationComboBox);
        this.add(comboBoxPanel, BorderLayout.SOUTH);

        optimizationCriteriaComboBox.setItems(TextConstants.OPTIMIZATION_CRITERIA);
        comboBoxPanel.add(new GeneralLabel(TextConstants.CRITERIA_COMBO_BOX_LABEL_TEXT, Color.white));
        comboBoxPanel.add(optimizationCriteriaComboBox);
        optimizationCriteriaComboBox.addItemListener(ComboBoxListeners.optimizationCriteriaComboBoxListener);

        this.add(comboBoxPanel, BorderLayout.SOUTH);
        final GeneralButton findButton = new GeneralButton(TextConstants.FIND_BUTTON_TEXT);
        findButton.addActionListener(ButtonListeners.findButtonListener);
        uimInstance.setFindButton(findButton);

        comboBoxPanel.add(findButton);

        smInstance.addPrimaryInteractiveComponent(this.startComboBox);
        smInstance.addPrimaryInteractiveComponent(this.destinationComboBox);
        smInstance.addPrimaryInteractiveComponent(this.optimizationCriteriaComboBox);
        smInstance.addPrimaryInteractiveComponent(findButton);
    }
}
