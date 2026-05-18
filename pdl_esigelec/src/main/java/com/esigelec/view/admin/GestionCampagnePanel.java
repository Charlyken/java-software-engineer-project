package com.esigelec.view.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionCampagnePanel extends JPanel {
    private JButton btnOuvrirCampagne;
    private JButton btnFermerCampagne;
    private JButton btnLancerTraitement;

    // Champs CRUD
    private JTextField txtNom;
    private JTextField txtDateDebut;
    private JTextField txtDateFin;
    private JSpinner nbChoix;
    
    // Boutons CRUD
    private JButton btnCreer;
    private JButton btnModifier;
    private JButton btnSupprimer;
    private JButton btnReset;

    private JLabel statusLabel;
    private JTable tableCampagnes;
    private DefaultTableModel tableModel;

    public GestionCampagnePanel() {
        this.setLayout(new BorderLayout());
        buildUI();
    }

    private void buildUI() {
        // --- 1. Formulaire (Haut) ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Gestion des Campagnes"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        formPanel.add(new JLabel("Nom de la campagne :"), c);
        txtNom = new JTextField(15);
        c.gridx = 1;
        formPanel.add(txtNom, c);

        c.gridx = 0; c.gridy = 1;
        formPanel.add(new JLabel("Date de début (YYYY-MM-DD):"), c);
        txtDateDebut = new JTextField(10);
        c.gridx = 1;
        formPanel.add(txtDateDebut, c);

        c.gridx = 2; c.gridy = 1;
        formPanel.add(new JLabel("Date de fin (YYYY-MM-DD):"), c);
        txtDateFin = new JTextField(10);
        c.gridx = 3;
        formPanel.add(txtDateFin, c);

        c.gridx = 0; c.gridy = 2;
        formPanel.add(new JLabel("Choix max/étudiant :"), c);
        nbChoix = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        c.gridx = 1;
        formPanel.add(nbChoix, c);

        // --- Boutons CRUD ---
        JPanel crudButtonsPanel = new JPanel();
        btnCreer = new JButton("Créer");
        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");
        btnReset = new JButton("Vider");
        crudButtonsPanel.add(btnCreer);
        crudButtonsPanel.add(btnModifier);
        crudButtonsPanel.add(btnSupprimer);
        crudButtonsPanel.add(btnReset);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 4;
        formPanel.add(crudButtonsPanel, c);

        this.add(formPanel, BorderLayout.NORTH);

        // --- 2. Table (Centre) ---
        String[] columns = {"ID", "Nom", "Début", "Fin", "Choix Max", "État"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableCampagnes = new JTable(tableModel);
        this.add(new JScrollPane(tableCampagnes), BorderLayout.CENTER);

        // --- 3. Contrôle de la campagne (Bas) ---
        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createTitledBorder("Pilotage de la campagne sélectionnée"));
        
        statusLabel = new JLabel("Sélectionnez une campagne");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        controlPanel.add(statusLabel);

        btnOuvrirCampagne = new JButton("Ouvrir");
        btnFermerCampagne = new JButton("Fermer");
        btnLancerTraitement = new JButton("Lancer Algorithme");
        btnLancerTraitement.setBackground(Color.RED);
        btnLancerTraitement.setForeground(Color.WHITE);

        controlPanel.add(btnOuvrirCampagne);
        controlPanel.add(btnFermerCampagne);
        controlPanel.add(btnLancerTraitement);

        this.add(controlPanel, BorderLayout.SOUTH);
    }

    // Getters pour les événements et données
    public JTextField getTxtNom() { return txtNom; }
    public JTextField getTxtDateDebut() { return txtDateDebut; }
    public JTextField getTxtDateFin() { return txtDateFin; }
    public Integer getNbChoixMax() { return (Integer) nbChoix.getValue(); }
    public JSpinner getNbChoixSpinner() { return nbChoix; }
    
    public JButton getBtnCreer() { return btnCreer; }
    public JButton getBtnModifier() { return btnModifier; }
    public JButton getBtnSupprimer() { return btnSupprimer; }
    public JButton getBtnReset() { return btnReset; }

    public JButton getBtnOuvrirCampagne() { return btnOuvrirCampagne; }
    public JButton getBtnFermerCampagne() { return btnFermerCampagne; }
    public JButton getBtnLancerTraitement() { return btnLancerTraitement; }
    
    public JLabel getStatusLabel() { return statusLabel; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTable getTableCampagnes() { return tableCampagnes; }
}
