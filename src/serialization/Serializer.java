package serialization;

import org.w3c.dom.Text;
import util.constants.GeneralConstants;
import util.constants.TextConstants;

import javax.swing.*;
import java.io.*;

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
}
