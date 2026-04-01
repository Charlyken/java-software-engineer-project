package com.esigelec.model;

import java.time.LocalDate;

public class Campagne {
    private Long id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int nbreChoix;
    private EtatCampagne  etat;


    public Campagne(String nom, LocalDate dateDebut, LocalDate dateFin, int nbreChoix, EtatCampagne etat) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbreChoix = nbreChoix;
        this.etat = etat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtatCampagne getEtat() {
        return etat;
    }

    public void setEtat(EtatCampagne etat) {
        this.etat = etat;
    }

    public int getNbreChoix() {
        return nbreChoix;
    }

    public void setNbreChoix(int nbreChoix) {
        this.nbreChoix = nbreChoix;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
