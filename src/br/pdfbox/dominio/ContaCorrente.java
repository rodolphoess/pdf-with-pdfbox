package br.pdfbox.dominio;

import java.math.BigDecimal;
import java.util.List;

public class ContaCorrente {

    private final Cliente cliente;

    private final String instituicaoFinanceira = "GENIAL";

    private final String numeroDaConta;
    private final BigDecimal saldoTotal;
    private final BigDecimal saldoDisponivel;
    private final BigDecimal saldoAnterior;

    private List<Movimentacao> movimentacoes;

    private ContaCorrente(Cliente cliente, String numeroDaConta, BigDecimal saldoTotal, BigDecimal saldoDisponivel, BigDecimal saldoAnterior, List<Movimentacao> movimentacoes) {
        this.numeroDaConta = numeroDaConta;
        this.saldoTotal = saldoTotal;
        this.saldoDisponivel = saldoDisponivel;
        this.saldoAnterior = saldoAnterior;
        this.cliente = cliente;
        this.movimentacoes = movimentacoes;
    }

    public static ContaCorrente criar(Cliente cliente, String numeroDaConta, BigDecimal saldoTotal, BigDecimal saldoDisponivel, BigDecimal saldoAnterior, List<Movimentacao> movimentacoes) {
        return new ContaCorrente(cliente, numeroDaConta, saldoTotal, saldoDisponivel, saldoAnterior, movimentacoes);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public BigDecimal getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public BigDecimal getSaldoAnterior() {
        return saldoAnterior;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }
}
