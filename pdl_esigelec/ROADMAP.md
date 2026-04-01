| **ROADMAP DE DÉVELOPPEMENT** Système de Gestion des Inscriptions aux Sessions de Dominantes SIGSD — Projet PDL ESIGELEC 2025-2026 **Stack technique : Java Swing • JDBC • MySQL 8.0 • Architecture MVC Desktop** |
| :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |

| **Groupe**          | **Binôme**                     | **Encadrant** | **Version**      |
| :-----------------: | :----------------------------: | :-----------: | :--------------: |
| Groupe 6 — ESIGELEC | Julien KEUNI & Tobi KOUTCHAKPO | Mr TEBOUL     | v2.0 — Mars 2026 |

| **Objet du document**                                                                                                                                                                          | **Phases du planning ESIGELEC** |
| :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :-----------------------------: |
| Ce document est la feuille de route officielle du projet SIGSD. Il détaille les phases, sprints, tâches et responsabilités pour livrer une application desktop Java complète et fonctionnelle. |                                 |
| Phase 1 : UML & Documentation (S1–S4)<br>Phase 2 : Développement Java/SQL (S6–S12)<br>Phase 3 : Recette & Livraison (S13–S15)                                                                  |                                 |

# **1. VISION GLOBALE DU PROJET**

## **1.1 Architecture technique — Java Swing + JDBC + MySQL (MVC Desktop)**

| **VUE (View)**                                                                                                                                                   | **CONTRÔLEUR (Controller)**                                                                                       | **MODÈLE (Model)**                                                                                                     |
| :--------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------: |
| \* Java Swing (JFrame, JPanel...)<br>\* Fenêtres : Login, Dashboard Admin, Dashboard Étudiant<br>\* Dialogues, JTable, JComboBox<br>\* Aucune logique métier ici | \* Classes \*Controller.java<br>\* Reçoit les événements Swing<br>\* Appelle les Services<br>\* Met à jour la Vue | \* Entités Java (POJO)<br>\* Services (logique métier)<br>\* DAO — accès BDD via JDBC<br>\* MySQL 8.0 (script SQL DCD) |

## **1.2 Structure des packages Java**

| **Package**               | **Contenu**                                                                                                                     |
| :-----------------------: | :-----------------------------------------------------------------------------------------------------------------------------: |
| **sigsd.model.entity**    | Utilisateur.java, Etudiant.java, Administrateur.java, Campagne.java, Session.java, Dominante.java, Choix.java, Inscription.java |
| **sigsd.dao**             | DAO interfaces + implémentations : CampagneDAO, SessionDAO, ChoixDAO, InscriptionDAO, UtilisateurDAO...                         |
| **sigsd.service**         | AuthService, CampagneService, SessionService, ChoixService, InscriptionService, AffectationService                              |
| **sigsd.database**        | DatabaseConnection.java (Singleton JDBC), ScriptSQL.java                                                                        |
| **sigsd.controller**      | LoginController, AdminController, EtudiantController, CampagneController, ChoixController                                       |
| **sigsd.view.auth**       | LoginFrame.java                                                                                                                 |
| **sigsd.view.admin**      | AdminDashboard.java, GestionCampagnePanel.java, GestionSessionPanel.java, StatistiquesPanel.java                                |
| **sigsd.view.etudiant**   | EtudiantDashboard.java, SessionsDisponiblesPanel.java, MesVoeuxPanel.java, ResultatsPanel.java                                  |
| **sigsd.view.components** | Composants Swing réutilisables (tableaux, dialogues, badges)                                                                    |
| **sigsd.util**            | DateUtils.java, ValidationUtils.java, HoraireValidator.java, AlerteDialog.java                                                  |

## **1.3 Cas d'utilisation couverts (7 bordereaux)**

