package com.esigelec.model;

public class Dominante {
    private Long id;
    private String nomDominante;
    private String description;

    public Dominante() {
    }

    public Dominante(String nomDominante, String description) {
        this.nomDominante = nomDominante;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomDominante() {
        return nomDominante;
    }

    public void setNomDominante(String nomDominante) {
        this.nomDominante = nomDominante;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
