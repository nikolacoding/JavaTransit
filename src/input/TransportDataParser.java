package input;

import input.types.CountryMap;
import input.types.Departure;
import input.types.Station;
import util.StringOperations;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa statickih metoda za rad sa ulaznim podacima u obliku JSON datoteke generisane u <code>TransportDataGenerator</code>-u.
 * @author Nikola Markovic
 */
public final class TransportDataParser {
    public static File jsonFile = null;
    public static Path jsonPath = null;

    /**
     * @param path Putanja ulazne JSON datoteke
     * @return Status uspjesnosti lociranja ulazne JSON datoteke
     * @author Nikola Markovic
     */
    public static boolean setJsonPath(String path){
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

    /**
     * Metoda za sakupljanje String podataka u odredjenom segmentu ulazne JSON datoteke.
     * @param type Tip podatka ciji String podaci se ekstraktuju. Moze biti:
     *             <ul>
     *             <li><code>"countryMap"</code> - Stringovi koji opisuju ulaznu mapu grada</li>
     *             <li><code>"stations"</code> - Stringovi koji opisuju ulazne stanice</li>
     *             <li><code>"departures"</code> - Stringovi koji opisuju ulazna kretanja vozila</li>
     *             </ul>
     * @return Lista relevantnih Stringova za odabrani tip
     * @author Nikola Markovic
     */
    private static List<String> getStringsFromJsonOfType(String type){
        List<String> lines = new ArrayList<>();

        try {
            final Scanner fileScanner = new Scanner(jsonFile);

            String startReadingAt;
            String stopReadiNgAt;

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

    /**
     * Metoda za generisanje CountryMap objekta u skladu sa ulaznim podacima nad kojima se poziva
     * <code>getStringsFromJsonOfType("countrymap")</code>.
     * @return CountryMap objekat
     * @author Nikola Markovic
     */
    public static CountryMap generateCountryMap(){
        List<String> lines = getStringsFromJsonOfType("countrymap");

        if (lines != null) {
            final int n = lines.size();
            final int m = StringOperations.countChars(lines.getFirst(), 'G');

            final String[][] res = new String[n][m];

            for (int i = 0; i < n; i++){
                res[i] = new String[m];
                final String[] citiesOnLine = StringOperations.parseStringArray(lines.get(i));

                for (int j = 0; j < m; j++){
                    res[i][j] = citiesOnLine[j].replace(" ", "");
                }
            }

            return new CountryMap(res);
        }

        return null;
    }

    /**
     * Metoda za generisanje ArrayList objekta Departure objekata u skladu sa ulaznim podacima nad kojima se poziva
     * <code>getStringsFromJsonOfType("departures")</code>.
     * @return ArrayList (iza interfejsa List) polazaka
     * @author Nikola Markovic
     */
    public static List<Departure> generateDepartures(){
        List<String> lines = getStringsFromJsonOfType("departures");
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

    /**
     * Metoda za generisanje ArrayList objekta Station objekata u skladu sa ulaznim podacima nad kojima se poziva
     * <code>getStringsFromJsonOfType("stations")</code>.
     * @return ArrayList (iza interfejsa List) stanica
     * @author Nikola Markovic
     */
    public static List<Station> generateStations(){
        List<String> lines = getStringsFromJsonOfType("stations");
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
