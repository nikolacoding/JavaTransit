package org.unibl.etf.pj2.app.state;

import org.unibl.etf.pj2.app.pathfinding.yen.types.PathObject;
import org.unibl.etf.pj2.app.util.constants.TextConstants;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data-holder Singleton klasa koja cuva podatke o opstem stanju izvrsavanja aplikacije. Singleton sablon omogucava pristup
 * ovim podacima iz bilo kog dijela koda u bilo kom dijelu izvrsavanja uz garanciju da se oni nece mijenjati, buduci da se
 * ucitavaju samo jednom - na pocetku izvrsavanja.
 * @author Nikola Markovic
 */
public final class StateManager {
    private final List<JComponent> primaryInteractiveComponents = new ArrayList<>();
    private String criteriaTableName = TextConstants.CRITERIA_PRICE_DISPLAY_NAME;
    private List<PathObject> currentYenResult;
    private final List<JFrame> activeJFrames = new ArrayList<>();

    // Podaci za stampanje racuna
    private int currentReceiptPrice;
    private String currentReceiptFrom;
    private String currentReceiptTo;
    private String currentReceiptDepartureTime;
    private String currentReceiptArrivalTime;
    private int currentReceiptNumVehicleChanges;

    private String currentReceiptPath;

    // prevencija instanciranja
    private StateManager(){ }

    private final static StateManager globalStateManager = new StateManager();

    public void closeAllButMainWindow(){
        activeJFrames.forEach(jF -> {
            jF.setVisible(false);
            jF.dispose();
        });
    }

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

    public List<PathObject> getCurrentYenResult() {
        return currentYenResult;
    }

    public void setCurrentYenResult(List<PathObject> currentYenResult) {
        this.currentYenResult = currentYenResult;
    }

    public void addActiveJFrame(JFrame frame){
        this.activeJFrames.add(frame);
    }

    public List<JFrame> getActiveJFrames(){
        return this.activeJFrames;
    }

    public String getCurrentReceiptPath() {
        return currentReceiptPath;
    }

    public void setCurrentReceiptPath(String currentReceiptPath) {
        this.currentReceiptPath = currentReceiptPath;
    }

    public int getCurrentReceiptNumVehicleChanges() {
        return currentReceiptNumVehicleChanges;
    }

    public void setCurrentReceiptNumVehicleChanges(int currentReceiptNumVehicleChanges) {
        this.currentReceiptNumVehicleChanges = currentReceiptNumVehicleChanges;
    }

    public String getCurrentReceiptArrivalTime() {
        return currentReceiptArrivalTime;
    }

    public void setCurrentReceiptArrivalTime(String currentReceiptArrivalTime) {
        this.currentReceiptArrivalTime = currentReceiptArrivalTime;
    }

    public String getCurrentReceiptDepartureTime() {
        return currentReceiptDepartureTime;
    }

    public void setCurrentReceiptDepartureTime(String currentReceiptDepartureTime) {
        this.currentReceiptDepartureTime = currentReceiptDepartureTime;
    }

    public String getCurrentReceiptTo() {
        return currentReceiptTo;
    }

    public void setCurrentReceiptTo(String currentReceiptTo) {
        this.currentReceiptTo = currentReceiptTo;
    }

    public String getCurrentReceiptFrom() {
        return currentReceiptFrom;
    }

    public void setCurrentReceiptFrom(String currentReceiptFrom) {
        this.currentReceiptFrom = currentReceiptFrom;
    }

    public int getCurrentReceiptPrice() {
        return currentReceiptPrice;
    }

    public void setCurrentReceiptPrice(int currentReceiptPrice) {
        this.currentReceiptPrice = currentReceiptPrice;
    }
}
