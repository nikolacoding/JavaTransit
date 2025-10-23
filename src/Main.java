import input.InputData;
import input.TransportDataParser;
import ui.MainWindow;

public class Main {
    public static void main(String[] args) {
        if (TransportDataParser.SetJsonPath("transport_dataX.json")) {
            InputData.setInputData(TransportDataParser.GenerateCountryMap(), TransportDataParser.GenerateStations(), TransportDataParser.GenerateDepartures());
            new MainWindow();
        }
    }
}