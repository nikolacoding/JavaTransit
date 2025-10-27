package ui.secondary;

import pathfinding.yen.types.PathObject;
import state.UIManager;
import state.StateManager;
import ui.secondary.table.PathTable;
import ui.secondary.table.PathTableScrollPane;
import ui.secondary.table.TableDataFormatter;
import ui.shared.GeneralButton;
import ui.shared.GeneralLabel;
import ui.shared.Listeners.ButtonListeners;
import util.constants.TextConstants;
import util.constants.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Klasa koja opisuje JFrame prozor koji korisniku tablearno prikazuje 5 najoptimalnijih putanja.
 * @author Nikola Markovic
 */
public final class PathsWindow extends JFrame {
    public PathsWindow(ArrayList<PathObject> paths){
        super(TextConstants.PATHS_WINDOW_TITLE);

        final UIManager uimInstance = UIManager.getInstance();
        final StateManager smInstance = StateManager.getInstance();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        final JPanel mainPanel = new JPanel();

        final JPanel buyPanel = new JPanel(new BorderLayout());
        buyPanel.setPreferredSize(UIConstants.BUY_PANEL_DIMENSION);

        final String[] tableCols = TableDataFormatter.getTableCols(StateManager.getInstance().getCriteriaTableName());
        final String[][] tableData = TableDataFormatter.getTableData(paths);
        PathTable table = new PathTable(tableData, tableCols);
        PathTableScrollPane tableScrollPane = new PathTableScrollPane(table);

        final GeneralLabel buyLabel = new GeneralLabel(TextConstants.SELECT_PATH_LABEL_TEXT, Color.black);
        final GeneralButton buyButton = new GeneralButton(TextConstants.BUY_TICKET_BUTTON_TEXT);
        buyButton.setVisible(false);
        uimInstance.setBuyButton(buyButton);

        buyButton.addActionListener(ButtonListeners.buyButtonListener);

        buyPanel.add(buyLabel, BorderLayout.CENTER);
        buyPanel.add(buyButton, BorderLayout.EAST);
        uimInstance.setBuyLabel(buyLabel);

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
        smInstance.addActiveJFrame(this);
    }

    /**
     * Metoda koja rukuje zatvaranjem prozora tako sto u ranije koristenom dijelu UI-ja ponovo aktivira sve deaktivirane
     * UI elemente, pa disposuje trenutni prozor.
     * @author Nikola Markovic
     */
    private void closeWindow(){
        StateManager smInstance = StateManager.getInstance();
        smInstance.getPrimaryInteractiveComponents().forEach(component -> component.setEnabled(true));
        this.setVisible(false);
        this.dispose();
    }
}
