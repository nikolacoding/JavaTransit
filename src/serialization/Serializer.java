package serialization;

import util.constants.GeneralConstants;
import util.constants.TextConstants;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;

/**
 * Klasa koja sadrzi metode za serijalizaciju i deserijalizaciju ranije serijalizovanih fiskalnih racuna.
 * @author Nikola Markovic
 */
public final class Serializer {

    /**
     * Klasa za serijalizaciju odnosno upis racuna u tekstualni fajl na predodredjenoj relativnoj putanji <code>\GeneralConstants.RECEIPT_PATH</code>.
     * Ukoliko postoji direktorijum, tekstualni fajl se upisuje u njega. Ukoliko ne postoji direktorijum, kreira se pa se potom u njega upisuje tekstualni fajl.
     * @param receipt Racun za serijalizaciju
     * @param statusLabel JLabel objekat koji ce da poprimi tekst koji reprezentuje rezultat serijalizacije nakon pokusaja iste
     * @author Nikola Markovic
     */
    public static void serializeReceipt(Receipt receipt, JLabel statusLabel){
        final String dirPath = GeneralConstants.RECEIPT_PATH;
        final String outputFilePath = dirPath + File.separator + receipt.toString() + ".txt";

        final File dir = new File(dirPath);
        if (!dir.exists())
            dir.mkdir();

        final File outputFile = new File(outputFilePath);

        System.out.println(outputFile.getAbsolutePath());
        if (!outputFile.isDirectory()){
            try (final PrintWriter printWriter = new PrintWriter(outputFile)){
                StringBuilder receiptSb = new StringBuilder();

                receiptSb
                        .append("Vrijeme:\t").append(receipt.getDateTimePretty()).append("\n")
                        .append("Cijena:\t\t").append(receipt.getPrice()).append("\n")
                        .append("\n")
                        .append("Relacija:\t").append(receipt.getFrom()).append(" do ").append(receipt.getTo()).append("\n")
                        .append("Polazak:\t").append(receipt.getDepartureTime()).append("\n")
                        .append("Dolazak:\t").append(receipt.getArrivalTime()).append("\n")
                        .append("Presjedanja:\t").append(receipt.getNumVehicleChanges()).append("\n")
                        .append("Putanja:\t").append(receipt.getPath());

                printWriter.print(receiptSb);

                final String successText = TextConstants.RECEIPT_SUCCESS_TEXT + "'" + outputFile.getAbsolutePath() + "'.";

                statusLabel.setText(successText);
                System.out.println(successText);
            } catch (IOException ioe){
                statusLabel.setText(TextConstants.SERIALIZATION_ERROR_LABEL_TEXT);
                System.out.println(TextConstants.SERIALIZATION_LABEL_LOG_TEXT);
            }
        }
    }

    /**
     * Metoda za deserijalizaciju odnosno ucitavanje svih prethodno izdatih racuna sa putanje <code>\GeneralConstants.RECEIPT_PATH</code> i brojanje
     * njihovog ukupnog broja i sume svih cjenovnih iznosa u njima.
     * @return Dva int podatka na indeksima: <ul>
     *     <li><code>0</code> - ukupan broj racuna</li>
     *     <li><code>1</code> - ukupna cijena svih racuna</li>
     * </ul>
     * @author Nikola Markovic
     */
    public static int[] getSalesInfo(){
        int[] res = new int[]{0, 0};

        File dir = new File(GeneralConstants.RECEIPT_PATH);
        try {
            Files.walk(dir.toPath())
                    .filter(p -> p.toFile().getName().startsWith("RACUN"))
                    .forEach(file -> {

                        res[0]++;

                        try {
                            Files.lines(file)
                                    .filter(line -> line.startsWith("Cijena:"))
                                    .map(line -> line.replace(" ", ""))
                                    .map(line -> line.replace("\t", ""))
                                    .forEach(cijenaLine -> res[1] += Integer.parseInt(cijenaLine.substring(7)));
                        } catch (IOException ioe) {
                            System.out.println("IOException (na Files.lines())");
                        }
                    });
        } catch (IOException ioe){
            System.out.println("IOException (na Files.walk())");
        }

        return res;
    }
}
