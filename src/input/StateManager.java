package input;

import pathfinding.YenKShortestPaths;
import ui.primary.MainPanel;
import ui.primary.OptionsPanel;
import ui.primary.SearchResultPanel;

import javax.swing.*;
import java.util.List;

public final class StateManager {
    private MainPanel mainPanel;
    private OptionsPanel optionsPanel;
    private SearchResultPanel searchResultPanel;

    private JButton extraButton;

    private String criteriaTableName = "Cijena";
    private List<YenKShortestPaths.PathObject> currentYenResult;

    private StateManager(){

    }

    private final static StateManager globalStateManager = new StateManager();

    public static StateManager getInstance(){
        return globalStateManager;
    }

    public String getCriteriaTableName() {
        return criteriaTableName;
    }

    public void setCriteriaTableName(String criteriaTableName) {
        this.criteriaTableName = criteriaTableName;
    }

    public List<YenKShortestPaths.PathObject> getCurrentYenResult() {
        return currentYenResult;
    }

    public void setCurrentYenResult(List<YenKShortestPaths.PathObject> currentYenResult) {
        this.currentYenResult = currentYenResult;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public OptionsPanel getOptionsPanel() {
        return optionsPanel;
    }

    public void setOptionsPanel(OptionsPanel optionsPanel) {
        this.optionsPanel = optionsPanel;
    }

    public SearchResultPanel getSearchResultPanel() {
        return searchResultPanel;
    }

    public void setSearchResultPanel(SearchResultPanel searchResultPanel) {
        this.searchResultPanel = searchResultPanel;
    }

    public JButton getExtraButton() {
        return extraButton;
    }

    public void setExtraButton(JButton extraButton) {
        this.extraButton = extraButton;
    }
}
