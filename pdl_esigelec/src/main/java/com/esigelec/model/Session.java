package com.esigelec.model;

import java.time.*;
import java.sql.Date;

public class Session {
    private Long id;
    private Date date;
    private String heureDebut;
    private String heureFin;
    private int capaciteMax;
    
    private Long  idCampagne;
    private Long idDominante;
    
    public Session() {}
    public Session(int capaciteMax, Long idCampagne, Long idDominante, Date date, String heureDebut, String heureFin) {
        this.capaciteMax = capaciteMax;
        this.idCampagne = idCampagne;
        this.idDominante = idDominante;
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

    public Long getDominante() {
        return idDominante;
    }

    public void setDominante(Long idDominante) {
        this.idDominante = idDominante;
    }

    public Long getCampagne() {
        return idCampagne;
    }

    public void setCampagne(Long idCampagne) {
        this.idCampagne = idCampagne;
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
