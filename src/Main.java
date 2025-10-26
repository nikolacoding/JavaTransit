import state.InputData;
import input.TransportDataParser;
import ui.primary.MainWindow;

public class Main {
    public static void main(String[] args) {
        if (TransportDataParser.setJsonPath("transport_data50MP.json")) {
            InputData.setInputData(TransportDataParser.generateCountryMap(), TransportDataParser.generateStations(), TransportDataParser.generateDepartures());
            new MainWindow();
        }
    }
}