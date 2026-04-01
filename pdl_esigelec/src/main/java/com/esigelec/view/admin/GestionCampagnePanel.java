package com.esigelec.view.admin;

import javax.swing.*;
import java.awt.*;

public class GestionCampagnePanel extends JPanel {
    private JButton btnOuvrirCampagne;
    private JButton btnFermerCampagne;
    private JButton btnLancerTraitement;
    private JSpinner nbChoix;
    private JLabel statusLabel;

    public GestionCampagnePanel() {
        this.setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        // Label Choix
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("Nbre de Choix max par étudiant :"), c);

        // Spinner Choix
        nbChoix = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        this.add(nbChoix, c);

        // État de la campagne
        statusLabel = new JLabel("Etat de la campagne : Fermée");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2; // Prend 2 colonnes
        c.anchor = GridBagConstraints.CENTER;
        this.add(statusLabel, c);

        // Boutons
        btnOuvrirCampagne = new JButton("1. Ouvrir les Inscriptions");
        btnFermerCampagne = new JButton("2. Fermer les Inscriptions");
        btnFermerCampagne.setEnabled(false);
        btnLancerTraitement = new JButton("3. Lancer le Traitement Automatique");
        btnLancerTraitement.setBackground(Color.RED);
        btnLancerTraitement.setEnabled(false);

        c.fill = GridBagConstraints.HORIZONTAL; // Étire les boutons
        c.gridwidth = 2;

        c.gridy = 2;
        this.add(btnOuvrirCampagne, c);
        c.gridy = 3;
        this.add(btnFermerCampagne, c);
        c.gridy = 4;
        this.add(btnLancerTraitement, c);
    }

    public JButton getBtnOuvrirCampagne() { return btnOuvrirCampagne; }
    public JButton getBtnFermerCampagne() { return btnFermerCampagne; }
    public JButton getBtnLancerTraitement() { return btnLancerTraitement; }
    public Integer getNbChoixMax() { return (Integer) nbChoix.getValue(); }
    public JLabel getStatusLabel() { return statusLabel; }
}
