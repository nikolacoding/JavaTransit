package input;

import input.types.CountryMap;
import input.types.Departure;
import input.types.Station;
import util.StringOperations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public final class TransportDataParser {
    public static File jsonFile = null;
    public static Path jsonPath = null;

    public static boolean SetJsonPath(String path){
        jsonFile = new File(path);
        if (jsonFile.exists()) {
            jsonPath = jsonFile.toPath();
            System.out.println("Uspjesno ucitana ulazna JSON datoteka.");
            return true;
        } else {
            System.out.println("Putanja ulazne JSON datoteke nije validna.");
            return false;
        }
    }

    public static void ForEachLine(Consumer<String> action) {
        try {
            Files.lines(jsonPath).forEach(action);
        } catch (IOException ioe){
            System.out.println("Greska pri radu sa JSON-om");
        }
    }

    private static List<String> GetStringsFromJsonOfType(String type){
        List<String> lines = new ArrayList<>();

        try {
            final Scanner fileScanner = new Scanner(jsonFile);

            String startReadingAt = null;
            String stopReadiNgAt = null;

            switch (type.toLowerCase()){
                case "countrymap" -> {
                    startReadingAt = "  \"countryMap\"";
                    stopReadiNgAt = "  ]";
                }
                case "stations" -> {
                    startReadingAt = "  \"stations\"";
                    stopReadiNgAt = "  ]";
                }
                case "departures" -> {
                    startReadingAt = "  \"departures\"";
                    stopReadiNgAt = "  ]";
                }
                default -> {
                    return null;
                }
            }

            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                if (line.startsWith(startReadingAt)){
                    //fileScanner.nextLine();
                    while (!line.startsWith(stopReadiNgAt)){
                        lines.add(line);
                        line = fileScanner.nextLine();
                    }
                }
            }

            lines.removeFirst();
        } catch (Exception e){
            e.printStackTrace();
        }
        return lines;
    }

    public static CountryMap GenerateCountryMap(){
        List<String> lines = GetStringsFromJsonOfType("countrymap");

        if (lines != null) {
            final int n = lines.size();
            final int m = StringOperations.CountChars(lines.getFirst(), 'G');

            final String[][] res = new String[n][m];

            for (int i = 0; i < n; i++){
                res[i] = new String[m];
                final String[] citiesOnLine = StringOperations.ParseStringArray(lines.get(i));

                for (int j = 0; j < m; j++){
                    res[i][j] = citiesOnLine[j].replace(" ", "");
                }
            }

            return new CountryMap(res);
        }

        return null;
    }

    public static List<Departure> GenerateDepartures(){
        List<String> lines = GetStringsFromJsonOfType("departures");
        List<Departure> res = new ArrayList<>();

        if (lines != null){
            lines.forEach(line -> {
                line = line.replace("{", "");
                line = line.replace("}", "");
                line = line.replace(":", "");
                line = line.replace("\"type\"", "");
                line = line.replace("\"from\"", "");
                line = line.replace("\"to\"", "");
                line = line.replace("\"departureTime\"", "");
                line = line.replace("\"duration\"", "");
                line = line.replace("\"price\"", "");
                line = line.replace("\"minTransferTime\"", "");
                line = line.replace(" ", "");
                line = line.replace("\"", "");

                String[] args = line.split(",");
                final Departure newDeparture = new Departure(
                        args[0],
                        args[1],
                        args[2],
                        args[3].substring(0, 2) + ":" + args[3].substring(2, 4),
                        Integer.parseInt(args[4]),
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6])
                );

                res.add(newDeparture);
            });

            return res;
        }

        return null;
    }

    public static List<Station> GenerateStations(){
        List<String> lines = GetStringsFromJsonOfType("stations");
        List<Station> res = new ArrayList<>();

        if (lines != null){
            lines.forEach(line -> {
                line = line.replace("{", "");
                line = line.replace("}", "");
                line = line.replace(":", "");
                line = line.replace("\"city\"", "");
                line = line.replace("\"busStation\"", "");
                line = line.replace("\"trainStation\"", "");
                line = line.replace(" ", "");
                line = line.replace("\"", "");

                String[] args = line.split(",");
                final Station newStation = new Station(
                        args[0],
                        args[1],
                        args[2]
                );

                res.add(newStation);
            });

            return res;
        }

        return null;
    }
}
