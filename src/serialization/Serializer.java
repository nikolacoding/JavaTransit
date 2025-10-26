package serialization;

import org.w3c.dom.Text;
import util.constants.GeneralConstants;
import util.constants.TextConstants;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;

public final class Serializer {

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
