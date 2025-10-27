package org.unibl.etf.pj2.app.state;

import org.unibl.etf.pj2.app.ui.primary.GeneralComboBox;
import org.unibl.etf.pj2.app.ui.primary.MainPanel;
import org.unibl.etf.pj2.app.ui.primary.OptionsPanel;
import org.unibl.etf.pj2.app.ui.primary.SearchResultPanel;

import javax.swing.*;

/**
 * Data-holder Singleton klasa koja cuva podatke o relevantnim aktivnim UI elementima aplikacije. Singleton sablon omogucava pristup
 * ovim elementima iz bilo kog dijela koda u bilo kom dijelu izvrsavanja uz garanciju da se oni nece mijenjati, buduci da se
 * ucitavaju samo jednom - na pocetku izvrsavanja.
 * @author Nikola Markovic
 */
public final class UIManager {
    private MainPanel mainPanel;
    private OptionsPanel optionsPanel;
    private SearchResultPanel searchResultPanel;

    private JButton findButton = null;
    private JButton extraButton = null;

    private JLabel buyLabel = null;
    private JLabel topResultLabel = null;
    private JButton buyButton = null;

    private GeneralComboBox startComboBox = null;
    private GeneralComboBox destinationComboBox = null;
    private GeneralComboBox optimizationCriteriaComboBox = null;

    private String selectedRowNum = "";
    private String selectedRowPath = "";
    private String selectedRowValue = "";

    // prevencija instanciranja
    private UIManager(){ }

    private final static UIManager globalUImanager = new UIManager();

    public static UIManager getInstance(){
        return globalUImanager;
    }

    public SearchResultPanel getSearchResultPanel() {
        return searchResultPanel;
    }

    public void setSearchResultPanel(SearchResultPanel searchResultPanel) {
        this.searchResultPanel = searchResultPanel;
    }

    public OptionsPanel getOptionsPanel() {
        return optionsPanel;
    }

    public void setOptionsPanel(OptionsPanel optionsPanel) {
        this.optionsPanel = optionsPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JButton getFindButton() {
        return findButton;
    }

    public void setFindButton(JButton findButton) {
        this.findButton = findButton;
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

    public JButton getBuyButton() {
        return buyButton;
    }

    public void setBuyButton(JButton buyButton) {
        this.buyButton = buyButton;
    }

    public JLabel getTopResultLabel() {
        return topResultLabel;
    }

    public void setTopResultLabel(JLabel topResultLabel) {
        this.topResultLabel = topResultLabel;
    }

    public JLabel getBuyLabel() {
        return buyLabel;
    }

    public void setBuyLabel(JLabel buyLabel) {
        this.buyLabel = buyLabel;
    }

    public GeneralComboBox getStartComboBox() {
        return startComboBox;
    }

    public void setStartComboBox(GeneralComboBox startComboBox) {
        this.startComboBox = startComboBox;
    }

    public GeneralComboBox getDestinationComboBox() {
        return destinationComboBox;
    }

    public void setDestinationComboBox(GeneralComboBox destinationComboBox) {
        this.destinationComboBox = destinationComboBox;
    }

    public GeneralComboBox getOptimizationCriteriaComboBox() {
        return optimizationCriteriaComboBox;
    }

    public void setOptimizationCriteriaComboBox(GeneralComboBox optimizationCriteriaComboBox) {
        this.optimizationCriteriaComboBox = optimizationCriteriaComboBox;
    }
}