| **N°**  | **Cas d'utilisation**            | **Acteur**       | **Fenêtre Swing principale**             | **Bordereau** |
| :-----: | :------------------------------: | :--------------: | :--------------------------------------: | :-----------: |
| **UC1** | S'inscrire à une session         | Étudiant         | SessionsDisponiblesPanel + MesVoeuxPanel | BORDEREAU 1   |
| **UC2** | Modifier ses inscriptions        | Étudiant         | MesVoeuxPanel (drag & drop / boutons)    | BORDEREAU 2   |
| **UC3** | Visualiser ses inscriptions      | Étudiant         | ResultatsPanel                           | BORDEREAU 3   |
| **UC4** | S'authentifier                   | Étudiant + Admin | LoginFrame                               | BORDEREAU 4   |
| **UC5** | Choisir une session alternative  | Étudiant         | AlternativeSessionDialog                 | BORDEREAU 5   |
| **UC6** | Paramétrer les campagnes         | Administrateur   | GestionCampagnePanel                     | BORDEREAU 6   |
| **UC7** | Ouvrir / Fermer les inscriptions | Administrateur   | AdminDashboard (boutons état)            | BORDEREAU 7   |

# **2. RÉPARTITION DES RÔLES ET RESPONSABILITÉS**

| **JULIEN KEUNI**                                                                                                                                                                                                                                                                                                                                            | **KOUTCHAKPO TOBI**                                                                                                                                                                                                                                                                                                                           |
| :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| **Responsabilités principales**<br>\* Conception & implémentation Vues Swing<br>\* LoginFrame, AdminDashboard, tous les Panels<br>\* Portail Administrateur complet (UC6, UC7)<br>\* Gestion du Référentiel (dominantes, sessions)<br>\* Export CSV des résultats<br>\* Algorithme d'affectation automatique<br>\* Design des IHM (maquettes DSL section 5) | **Responsabilités principales**<br>\* Couche BDD : script SQL + connexion JDBC<br>\* Toutes les classes DAO (accès MySQL)<br>\* Entités Java (POJO) et Services métier<br>\* Portail Étudiant : UC1, UC2, UC3, UC5<br>\* Gestion des campagnes (UC6 backend)<br>\* Validation règles métier (BR1–BR7)<br>\* Plan de Validation Logiciel (PVL) |
| **Tâches communes**<br>\* Connexion View ↔ Controller ↔ DAO<br>\* Tests fonctionnels (PVL — 23 cas)<br>\* Code review croisée obligatoire                                                                                                                                                                                                                   | **Règles de collaboration**<br>\* Git : branches feature/xxx → dev → main<br>\* Pull Request avant tout merge<br>\* Réunion de sync à chaque début de sprint<br>\* Suivi des anomalies : GitHub Issues                                                                                                                                        |

# **3. TIMELINE DES PHASES**

| **Phase**   | **Semaines** | **Durée**  | **Objectif principal**                              |
| :---------: | :----------: | :--------: | :-------------------------------------------------: |
| **PHASE 1** | S1 → S4      | 4 semaines | Analyse, UML, DSL, PVL, DCD, Bordereaux (✅ livrés)  |
| **PHASE 2** | S6 → S12     | 7 semaines | Développement Java Swing + JDBC + MySQL (5 sprints) |
| **PHASE 3** | S13 → S15    | 3 semaines | Recette PVL, corrections, livraison finale          |

# **4. PHASE 1 — ANALYSE & CONCEPTION (S1–S4)**

La Phase 1 est terminée. Les livrables DSL, DCD (avec scripts SQL), Bordereaux 1–7 et PVL constituent la base documentaire sur laquelle repose tout le développement Java/SQL.

| **PHASE 1** | **Analyse, Modélisation UML & Documentation** | **Période** | **Porteur**   |
| :---------: | :-------------------------------------------: | :---------: | :-----------: |
| **PHASE 1** | Analyse, Modélisation UML & Documentation     | S1 → S4     | Julien + Tobi |

