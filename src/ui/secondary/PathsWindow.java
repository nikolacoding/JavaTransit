package ui.secondary;

import input.StateManager;
import pathfinding.*;
import ui.secondary.table.PathTable;
import ui.secondary.table.PathTableScrollPane;
import ui.secondary.table.TableDataFormatter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public final class PathsWindow extends JFrame {
    public PathsWindow(ArrayList<YenKShortestPaths.PathObject> paths){
        super("Rezultat pretrage");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        final JPanel mainPanel = new JPanel();

        final String[] tableCols = TableDataFormatter.GetTableCols(StateManager.getInstance().getCriteriaTableName());
        final String[][] tableData = TableDataFormatter.GetTableData(paths);
        PathTable table = new PathTable(tableData, tableCols);
        PathTableScrollPane tableScrollPane = new PathTableScrollPane(table);

        mainPanel.add(tableScrollPane);
        this.add(mainPanel, BorderLayout.CENTER);

        this.pack();
        this.repaint();
        this.revalidate();
        this.setVisible(true);
    }
}
