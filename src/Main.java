import input.InputData;
import input.TransportDataGenerator;
import input.TransportDataParser;
import input.types.CountryMap;
import ui.MainWindow;

public class Main {
    public static void main(String[] args) {
        if (TransportDataParser.SetJsonPath("transport_data.json")) {
            InputData.setInputData(TransportDataParser.GenerateCountryMap(), TransportDataParser.GenerateStations(), TransportDataParser.GenerateDepartures());
            new MainWindow();
        }
    }
}