package com.esigelec.view.admin;

import javax.swing.*;
import java.awt.*;
/**
 * @author Julien
 */
public class AdminDashboard extends JFrame {
      private GestionCampagnePanel campagnePanel;
      private GestionSessionPanel sessionPanel;
      private GestionDominantePanel dominantePanel;
   // private StatistiquesPanel statsPanel;

    public AdminDashboard() {
        this.setTitle("Espace Administrateur");
        this.setLayout(new BorderLayout()); // Optimal pour la structure globale
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1024, 748);
        this.setLocationRelativeTo(null);

        // Instanciation des sous-onglets
        campagnePanel = new GestionCampagnePanel();
        sessionPanel = new GestionSessionPanel();
          dominantePanel = new GestionDominantePanel();
       // statsPanel = new StatistiquesPanel();

        buildUI();
    }

    private void buildUI() {
        // En-tête
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Espace Administrateur - Administration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.RED);
        headerPanel.add(titleLabel);
        this.add(headerPanel, BorderLayout.NORTH);

        // Onglets
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Gestion Campagne", campagnePanel);
        tabbedPane.addTab("Gestion Sessions", sessionPanel);
       // tabbedPane.addTab("Statistiques", statsPanel);
          tabbedPane.addTab("Dominantes", dominantePanel);

        this.add(tabbedPane, BorderLayout.CENTER); //ajout au centre
    }

      public GestionCampagnePanel getCampagnePanel() { return campagnePanel; }
    public GestionSessionPanel getSessionPanel() { return sessionPanel; }
   // public StatistiquesPanel getStatsPanel() { return statsPanel; }

    public GestionDominantePanel getDominantePanel() {
        return dominantePanel;
    }
}
