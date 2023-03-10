package br.pdfbox.dominio;

import java.math.BigDecimal;

public class Movimentacao {

    private final String dataMovimentacao;
    private final BigDecimal valorMovimentado;
    private final BigDecimal saldoEmContaAposMovimentacao;

    private Movimentacao(String dataMovimentacao, BigDecimal valorMovimentado, BigDecimal saldoEmContaAposMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
        this.valorMovimentado = valorMovimentado;
        this.saldoEmContaAposMovimentacao = saldoEmContaAposMovimentacao;
    }

    public static Movimentacao criar(String dataMovimentacao, BigDecimal valorMovimentado, BigDecimal saldoEmContaAposMovimentacao) {
        return new Movimentacao(dataMovimentacao, valorMovimentado, saldoEmContaAposMovimentacao);
    }

    public String getDataMovimentacao() {
        return dataMovimentacao;
    }

    public BigDecimal getValorMovimentado() {
        return valorMovimentado;
    }

    public BigDecimal getSaldoEmContaAposMovimentacao() {
        return saldoEmContaAposMovimentacao;
    }
}
