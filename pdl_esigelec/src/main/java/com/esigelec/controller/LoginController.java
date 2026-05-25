package com.esigelec.controller;

import com.esigelec.model.*;
import com.esigelec.service.AuthService;
import com.esigelec.view.admin.AdminDashboard;
import com.esigelec.view.LoginView;
import com.esigelec.view.etudiant.EtudiantDashboard;

public class LoginController {
    private LoginView loginView;
    private AuthService authService;

    public LoginController(LoginView loginView, AuthService authService) {
        this.loginView = loginView;
        this.authService = authService;

        this.loginView.getLoginButton().addActionListener(e -> traiterConnexion());
    }

    private void traiterConnexion() {
        String email = loginView.getEmail();
        String mdp = loginView.getPassword();

        if (email.isEmpty() || mdp.isEmpty()) {
            loginView.afficherMessage("Veuillez remplir tous les champs", true);
            return;
        }

        try{
            Utilisateur user = authService.login(email, mdp);
            loginView.afficherMessage("Bienvenue "+user.getNom(), false);
            loginView.dispose();

            if("ADMIN".equals(user.getRole().toString())){
                AdminDashboard adminDashboard = new AdminDashboard();
                new AdminController(adminDashboard);
                adminDashboard.setVisible(true);
            } else {
                EtudiantDashboard etudiantDashboard = new EtudiantDashboard();
                new EtudiantController(etudiantDashboard, (Etudiant) user);
                etudiantDashboard.setVisible(true);
            }

        } catch (Exception e) {
            loginView.afficherMessage(e.getMessage(), true);
        }
    }
}
