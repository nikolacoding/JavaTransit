package ui;

import graph.MapGraph;
import input.InputData;
import input.TransportDataParser;
import org.graphstream.graph.Graph;
import pathfinding.YenKShortestPaths;
import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public final class BottomPanel extends JPanel {
    private final CityComboBox startComboBox = new CityComboBox("fill-color: red; size: 20px;", "fill-color: black; size: 10px;", "A");
    private final CityComboBox destinationComboBox = new CityComboBox("fill-color: blue; size: 20px;", "fill-color: black; size: 10px;", "B");
    private final GeneralComboBox optimizationCriteriaComboBox = new GeneralComboBox();
    private final MapGraph graph;

    public BottomPanel(){
        super(new BorderLayout());
        this.graph = InputData.getInstance().getMapGraph();

        this.setLayout(new BorderLayout());
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH_MAX, Constants.BOTTOM_PANEL_HEIGHT));

        final JPanel labelPanel = new JPanel();
        labelPanel.add(new GeneralLabel("Opcije", Color.white, SwingConstants.CENTER));
        labelPanel.setBackground(Color.darkGray);
        this.add(labelPanel, BorderLayout.NORTH);

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
                    }
                    case "Najkracem vremenu puta" -> {
                        this.graph.clearEdges();
                        this.graph.connectAdjacent("duration");
                    }
                }
            }
        });

        this.add(comboBoxPanel, BorderLayout.SOUTH);

        final JButton findButton = new JButton("Pronadji");

        findButton.addActionListener(ae -> {
            var y = new YenKShortestPaths(this.graph, (String)startComboBox.getSelectedItem(), (String)destinationComboBox.getSelectedItem());
            var res = y.yen(5);
            System.out.println(res.toString());
        });

        findButton.setFocusable(false);
        comboBoxPanel.add(findButton);
    }
}
