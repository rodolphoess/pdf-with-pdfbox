package br.pdfbox.dominio;

public class Endereco {

    private String logradouro;
    private String cep;
    private String cidade;
    private String estado;

    private Endereco(String logradouro, String cep, String cidade, String estado) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public static Endereco criar(String logradouro, String cep, String cidade, String estado) {
        return new Endereco(logradouro, cep, cidade, estado);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}
