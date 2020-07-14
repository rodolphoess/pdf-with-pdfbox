package br.pdfbox.pdfimport;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;

public class InterPDF {

    public String extrairTextoPdf(InputStream file) {
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
