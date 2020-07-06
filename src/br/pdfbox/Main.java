package br.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        try {

            Long tempoInicial = System.currentTimeMillis();
            File file = new File("../pdf-with-pdfbox/src/br/pdfbox/pdf-fundos/extrato-completo.pdf");

            PDDocument document = PDDocument.load(file);

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            String conteudo = pdfTextStripper.getText(document);
            Long tempoFinal = System.currentTimeMillis();

            System.out.println(conteudo);

            Long tempoExecucao = tempoFinal - tempoInicial;

            System.out.println("--------------------------------\n\n" + "Tempo de execução: " + tempoExecucao + "ms");

        } catch (Exception e) {
            System.out.print(e);
        }

    }

}
