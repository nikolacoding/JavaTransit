package org.unibl.etf.pj2.app.input.types;

/**
 * Data-holder klasa koja reprezentuje jedan put od cvora <code>from</code> do cvora <code>to</code> sa zadatim
 * vremenom polaska, vrstom vozila, trajanjem puta, cijenom i najkracim vremenom za presjedanje na naredno vozilo po
 * dolasku na destinaciju.
 * @author Nikola Markovic
 */
public final class Departure {
    private final String type;
    private final String from;
    private final String to;
    private final String departureTime;
    private final int duration;
    private final int price;
    private final int minTransferTime;

    /**
     * @param type Vrsta vozila
     * @param from Mjesto polaska
     * @param to Mjesto dolaska
     * @param departureTime Vrijeme polaska
     * @param duration Trajanje
     * @param price Cijena
     * @param minTransferTime Najkrace vrijeme za presjedanje po dolasku na <code>to</code>
     * @author Nikola Markovic
     */
    public Departure(String type, String from, String to, String departureTime, int duration, int price, int minTransferTime){
        this.type = type;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.duration = duration;
        this.price = price;
        this.minTransferTime = minTransferTime;
    }

    @Override
    public String toString(){
        return type + " " + from + " " + to + " " + departureTime + " " + duration + " " + price + " " + minTransferTime;
    }

    public String getType() {
        return type;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public int getMinTransferTime() {
        return minTransferTime;
    }
}