| **ID** | **Tâche**                                                    | **Responsable**   | **Priorité** | **Statut** | **Référence**     |
| :----: | :----------------------------------------------------------: | :---------------: | :----------: | :--------: | :---------------: |
| 1.1    | Diagramme des cas d'utilisation (UC)                         | Tobi              | **Critique** | ✅ Livré    | DSL — Section 3   |
| 1.2    | Diagramme de classes application + BDD                       | **Julien + Tobi** | **Critique** | ✅ Livré    | DCD — Figures 1-2 |
| 1.3    | Schéma SQL + scripts CREATE TABLE (MySQL)                    | Tobi              | **Critique** | ✅ Livré    | DCD — Section 4   |
| 1.4    | DSL complet (fonctionnalités, IHM, contraintes)              | **Julien + Tobi** | **Critique** | ✅ Livré    | DSL v1.0          |
| 1.5    | Bordereaux 1 à 7 (UC détaillés)                              | Tobi              | **Critique** | ✅ Livré    | Bordereaux 1–7    |
| 1.6    | Plan de Validation Logiciel (23 cas de test)                 | Tobi              | **Haute**    | ✅ Livré    | PVL v0.1          |
| 1.7    | Maquettes IHM Swing (AdminView, StudentDashboard, LoginView) | **Julien**        | **Haute**    | ✅ Livré    | DSL — Figures 1-3 |

# **5. PHASE 2 — DÉVELOPPEMENT JAVA / SQL (S6–S12)**

5 sprints itératifs. Chaque sprint produit un incrément compilable et testable. La connexion View–Controller–DAO est validée en commun à la fin de chaque sprint.

| **Julien** | **Tobi** | **Julien + Tobi** | **Légende des responsables** |
| :--------: | :------: | :---------------: | :--------------------------: |

| **Critique** | **Haute** | **Normale** | **Légende des priorités** |
| :----------: | :-------: | :---------: | :-----------------------: |

| **SPRINT 2.1** | **Infrastructure : BDD, JDBC & Authentification** | **Période** | **Porteur**                   |
| :------------: | :-----------------------------------------------: | :---------: | :---------------------------: |
| SPRINT 2.1     | Infrastructure : BDD, JDBC & Authentification     | S6          | Julien (UI) + Tobi (BDD/Auth) |

**Objectif : Poser les fondations du projet — connexion MySQL, entités Java, authentification fonctionnelle avec la fenêtre de login Swing.**

| **ID** | **Tâche**                                                                                 | **Responsable**   | **Priorité** | **Statut** | **Référence**           |
| :----: | :---------------------------------------------------------------------------------------: | :---------------: | :----------: | :--------: | :---------------------: |
| 2.1.1  | Créer le projet Java (IntelliJ/Eclipse, Maven ou classpath), ajouter mysql-connector-java | **Julien + Tobi** | **Critique** | Fait       | DCD                     |
| 2.1.2  | Exécuter le script SQL DCD sur MySQL 8.0 (créer toutes les tables)                        | Tobi              | **Critique** | Fait       | DCD — Section 4         |
| 2.1.3  | DatabaseConnection.java — Singleton JDBC (url, user, password, getConnection())           | Tobi              | **Critique** | Fait       |                         |
| 2.1.4  | Entités POJO : Utilisateur, Etudiant, Administrateur, Role (enum)                         | Tobi              | **Critique** | Fait       | DCD — Tables            |
| 2.1.5  | UtilisateurDAO : findByEmail(), checkPassword() (hash SHA-256)                            | Tobi              | **Critique** | Fait       | Bordereau 4 — BR2       |
| 2.1.6  | AuthService : login(email, mdp) → rôle, compteur tentatives, blocage après 3 échecs       | Tobi              | **Critique** | Fait       | Bordereau 4 — BR3       |
| 2.1.7  | LoginFrame.java (Swing) : champs Email/MDP, bouton Se connecter, messages d'erreur        | **Julien**        | **Critique** | Fait       | DSL — Figure 3          |
| 2.1.8  | LoginController : capture événement login → appelle AuthService → redirige vers dashboard | Tobi              | **Critique** | Fait       | Bordereau 4             |
| 2.1.9  | Redirection conditionnelle : AdminDashboard (ADMIN) vs EtudiantDashboard (ETUDIANT)       | **Julien**        | **Haute**    | À faire    | Bordereau 4 — ScA3/ScA4 |
| 2.1.10 | Jeu de données de test SQL : 2 admins + 5 étudiants + 3 dominantes fictives               | Tobi              | **Haute**    | À faire    | PVL — Données test      |
| 2.1.11 | Test manuel PVL \#8 (connexion OK) et \#9 (blocage 3 tentatives)                          | **Julien + Tobi** | **Haute**    | À faire    | PVL — Tests 8-9         |

