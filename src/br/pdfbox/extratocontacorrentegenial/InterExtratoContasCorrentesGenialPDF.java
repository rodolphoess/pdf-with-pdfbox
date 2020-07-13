package br.pdfbox.extratocontacorrentegenial;

import br.pdfbox.dominio.ContaCorrente;
import br.pdfbox.factory.ContaCorrenteFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Scanner;

import static com.google.common.collect.Lists.newArrayList;

public class InterExtratoContasCorrentesGenialPDF {

    public String extrairInformacoesContasCorrentes(String conteudo) {
        String json = "";
        try {
            List<StringBuilder> clientes = separarStringPorCliente(conteudo);

            ContaCorrenteFactory contaCorrenteFactory = new ContaCorrenteFactory();
            List<ContaCorrente> contasCorrentes = contaCorrenteFactory.popularContasCorrentes(clientes);

            ObjectMapper mapper = new ObjectMapper();

            json = mapper.writeValueAsString(contasCorrentes);
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }

        return json;
    }

    private List<StringBuilder> separarStringPorCliente(String conteudo) {

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
