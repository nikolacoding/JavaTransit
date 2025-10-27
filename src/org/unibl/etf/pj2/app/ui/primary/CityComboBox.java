package org.unibl.etf.pj2.app.ui.primary;

import org.unibl.etf.pj2.app.graph.MapGraph;
import org.unibl.etf.pj2.app.state.InputData;
import org.unibl.etf.pj2.app.pathfinding.reconstruction.PathReconstructor;

import java.awt.event.ItemEvent;

/**
 * Klasa izvedena iz <code>GeneralComboBox</code> koja dodatno sadrzi ItemListener za selekciju gradova (cvorova grafa), pri
 * cemu se uklanjaju ostaci prethodne simulacije.
 * @author Nikola Markovic
 */
public final class CityComboBox extends GeneralComboBox {
    private final MapGraph graph;

    /**
     * @param selectionType Tip selekcije (highlightinga) za cvor je opisan datim <code>CityComboBox</code> objektom.
     * @author Nikola Markovic
     */
    public CityComboBox(String selectionType) {
        super();
        this.graph = InputData.getInstance().getMapGraph();

        this.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String)ie.getItem();
                if (!selectedItem.isEmpty()) {
                    this.graph.setSelected(selectedItem, true, selectionType);
                    PathReconstructor.undoAllPreviousReconstructions(graph);
                }
            } else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                String selectedItem = (String)ie.getItem();
                if (!selectedItem.isEmpty()) {
                    this.graph.setSelected(selectedItem, false, selectionType);
                    PathReconstructor.undoAllPreviousReconstructions(graph);
                }
            }
        });
    }
}