| **SPRINT 2.2** | **Référentiel & Gestion des Campagnes** | **Période** | **Porteur**                                |
| :------------: | :-------------------------------------: | :---------: | :----------------------------------------: |
| SPRINT 2.2     | Référentiel & Gestion des Campagnes     | S7–S8       | Tobi (DAO/Service) + Julien (Panels Swing) |

**Objectif : Permettre à l'administrateur de créer/gérer les campagnes, dominantes et sessions depuis l'interface Swing.**

| **ID** | **Tâche**                                                                                      | **Responsable**   | **Priorité** | **Statut** | **Référence**             |
| :----: | :--------------------------------------------------------------------------------------------: | :---------------: | :----------: | :--------: | :-----------------------: |
| 2.2.1  | Entités POJO : Campagne, Dominante, Session, EtatCampagne (enum)                               | Tobi              | **Critique** | À faire    | DCD — Tables              |
| 2.2.2  | DominanteDAO : findAll(), create(), update(), delete()                                         | Tobi              | **Haute**    | À faire    |                           |
| 2.2.3  | SessionDAO : findByCampagne(), create(), update(), delete()                                    | Tobi              | **Critique** | À faire    |                           |
| 2.2.4  | CampagneDAO : create(), findAll(), updateEtat(), findById()                                    | Tobi              | **Critique** | À faire    |                           |
| 2.2.5  | CampagneService : créer campagne avec validation (N\>0, dates cohérentes)                      | Tobi              | **Critique** | À faire    | Bordereau 6 — BR1-BR3     |
| 2.2.6  | CampagneService : changerEtat() avec machine à états (prépa→ouverte→fermée…)                   | Tobi              | **Critique** | À faire    | Bordereau 7               |
| 2.2.7  | HoraireValidator.java : vérifier 08:30–12:30 / 13:30–17:30 pour chaque session                 | Tobi              | **Critique** | À faire    | DSL — Section 6 / PVL \#4 |
| 2.2.8  | SessionService : créer session avec validation horaire, rejet si hors plage                    | Tobi              | **Critique** | À faire    | PVL — Tests 4-6           |
| 2.2.9  | AdminDashboard.java : JTabbedPane (Pilotage Campagne / Référentiel / Statistiques)             | **Julien**        | **Critique** | À faire    | DSL — Figure 1            |
| 2.2.10 | GestionCampagnePanel : formulaire création campagne, boutons Ouvrir/Fermer, badge état         | **Julien**        | **Critique** | À faire    | Bordereaux 6 & 7          |
| 2.2.11 | GestionSessionPanel : JTable sessions, boutons Ajouter/Modifier/Supprimer, validation horaire  | **Julien**        | **Haute**    | À faire    | PVL — Tests 3-6           |
| 2.2.12 | AdminController : câblage events Swing → CampagneService / SessionService                      | Tobi              | **Haute**    | À faire    |                           |
| 2.2.13 | Traçabilité changements d'état (date + heure + id admin stockés en BDD)                        | Tobi              | **Normale**  | À faire    | Bordereau 7 — BR3         |
| 2.2.14 | Tests manuels PVL : \#1 (créer campagne), \#2 (N=0 rejeté), \#5 (session valide), \#7 (ouvrir) | **Julien + Tobi** | **Haute**    | À faire    | PVL — Tests 1-7           |

