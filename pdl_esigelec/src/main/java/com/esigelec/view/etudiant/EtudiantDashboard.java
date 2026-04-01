package com.esigelec.view.etudiant;

import javax.swing.*;
import java.awt.*;

public class EtudiantDashboard extends JFrame {
    private SessionsDisponiblesPanel sessionsPanel;
    private MesChoixPanel choixPanel;
    private ResultatsPanel resultatsPanel;

    public EtudiantDashboard() {
        this.setTitle("Espace Étudiant - Sélection des Dominantes");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1024, 748);
        this.setLocationRelativeTo(null); // Centrer

        sessionsPanel = new SessionsDisponiblesPanel();
        choixPanel = new MesChoixPanel();
        resultatsPanel = new ResultatsPanel();

        buildUI();
    }

    private void buildUI() {
        // Le Header
        JPanel headerPanel = new JPanel();
        JLabel lblHeader = new JLabel("Bienvenue dans votre espace étudiant");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(lblHeader);
        this.add(headerPanel, BorderLayout.NORTH);

        // --- L'onglet Saisie des xhoix avec JSplitPane ---
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                sessionsPanel,
                choixPanel
        );
        splitPane.setDividerLocation(500); // Couper à peu près au milieu
        splitPane.setResizeWeight(0.5);

        // --- Ajout dans le gestionnaire d'onglets ---
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Saisie des Vœux", splitPane);
        tabbedPane.addTab("Mes Résultats", resultatsPanel);

        this.add(tabbedPane, BorderLayout.CENTER);
    }


    public SessionsDisponiblesPanel getSessionsPanel() { return sessionsPanel; }
    public MesChoixPanel getVoeuxPanel() { return choixPanel; }
    public ResultatsPanel getResultatsPanel() { return resultatsPanel; }
}
