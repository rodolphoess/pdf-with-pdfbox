package br.pdfbox.dominio;

import java.time.LocalDate;

public class Movimentacao {

    private LocalDate dataMovimentacao;
    private String descricao;
    private double valorMovimentado;
    private double saldoEmContaAposMovimentacao;

    private Movimentacao(LocalDate dataMovimentacao, String descricao, double valorMovimentado, double saldoEmContaAposMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
        this.descricao = descricao;
        this.valorMovimentado = valorMovimentado;
        this.saldoEmContaAposMovimentacao = saldoEmContaAposMovimentacao;
    }

    public static Movimentacao criar(LocalDate dataMovimentacao, String descricao, double valorMovimentado, double saldoEmContaAposMovimentacao) {
        return new Movimentacao(dataMovimentacao, descricao, valorMovimentado, saldoEmContaAposMovimentacao);
    }

}
