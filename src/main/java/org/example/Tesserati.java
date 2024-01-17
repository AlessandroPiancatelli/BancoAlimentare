package org.example;

public class Tesserati {
    private String nome;
    private Long codice;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodice() {
        return codice;
    }

    public void setCodice(Long codice) {
        this.codice = codice;
    }

    public Tesserati(String nome, Long codice) {
        this.nome = nome;
        this.codice = codice;
    }

    public Tesserati(){

    }
}
