package com.esigelec.view.etudiant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ResultatsPanel extends JPanel {
    private JLabel lblResultatAffectation;
    private DefaultTableModel modelResultats;
    private JTable tableResultats;

    public ResultatsPanel() {
        this.setLayout(new BorderLayout(10, 10));
        buildUI();
    }

    private void buildUI() {
        lblResultatAffectation = new JLabel("Les résultats ne sont pas encore disponibles.");
        lblResultatAffectation.setFont(new Font("Arial", Font.BOLD, 16));
        lblResultatAffectation.setForeground(Color.BLUE);
        lblResultatAffectation.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        String[] colonnes = {"Session", "Dominante", "Date", "Horaires"};
        modelResultats = new DefaultTableModel(null, colonnes) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableResultats = new JTable(modelResultats);

        this.add(lblResultatAffectation, BorderLayout.NORTH);
        this.add(new JScrollPane(tableResultats), BorderLayout.CENTER);
    }

    public JLabel getLblResultatAffectation() { return lblResultatAffectation; }
    public DefaultTableModel getModelResultats() { return modelResultats; }
    public JTable getTableResultats() { return tableResultats; }
}
