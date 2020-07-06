package br.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        try {

            String path = "../pdf-with-pdfbox/src/br/pdfbox/pdfcontas/extrato-completo.pdf";

            long tempoInicial = System.currentTimeMillis();
            File file = new File(path);

            PDDocument document = PDDocument.load(file);

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            String conteudo = pdfTextStripper.getText(document);
            long tempoFinal = System.currentTimeMillis();

            System.out.println(conteudo);

            long tempoExecucao = tempoFinal - tempoInicial;

            System.out.println("--------------------------------\n\n" + "Tempo de execução: " + tempoExecucao + "ms");

        } catch (Exception e) {
            System.out.print(e);
        }

    }

}
