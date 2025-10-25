package ui.primary;

import graph.MapGraph;
import input.InputData;
import input.StateManager;
import pathfinding.YenKShortestPaths;
import ui.secondary.PathsWindow;
import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public final class OptionsPanel extends TitledPanel {
    private final CityComboBox startComboBox = new CityComboBox("A");
    private final CityComboBox destinationComboBox = new CityComboBox("B");
    private final GeneralComboBox optimizationCriteriaComboBox = new GeneralComboBox();
    private final MapGraph graph;

    public OptionsPanel(){
        super("Opcije", Color.darkGray, Color.white);

        this.graph = InputData.getInstance().getMapGraph();

        final JPanel comboBoxPanel = new JPanel();

        final String[] cityNames = InputData.getInstance().getCountryMap().getCityNames();

        startComboBox.addItem("");
        startComboBox.setItems(cityNames);
        comboBoxPanel.setBackground(Color.darkGray);
        comboBoxPanel.add(new GeneralLabel("Polazak", Color.white));
        comboBoxPanel.add(startComboBox);

        destinationComboBox.addItem("");
        destinationComboBox.setItems(cityNames);
        comboBoxPanel.add(new GeneralLabel("Destinacija", Color.white));
        comboBoxPanel.add(destinationComboBox);
        this.add(comboBoxPanel, BorderLayout.SOUTH);

        optimizationCriteriaComboBox.setItems(Constants.OPTIMIZATION_CRITERIA);
        comboBoxPanel.add(new GeneralLabel("Optimizuj po", Color.white));
        comboBoxPanel.add(optimizationCriteriaComboBox);
        optimizationCriteriaComboBox.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) ie.getItem();

                switch (selectedItem) {
                    case "Najnizoj cijeni" -> {
                        this.graph.clearEdges();
                        this.graph.connectAdjacent("price");
                        StateManager.getInstance().setCriteriaTableName("Cijena");
                    }
                    case "Najkracem vremenu puta" -> {
                        this.graph.clearEdges();
                        this.graph.connectAdjacent("duration");
                        StateManager.getInstance().setCriteriaTableName("Trajanje");
                    }
                }
            }
        });

        this.add(comboBoxPanel, BorderLayout.SOUTH);

        final JButton findButton = new JButton("Pronadji");

        findButton.addActionListener(ae -> {
            var yenGenerator = new YenKShortestPaths(this.graph, (String)startComboBox.getSelectedItem(), (String)destinationComboBox.getSelectedItem());
            StateManager.getInstance().setCurrentYenResult(yenGenerator.yen(5));
            StateManager.getInstance().getSearchResultPanel().setResult();
        });

        findButton.setFocusable(false);
        comboBoxPanel.add(findButton);
    }
}
