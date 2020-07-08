package br.pdfbox.factory;

import br.pdfbox.dominio.Cliente;
import br.pdfbox.dominio.ContaCorrente;
import br.pdfbox.dominio.Endereco;
import br.pdfbox.dominio.Movimentacao;

import java.time.LocalDate;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ContaCorrenteFactory {

    private ContaCorrenteFactory() { }

    public static List<ContaCorrente> popularContasCorrentes(List<StringBuilder> clientes) {

        List<ContaCorrente> contasCorrentes = newArrayList();

        for (StringBuilder cliente : clientes) {

            String nome = extrairNome(cliente);

            String logradouro = extrairLogradouro(cliente);
            String complemento = extrairComplemento(cliente);
            String numero = extrairNumero(cliente);
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

            ContaCorrente contaCorrente = construirContaCorrente(nome, logradouro, complemento, numero, cep, cidade, estado,
                    numeroConta, saldoTotal, saldoDisponivel, saldoAnterior, dataMovimentacao, descricao, valorMovimentado,
                    saldoEmContaAposMovimentacao);

            contasCorrentes.add(contaCorrente);
        }

        return contasCorrentes;
    }

    private static ContaCorrente construirContaCorrente(
                                                        String nome,
                                                        String logradouro,
                                                        String complemento,
                                                        String numero,
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
                                   construirCliente(nome, construirEndereco(logradouro, complemento, numero, cep, cidade, estado)),
                                   numeroConta,
                                   Double.parseDouble(saldoTotal),
                                   Double.parseDouble(saldoDisponivel),
                                   Double.parseDouble(saldoAnterior),
                                   construirMovimentacoes(dataMovimentacao, descricao, valorMovimentado, saldoEmContaAposMovimentacao)
        );
    }

    private static List<Movimentacao> construirMovimentacoes(String dataMovimentacao, String descricao, String valorMovimentado, String saldoEmContaAposMovimentacao) {
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

        return Movimentacao.criar(LocalDate.of(ano, mes, dia), descricao, Double.parseDouble(valorMovimentado), Double.parseDouble(saldoEmContaAposMovimentacao));
    }

    private static Cliente construirCliente(String nome, Endereco endereco) {
        return Cliente.criar(nome, endereco);
    }

    private static Endereco construirEndereco(String logradouro, String complemento, String numero, String cep, String cidade, String estado) {
        return Endereco.criar(logradouro, complemento, numero, cep, cidade, estado);
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
        return "10.0";
    }

    private static String extrairSaldoTotal(StringBuilder cliente) {
        return "10.0";
    }

    private static String extrairSaldoDisponivel(StringBuilder cliente) {
        return "10.0";
    }

    private static String extrairNumeroConta(StringBuilder cliente) {
        return "";
    }

    private static String extrairEstado(StringBuilder cliente) {
        return "";
    }

    private static String extrairCidade(StringBuilder cliente) {
        return "";
    }

    private static String extrairCep(StringBuilder cliente) {
        return "";
    }

    private static String extrairNumero(StringBuilder cliente) {
        return "";
    }

    private static String extrairComplemento(StringBuilder cliente) {
        return "";
    }

    private static String extrairLogradouro(StringBuilder cliente) {
        return "";
    }

    private static String extrairNome(StringBuilder cliente) {

        int indiceInicioNome = 0;

        for (int linha = 0; linha < 5; linha++) {
            indiceInicioNome = cliente.indexOf(" || ", indiceInicioNome + 2);
        }

        int indiceTextoCliente = cliente.indexOf("Cliente");

        return cliente.substring(indiceInicioNome + 4, indiceTextoCliente);
    }

}
