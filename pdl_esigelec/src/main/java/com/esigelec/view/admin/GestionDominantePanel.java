package com.esigelec.view.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionDominantePanel extends JPanel {
    private DefaultTableModel dominantesTableModel;
    private JTable tableDominantes;
    private JButton btnAjouterDominante;
    private JButton btnModifierDominante;
    private JButton btnSupprimerDominante;

    public GestionDominantePanel() {
        this.setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        // Modèle de tableau (colonnes adaptées pour les dominantes)
        String[] columns = {"ID", "Sigle", "Description"};
        dominantesTableModel = new DefaultTableModel(null, columns) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableDominantes = new JTable(dominantesTableModel);

        // Positionnement du tableau (il s'étire au maximum)
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        this.add(new JScrollPane(tableDominantes), c);

        // Positionnement des boutons en bas
        c.weighty = 0.0; // espace du bas vide pour les boutons
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 1;
        c.gridy = 1;

        btnAjouterDominante = new JButton("Ajouter une Dominante");
        c.gridx = 0;
        c.anchor = GridBagConstraints.EAST; // position x=0-est
        this.add(btnAjouterDominante, c);

        btnModifierDominante = new JButton("Modifier");
        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(btnModifierDominante, c);

        btnSupprimerDominante = new JButton("Supprimer");
        c.gridx = 2;
        c.anchor = GridBagConstraints.WEST;
        this.add(btnSupprimerDominante, c); // position x=2-ouest
    }

    // Getters pour que le contrôleur puisse interagir
    public DefaultTableModel getDominantesTableModel() { return dominantesTableModel; }
    public JTable getTableDominantes() { return tableDominantes; }
    public JButton getBtnAjouterDominante() { return btnAjouterDominante; }
    public JButton getBtnModifierDominante() { return btnModifierDominante; }
    public JButton getBtnSupprimerDominante() { return btnSupprimerDominante; }
}
