package com.esigelec.controller;

import com.esigelec.dao.campagne.CampagneDAOImpl;
import com.esigelec.dao.session.SessionDAOImpl;
import com.esigelec.model.Campagne;
import com.esigelec.model.Choix;
import com.esigelec.model.EtatCampagne;
import com.esigelec.model.Utilisateur;
import com.esigelec.model.Inscription;
import com.esigelec.model.Session;
import com.esigelec.service.CampagneService;
import com.esigelec.service.ChoixService;
import com.esigelec.service.InscriptionService;
import com.esigelec.service.SessionService;
import com.esigelec.view.etudiant.EtudiantDashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.esigelec.view.etudiant.AlternativeSessionDialog;

public class EtudiantController {
    private EtudiantDashboard vue;
    private Utilisateur etudiantConnecte;
    private Campagne campagneActive;

    private CampagneService campagneService;
    private SessionService sessionService;
    private ChoixService choixService;
    private InscriptionService inscriptionService;

    public EtudiantController(EtudiantDashboard vue, Utilisateur etudiant) {
        this.vue = vue;
        this.etudiantConnecte = etudiant;

        this.campagneService = new CampagneService(new CampagneDAOImpl());
        this.sessionService = new SessionService(new SessionDAOImpl());
        this.choixService = new ChoixService();
        this.inscriptionService = new InscriptionService();

        trouverCampagneActive();
        initListeners();
        
        if (campagneActive != null) {
            if (campagneActive.getEtat() == EtatCampagne.OUVERTE) {
                chargerSessionsDisponibles();
                chargerMesChoix();
            } else {
                bloquerInterface("La campagne n'est pas actuellement ouverte. Vous pouvez consulter vos résultats.");
                chargerResultats();
            }
        } else {
            bloquerInterface("Aucune campagne active n'est disponible.");
        }
    }

    private void trouverCampagneActive() {
        // Recherche de la première campagne à l'état OUVERTE
        List<Campagne> campagnes = campagneService.getAllCampagnes();
        for (Campagne c : campagnes) {
            if (c.getEtat() == EtatCampagne.OUVERTE) {
                campagneActive = c;
                return;
            }
        }
        // Si aucune ouverte, on regarde s'il y a une campagne fermée (pour l'affichage des résultats plus tard)
        for (Campagne c : campagnes) {
            if (c.getEtat() == EtatCampagne.FERMEE || c.getEtat() == EtatCampagne.VALIDEE) {
                campagneActive = c;
                return;
            }
        }
    }

