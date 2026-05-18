package com.esigelec.controller;

import com.esigelec.dao.campagne.CampagneDAO;
import com.esigelec.dao.campagne.CampagneDAOImpl;
import com.esigelec.dao.dominante.DominanteDAO;
import com.esigelec.dao.dominante.DominanteDAOImpl;
import com.esigelec.dao.session.SessionDAO;
import com.esigelec.dao.session.SessionDAOImpl;
import com.esigelec.model.Campagne;
import com.esigelec.model.Dominante;
import com.esigelec.model.Session;
import com.esigelec.service.CampagneService;
import com.esigelec.service.SessionService;
import com.esigelec.view.admin.AdminDashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.List;

public class AdminController {
    private AdminDashboard vue;
    private DominanteDAO dominanteDAO;
    private SessionDAO sessionDAO;
    private CampagneDAO campagneDAO;
    private CampagneService campagneService;
    private SessionService sessionService;

    public AdminController(AdminDashboard vue) {
        this.vue = vue;
        this.dominanteDAO = new DominanteDAOImpl();
        this.sessionDAO = new SessionDAOImpl();
        this.campagneDAO = new CampagneDAOImpl();
        this.campagneService = new CampagneService(campagneDAO);
        this.sessionService = new SessionService(sessionDAO);
        initListeners();
        chargerDominantes(); // chargement du tableau au démarrage
    }

    /**
     * Actualise ma page dominantes
     */
    private void chargerDominantes() {
        // 1. On nettoie le tableau actuel
        DefaultTableModel model = vue.getDominantePanel().getDominantesTableModel();
        model.setRowCount(0);

        // 2. On demande au DAO (BDD) la liste des dominantes
        List<Dominante> liste = dominanteDAO.getAllDominantes();

        // 3. On ajoute chaque ligne dans la vue
        for (Dominante d : liste) {
            model.addRow(new Object[]{d.getId(), d.getNomDominante(), d.getDescription()});
        }
    }

    /**
     * Ajout des dominantes
     */
    private void initListeners() {
        initDominanteListeners();
        initCampagneListeners();
        initSessionListeners();
    }

    private void initDominanteListeners() {
        vue.getDominantePanel().getBtnAjouterDominante().addActionListener(e -> onAjouterDominante());
        vue.getDominantePanel().getBtnSupprimerDominante().addActionListener(e -> onSupprimerDominante());
        vue.getDominantePanel().getBtnModifierDominante().addActionListener(e -> onModifierDominante());
    }

    private void initCampagneListeners() {
        vue.getCampagnePanel().getBtnOuvrirCampagne().addActionListener(e -> onOuvrirCampagne());
        vue.getCampagnePanel().getBtnFermerCampagne().addActionListener(e -> onFermerCampagne());
        vue.getCampagnePanel().getBtnLancerTraitement().addActionListener(e -> onLancerTraitement());
    }

    private void initSessionListeners() {
        vue.getSessionPanel().getBtnCreer().addActionListener(e -> onCreerSession());
        vue.getSessionPanel().getBtnModifier().addActionListener(e -> onModifierSession());
        vue.getSessionPanel().getBtnSupprimer().addActionListener(e -> onSupprimerSession());
        vue.getSessionPanel().getBtnReset().addActionListener(e -> onResetSessionForm());
    }

    private void onAjouterDominante() {
        JTextField nomField = new JTextField(15);
        JTextField descField = new JTextField(15);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nom :"));
        panel.add(nomField);
        panel.add(new JLabel("Description :"));
        panel.add(descField);

        int result = JOptionPane.showConfirmDialog(vue, panel,
                "Ajouter une dominante", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String nomDominante = nomField.getText();
            String description = descField.getText();

            if (nomDominante != null && !nomDominante.isEmpty()) {
                Dominante d = new Dominante();
                d.setNomDominante(nomDominante);
                d.setDescription(description);

                dominanteDAO.createDominante(d);
                chargerDominantes();
            }
        }
    }

    private void onSupprimerDominante() {
        JTable table = vue.getDominantePanel().getTableDominantes();
        int selectedRow = getSelectedRow(table, "Veuillez sélectionner une dominante à supprimer.");
        if (selectedRow == -1) {
            return;
        }

        Long id = (Long) table.getValueAt(selectedRow, 0);
        dominanteDAO.deleteDominante(id);
        chargerDominantes();
    }

    private void onModifierDominante() {
        JTable table = vue.getDominantePanel().getTableDominantes();
        int selectedRow = getSelectedRow(table, "Veuillez sélectionner une dominante à modifier.");
        if (selectedRow == -1) {
            return;
        }

        Long id = (Long) table.getValueAt(selectedRow, 0);
        String nomActuel = (String) table.getValueAt(selectedRow, 1);
        String descActuelle = (String) table.getValueAt(selectedRow, 2);

        JTextField nomField = new JTextField(nomActuel, 15);
        JTextField descField = new JTextField(descActuelle, 30);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nom :"));
        panel.add(nomField);
        panel.add(new JLabel("Description :"));
        panel.add(descField);

        int result = JOptionPane.showConfirmDialog(vue, panel,
                "Modifier la dominante", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String nouveauNom = nomField.getText();
            String nouvelleDesc = descField.getText();

            if (nouveauNom != null && !nouveauNom.isEmpty()) {
                Dominante d = new Dominante();
                d.setId(id);
                d.setNomDominante(nouveauNom);
                d.setDescription(nouvelleDesc);

                dominanteDAO.updateDominante(d);
                chargerDominantes();
            }
        }
    }

