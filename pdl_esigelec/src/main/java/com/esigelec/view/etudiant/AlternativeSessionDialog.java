package com.esigelec.view.etudiant;

import com.esigelec.model.Session;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AlternativeSessionDialog extends JDialog {
    private JComboBox<SessionItem> cbAlternatives;
    private JButton btnRemplacer;
    private JButton btnAnnuler;
    private Session sessionSelectionnee = null;
    private boolean aValide = false;

    public AlternativeSessionDialog(Frame parent, List<Session> alternatives, String messageExplicatif) {
        super(parent, "Sessions Alternatives (Conflit)", true);
        this.setLayout(new BorderLayout(10, 10));
        this.setSize(450, 200);
        this.setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Message
        JLabel lblMessage = new JLabel("<html>" + messageExplicatif.replace("\n", "<br>") + "</html>");
        lblMessage.setForeground(Color.RED);
        mainPanel.add(lblMessage);

        // Liste déroulante des alternatives
        cbAlternatives = new JComboBox<>();
        for (Session s : alternatives) {
            cbAlternatives.addItem(new SessionItem(s));
        }
        mainPanel.add(cbAlternatives);
        
        this.add(mainPanel, BorderLayout.CENTER);

        // Boutons
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnRemplacer = new JButton("Remplacer et Ajouter");
        btnAnnuler = new JButton("Annuler");

        pnlButtons.add(btnRemplacer);
        pnlButtons.add(btnAnnuler);
        this.add(pnlButtons, BorderLayout.SOUTH);

        // Listeners
        btnRemplacer.addActionListener(e -> {
            if (cbAlternatives.getSelectedItem() != null) {
                SessionItem item = (SessionItem) cbAlternatives.getSelectedItem();
                this.sessionSelectionnee = item.getSession();
                this.aValide = true;
                dispose();
            }
        });

        btnAnnuler.addActionListener(e -> {
            this.aValide = false;
            dispose();
        });
    }

    public boolean isAValide() { return aValide; }
    public Session getSessionSelectionnee() { return sessionSelectionnee; }

    // Wrapper pour afficher proprement l'item dans la JComboBox
    private class SessionItem {
        private Session session;
        public SessionItem(Session session) { this.session = session; }
        public Session getSession() { return session; }
        @Override
        public String toString() {
            return "ID " + session.getId() + " - " + session.getDate() + " [" + session.getHeureDebut() + " - " + session.getHeureFin() + "]";
        }
    }
}