    private void bloquerInterface(String message) {
        vue.getSessionsPanel().getBtnAjouterChoix().setEnabled(false);
        vue.getVoeuxPanel().getBtnSoumettreChoix().setEnabled(false);
        vue.getVoeuxPanel().getBtnRetirerChoix().setEnabled(false);
        vue.getVoeuxPanel().getBtnMonterPriorite().setEnabled(false);
        vue.getVoeuxPanel().getBtnDescendrePriorite().setEnabled(false);
        
        JOptionPane.showMessageDialog(vue, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void chargerSessionsDisponibles() {
        DefaultTableModel model = vue.getSessionsPanel().getModelSessionsDispos();
        model.setRowCount(0);

        List<Session> sessions = sessionService.getSessionsByCampagne(campagneActive.getId());
        for (Session s : sessions) {
            model.addRow(new Object[]{
                s.getId(),
                "Dominante ID: " + s.getDominante(), // Idéalement, on afficherait le nom de la dominante
                s.getDate() + " [" + s.getHeureDebut() + " - " + s.getHeureFin() + "]"
            });
        }
    }

    private void chargerMesChoix() {
        DefaultTableModel model = vue.getVoeuxPanel().getModelMesVoeux();
        model.setRowCount(0);

        List<Choix> choixList = choixService.getChoixByEtudiantAndCampagne(etudiantConnecte.getId(), campagneActive.getId());
        for (Choix c : choixList) {
            // Récupérer la session correspondante pour afficher les détails
            Session s = sessionService.getSessionById(c.getIdSession());
            String descSession = s != null ? s.getDate() + " [" + s.getHeureDebut() + "-" + s.getHeureFin() + "]" : "ID: " + c.getIdSession();
            String descDominante = s != null ? "Dominante ID: " + s.getDominante() : "";
            
            model.addRow(new Object[]{
                c.getPriorite(),
                descDominante,
                descSession,
                c.getId() // Stocker l'ID du choix en caché ou non
            });
        }
    }

    private void chargerResultats() {
        DefaultTableModel model = vue.getResultatsPanel().getModelResultats();
        model.setRowCount(0);

        if (campagneActive == null) {
            vue.getResultatsPanel().getLblResultatAffectation().setText("Aucun résultat disponible.");
            return;
        }

        List<Inscription> inscriptions = inscriptionService.getInscriptionsByEtudiantAndCampagne(
                etudiantConnecte.getId(),
                campagneActive.getId()
        );

        if (inscriptions.isEmpty()) {
            vue.getResultatsPanel().getLblResultatAffectation().setText("Aucun résultat disponible pour le moment.");
            return;
        }

        vue.getResultatsPanel().getLblResultatAffectation().setText("Résultats de votre affectation.");
        for (Inscription i : inscriptions) {
            Session s = sessionService.getSessionById(i.getIdSession());
            String dominante = s != null ? "Dominante ID: " + s.getDominante() : "Dominante inconnue";
            String date = s != null ? String.valueOf(s.getDate()) : "";
            String horaires = s != null ? s.getHeureDebut() + " - " + s.getHeureFin() : "";

            model.addRow(new Object[]{
                    i.getIdSession(),
                    dominante,
                    date,
                    horaires
            });
        }
    }

    private void initListeners() {
        vue.getSessionsPanel().getBtnAjouterChoix().addActionListener(e -> onAjouterChoix());
        vue.getVoeuxPanel().getBtnRetirerChoix().addActionListener(e -> onRetirerChoix());
        vue.getVoeuxPanel().getBtnMonterPriorite().addActionListener(e -> onChangerPriorite(true));
        vue.getVoeuxPanel().getBtnDescendrePriorite().addActionListener(e -> onChangerPriorite(false));
        vue.getVoeuxPanel().getBtnSoumettreChoix().addActionListener(e -> onSoumettreVoeux());
    }

    private void onAjouterChoix() {
        int row = vue.getSessionsPanel().getTableSessionsDispos().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(vue, "Veuillez sélectionner une session disponible.");
            return;
        }

        Long idSession = (Long) vue.getSessionsPanel().getModelSessionsDispos().getValueAt(row, 0);
        
        // Déterminer la priorité (fin de liste)
        int priorite = vue.getVoeuxPanel().getModelMesVoeux().getRowCount() + 1;
        
        Choix nouveauChoix = new Choix(
            etudiantConnecte.getId(), 
            idSession, 
            campagneActive.getId(), 
            Date.valueOf(LocalDate.now()), 
            priorite
        );

        try {
            // Le service doit gérer detecterConflit() à l'intérieur
            choixService.ajouterChoix(nouveauChoix);
            chargerMesChoix();
            JOptionPane.showMessageDialog(vue, "Vœu ajouté avec succès.");
        } catch (Exception ex) {
            // En cas de conflit, on affiche l'erreur et on peut proposer une alternative
            // On devrait interroger le service pour avoir une liste d'alternatives
            // Ici, pour l'instant, on lance une recherche basique de même dominante
            Session sessionEnConflit = sessionService.getSessionById(idSession);
            List<Session> alternatives = sessionService.getSessionsByCampagne(campagneActive.getId());
            if (sessionEnConflit != null) {
                List<Session> filtrees = new ArrayList<>();
                for (Session s : alternatives) {
                    boolean memeDominante = sessionEnConflit.getDominante() != null
                            && sessionEnConflit.getDominante().equals(s.getDominante());
                    boolean autreSession = s.getId() != null && !s.getId().equals(sessionEnConflit.getId());
                    if (memeDominante && autreSession) {
                        filtrees.add(s);
                    }
                }
                alternatives = filtrees;
            }

            if (alternatives.isEmpty()) {
                JOptionPane.showMessageDialog(vue, "Aucune session alternative disponible.");
                return;
            }
            
            AlternativeSessionDialog dialog = new AlternativeSessionDialog(vue, alternatives, ex.getMessage());
            dialog.setVisible(true);

            if (dialog.isAValide()) {
                Session newSession = dialog.getSessionSelectionnee();
                Choix choixAlternatif = new Choix(
                    etudiantConnecte.getId(), 
                    newSession.getId(), 
                    campagneActive.getId(), 
                    Date.valueOf(LocalDate.now()), 
                    priorite
                );
                try {
                    choixService.ajouterChoix(choixAlternatif);
                    chargerMesChoix();
                    JOptionPane.showMessageDialog(vue, "Vœu alternatif ajouté avec succès.");
                } catch (Exception ex2) {
                    JOptionPane.showMessageDialog(vue, "Erreur lors de l'ajout alternatif: " + ex2.getMessage());
                }
            }
        }
    }

    private void onRetirerChoix() {
        int row = vue.getVoeuxPanel().getTableMesChoix().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un vœu à retirer.");
            return;
        }

        Long idChoix = (Long) vue.getVoeuxPanel().getModelMesVoeux().getValueAt(row, 3);
        try {
            choixService.supprimerChoix(idChoix);
            chargerMesChoix(); // Ce rechargement devrait rééquilibrer les priorités via la logique métier idéalement
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors de la suppression : " + ex.getMessage());
        }
    }

    private void onChangerPriorite(boolean monter) {
        int row = vue.getVoeuxPanel().getTableMesChoix().getSelectedRow();
        if (row == -1) return;
        if (monter && row == 0) return;
        if (!monter && row == vue.getVoeuxPanel().getModelMesVoeux().getRowCount() - 1) return;

        Long idChoix = (Long) vue.getVoeuxPanel().getModelMesVoeux().getValueAt(row, 3);
        int prioriteActuelle = (int) vue.getVoeuxPanel().getModelMesVoeux().getValueAt(row, 0);
        int nouvellePriorite = monter ? prioriteActuelle - 1 : prioriteActuelle + 1;

        try {
            Choix choix = new Choix();
            choix.setId(idChoix);
            choix.setIdEtudiant(etudiantConnecte.getId());
            choix.setIdCampagne(campagneActive.getId());
            choix.setPriorite(prioriteActuelle);

            choixService.modifierOrdre(choix, nouvellePriorite);
            chargerMesChoix();
            
            // Garder la sélection
            int newRow = monter ? row - 1 : row + 1;
            vue.getVoeuxPanel().getTableMesChoix().setRowSelectionInterval(newRow, newRow);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors du changement de priorité : " + ex.getMessage());
        }
    }

    private void onSoumettreVoeux() {
        if (campagneActive == null) {
            JOptionPane.showMessageDialog(vue, "Aucune campagne active.");
            return;
        }
        if (vue.getVoeuxPanel().getModelMesVoeux().getRowCount() < campagneActive.getNbreChoix()) {
            JOptionPane.showMessageDialog(vue, "Vous devez saisir au moins " + campagneActive.getNbreChoix() + " choix.", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        bloquerInterface("Vos vœux ont bien été soumis et sont désormais verrouillés !");
    }
}