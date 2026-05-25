package com.esigelec.model;

import java.sql.Date;

public class Inscription {
    private Long id;
    private Long idEtudiant;
    private Long idSession;
    private Long idCampagne;
    private Date dateInscription;

    public Inscription() {}

    public Inscription(Long idEtudiant, Long idSession, Long idCampagne, Date dateInscription) {
        this.idEtudiant = idEtudiant;
        this.idSession = idSession;
        this.idCampagne = idCampagne;
        this.dateInscription = dateInscription;
    }

    public Inscription(Long id, Long idEtudiant, Long idSession, Long idCampagne, Date dateInscription) {
        this.id = id;
        this.idEtudiant = idEtudiant;
        this.idSession = idSession;
        this.idCampagne = idCampagne;
        this.dateInscription = dateInscription;
    }

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

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "id=" + id +
                ", idEtudiant=" + idEtudiant +
                ", idSession=" + idSession +
                ", idCampagne=" + idCampagne +
                ", dateInscription=" + dateInscription +
                '}';
    }
}
