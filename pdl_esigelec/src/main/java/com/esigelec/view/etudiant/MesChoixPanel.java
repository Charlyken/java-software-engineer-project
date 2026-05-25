package com.esigelec.view.etudiant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MesChoixPanel extends JPanel {
    private DefaultTableModel modelMesVoeux;
    private JTable tableMesChoix;
    private JButton btnRetirerChoix;
    private JButton btnMonterPriorite;
    private JButton btnDescendrePriorite;
    private JButton btnSoumettreChoix;

    public MesChoixPanel() {
        this.setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        // Titre
        c.gridx = 0; c.gridy = 0; c.gridwidth = 3; c.anchor = GridBagConstraints.CENTER;
        JLabel lblTitre = new JLabel("Mes choix Sélectionnés (par priorité)");
        lblTitre.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(lblTitre, c);

        // Tableau avec l'ordre de priorité
        String[] colonnes = {"Priorité", "Dominante", "Horaires", "ID Choix"};
        modelMesVoeux = new DefaultTableModel(null, colonnes) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableMesChoix = new JTable(modelMesVoeux);
        // Cacher la colonne ID pour l'affichage (index 3)
        tableMesChoix.getColumnModel().getColumn(3).setMinWidth(0);
        tableMesChoix.getColumnModel().getColumn(3).setMaxWidth(0);
        tableMesChoix.getColumnModel().getColumn(3).setWidth(0);

        c.gridy = 1;
        c.weightx = 1.0; c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(tableMesChoix), c);

        // Panel de contrôle pour les priorités et suppression
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnMonterPriorite = new JButton("Monter ▲");
        btnDescendrePriorite = new JButton("Descendre ▼");
        btnRetirerChoix = new JButton("<< Retirer le vœu");

        controlPanel.add(btnRetirerChoix);
        controlPanel.add(btnMonterPriorite);
        controlPanel.add(btnDescendrePriorite);

        c.gridy = 2; c.weighty = 0.0; c.fill = GridBagConstraints.HORIZONTAL;
        this.add(controlPanel, c);

        // Bouton Soumettre
        btnSoumettreChoix = new JButton("VALIDER MES VŒUX");
        btnSoumettreChoix.setBackground(new Color(34, 139, 34)); // Vert
        btnSoumettreChoix.setForeground(Color.WHITE);
        c.gridy = 3;
        this.add(btnSoumettreChoix, c);
    }

    public JButton getBtnMonterPriorite() {
        return btnMonterPriorite;
    }

    public JButton getBtnRetirerChoix() {
        return btnRetirerChoix;
    }

    public JButton getBtnDescendrePriorite() {
        return btnDescendrePriorite;
    }

    public JButton getBtnSoumettreChoix() {
        return btnSoumettreChoix;
    }

    public DefaultTableModel getModelMesVoeux() {
        return modelMesVoeux;
    }

    public JTable getTableMesChoix() {
        return tableMesChoix;
    }
}
