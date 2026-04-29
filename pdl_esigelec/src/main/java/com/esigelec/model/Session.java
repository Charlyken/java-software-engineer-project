package com.esigelec.model;

import java.time.*;
import java.sql.Date;

public class Session {
    private Long id;
    private Date date;
    private String heureDebut;
    private String heureFin;
    private int capaciteMax;
    
    private Campagne campagne;
    private Dominante dominante;

    public Session(int capaciteMax, Campagne campagne, Dominante dominante, Date date, String heureDebut, String heureFin) {
        this.capaciteMax = capaciteMax;
        this.campagne = campagne;
        this.dominante = dominante;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dominante getDominante() {
        return dominante;
    }

    public void setDominante(Dominante dominante) {
        this.dominante = dominante;
    }

    public Campagne getCampagne() {
        return campagne;
    }

    public void setCampagne(Campagne campagne) {
        this.campagne = campagne;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    


    
}
