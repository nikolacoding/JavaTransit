package org.unibl.etf.pj2.app.state;

import org.unibl.etf.pj2.app.graph.MapGraph;
import org.unibl.etf.pj2.app.input.types.CountryMap;
import org.unibl.etf.pj2.app.input.types.Departure;
import org.unibl.etf.pj2.app.input.types.Station;
import org.unibl.etf.pj2.app.util.constants.GeneralConstants;

import java.util.List;

/**
 * Data-holder Singleton klasa koja cuva podatke ucitane iz ulazne JSON datoteke. Singleton sablon omogucava pristup
 * ovim podacima iz bilo kog dijela koda u bilo kom dijelu izvrsavanja uz garanciju da se oni nece mijenjati, buduci da se
 * ucitavaju samo jednom - na pocetku izvrsavanja.
 * @author Nikola Markovic
 */
public final class InputData {
    private final CountryMap countryMap;
    private final List<Station> stationList;
    private final List<Departure> departureList;
    private final MapGraph mapGraph;

    private static InputData globalInputData;

    /**
     * @param countryMap Mapa grada
     * @param stationList Lista stanica
     * @param departureList Lista polazaka
     * @author Nikola Markovic
     */
    private InputData(CountryMap countryMap, List<Station> stationList, List<Departure> departureList){
        this.countryMap = countryMap;
        this.stationList = stationList;
        this.departureList = departureList;

        mapGraph = new MapGraph(GeneralConstants.MAIN_GRAPH_ID);
}

    /**
     * Metoda za postavljanje vrijednosti podataka ove klase, odnosno za instanciranje staticke instance InputData klase koja se
     * dalje po potrebi poziva <code>getInstance()</code> metodom.
     * @param countryMap Mapa grada
     * @param stationList Lista stanica
     * @param departureList Lista polazaka
     * @author Nikola Markovic
     */
    public static void setInputData(CountryMap countryMap, List<Station> stationList, List<Departure> departureList){
        globalInputData = new InputData(countryMap, stationList, departureList);
        System.out.println("InputData uspjesno inicijalizovan.");
    }

    /**
     * Geter za jedinu staticku Singleton instancu InputData objekta sa podacima ranije ucitanim <code>setInputData(...)</code> metodom.
     * @return Aktivna instanca InputData objekta.
     * @author Nikola Markovic
     */
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
