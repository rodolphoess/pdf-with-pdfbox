package br.pdfbox.dominio;

import java.util.List;

public class ContaCorrente {

    private Cliente cliente;

    private String numeroDaConta;
    private double saldoTotal;
    private double saldoDisponivel;
    private double saldoAnterior;

    private List<Movimentacao> movimentacoes;

    private ContaCorrente(Cliente cliente, String numeroDaConta, double saldoTotal, double saldoDisponivel, double saldoAnterior, List<Movimentacao> movimentacoes) {
        this.numeroDaConta = numeroDaConta;
        this.saldoTotal = saldoTotal;
        this.saldoDisponivel = saldoDisponivel;
        this.saldoAnterior = saldoAnterior;
        this.cliente = cliente;
        this.movimentacoes = movimentacoes;
    }

    public static ContaCorrente criar(Cliente cliente, String numeroDaConta, double saldoTotal, double saldoDisponivel, double saldoAnterior, List<Movimentacao> movimentacoes) {
        return new ContaCorrente(cliente, numeroDaConta, saldoTotal, saldoDisponivel, saldoAnterior, movimentacoes);
    }

}
