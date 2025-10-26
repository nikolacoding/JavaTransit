package ui.secondary;

import input.StateManager;
import pathfinding.*;
import ui.secondary.table.PathTable;
import ui.secondary.table.PathTableScrollPane;
import ui.secondary.table.TableDataFormatter;
import ui.tertiary.DetailedPathWindow;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public final class PathsWindow extends JFrame {
    public PathsWindow(ArrayList<YenKShortestPaths.PathObject> paths){
        super("Rezultat pretrage");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        final JPanel mainPanel = new JPanel();
        final JPanel buyPanel = new JPanel(new BorderLayout());
        buyPanel.setPreferredSize(new Dimension(1180, 50));

        final String[] tableCols = TableDataFormatter.GetTableCols(StateManager.getInstance().getCriteriaTableName());
        final String[][] tableData = TableDataFormatter.GetTableData(paths);
        PathTable table = new PathTable(tableData, tableCols);
        PathTableScrollPane tableScrollPane = new PathTableScrollPane(table);

        final JLabel buyLabel = new JLabel("Izaberi put za kupovinu karte.");
        final JButton buyButton = new JButton("Kupi kartu");
        buyButton.setVisible(false);
        StateManager.getInstance().setBuyButton(buyButton);

        buyButton.addActionListener(ae -> {
            final StateManager smInstance = StateManager.getInstance();

            JLabel topResultLabel = smInstance.getTopResultLabel();
            //topResultLabel.setText("Karta je kupljena i racun je sacuvan u <RACUN_PATH>");
            smInstance.getExtraButton().setVisible(false);

            new DetailedPathWindow();
        });

        buyPanel.add(buyLabel, BorderLayout.CENTER);
        buyPanel.add(buyButton, BorderLayout.EAST);
        StateManager.getInstance().setBuyLabel(buyLabel);

        mainPanel.add(tableScrollPane);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buyPanel, BorderLayout.SOUTH);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                StateManager smInstance = StateManager.getInstance();

                smInstance.getPrimaryInteractiveComponents().forEach(component -> component.setEnabled(false));
            }

            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        this.pack();
        this.repaint();
        this.revalidate();
        this.setVisible(true);
    }

    private void closeWindow(){
        StateManager smInstance = StateManager.getInstance();
        smInstance.getPrimaryInteractiveComponents().forEach(component -> component.setEnabled(true));
        this.setVisible(false);
        this.dispose();
    }
}
