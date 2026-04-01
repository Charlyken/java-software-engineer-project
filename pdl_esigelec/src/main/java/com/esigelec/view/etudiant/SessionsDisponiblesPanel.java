package com.esigelec.view.etudiant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SessionsDisponiblesPanel extends JPanel {
    private DefaultTableModel modelSessionsDispos;
    private JTable tableSessionsDispos;
    private JButton btnAjouterChoix;

    public SessionsDisponiblesPanel() {
        this.setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        // Titre
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.CENTER;
        JLabel lblTitre = new JLabel("Sessions Disponibles");
        lblTitre.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(lblTitre, c);

        // Tableau
        String[] colonnes = {"ID", "Dominante", "Horaires"};
        modelSessionsDispos = new DefaultTableModel(null, colonnes) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableSessionsDispos = new JTable(modelSessionsDispos);

        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(tableSessionsDispos), c);

        // Bouton Ajouter
        btnAjouterChoix = new JButton("Ajouter à mes vœux >>");
        c.gridy = 2;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        this.add(btnAjouterChoix, c);
    }

    public DefaultTableModel getModelSessionsDispos() { return modelSessionsDispos; }
    public JTable getTableSessionsDispos() { return tableSessionsDispos; }
    public JButton getBtnAjouterChoix() { return btnAjouterChoix; }
}
