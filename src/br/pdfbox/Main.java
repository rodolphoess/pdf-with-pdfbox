package br.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        try {

            long tempoInicial = System.currentTimeMillis();
            File file = new File("../pdf-with-pdfbox/src/br/pdfbox/pdfcontas/extrato-completo.pdf");

            PDDocument document = PDDocument.load(file);

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            String conteudo = pdfTextStripper.getText(document);
            long tempoFinal = System.currentTimeMillis();

            System.out.println(conteudo);

            long tempoExecucao = tempoFinal - tempoInicial;

            System.out.println("--------------------------------\n\n" + "Tempo de execu��o: " + tempoExecucao + "ms");

        } catch (Exception e) {
            System.out.print(e);
        }

    }

}
