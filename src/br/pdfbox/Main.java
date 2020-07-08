package br.pdfbox;

import br.pdfbox.dominio.ContaCorrente;
import br.pdfbox.factory.ContaCorrenteFactory;
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
            //Parâmetro de entrada para leitura do PDF.
            String path = "../pdf-with-pdfbox/src/br/pdfbox/pdfcontas/extrato-completo.pdf";

            /** Início do serviço geral de leitura de PDF recebendo como parâmetro o path e retornando a String bruta com o conteúdo do PDF. InterPDF.java **/
            long tempoInicial = System.currentTimeMillis();
            File file = new File(path);

            PDDocument document = PDDocument.load(file);

            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            pdfTextStripper.setWordSeparator(" | ");
            pdfTextStripper.setLineSeparator(" || ");

            String conteudo = pdfTextStripper.getText(document);
            long tempoFinal = System.currentTimeMillis();
            /** Final do serviço geral de leitura de PDF. **/

            /** Serviço para leitura de extrato de contas correntes. LeituraExtratoContasCorrentesPDF.java **/
            List<StringBuilder> clientes = quebrarStringPorCliente(conteudo);

            List<ContaCorrente> contasCorrentes = ContaCorrenteFactory.popularContasCorrentes(clientes);

            //TODO: Após retornar uma lista de ContaCorrente, fazer chamada aqui para transformar a lista em JSON.
            /** Final serviço para leitura extrato de contas correntes. **/

            /** Desnecessário até o catch **/
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
