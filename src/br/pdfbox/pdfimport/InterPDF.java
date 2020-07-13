package br.pdfbox.pdfimport;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class InterPDF {

    public String extractTextPdf(File file) {
        String pdfText = "";

        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            pdfTextStripper.setWordSeparator(" | ");
            pdfTextStripper.setLineSeparator(" || ");

            pdfText = pdfTextStripper.getText(document);
        } catch (IOException io) {
//            log.error(io);
        }

        return pdfText;
    }

}