| **SPRINT 2.3** | **Portail Étudiant — Inscription & Choix** | **Période** | **Porteur**                  |
| :------------: | :----------------------------------------: | :---------: | :--------------------------: |
| SPRINT 2.3     | Portail Étudiant — Inscription & Choix     | S9–S10      | Tobi (backend) + Julien (UI) |

**Objectif : Implémenter le cœur fonctionnel étudiant — consultation sessions, saisie/modification des N vœux, détection conflits horaires, sessions alternatives.**

| **ID** | **Tâche**                                                                                   | **Responsable**   | **Priorité** | **Statut**   | **Référence**          |
| :----: | :-----------------------------------------------------------------------------------------: | :---------------: | :----------: | :----------: | :--------------------: |
| 2.3.1  | Entités POJO : Choix (id, étudiant, session, campagne, priorité, dateSaisie)                | Tobi              | **Critique** | À faire      | DCD — Table Choix      |
| 2.3.2  | ChoixDAO : findByEtudiantAndCampagne(), insert(), delete(), updatePriorite()                | Tobi              | **Critique** | À faire      |                        |
| 2.3.3  | ChoixService : ajouterChoix() — vérifie N max, unicité session, conflit horaire             | Tobi              | **Critique** | À faire      | Bordereau 1 — BR1-BR4  |
| 2.3.4  | ChoixService : détecterConflit() — compare plages horaires des choix existants              | Tobi              | **Critique** | À faire      | Bordereau 1 / PVL \#12 |
| 2.3.5  | ChoixService : proposerAlternatives() — même dominante, compatible horaire + capacité dispo | Tobi              | **Critique** | À faire      | Bordereau 5            |
| 2.3.6  | ChoixService : modifierOrdre() — réorganise les rangs de 1 à N, sans doublon                | Tobi              | **Haute**    | À faire      | Bordereau 2 — BR2      |
| 2.3.7  | ChoixService : supprimerChoix() — interdit si campagne \!= Ouverte                          | Tobi              | **Haute**    | À faire      | Bordereau 2 — BR1      |
| 2.3.8  | EtudiantDashboard.java : JSplitPane (Sessions disponibles                                   | Mes vœux)         | **Julien**   | **Critique** | À faire                |
| 2.3.9  | SessionsDisponiblesPanel : JTable avec colonnes Dominante/Début/Fin/Salle/Places, filtre    | **Julien**        | **Critique** | À faire      | DSL — Figure 2         |
| 2.3.10 | MesVoeuxPanel : JTable ordonnée (rang 1→N), boutons Monter/Descendre/Retirer                | **Julien**        | **Critique** | À faire      | Bordereau 2 — Va5      |
| 2.3.11 | Bouton 'Ajouter à mes vœux' + validation immédiate (badge Conflit si problème)              | **Julien**        | **Critique** | À faire      | Bordereau 1            |
| 2.3.12 | AlternativeSessionDialog.java : fenêtre modale listant les sessions alternatives            | **Julien**        | **Haute**    | À faire      | Bordereau 5 — Va4      |
| 2.3.13 | Bouton VALIDER MES VŒUX : vérifie exactement N choix, enregistre en BDD                     | **Julien + Tobi** | **Critique** | À faire      | PVL — Tests 10-15      |
| 2.3.14 | Blocage de l'interface si campagne \!= Ouverte (message + grisage des boutons)              | **Julien**        | **Critique** | À faire      | PVL — Test \#17        |
| 2.3.15 | Tests manuels PVL : \#10 à \#15, \#17 (inscription, conflits, alternatives, limites)        | **Julien + Tobi** | **Critique** | À faire      | PVL — Tests 10-17      |

