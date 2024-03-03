package org.example.entita;

public class Alimento {
    private String id;

    private String nome;

    private Long quantita;

    public Long getQuantita() {
        return quantita;
    }

    public void setQuantita(Long quantita) {
        this.quantita = quantita;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Alimento(String id, String nome, Long quantita) {
        this.id = id;
        this.nome = nome;
        this.quantita = quantita;
    }

    public Alimento() {
    }
}
