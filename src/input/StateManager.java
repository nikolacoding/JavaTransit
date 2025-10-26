package input;

import pathfinding.YenKShortestPaths;
import ui.primary.MainPanel;
import ui.primary.OptionsPanel;
import ui.primary.SearchResultPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public final class StateManager {
    private MainPanel mainPanel;
    private OptionsPanel optionsPanel;
    private SearchResultPanel searchResultPanel;

    private JButton findButton = null;
    private JButton extraButton = null;

    private List<JComponent> primaryInteractiveComponents = new ArrayList<>();

    private String criteriaTableName = "Cijena";
    private List<YenKShortestPaths.PathObject> currentYenResult;

    private String selectedRowNum = "";
    private String selectedRowPath = "";
    private String selectedRowValue = "";

    private JLabel buyLabel = null;
    private JLabel topResultLabel = null;
    private JButton buyButton = null;

    private StateManager(){

    }

    private final static StateManager globalStateManager = new StateManager();

    public static StateManager getInstance(){
        return globalStateManager;
    }

    public void addPrimaryInteractiveComponent(JComponent component){
        this.primaryInteractiveComponents.add(component);
    }

    public List<JComponent> getPrimaryInteractiveComponents(){
        return this.primaryInteractiveComponents;
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

    public String getSelectedRowValue() {
        return selectedRowValue;
    }

    public void setSelectedRowValue(String selectedRowValue) {
        this.selectedRowValue = selectedRowValue;
    }

    public String getSelectedRowPath() {
        return selectedRowPath;
    }

    public void setSelectedRowPath(String selectedRowPath) {
        this.selectedRowPath = selectedRowPath;
    }

    public String getSelectedRowNum() {
        return selectedRowNum;
    }

    public void setSelectedRowNum(String selectedRowNum) {
        this.selectedRowNum = selectedRowNum;
    }

    public JLabel getBuyLabel() {
        return buyLabel;
    }

    public void setBuyLabel(JLabel buyLabel) {
        this.buyLabel = buyLabel;
    }

    public JButton getBuyButton() {
        return buyButton;
    }

    public void setBuyButton(JButton buyButton) {
        this.buyButton = buyButton;
    }

    public JButton getFindButton() {
        return findButton;
    }

    public void setFindButton(JButton findButton) {
        this.findButton = findButton;
    }

    public JLabel getTopResultLabel() {
        return topResultLabel;
    }

    public void setTopResultLabel(JLabel topResultLabel) {
        this.topResultLabel = topResultLabel;
    }
}