| **SPRINT 2.4** | **Algorithme d'affectation & Résultats** | **Période** | **Porteur**                         |
| :------------: | :--------------------------------------: | :---------: | :---------------------------------: |
| SPRINT 2.4     | Algorithme d'affectation & Résultats     | S11         | Tobi (algo) + Julien (UI résultats) |

**Objectif : Implémenter le traitement automatique d'attribution et permettre la consultation des résultats.**

| **ID** | **Tâche**                                                                                | **Responsable**   | **Priorité** | **Statut** | **Référence**           |
| :----: | :--------------------------------------------------------------------------------------: | :---------------: | :----------: | :--------: | :---------------------: |
| 2.4.1  | Entité Inscription (id, étudiant, session, campagne, dateInscription, statut)            | Tobi              | **Critique** | À faire    | DCD — Table Inscription |
| 2.4.2  | InscriptionDAO : insert(), findByEtudiantAndCampagne(), findAllByCampagne()              | Tobi              | **Critique** | À faire    |                         |
| 2.4.3  | AffectationService : algorithme greedy (itère étudiants par priorité, remplit sessions)  | Tobi              | **Critique** | À faire    | PVL — Test \#18         |
| 2.4.4  | AffectationService : vérifie capacité max par session avant affectation                  | Tobi              | **Critique** | À faire    | PVL — Test \#18         |
| 2.4.5  | AffectationService : passe état campagne Fermée → Traitée → Validée                      | Tobi              | **Critique** | À faire    | Bordereau 7             |
| 2.4.6  | Bouton 'Lancer le Traitement Automatique' dans AdminDashboard (avec confirmation)        | **Julien**        | **Haute**    | À faire    | DSL — Figure 1          |
| 2.4.7  | ResultatsPanel.java (étudiant) : affichage session attribuée, dominante, créneau, statut | **Julien**        | **Haute**    | À faire    | Bordereau 3 / PVL \#19  |
| 2.4.8  | StatistiquesPanel.java (admin) : JTable taux remplissage, % rang 1/2/N satisfaits        | **Julien**        | **Haute**    | À faire    | PVL — Test \#20         |
| 2.4.9  | Export CSV (admin) : liste inscriptions finales par session (FileWriter JDBC)            | **Julien**        | **Normale**  | À faire    | DSL — Section 2.1       |
| 2.4.10 | Tests manuels PVL : \#18 (traitement), \#19 (résultat étudiant), \#20 (statistiques)     | **Julien + Tobi** | **Haute**    | À faire    | PVL — Tests 18-20       |

| **SPRINT 2.5** | **Intégration, Qualité & Performance** | **Période** | **Porteur**   |
| :------------: | :------------------------------------: | :---------: | :-----------: |
| SPRINT 2.5     | Intégration, Qualité & Performance     | S12         | Julien + Tobi |

**Objectif : Finaliser l'intégration MVC, respecter les SLA du PVL, corriger les bugs et préparer la démo prototype.**

