package org.unibl.etf.pj2.app.input.types;

/**
 * Data-holder klasa koja reprezentuje sveobuhvatnu stanicu za neki grad. Jedna stanica se sastoji od grada u kom se nalazi, kao i
 * naziva autobuske i zeljeznicke (pod)stanice u njenom okviru.
 * @author Nikola Markovic
 */
public final class Station {
    private final String city;
    private final String busStation;
    private final String trainStation;

    /**
     * @param city Naziv grada
     * @param busStation Naziv autobuske stanice
     * @param trainStation Naziv zeljeznicke stanice
     * @author Nikola Markovic
     */
    public Station(String city, String busStation, String trainStation){
        this.city = city;
        this.busStation = busStation;
        this.trainStation = trainStation;
    }

    public String getTrainStation() {
        return trainStation;
    }

    public String getBusStation() {
        return busStation;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString(){
        return city + " " + busStation + " " + trainStation;
    }
}
