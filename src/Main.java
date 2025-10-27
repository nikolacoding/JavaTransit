import serialization.Serializer;
import state.InputData;
import input.TransportDataParser;
import ui.primary.MainWindow;

import java.io.Serial;

public class Main {
    public static void main(String[] args) {
        if (TransportDataParser.setJsonPath("transport_data.json")) {
            InputData.setInputData(TransportDataParser.generateCountryMap(), TransportDataParser.generateStations(), TransportDataParser.generateDepartures());
            new MainWindow();
        }
    }
}