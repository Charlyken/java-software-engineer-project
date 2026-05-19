package com.esigelec.model;

import java.sql.Date;

public class Choix {
    private Long id;
    private Long idEtudiant;
    private Long idSession;
    private Long idCampagne;
    private Date dateSaisie;
    private int priorite;

    public Choix() {}

    public Choix(Long idEtudiant, Long idSession, Long idCampagne, Date dateSaisie, int priorite) {
        this.idEtudiant = idEtudiant;
        this.idSession = idSession;
        this.idCampagne = idCampagne;
        this.dateSaisie = dateSaisie;
        this.priorite = priorite;
    }

    public Choix(Long id, Long idEtudiant, Long idSession, Long idCampagne, Date dateSaisie, int priorite) {
        this.id = id;
        this.idEtudiant = idEtudiant;
        this.idSession = idSession;
        this.idCampagne = idCampagne;
        this.dateSaisie = dateSaisie;
        this.priorite = priorite;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Long idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Long getIdSession() {
        return idSession;
    }

    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    public Long getIdCampagne() {
        return idCampagne;
    }

    public void setIdCampagne(Long idCampagne) {
        this.idCampagne = idCampagne;
    }

    public Date getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    @Override
    public String toString() {
        return "Choix{" +
                "id=" + id +
                ", idEtudiant=" + idEtudiant +
                ", idSession=" + idSession +
                ", idCampagne=" + idCampagne +
                ", dateSaisie=" + dateSaisie +
                ", priorite=" + priorite +
                '}';
    }
}
