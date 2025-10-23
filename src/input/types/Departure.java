package input.types;

public final class Departure {
    private final String type;
    private final String from;
    private final String to;
    private final String departureTime;
    private final int duration;
    private final int price;
    private final int minTransferTime;

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
