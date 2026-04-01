package com.esigelec;


import com.esigelec.controller.AdminController;
import com.esigelec.controller.LoginController;
import com.esigelec.dao.dominante.DominanteDAO;
import com.esigelec.dao.dominante.DominanteDAOImpl;
import com.esigelec.dao.utilisateur.UtilisateurDAO;
import com.esigelec.dao.utilisateur.UtilisateurDAOImpl;
import com.esigelec.service.AuthService;
import com.esigelec.view.LoginView;
import com.esigelec.view.admin.AdminDashboard;
import com.esigelec.view.etudiant.EtudiantDashboard;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

                UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
                AuthService authService = new AuthService(utilisateurDAO);
                DominanteDAO dominanteDAO;
                LoginView loginView = new LoginView();
                LoginController loginController = new LoginController(loginView, authService);
                loginView.setVisible(true);

//            AdminDashboard adminDashboard = new AdminDashboard();
//            new AdminController(adminDashboard);
//            adminDashboard.setVisible(true);

//            EtudiantDashboard  etudiantDashboard = new EtudiantDashboard();
//            etudiantDashboard.setVisible(true);



        });
    }
}
