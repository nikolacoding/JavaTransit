package pathfinding;

public final class DepartureUtility {
    public static String stationToCity(String station){
        return station.replace("A", "G").replace("Z", "G");
    }
}
