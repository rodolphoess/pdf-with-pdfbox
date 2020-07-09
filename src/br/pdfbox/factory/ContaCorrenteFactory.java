package br.pdfbox.factory;

import br.pdfbox.dominio.Cliente;
import br.pdfbox.dominio.ContaCorrente;
import br.pdfbox.dominio.Endereco;
import br.pdfbox.dominio.Movimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ContaCorrenteFactory {

    public static final int RETIRA_PIPES_E_ESPACOS = 4;
    public static final int RETIRA_PIPES = 2;

    private ContaCorrenteFactory() { }

    public static List<ContaCorrente> popularContasCorrentes(List<StringBuilder> clientes) {

        List<ContaCorrente> contasCorrentes = newArrayList();

        for (StringBuilder cliente : clientes) {

            String nome = extrairNome(cliente);

            String logradouro = extrairLogradouro(cliente);
            String cep = extrairCep(cliente);
            String cidade = extrairCidade(cliente);
            String estado = extrairEstado(cliente);

            String numeroConta = extrairNumeroConta(cliente);
            String saldoTotal = extrairSaldoTotal(cliente);
            String saldoDisponivel = extrairSaldoDisponivel(cliente);
            String saldoAnterior = extrairSaldoAnterior(cliente);

            String dataMovimentacao = extrairDataMovimentacao(cliente);
            String descricao = extrairDescricaoMovimentacao(cliente);
            String valorMovimentado = extrairValorMovimentado(cliente);
            String saldoEmContaAposMovimentacao = extrairSaldoEmContaAposMovimentacao(cliente);

            ContaCorrente contaCorrente = construirContaCorrente(nome, logradouro, cep, cidade, estado,
                    numeroConta, saldoTotal, saldoDisponivel, saldoAnterior, dataMovimentacao, descricao, valorMovimentado,
                    saldoEmContaAposMovimentacao);

            contasCorrentes.add(contaCorrente);
        }

        return contasCorrentes;
    }

    private static ContaCorrente construirContaCorrente(
                                                        String nome,
                                                        String logradouro,
                                                        String cep,
                                                        String cidade,
                                                        String estado,
                                                        String numeroConta,
                                                        String saldoTotal,
                                                        String saldoDisponivel,
                                                        String saldoAnterior,
                                                        String dataMovimentacao,
                                                        String descricao,
                                                        String valorMovimentado,
                                                        String saldoEmContaAposMovimentacao) {

        return ContaCorrente.criar(
                                   construirCliente(nome, construirEndereco(logradouro, cep, cidade, estado)),
                                   numeroConta,
                                   new BigDecimal(saldoTotal),
                                   new BigDecimal(saldoDisponivel),
                                   new BigDecimal(saldoAnterior),
                                   construirListaMovimentacoes(dataMovimentacao, descricao, valorMovimentado, saldoEmContaAposMovimentacao)
        );
    }

    private static List<Movimentacao> construirListaMovimentacoes(String dataMovimentacao, String descricao, String valorMovimentado, String saldoEmContaAposMovimentacao) {
        List<Movimentacao> movimentacoes = newArrayList();

        Movimentacao movimentacao = construirMovimentacao(dataMovimentacao, descricao, valorMovimentado, saldoEmContaAposMovimentacao);

        movimentacoes.add(movimentacao);

        return movimentacoes;
    }

    private static Movimentacao construirMovimentacao(String dataMovimentacao, String descricao, String valorMovimentado, String saldoEmContaAposMovimentacao) {
        String diaTexto = dataMovimentacao.substring(0, 2);
        String mesTexto = dataMovimentacao.substring(3, 5);
        String anoTexto = dataMovimentacao.substring(6);

        int dia = Integer.parseInt(diaTexto);
        int mes = Integer.parseInt(mesTexto);
        int ano = Integer.parseInt(anoTexto);

        return Movimentacao.criar(LocalDate.of(ano, mes, dia), descricao, new BigDecimal(valorMovimentado), new BigDecimal(saldoEmContaAposMovimentacao));
    }

    private static Cliente construirCliente(String nome, Endereco endereco) {
        return Cliente.criar(nome, endereco);
    }

    private static Endereco construirEndereco(String logradouro, String cep, String cidade, String estado) {
        return Endereco.criar(logradouro, cep, cidade, estado);
    }

    private static String extrairSaldoEmContaAposMovimentacao(StringBuilder cliente) {
        return "10.0";
    }

    private static String extrairValorMovimentado(StringBuilder cliente) {
        return "10.0";
    }

    private static String extrairDescricaoMovimentacao(StringBuilder cliente) {
        return "";
    }

    private static String extrairDataMovimentacao(StringBuilder cliente) {
        return "10/12/2020";
    }

    private static String extrairSaldoAnterior(StringBuilder cliente) {
        int indiceInicioSaldoAnterior = 0;
        for (int linha = 0; linha < 11; linha++) {
            indiceInicioSaldoAnterior = cliente.indexOf(" || ", indiceInicioSaldoAnterior + RETIRA_PIPES);
        }

        int indiceFinalSaldoAnterior = 0;
        for (int linha = 0; linha < 12; linha++) {
            indiceFinalSaldoAnterior = cliente.indexOf(" || ", indiceFinalSaldoAnterior + RETIRA_PIPES);
        }

        String saldo = cliente.substring(indiceInicioSaldoAnterior + RETIRA_PIPES_E_ESPACOS, indiceFinalSaldoAnterior);

        return aplicarFormatacaoBigDecimal(saldo);
    }

    private static String extrairSaldoTotal(StringBuilder cliente) {
        int indiceInicioFraseSaldo = cliente.indexOf("Total Cliente");
        int indiceInicioSaldo = indiceInicioFraseSaldo + 14;

        int indiceFinalFrase = cliente.indexOf("Disponível");

        String saldo = cliente.substring(indiceInicioSaldo, indiceFinalFrase);

        if (saldo.equals("0,00")) {
            return saldo.replace(",", ".");
        }

        int indiceFinalSaldo = indiceFinalFrase - 10;

        saldo = cliente.substring(indiceInicioSaldo, indiceFinalSaldo);

        return aplicarFormatacaoBigDecimal(saldo);
    }

    private static String extrairSaldoDisponivel(StringBuilder cliente) {
        int indiceInicioFraseSaldo = cliente.indexOf("Disponível");
        int indiceInicioSaldo = indiceInicioFraseSaldo + 14;

        int indiceFinalCliente = cliente.length();
        int indiceFinalSaldo = indiceFinalCliente - 10;

        String saldoComTexto = cliente.substring(indiceInicioSaldo, indiceFinalCliente);

        if (saldoComTexto.equals("0,00")) {
            return saldoComTexto.replace(",", ".");
        }

        String saldoString = cliente.substring(indiceInicioSaldo, indiceFinalSaldo);

        return aplicarFormatacaoBigDecimal(saldoString);
    }

    private static String extrairNumeroConta(StringBuilder cliente) {
        final int RETIRA_TEXTO_LINHA = 33;

        int indiceInicioConta = 0;
        for (int linha = 0; linha < 10; linha++) {
            indiceInicioConta = cliente.indexOf(" || ", indiceInicioConta + RETIRA_PIPES);
        }

        int indiceFinalLinhaConta = 0;
        for (int linha = 0; linha < 11; linha++) {
            indiceFinalLinhaConta = cliente.indexOf(" || ", indiceFinalLinhaConta + RETIRA_PIPES);
        }

        String numeroConta = cliente.substring(indiceInicioConta + RETIRA_PIPES_E_ESPACOS, indiceFinalLinhaConta - RETIRA_TEXTO_LINHA);

        String digitos = numeroConta.substring(0, 2);

        numeroConta = numeroConta.replace(digitos, "");

        return numeroConta.concat(digitos);
    }

    private static String extrairEstado(StringBuilder cliente) {
        int indiceFinalEstado = 0;
        for (int linha = 0; linha < 8; linha++) {
            indiceFinalEstado = cliente.indexOf(" || ", indiceFinalEstado + 1);
        }

        return cliente.substring(indiceFinalEstado - 2, indiceFinalEstado);
    }

    private static String extrairCidade(StringBuilder cliente) {
        final int RETIRA_ESTADO = 5;
        final int RETIRA_TRACO = 3;

        int indiceInicioLinhaCidade = 0;
        for (int linha = 0; linha < 7; linha++) {
            indiceInicioLinhaCidade = cliente.indexOf(" || ", indiceInicioLinhaCidade + 1);
        }

        int indiceFinalCidade = 0;
        for (int linha = 0; linha < 8; linha++) {
            indiceFinalCidade = cliente.indexOf(" || ", indiceFinalCidade + 1);
        }

        String linhaCidade = cliente.substring(indiceInicioLinhaCidade + RETIRA_PIPES_E_ESPACOS, indiceFinalCidade);

        int indiceInicioCidade = 0;
        for (int traco = 0; traco < 1; traco++) {
            indiceInicioCidade = linhaCidade.indexOf(" - ", indiceInicioCidade + RETIRA_PIPES);
        }

        return linhaCidade.substring(indiceInicioCidade + RETIRA_TRACO, linhaCidade.length() - RETIRA_ESTADO);
    }

    private static String extrairCep(StringBuilder cliente) {
        final int TAMANHO_CEP_PDF = 15;

        int indiceInicioCep = 0;
        for (int linha = 0; linha < 7; linha++) {
            indiceInicioCep = cliente.indexOf(" || ", indiceInicioCep + RETIRA_PIPES);
        }

        int indiceFimCep = indiceInicioCep + TAMANHO_CEP_PDF;

        String cep = cliente.substring(indiceInicioCep + RETIRA_PIPES_E_ESPACOS, indiceFimCep);

        return cep.replace(" ", "");
    }

    private static String extrairLogradouro(StringBuilder cliente) {
        int indiceInicioLogradouro = 0;
        for (int linha = 0; linha < 6; linha++) {
            indiceInicioLogradouro = cliente.indexOf(" || ", indiceInicioLogradouro + RETIRA_PIPES);
        }

        int indiceFimLogradouro = cliente.indexOf(" || ", indiceInicioLogradouro + RETIRA_PIPES);

        String logradouro = cliente.substring(indiceInicioLogradouro + RETIRA_PIPES_E_ESPACOS, indiceFimLogradouro);

        return logradouro.replace("  ", "");
    }

    public static String extrairNome(StringBuilder cliente) {
        int indiceInicioNome = 0;
        for (int linha = 0; linha < 5; linha++) {
            indiceInicioNome = cliente.indexOf(" || ", indiceInicioNome + RETIRA_PIPES);
        }

        int indiceFimNome = cliente.indexOf("Cliente");

        return cliente.substring(indiceInicioNome + RETIRA_PIPES_E_ESPACOS, indiceFimNome);
    }

    private static String aplicarFormatacaoBigDecimal(String valor) {
        valor = valor.replace(".", "");

        return valor.replace(",", ".");
    }

}
