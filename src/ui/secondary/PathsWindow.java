package ui.secondary;

import pathfinding.*;
import ui.secondary.table.PathTable;
import ui.secondary.table.PathTableScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public final class PathsWindow extends JFrame {
    public PathsWindow(ArrayList<YenKShortestPaths.PathObject> paths){
        super("Rezultat pretrage");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        // pr

        final JPanel mainPanel = new JPanel();

//        paths.forEach(pathObj -> {
//            mainPanel.add(new JLabel("" + pathObj.getTotalCost()));
//        });

        String[][] data = {
                {"G_0_0 -> G_0_1 -> G_1_1 -> G_1_2", "134", "1561", "2"},
                {"G_0_0 -> G_1_2", "42", "194", "0"},
                {"G_0_0 -> G_0_1 -> G_1_1 -> G_1_0 -> G_1_1 -> G_1_2", "344", "2100", "3"},
                {"G_0_0 -> G_0_1 -> G_1_1 -> G_1_0 -> G_1_1 -> G_0_1 -> G_1_1 -> G_1_2", "542", "2461", "5"},
                {"G_0_0 -> G_1_0 -> G_1_1 -> G_1_2", "231", "2712", "0"}
        };

        String[] cols = {"Putanja", "Trajanje", "Cijena", "# presjedanja"};

        // TODO: napisati klasu koja parsira podatke iz paths (argumenta konstruktora) u data i cols

        PathTable table = new PathTable(data, cols);
        PathTableScrollPane tableScrollPane = new PathTableScrollPane(table);

        mainPanel.add(tableScrollPane);
        this.add(mainPanel, BorderLayout.CENTER);

        this.pack();
        this.repaint();
        this.revalidate();
        this.setVisible(true);
    }
}
