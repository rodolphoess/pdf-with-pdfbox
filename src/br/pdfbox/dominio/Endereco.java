package br.pdfbox.dominio;

public class Endereco {

    private String logradouro;
    private String complemento;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;

    private Endereco(String logradouro, String complemento, String numero, String cep, String cidade, String estado) {
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public static Endereco criar(String logradouro, String complemento, String numero, String cep, String cidade, String estado) {
        return new Endereco(logradouro, complemento, numero, cep, cidade, estado);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getNumero() {
        return numero;
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
