package com.esigelec.view.etudiant;

import javax.swing.*;
import java.awt.*;

public class ResultatsPanel extends JPanel {
    private JLabel lblResultatAffectation;

    public ResultatsPanel() {
        this.setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 20, 20, 20);

        lblResultatAffectation = new JLabel("Les résultats ne sont pas encore disponibles.");
        lblResultatAffectation.setFont(new Font("Arial", Font.BOLD, 16));
        lblResultatAffectation.setForeground(Color.BLUE);

        this.add(lblResultatAffectation, c);
    }

    public JLabel getLblResultatAffectation() { return lblResultatAffectation; }
}
