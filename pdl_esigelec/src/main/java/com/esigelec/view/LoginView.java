package com.esigelec.view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LoginView extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
        customUI();
    }

    private void initUI(){

        this.setBackground(Color.WHITE);
        this.setSize(1024,748);
        this.setTitle("ESIGELEC - Gestion de l'attribution des dominantes");
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Se connecter");
        loginButton.setBackground(Color.RED);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void customUI(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        //titre
        JLabel titleLabel = new JLabel("Connexion", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; //colonnes
        this.add(titleLabel, gbc);

        // label Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(Color.BLACK);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(emailLabel, gbc);

        //champ email
        gbc.gridx = 1;
        this.add(emailField, gbc);

        // Label Mot de passe
        JLabel mdpLabel = new JLabel("Mot de passe :");
        mdpLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(mdpLabel, gbc);

        //champ mdp
        gbc.gridx = 1;
        this.add(passwordField, gbc);

        // Bouton de connexion
        gbc.gridy = 4;
        this.add(loginButton, gbc);

    }

    //Getters pour recuperer les donnees du formulaire
    public String getEmail(){
        return emailField.getText();
    }

    public String getPassword(){
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton(){
        return loginButton;
    }

    public void afficherMessage (String message, boolean erreur){
        int typeMessage = 0;

        if(erreur){
            typeMessage = JOptionPane.ERROR_MESSAGE;
        }
        else{
            typeMessage = JOptionPane.INFORMATION_MESSAGE;
        }

        JOptionPane.showMessageDialog(this, message, "Information", typeMessage);
    }


}
