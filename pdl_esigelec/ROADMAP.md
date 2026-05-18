# ROADMAP DE DÉVELOPPEMENT
**Système de Gestion des Inscriptions aux Sessions de Dominantes SIGSD — Projet PDL ESIGELEC 2025-2026**
*Stack technique : Java Swing • JDBC • MySQL 8.0 • Architecture MVC Desktop*

**Informations Générales**
- **Groupe :** Groupe 6 — ESIGELEC
- **Binôme :** Julien KEUNI & Tobi KOUTCHAKPO
- **Encadrant :** Mr TEBOUL
- **Version :** v2.0 — Mars 2026

**Objet du document :** Ce document est la feuille de route officielle du projet SIGSD. Il détaille les phases, sprints, tâches et responsabilités pour livrer une application desktop Java complète et fonctionnelle.

---

## 1. VISION GLOBALE DU PROJET

### 1.1 Architecture technique (MVC Desktop)
- **VUE (View)** : Java Swing (JFrame, JPanel...). Fenêtres de Login, Dashboards. Aucune logique métier.
- **CONTRÔLEUR (Controller)** : Classes `*Controller.java`. Reçoit les événements Swing, appelle les Services, met à jour la Vue.
- **MODÈLE (Model)** : Entités Java (POJO), Services (logique métier), DAO (accès BDD via JDBC), MySQL 8.0.

### 1.2 Structure des packages Java
- `sigsd.model.entity` : Utilisateur, Etudiant, Administrateur, Campagne, Session, Dominante, Choix, Inscription
- `sigsd.dao` : DAO interfaces + implémentations (CampagneDAO, SessionDAO, etc.)
- `sigsd.service` : AuthService, CampagneService, SessionService, etc.
- `sigsd.database` : DatabaseConnection (Singleton JDBC), ScriptSQL
- `sigsd.controller` : LoginController, AdminController, etc.
- `sigsd.view.auth` : LoginFrame
- `sigsd.view.admin` : AdminDashboard, GestionCampagnePanel, GestionSessionPanel, StatistiquesPanel
- `sigsd.view.etudiant` : EtudiantDashboard, SessionsDisponiblesPanel, MesVoeuxPanel, ResultatsPanel
- `sigsd.view.components` : Composants Swing réutilisables
- `sigsd.util` : DateUtils, ValidationUtils, HoraireValidator, AlerteDialog

### 1.3 Cas d'utilisation couverts
1. **UC1 (Bordereau 1)** : S'inscrire à une session (Étudiant)
2. **UC2 (Bordereau 2)** : Modifier ses inscriptions (Étudiant)
3. **UC3 (Bordereau 3)** : Visualiser ses inscriptions (Étudiant)
4. **UC4 (Bordereau 4)** : S'authentifier (Étudiant + Admin)
5. **UC5 (Bordereau 5)** : Choisir une session alternative (Étudiant)
6. **UC6 (Bordereau 6)** : Paramétrer les campagnes (Admin)
7. **UC7 (Bordereau 7)** : Ouvrir / Fermer les inscriptions (Admin)

---

## 2. RÉPARTITION DES RÔLES ET RESPONSABILITÉS

### JULIEN KEUNI
- Conception & implémentation Vues Swing (LoginFrame, AdminDashboard, Panels)
- Portail Administrateur complet (UC6, UC7)
- Gestion du Référentiel (dominantes, sessions)
- Export CSV des résultats
- Algorithme d'affectation automatique
- Design des IHM

### KOUTCHAKPO TOBI
- Couche BDD : script SQL + connexion JDBC
- Toutes les classes DAO (accès MySQL)
- Entités Java (POJO) et Services métier
- Portail Étudiant : UC1, UC2, UC3, UC5
- Gestion des campagnes (UC6 backend)
- Validation règles métier (BR1–BR7)
- Plan de Validation Logiciel (PVL)

**Tâches communes et collaboration :**
- Connexion View ↔ Controller ↔ DAO
- Tests fonctionnels (PVL — 23 cas)
- Code review croisée obligatoire (Pull Request avant tout merge sur main)

---

## 3. TIMELINE DES PHASES

- **PHASE 1 (S1 → S4) :** Analyse, UML, DSL, PVL, DCD, Bordereaux (Terminée)
- **PHASE 2 (S6 → S12) :** Développement Java Swing + JDBC + MySQL
- **PHASE 3 (S13 → S15) :** Recette PVL, corrections, livraison finale

---

## 4. SUIVI DES PHASES ET SPRINTS

### PHASE 1 — ANALYSE & CONCEPTION (S1–S4) - TERMINEE
- [x] 1.1 Diagramme des cas d'utilisation (UC) - *Tobi*
- [x] 1.2 Diagramme de classes application + BDD - *Julien + Tobi*
- [x] 1.3 Schéma SQL + scripts CREATE TABLE (MySQL) - *Tobi*
- [x] 1.4 DSL complet - *Julien + Tobi*
- [x] 1.5 Bordereaux 1 à 7 - *Tobi*
- [x] 1.6 Plan de Validation Logiciel - *Tobi*
- [x] 1.7 Maquettes IHM Swing - *Julien*

### PHASE 2 — DÉVELOPPEMENT JAVA / SQL (S6–S12)

