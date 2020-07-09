package br.pdfbox;

import br.pdfbox.dominio.ContaCorrente;
import br.pdfbox.factory.ContaCorrenteFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            String path = "../pdf-with-pdfbox/src/br/pdfbox/pdfcontas/extrato-completo-junho.pdf";

            long tempoInicial = System.currentTimeMillis(); //TODO: Retirar

            /** Início do serviço geral de leitura de PDF recebendo como parâmetro o path e retornando a String bruta com o conteúdo do PDF. InterPDF.java **/
            File file = new File(path);

            PDDocument document = PDDocument.load(file);

            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            pdfTextStripper.setWordSeparator(" | ");
            pdfTextStripper.setLineSeparator(" || ");

            String conteudo = pdfTextStripper.getText(document);
            /** Final do serviço geral de leitura de PDF. **/

            /** Serviço para leitura de extrato de contas correntes. LeituraExtratoContasCorrentesPDF.java **/
            List<StringBuilder> clientes = quebrarStringPorCliente(conteudo);

            List<ContaCorrente> contasCorrentes = ContaCorrenteFactory.popularContasCorrentes(clientes);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(contasCorrentes);
            /** Final serviço para leitura extrato de contas correntes. **/

            long tempoFinal = System.currentTimeMillis(); //TODO: Retirar

            /** Desnecessário até o catch **/
            for (StringBuilder cliente : clientes) {
                System.out.println(cliente);
            }
            System.out.println("\n" + json);

            long tempoExecucao = tempoFinal - tempoInicial;

            System.out.println("\n--------------------------------\n\n" + "Tempo de execução: " + tempoExecucao + "ms");

        } catch (IOException e) {
            System.out.print(e);
        }

    }

    private static List<StringBuilder> quebrarStringPorCliente(String conteudo) {

        List<StringBuilder> clientes = newArrayList();
        Scanner scanner = new Scanner(conteudo);
        StringBuilder cliente = new StringBuilder();
        StringBuilder proximoCliente = new StringBuilder();
        boolean clientesIguais = false;

        while (scanner.hasNextLine()) {

            String linha = scanner.nextLine();
            cliente.append(linha);

            if (scanner.hasNextLine()) {
                linha = scanner.nextLine();

                while (scanner.hasNextLine() && linha.equals("")) {
                    linha = scanner.nextLine();
                }

                proximoCliente.append(linha);

                clientesIguais = ContaCorrenteFactory.extrairNome(cliente).equals(ContaCorrenteFactory.extrairNome(proximoCliente));
            }

            if (!clientesIguais) {
                clientes.add(cliente);

                cliente = proximoCliente;
                proximoCliente = new StringBuilder();
            } else {
                cliente.append(proximoCliente);
                proximoCliente = new StringBuilder();

                clientesIguais = false;
            }
        }

        scanner.close();

        return clientes;
    }

}
