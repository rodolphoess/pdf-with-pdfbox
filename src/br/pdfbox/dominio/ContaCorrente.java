package br.pdfbox.dominio;

import java.util.List;

public class ContaCorrente {

    private Cliente cliente;

    private String numeroDaConta;
    private double saldoTotal;
    private double saldoDisponivel;
    private double saldoAnterior;
    private TipoConta tipoDaConta;

    private List<Movimentacao> movimentacoes;

    private ContaCorrente(Cliente cliente, String numeroDaConta, double saldoTotal, double saldoDisponivel, double saldoAnterior, TipoConta tipoDaConta, List<Movimentacao> movimentacoes) {
        this.numeroDaConta = numeroDaConta;
        this.saldoTotal = saldoTotal;
        this.saldoDisponivel = saldoDisponivel;
        this.saldoAnterior = saldoAnterior;
        this.tipoDaConta = tipoDaConta;
        this.cliente = cliente;
        this.movimentacoes = movimentacoes;
    }

    public static ContaCorrente criar(Cliente cliente, String numeroDaConta, double saldoTotal, double saldoDisponivel, double saldoAnterior, TipoConta tipoDaConta, List<Movimentacao> movimentacoes) {
        return new ContaCorrente(cliente, numeroDaConta, saldoTotal, saldoDisponivel, saldoAnterior, tipoDaConta, movimentacoes);
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public double getSaldoAnterior() {
        return saldoAnterior;
    }

    public TipoConta getTipoDaConta() {
        return tipoDaConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

}
