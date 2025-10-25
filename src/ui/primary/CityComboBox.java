package ui.primary;

import graph.MapGraph;
import input.InputData;
import pathfinding.PathReconstructor;

import java.awt.event.ItemEvent;

public final class CityComboBox extends GeneralComboBox {

    private final MapGraph graph;

    public CityComboBox(String selectionType) {
        super();
        this.graph = InputData.getInstance().getMapGraph();

        this.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) ie.getItem();
                if (!selectedItem.isEmpty()) {
                    this.graph.setSelected(selectedItem, true, selectionType);
                    PathReconstructor.undoAllPreviousReconstructions(graph);
                }
            } else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                String selectedItem = (String) ie.getItem();
                if (!selectedItem.isEmpty()) {
                    this.graph.setSelected(selectedItem, false, selectionType);
                    PathReconstructor.undoAllPreviousReconstructions(graph);
                }
            }
        });
    }
}
