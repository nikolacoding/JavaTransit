package serialization;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Data-holder klasa podataka o jednom fiskalnom racunu koji se generise pri uspjesnoj kupovini karte na nekoj trazenoj relaciji.
 * @author Nikola Markovic
 */
public final class Receipt {
    private String dateTimePurchased;
    private int price;

    private String from;
    private String to;
    private String departureTime;
    private String arrivalTime;
    private int numVehicleChanges;

    private String path;

    /**
     * @param price Cijena karte
     * @param from Grad polaska
     * @param to Grad destinacija
     * @param departureTime Vrijeme polaska (String reprezentacija 24-satnog vremena)
     * @param arrivalTime Vrijeme dolaska na destinaciju
     * @param numVehicleChanges Broj presjedanja
     * @param path Putanja
     * @author Nikola Markovic
     */
    public Receipt(int price, String from, String to, String departureTime, String arrivalTime, int numVehicleChanges, String path) {
        this.dateTimePurchased = getCurrentDateTimeString();

        this.price = price;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.numVehicleChanges = numVehicleChanges;
        this.path = path;
    }

    @Override
    public String toString(){
        return "RACUN_" + this.dateTimePurchased;
    }

    /**
     * Metoda koja vraca trenutno vrijeme (vrijeme pozivanja) u citkom String formatu.
     * @return String reprezentacija trenutnog datuma i vremena u obliku <code>yyyy_MM_dd-HH_mm_ss</code>. Na primjer: <code>2025_10_27-13_30_27</code>.
     * @author Nikola Markovic
     */
    private static String getCurrentDateTimeString(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");

        return currentTime.format(format);
    }

    /**
     * Metoda koja vraca vrijeme stampanja racuna u ljudski uljepsanom String formatu.
     * @return Uljepsana String reprezentacija trenutnog datuma i vremena u obliku <code>dd.MM.yyyy u HH:mm;ss</code>. Na primjer: <code>27.10.2025. u 13:30;27</code>.
     * @author Nikola Markovic
     */
    public String getDateTimePretty(){
        String[] args = this.dateTimePurchased.split("-");

        String[] argsDate = args[0].split("_");
        String[] argsTime = args[1].split("_");

        final String day = (argsDate[2].length() == 2) ? argsDate[2] : "0" + argsDate[2];
        final String month = (argsDate[1].length() == 2) ? argsDate[1] : "0" + argsDate[1];
        final String year = argsDate[0];

        final String hrs = (argsTime[0].length() == 2) ? argsTime[0] : "0" + argsTime[0];
        final String min = (argsTime[1].length() == 2) ? argsTime[1] : "0" + argsTime[1];
        final String sec = argsTime[2];

        return day + "." + month + "." + year + "." + " u " + hrs + ":" + min + ";" + sec;
    }

    public String getDateTimePurchased() {
        return dateTimePurchased;
    }

    public void setDateTimePurchased(String dateTimePurchased) {
        this.dateTimePurchased = dateTimePurchased;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getNumVehicleChanges() {
        return numVehicleChanges;
    }

    public void setNumVehicleChanges(int numVehicleChanges) {
        this.numVehicleChanges = numVehicleChanges;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
