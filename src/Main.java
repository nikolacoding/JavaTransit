import input.InputData;
import input.TransportDataParser;
import pathfinding.DepartureUtility;
import ui.primary.MainWindow;

public class Main {
    public static void main(String[] args) {
        if (TransportDataParser.SetJsonPath("transport_dataMP.json")) {
            InputData.setInputData(TransportDataParser.GenerateCountryMap(), TransportDataParser.GenerateStations(), TransportDataParser.GenerateDepartures());
            new MainWindow();
        }
    }
}