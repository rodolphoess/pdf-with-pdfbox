package br.pdfbox;

import br.pdfbox.extratocontacorrentegenial.InterExtratoContasCorrentesGenialPDF;
import br.pdfbox.pdfimport.InterPDF;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {

            long tempoInicial = System.currentTimeMillis(); //TODO: Retirar

            File file = new File("../pdf-with-pdfbox/src/br/pdfbox/pdfcontas/extrato-completo-junho.pdf");

            // In�cio do servi�o geral de leitura de PDF recebendo como par�metro o path e retornando a String bruta com o conte�do do PDF. InterPDF.java
            InterPDF interPDF = new InterPDF();
            String conteudoBrutoPdf = interPDF.extractTextPdf(file);
            // Final do servi�o geral de leitura de PDF.

            // Servi�o para leitura de extrato de contas correntes. InterExtratoContasCorrentesGenialPDF.java
            InterExtratoContasCorrentesGenialPDF contasCorrentes = new InterExtratoContasCorrentesGenialPDF();
            String json = contasCorrentes.extrairInformacoesContasCorrentes(conteudoBrutoPdf);
            // Final servi�o para leitura extrato de contas correntes.

            long tempoFinal = System.currentTimeMillis(); //TODO: Retirar

            System.out.println("\n" + json);

            long tempoExecucao = tempoFinal - tempoInicial;

            System.out.println("\n--------------------------------\n\n" + "Tempo de execu��o: " + tempoExecucao + "ms");

        } catch (IOException e) {
            System.out.print(e);
        }

    }

}
