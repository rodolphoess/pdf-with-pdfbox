package br.pdfbox.dominio;

import java.math.BigDecimal;

public class Movimentacao {

    private String dataMovimentacao;
    private String descricao;
    private BigDecimal valorMovimentado;
    private BigDecimal saldoEmContaAposMovimentacao;

    private Movimentacao(String dataMovimentacao, String descricao, BigDecimal valorMovimentado, BigDecimal saldoEmContaAposMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
        this.descricao = descricao;
        this.valorMovimentado = valorMovimentado;
        this.saldoEmContaAposMovimentacao = saldoEmContaAposMovimentacao;
    }

    public static Movimentacao criar(String dataMovimentacao, String descricao, BigDecimal valorMovimentado, BigDecimal saldoEmContaAposMovimentacao) {
        return new Movimentacao(dataMovimentacao, descricao, valorMovimentado, saldoEmContaAposMovimentacao);
    }

    public String getDataMovimentacao() {
        return dataMovimentacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorMovimentado() {
        return valorMovimentado;
    }

    public BigDecimal getSaldoEmContaAposMovimentacao() {
        return saldoEmContaAposMovimentacao;
    }
}
