package com.esigelec.view.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionSessionPanel extends JPanel {

    private JTable sessionTable;
    private DefaultTableModel tableModel;

    private JTextField dateField;
    private JTextField heureDebutField;
    private JTextField heureFinField;
    private JTextField capaciteField;
    private JTextField idCampagneField;
    private JTextField idDominanteField;

    private JButton btnCreer;
    private JButton btnModifier;
    private JButton btnSupprimer;
    private JButton btnReset;

    public GestionSessionPanel() {
        setLayout(new BorderLayout(10, 10));
        initTable();
        initForm();
        initButtons();
    }

    private void initTable() {
        String[] columns = {"ID", "Date", "Heure Debut", "Heure Fin", "Capacite", "ID Campagne", "ID Dominante"};
        tableModel = new DefaultTableModel(columns, 0);
        sessionTable = new JTable(tableModel);
        add(new JScrollPane(sessionTable), BorderLayout.CENTER);
    }

    private void initForm() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        dateField = new JTextField(10);
        heureDebutField = new JTextField(10);
        heureFinField = new JTextField(10);
        capaciteField = new JTextField(10);
        idCampagneField = new JTextField(10);
        idDominanteField = new JTextField(10);

        c.gridx = 0; c.gridy = 0; formPanel.add(new JLabel("Date (YYYY-MM-DD)"), c);
        c.gridx = 1; formPanel.add(dateField, c);

        c.gridx = 0; c.gridy = 1; formPanel.add(new JLabel("Heure Debut (HH:mm)"), c);
        c.gridx = 1; formPanel.add(heureDebutField, c);

        c.gridx = 0; c.gridy = 2; formPanel.add(new JLabel("Heure Fin (HH:mm)"), c);
        c.gridx = 1; formPanel.add(heureFinField, c);

        c.gridx = 0; c.gridy = 3; formPanel.add(new JLabel("Capacite"), c);
        c.gridx = 1; formPanel.add(capaciteField, c);

        c.gridx = 0; c.gridy = 4; formPanel.add(new JLabel("ID Campagne"), c);
        c.gridx = 1; formPanel.add(idCampagneField, c);

        c.gridx = 0; c.gridy = 5; formPanel.add(new JLabel("ID Dominante"), c);
        c.gridx = 1; formPanel.add(idDominanteField, c);

        add(formPanel, BorderLayout.NORTH);
    }

    private void initButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        btnCreer = new JButton("Creer");
        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");
        btnReset = new JButton("Reinitialiser");

        buttonPanel.add(btnCreer);
        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);
        buttonPanel.add(btnReset);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTable getSessionTable() { return sessionTable; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public JTextField getDateField() { return dateField; }
    public JTextField getHeureDebutField() { return heureDebutField; }
    public JTextField getHeureFinField() { return heureFinField; }
    public JTextField getCapaciteField() { return capaciteField; }
    public JTextField getIdCampagneField() { return idCampagneField; }
    public JTextField getIdDominanteField() { return idDominanteField; }

    public JButton getBtnCreer() { return btnCreer; }
    public JButton getBtnModifier() { return btnModifier; }
    public JButton getBtnSupprimer() { return btnSupprimer; }
    public JButton getBtnReset() { return btnReset; }
}