| **ID** | **Tâche**                                                                          | **Responsable**   | **Priorité** | **Statut** | **Référence**   |
| :----: | :--------------------------------------------------------------------------------: | :---------------: | :----------: | :--------: | :-------------: |
| 2.5.1  | Intégration complète View ↔ Controller ↔ Service ↔ DAO pour tous les UC            | **Julien + Tobi** | **Critique** | À faire    |                 |
| 2.5.2  | Gestion globale des exceptions SQL (try-catch + messages utilisateur clairs)       | Tobi              | **Haute**    | À faire    |                 |
| 2.5.3  | Respect SLA : authentification \< 1s (p95) — mesure via System.currentTimeMillis() | Tobi              | **Haute**    | À faire    | PVL — PERF-01   |
| 2.5.4  | Respect SLA : affichage liste sessions (48 sessions) \< 2s (p95)                   | **Julien**        | **Haute**    | À faire    | PVL — Test \#22 |
| 2.5.5  | Respect SLA : sauvegarde 3 choix \< 1s (p95)                                       | Tobi              | **Haute**    | À faire    | PVL — Test \#23 |
| 2.5.6  | Vérification isolation données : étudiant autre promo → accès refusé               | Tobi              | **Haute**    | À faire    | PVL — Test \#21 |
| 2.5.7  | Validation de l'affichage sur résolution 1366×768 et 1920×1080                     | **Julien**        | **Normale**  | À faire    | PVL — Critères  |
| 2.5.8  | Code review croisée complète (Julien relit le code Tobi et vice-versa)             | **Julien + Tobi** | **Haute**    | À faire    |                 |
| 2.5.9  | Correction de tous les bugs détectés lors des tests PVL                            | **Julien + Tobi** | **Critique** | À faire    |                 |
| 2.5.10 | Préparation démo prototype en anglais (slides ou démo live)                        | **Julien + Tobi** | **Haute**    | À faire    | Planning S13    |

# **6. PHASE 3 — RECETTE & LIVRAISON (S13–S15)**

Exécution complète du PVL (23 cas de test), corrections des anomalies, livraison intermédiaire en S13, livraison finale en S14–S15.

| **SPRINT 3.1** | **Présentation prototype & Recette partielle** | **Période** | **Porteur**   |
| :------------: | :--------------------------------------------: | :---------: | :-----------: |
| SPRINT 3.1     | Présentation prototype & Recette partielle     | S13         | Julien + Tobi |

| **ID** | **Tâche**                                                       | **Responsable**   | **Priorité** | **Statut** | **Référence**           |
| :----: | :-------------------------------------------------------------: | :---------------: | :----------: | :--------: | :---------------------: |
| 3.1.1  | Présentation prototype (en anglais) — démo live ou slides       | **Julien + Tobi** | **Critique** | À faire    | Planning S13            |
| 3.1.2  | Exécution des 23 cas de test PVL (remplir colonnes OK/KO)       | Tobi              | **Critique** | À faire    | PVL v0.1 — Tableau IV.1 |
| 3.1.3  | Documenter chaque anomalie KO (description, gravité, composant) | **Julien + Tobi** | **Haute**    | À faire    | PVL — Suivi anomalies   |
| 3.1.4  | Livraison intermédiaire + bilan technique (ENT / GitHub)        | **Julien + Tobi** | **Critique** | À faire    | Planning S13            |
| 3.1.5  | Correction des anomalies critiques identifiées                  | **Julien + Tobi** | **Critique** | À faire    |                         |

| **SPRINT 3.2** | **Corrections finales & Livraison** | **Période** | **Porteur**   |
| :------------: | :---------------------------------: | :---------: | :-----------: |
| SPRINT 3.2     | Corrections finales & Livraison     | S14–S15     | Julien + Tobi |

| **ID** | **Tâche**                                                                   | **Responsable**   | **Priorité** | **Statut** | **Référence**     |
| :----: | :-------------------------------------------------------------------------: | :---------------: | :----------: | :--------: | :---------------: |
| 3.2.1  | Correction des anomalies non-critiques restantes                            | **Julien + Tobi** | **Haute**    | À faire    |                   |
| 3.2.2  | Tests de performance finaux (PVL PERF-01, PERF-02, PERF-03)                 | Tobi              | **Haute**    | À faire    | PVL — Tests 22-23 |
| 3.2.3  | Mise à jour du PVL avec résultats définitifs (colonnes OK/KO remplies)      | Tobi              | **Haute**    | À faire    | PVL               |
| 3.2.4  | README.md : instructions installation (JDK, MySQL, JDBC driver, script SQL) | **Julien**        | **Haute**    | À faire    |                   |
| 3.2.5  | Livraison finale — dépôt sur ENT et/ou GitHub (jar + sources + SQL + docs)  | **Julien + Tobi** | **Critique** | À faire    | Planning S14      |
| 3.2.6  | Recette finale avec l'encadrant M. Teboul                                   | **Julien + Tobi** | **Critique** | À faire    | Planning S15      |

