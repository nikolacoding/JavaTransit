package ui;

import graph.MapGraph;
import input.InputData;
import input.TransportDataParser;
import org.graphstream.graph.Graph;
import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public final class BottomPanel extends JPanel {
    private GeneralComboBox startComboBox = new GeneralComboBox();
    private GeneralComboBox destinationComboBox = new GeneralComboBox();
    private GeneralComboBox optimizationCriteriaComboBox = new GeneralComboBox();
    private MapGraph graph;

    public BottomPanel(){
        super(new BorderLayout());
        this.graph = InputData.getInstance().getMapGraph();

        this.setLayout(new BorderLayout());

        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH_MAX, Constants.BOTTOM_PANEL_HEIGHT));

        final JPanel labelPanel = new JPanel();
        labelPanel.add(new GeneralLabel("Options", Color.white, SwingConstants.CENTER));
        labelPanel.setBackground(Color.darkGray);
        this.add(labelPanel, BorderLayout.NORTH);

        final JPanel comboBoxPanel = new JPanel();

        final String[] cityNames = InputData.getInstance().getCountryMap().getCityNames();

        startComboBox.setItems(cityNames);
        comboBoxPanel.setBackground(Color.darkGray);
        comboBoxPanel.add(new GeneralLabel("Polazak", Color.white));
        comboBoxPanel.add(startComboBox);
        startComboBox.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED){
                String selectedItem = (String)ie.getItem();
                System.out.println("Izabran pocetak: " + selectedItem);

                this.graph.getNode(selectedItem).setAttribute("ui.style", "fill-color: red;");
            }
            else if (ie.getStateChange() == ItemEvent.DESELECTED){
                String selectedItem = (String)ie.getItem();
                this.graph.getNode(selectedItem).setAttribute("ui.style", "fill-color: black;");
            }
        });

        destinationComboBox.setItems(cityNames);
        comboBoxPanel.add(new GeneralLabel("Destinacija", Color.white));
        comboBoxPanel.add(destinationComboBox);
        this.add(comboBoxPanel, BorderLayout.SOUTH);
        destinationComboBox.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED){
                String selectedItem = (String)ie.getItem();
                System.out.println("Izabran pocetak: " + selectedItem);

                this.graph.getNode(selectedItem).setAttribute("ui.style", "fill-color: blue;");
            }
            else if (ie.getStateChange() == ItemEvent.DESELECTED){
                String selectedItem = (String)ie.getItem();
                this.graph.getNode(selectedItem).setAttribute("ui.style", "fill-color: black;");
            }
        });

        comboBoxPanel.add(new GeneralLabel("Optimizuj po", Color.white));
        comboBoxPanel.add(optimizationCriteriaComboBox);
        this.add(comboBoxPanel, BorderLayout.SOUTH);
    }
}
