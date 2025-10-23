package input.types;

public final class Station {
    private final String city;
    private final String busStation;
    private final String trainStation;

    public Station(String city, String busStation, String trainStation){
        this.city = city;
        this.busStation = busStation;
        this.trainStation = trainStation;
    }

    @Override
    public String toString(){
        return city + " " + busStation + " " + trainStation;
    }
}