# **7. RÈGLES DE COLLABORATION & BONNES PRATIQUES**

| **Conventions Java**                                                                                                                                                                                                                                                                                                                                  | **Critères de 'Terminé' (DoD)**                                                                                                                                                                                                                               |
| :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| \* Nommage : camelCase méthodes, PascalCase classes<br>\* DAO : une interface + une implémentation JDBC<br>\* Service : ne contient jamais de code SQL direct<br>\* View : aucune logique métier, événements seulement<br>\* Javadoc obligatoire sur toutes les méthodes publiques<br>\* Transactions JDBC : auto-commit=false pour les DAO critiques | \* Fonctionnalité implémentée et compilable<br>\* Test manuel PVL associé passé en OK<br>\* Aucune NullPointerException non gérée<br>\* Code revu par l'autre membre du binôme<br>\* Merge dans la branche dev effectué                                       |
| **Conventions SQL / BDD**                                                                                                                                                                                                                                                                                                                             | **Gestion des risques**                                                                                                                                                                                                                                       |
| \* Utiliser uniquement le script SQL du DCD comme référence<br>\* PreparedStatement obligatoire (jamais de concaténation SQL)<br>\* Fermeture des ressources JDBC dans un bloc finally<br>\* Données de test dans un fichier data\_test.sql séparé                                                                                                    | \* Retard : prioriser UC4 (auth), UC1 (inscription), UC6 (campagnes)<br>\* Bug bloquant : notifier immédiatement l'autre membre<br>\* Conflit de merge Git : résolution en binôme immédiate<br>\* Problème connexion MySQL : vérifier port 3306, user, schema |

# **8. RÉCAPITULATIF DES LIVRABLES**

| **Ph.** | **Livrable**                                      | **Responsable** | **Échéance** | **Statut**  |
| :-----: | :-----------------------------------------------: | :-------------: | :----------: | :---------: |
| **P1**  | DSL, DCD, Bordereaux 1–7, PVL v0.1                | Julien + Tobi   | **S3–S4**    | **✅ Livré** |
| **P2**  | Sprint 2.1 : DatabaseConnection + Auth Swing      | Julien + Tobi   | **S6**       | **À faire** |
| **P2**  | Sprint 2.2 : DAOs Campagne/Session + Panels Admin | Julien + Tobi   | **S7–S8**    | **À faire** |
| **P2**  | Sprint 2.3 : Portail Étudiant (UC1–UC5)           | Julien + Tobi   | **S9–S10**   | **À faire** |
| **P2**  | Sprint 2.4 : Algorithme affectation + Résultats   | Julien + Tobi   | **S11**      | **À faire** |
| **P2**  | Sprint 2.5 : Intégration MVC + Qualité + Démo     | Julien + Tobi   | **S12**      | **À faire** |
| **P3**  | Présentation prototype (anglais)                  | Julien + Tobi   | **S13**      | **À faire** |
| **P3**  | Livraison intermédiaire + Bilan technique         | Julien + Tobi   | **S13**      | **À faire** |
| **P3**  | Livraison finale (jar + sources + SQL + docs)     | Julien + Tobi   | **S14**      | **À faire** |
| **P3**  | Recette finale (PVL complet + rapport)            | Julien + Tobi   | **S15**      | **À faire** |

| Ce document est votre boussole tout au long du développement Java/SQL. Mettez-le à jour à chaque fin de sprint pour refléter l'avancement réel du projet. |
| :-------------------------------------------------------------------------------------------------------------------------------------------------------: |

