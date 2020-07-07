package br.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static com.google.common.collect.Lists.newArrayList;

public class Main {

    public static void main(String[] args) {

        try {

            String path = "../pdf-with-pdfbox/src/br/pdfbox/pdfcontas/extrato-completo.pdf";

            long tempoInicial = System.currentTimeMillis();
            File file = new File(path);

            PDDocument document = PDDocument.load(file);

            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            pdfTextStripper.setWordSeparator(" | ");
            pdfTextStripper.setLineSeparator(" || ");

            String conteudo = pdfTextStripper.getText(document);
            long tempoFinal = System.currentTimeMillis();

            List<StringBuilder> clientes = quebrarStringPorCliente(conteudo);

            for (StringBuilder cliente : clientes) {
                System.out.println(cliente);
            }

            long tempoExecucao = tempoFinal - tempoInicial;

            System.out.println("--------------------------------\n\n" + "Tempo de execução: " + tempoExecucao + "ms");

        } catch (IOException e) {
            System.out.print(e);
        }

    }

    private static List<StringBuilder> quebrarStringPorCliente(String conteudo) {

        List<StringBuilder> clientes = newArrayList();
        Scanner scanner = new Scanner(conteudo);
        StringBuilder cliente = new StringBuilder();

        while (scanner.hasNextLine()) {

            String linha = scanner.nextLine();
            cliente.append(linha);

            if (linha.equals("")) {
                clientes.add(cliente);

                cliente = new StringBuilder();
            }
        }

        scanner.close();

        return clientes;
    }

}
