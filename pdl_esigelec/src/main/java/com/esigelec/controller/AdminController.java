package com.esigelec.controller;

import com.esigelec.dao.dominante.DominanteDAO;
import com.esigelec.dao.dominante.DominanteDAOImpl;
import com.esigelec.model.Dominante;
import com.esigelec.view.admin.AdminDashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AdminController {
    private AdminDashboard vue;
    private DominanteDAO dominanteDAO;

    public AdminController(AdminDashboard vue) {
        this.vue = vue;
        this.dominanteDAO = new DominanteDAOImpl();

        initListeners();
        chargerDominantes(); // On remplit le tableau au démarrage (Read)
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
        // Action : Clic sur le bouton "Ajouter"
        vue.getDominantePanel().getBtnAjouterDominante().addActionListener(e -> {
                        // 1. Créer un panneau personnalisé et les champs de saisie
                        JTextField nomField = new JTextField(15);
                        JTextField descField = new JTextField(15);

                        JPanel panel = new JPanel();
                        panel.add(new JLabel("Nom :"));
                        panel.add(nomField);
                        panel.add(new JLabel("Description :"));
                        panel.add(descField);

                        // 2. Afficher la boîte de dialogue avec le panneau
                        int result = JOptionPane.showConfirmDialog(vue, panel,
                                "Ajouter une dominante", JOptionPane.OK_CANCEL_OPTION);

                        if (result == JOptionPane.OK_OPTION) {
                            String nomDominante = nomField.getText();
                            String description = descField.getText();

                            if (nomDominante != null && !nomDominante.isEmpty()) {
                                // 3. Créer l'objet
                                Dominante d = new Dominante();
                                d.setNomDominante(nomDominante);
                                d.setDescription(description); // Nouveau champ

                                // 4. Sauvegarder en base via le DAO
                                dominanteDAO.createDominante(d);

                                // 5. Rafraîchir le tableau
                                chargerDominantes();
                            }
                        }


        });


        // Action : Clic sur le bouton "Supprimer"
        vue.getDominantePanel().getBtnSupprimerDominante().addActionListener(e -> {
            JTable table = vue.getDominantePanel().getTableDominantes();
            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) { // Si une ligne est bien sélectionnée
                // 1. Récupérer l'ID de la ligne sélectionnée (colonne 0)
                Long id = (Long) table.getValueAt(selectedRow, 0);

                // 2. Supprimer en BDD
                dominanteDAO.deleteDominante(id);

                // 3. Rafraîchir la vue
                chargerDominantes();
            } else {
                JOptionPane.showMessageDialog(vue, "Veuillez sélectionner une dominante à supprimer.");
            }
        });

        // Action : Clic sur le bouton "Modifier"
        vue.getDominantePanel().getBtnModifierDominante().addActionListener(e -> {
            JTable table = vue.getDominantePanel().getTableDominantes();
            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) { // Si une ligne est sélectionnée
                // 1. Récupérer les données actuelles de la ligne
                Long id = (Long) table.getValueAt(selectedRow, 0);
                String nomActuel = (String) table.getValueAt(selectedRow, 1);
                String descActuelle = (String) table.getValueAt(selectedRow, 2);

                // 2. Préparer les champs pré-remplis
                JTextField nomField = new JTextField(nomActuel, 15);
                JTextField descField = new JTextField(descActuelle, 30);

                JPanel panel = new JPanel();
                panel.add(new JLabel("Nom :"));
                panel.add(nomField);
                panel.add(new JLabel("Description :"));
                panel.add(descField);

                // 3. Afficher la boîte de dialogue
                int result = JOptionPane.showConfirmDialog(vue, panel,
                        "Modifier la dominante", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    String nouveauNom = nomField.getText();
                    String nouvelleDesc = descField.getText();

                    if (nouveauNom != null && !nouveauNom.isEmpty()) {
                        // 4. Mettre à jour l'objet avec l'ID conservé !
                        Dominante d = new Dominante();
                        d.setId(id);
                        d.setNomDominante(nouveauNom);
                        d.setDescription(nouvelleDesc);

                        // 5. Appeler le DAO pour l'Update en BDD
                        dominanteDAO.updateDominante(d);

                        // 6. Rafraîchir le tableau
                        chargerDominantes();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(vue, "Veuillez sélectionner une dominante à modifier.");
            }
        });

    }
}