    private int getSelectedRow(JTable table, String emptySelectionMessage) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(vue, emptySelectionMessage);
        }
        return selectedRow;
    }



    private void onOuvrirCampagne() {
        Long idCampagne = demanderIdCampagne();
        if (idCampagne == null) {
            return;
        }

        try {
            campagneService.ouvrirCampagne(idCampagne);
            vue.getCampagnePanel().getStatusLabel().setText("Etat de la campagne : Ouverte");
            vue.getCampagnePanel().getBtnOuvrirCampagne().setEnabled(false);
            vue.getCampagnePanel().getBtnFermerCampagne().setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vue, e.getMessage());
        }
    }

    private void onFermerCampagne() {
        Long idCampagne = demanderIdCampagne();
        if (idCampagne == null) {
            return;
        }

        try {
            campagneService.fermerCampagne(idCampagne);
            vue.getCampagnePanel().getStatusLabel().setText("Etat de la campagne : Fermee");
            vue.getCampagnePanel().getBtnFermerCampagne().setEnabled(false);
            vue.getCampagnePanel().getBtnLancerTraitement().setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vue, e.getMessage());
        }
    }

    private void onLancerTraitement() {
        JOptionPane.showMessageDialog(vue, "Traitement automatique non implemente.");
    }

    private Long demanderIdCampagne() {
        String input = JOptionPane.showInputDialog(vue, "ID de la campagne :");
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vue, "ID campagne invalide.");
            return null;
        }
    }

    

    private void onCreerSession() {
        Session session = lireSessionDepuisFormulaire();
        if (session == null) {
            return;
        }

        try {
            sessionService.creerSession(
                    session.getCapaciteMax(),
                    session.getCampagne(),
                    session.getDominante(),
                    session.getDate(),
                    session.getHeureDebut(),
                    session.getHeureFin()
            );
            rechargerSessions(session.getCampagne());
            JOptionPane.showMessageDialog(vue, "Session creee.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vue, e.getMessage());
        }
    }

    private void onModifierSession() {
        JTable table = vue.getSessionPanel().getSessionTable();
        int selectedRow = getSelectedRow(table, "Selectionnez une session a modifier.");
        if (selectedRow == -1) {
            return;
        }

        Object idValue = table.getValueAt(selectedRow, 0);
        if (idValue == null) {
            JOptionPane.showMessageDialog(vue, "ID session manquant.");
            return;
        }

        Session session = lireSessionDepuisFormulaire();
        if (session == null) {
            return;
        }
        session.setId((Long) idValue);

        try {
            sessionService.updateSession(session);
            rechargerSessions(session.getCampagne());
            JOptionPane.showMessageDialog(vue, "Session modifiee.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vue, e.getMessage());
        }
    }

    private void onSupprimerSession() {
        JTable table = vue.getSessionPanel().getSessionTable();
        int selectedRow = getSelectedRow(table, "Selectionnez une session a supprimer.");
        if (selectedRow == -1) {
            return;
        }

        Object idValue = table.getValueAt(selectedRow, 0);
        if (idValue == null) {
            JOptionPane.showMessageDialog(vue, "ID session manquant.");
            return;
        }

        Long id = (Long) idValue;
        try {
            sessionService.deleteSession(id);
            Object idCampagneValue = table.getValueAt(selectedRow, 5);
            Long idCampagne = idCampagneValue instanceof Long ? (Long) idCampagneValue : null;
            rechargerSessions(idCampagne);
            JOptionPane.showMessageDialog(vue, "Session supprimee.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vue, e.getMessage());
        }
    }

    private void onResetSessionForm() {
        vue.getSessionPanel().getDateField().setText("");
        vue.getSessionPanel().getHeureDebutField().setText("");
        vue.getSessionPanel().getHeureFinField().setText("");
        vue.getSessionPanel().getCapaciteField().setText("");
        vue.getSessionPanel().getIdCampagneField().setText("");
        vue.getSessionPanel().getIdDominanteField().setText("");
    }

    private Session lireSessionDepuisFormulaire() {
        try {
            String dateStr = vue.getSessionPanel().getDateField().getText().trim();
            String heureDebut = vue.getSessionPanel().getHeureDebutField().getText().trim();
            String heureFin = vue.getSessionPanel().getHeureFinField().getText().trim();
            int capacite = Integer.parseInt(vue.getSessionPanel().getCapaciteField().getText().trim());
            Long idCampagne = Long.parseLong(vue.getSessionPanel().getIdCampagneField().getText().trim());
            Long idDominante = Long.parseLong(vue.getSessionPanel().getIdDominanteField().getText().trim());

            Date date = Date.valueOf(dateStr);
            return new Session(capacite, idCampagne, idDominante, date, heureDebut, heureFin);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(vue, "Champs invalides dans le formulaire de session.");
            return null;
        }
    }

    private void rechargerSessions(Long idCampagne) {
        if (idCampagne == null) {
            return;
        }

        DefaultTableModel model = vue.getSessionPanel().getTableModel();
        model.setRowCount(0);
        List<Session> sessions = sessionService.getSessionsByCampagne(idCampagne);
        for (Session session : sessions) {
            model.addRow(new Object[]{
                    session.getId(),
                    session.getDate(),
                    session.getHeureDebut(),
                    session.getHeureFin(),
                    session.getCapaciteMax(),
                    session.getCampagne(),
                    session.getDominante()
            });
        }
    }
}

