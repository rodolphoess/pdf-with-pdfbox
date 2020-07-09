package br.pdfbox.dominio;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Movimentacao {

    private LocalDate dataMovimentacao;
    private String descricao;
    private BigDecimal valorMovimentado;
    private BigDecimal saldoEmContaAposMovimentacao;

    private Movimentacao(LocalDate dataMovimentacao, String descricao, BigDecimal valorMovimentado, BigDecimal saldoEmContaAposMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
        this.descricao = descricao;
        this.valorMovimentado = valorMovimentado;
        this.saldoEmContaAposMovimentacao = saldoEmContaAposMovimentacao;
    }

    public static Movimentacao criar(LocalDate dataMovimentacao, String descricao, BigDecimal valorMovimentado, BigDecimal saldoEmContaAposMovimentacao) {
        return new Movimentacao(dataMovimentacao, descricao, valorMovimentado, saldoEmContaAposMovimentacao);
    }

    public LocalDate getDataMovimentacao() {
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