#### SPRINT 2.1 - Infrastructure : BDD, JDBC & Authentification (S6)
- [x] 2.1.1 Créer projet Java + mysql-connector
- [x] 2.1.2 Exécuter script SQL DCD sur MySQL 8.0
- [x] 2.1.3 DatabaseConnection.java (Singleton JDBC)
- [x] 2.1.4 Entités POJO : Utilisateur, Etudiant, Administrateur, Role
- [x] 2.1.5 UtilisateurDAO : findByEmail, checkPassword
- [x] 2.1.6 AuthService : login, gestion tentatives
- [x] 2.1.7 LoginFrame.java (Swing)
- [x] 2.1.8 LoginController : events + Auth
- [x] 2.1.9 Redirection conditionnelle : Dashboards Admin/Etudiant - *Julien*
- [x] 2.1.10 Jeu de données de test SQL - *Tobi*
- [x] 2.1.11 Test manuel PVL #8 et #9 - *Julien + Tobi*

#### SPRINT 2.2 - Référentiel & Gestion des Campagnes (S7-S8)
- [x] 2.2.1 Entités POJO : Campagne, Dominante, Session, EtatCampagne - *Tobi*
- [x] 2.2.2 DominanteDAO - *Tobi*
- [x] 2.2.3 SessionDAO - *Tobi*
- [x] 2.2.4 CampagneDAO - *Tobi*
- [x] 2.2.5 CampagneService (création) - *Tobi*
- [x] 2.2.6 CampagneService (machine à états) - *Tobi*
- [x] 2.2.7 HoraireValidator.java - *Tobi*
- [x] 2.2.8 SessionService - *Tobi*
- [x] 2.2.9 AdminDashboard.java (Tabs) - *Julien*
- [x] 2.2.10 GestionCampagnePanel - *Julien*
- [x] 2.2.11 GestionSessionPanel - *Julien*
- [x] 2.2.12 AdminController - *Tobi*
- [x] 2.2.13 Traçabilité changements d'état - *Tobi*
- [x] 2.2.14 Tests manuels PVL (#1, #2, #5, #7) - *Julien + Tobi*

#### SPRINT 2.3 - Portail Étudiant — Inscription & Choix (S9-S10)
- [ ] 2.3.1 Entité POJO : Choix - *Tobi*
- [ ] 2.3.2 ChoixDAO - *Tobi*
- [ ] 2.3.3 ChoixService : ajouterChoix - *Tobi*
- [ ] 2.3.4 ChoixService : détecterConflit - *Tobi*
- [ ] 2.3.5 ChoixService : proposerAlternatives - *Tobi*
- [ ] 2.3.6 ChoixService : modifierOrdre - *Tobi*
- [ ] 2.3.7 ChoixService : supprimerChoix - *Tobi*
- [ ] 2.3.8 EtudiantDashboard.java (JSplitPane) - *Julien*
- [ ] 2.3.9 SessionsDisponiblesPanel - *Julien*
- [ ] 2.3.10 MesVoeuxPanel - *Julien*
- [ ] 2.3.11 Bouton 'Ajouter à mes vœux' (validation immédiate) - *Julien*
- [ ] 2.3.12 AlternativeSessionDialog.java - *Julien*
- [ ] 2.3.13 Bouton VALIDER MES VŒUX - *Julien + Tobi*
- [ ] 2.3.14 Blocage interface si campagne != Ouverte - *Julien*
- [ ] 2.3.15 Tests manuels PVL (#10 à #15, #17) - *Julien + Tobi*

#### SPRINT 2.4 - Algorithme d'affectation & Résultats (S11)
- [ ] 2.4.1 Entité Inscription - *Tobi*
- [ ] 2.4.2 InscriptionDAO - *Tobi*
- [ ] 2.4.3 AffectationService (algo greedy) - *Tobi*
- [ ] 2.4.4 AffectationService (capacités max) - *Tobi*
- [ ] 2.4.5 AffectationService (changement état) - *Tobi*
- [ ] 2.4.6 Bouton 'Lancer Traitement' (Admin) - *Julien*
- [ ] 2.4.7 ResultatsPanel.java (Étudiant) - *Julien*
- [ ] 2.4.8 StatistiquesPanel.java (Admin) - *Julien*
- [ ] 2.4.9 Export CSV - *Julien*
- [ ] 2.4.10 Tests manuels PVL (#18, #19, #20) - *Julien + Tobi*

#### SPRINT 2.5 - Intégration, Qualité & Performance (S12)
- [ ] 2.5.1 Intégration MVC complète
- [ ] 2.5.2 Gestion exceptions SQL
- [ ] 2.5.3 Respect SLA : authentification < 1s
- [ ] 2.5.4 Respect SLA : affichage liste < 2s
- [ ] 2.5.5 Respect SLA : sauvegarde choix < 1s
- [ ] 2.5.6 Isolation données (promo)
- [ ] 2.5.7 Validation résolutions (1366x768 / 1080p)
- [ ] 2.5.8 Code review croisée finale
- [ ] 2.5.9 Correction bugs PVL
- [ ] 2.5.10 Préparation démo prototype

### PHASE 3 — RECETTE & LIVRAISON (S13–S15)
- [ ] SPRINT 3.1 : Présentation prototype & exécution 23 cas PVL (S13)
- [ ] SPRINT 3.2 : Corrections finales, Export Livrables, Recette Finale (S14-S15)