package state;

import pathfinding.yen.YenKShortestPaths;
import pathfinding.yen.types.PathObject;
import util.constants.TextConstants;
import util.constants.UIConstants;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public final class StateManager {
    private final List<JComponent> primaryInteractiveComponents = new ArrayList<>();
    private String criteriaTableName = TextConstants.CRITERIA_PRICE_DISPLAY_NAME;
    private List<PathObject> currentYenResult;
    private List<JFrame> activeJFrames = new ArrayList<>();

    private StateManager(){

    }

    private final static StateManager globalStateManager = new StateManager();

    public void closeAllButMainWindow(){

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
}
