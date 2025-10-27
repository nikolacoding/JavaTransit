package org.unibl.etf.pj2.app;

import org.unibl.etf.pj2.app.state.InputData;
import org.unibl.etf.pj2.app.input.TransportDataParser;
import org.unibl.etf.pj2.app.ui.primary.MainWindow;

public class Main {
    public static void main(String[] args) {
        if (TransportDataParser.setJsonPath("transport_dataMP.json")) {
            InputData.setInputData(TransportDataParser.generateCountryMap(), TransportDataParser.generateStations(), TransportDataParser.generateDepartures());
            new MainWindow();
        }
    }
}