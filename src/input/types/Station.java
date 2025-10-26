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
