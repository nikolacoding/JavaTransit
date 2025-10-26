package state;

import graph.MapGraph;
import input.types.CountryMap;
import input.types.Departure;
import input.types.Station;
import util.constants.GeneralConstants;

import java.util.List;

public final class InputData {
    private final CountryMap countryMap;
    private final List<Station> stationList;
    private final List<Departure> departureList;
    private final MapGraph mapGraph;

    private static InputData globalInputData;

    private InputData(CountryMap countryMap, List<Station> stationList, List<Departure> departureList){
        this.countryMap = countryMap;
        this.stationList = stationList;
        this.departureList = departureList;

        mapGraph = new MapGraph(GeneralConstants.MAIN_GRAPH_ID);
}

    public static void setInputData(CountryMap countryMap, List<Station> stationList, List<Departure> departureList){
        globalInputData = new InputData(countryMap, stationList, departureList);
        System.out.println("InputData uspjesno inicijalizovan.");
    }

    public static InputData getInstance(){
        return globalInputData;
    }

    public CountryMap getCountryMap(){
        return this.countryMap;
    }

    public List<Station> getStationList(){
        return this.stationList;
    }

    public List<Departure> getDepartureList(){
        return this.departureList;
    }

    public MapGraph getMapGraph(){
        return this.mapGraph;
    }
}
