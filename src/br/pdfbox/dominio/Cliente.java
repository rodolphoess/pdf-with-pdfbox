package br.pdfbox.dominio;

public class Cliente {

    private String nome;
    private Endereco endereco;

    private Cliente(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public static Cliente criar(String nome, Endereco endereco) {
        return new Cliente(nome, endereco);
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

}
