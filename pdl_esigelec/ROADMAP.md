Roadmap Complète : Système de Gestion des Inscriptions (ESIGELEC)

Ce document définit la stratégie d'implémentation itérative du projet. Chaque phase doit être validée avant de passer à la suivante (principe du "Fail Fast" : détecter les erreurs le plus tôt possible).

Phase 1 : Cadrage et Architecture (✅ Terminée)

Livrables : Cahier des charges, Charte de projet, Diagrammes (Use Cases, Classes), Modèle Physique de Données (Eraser.io), Squelettes des Vues Java Swing.

Objectif : Aligner la vision technique et métier.

Phase 2 : Socle Technique & Base de Données (En cours)

On ne peut pas manipuler des données si la base n'existe pas.

Sprint 2.1 : Configuration du projet ✅ Terminée

Initialisation du projet Java avec Maven ou Gradle.

Importation des dépendances : JDBC (ORACLE), JUnit (pour les tests).

Sprint 2.2 : Implémentation de la Base de Données

Création du script SQL (schema.sql) basé sur le dictionnaire de données.

Création d'un script d'insertion de données de test (seed.sql : quelques étudiants, admins, dominantes et sessions fictives).

Sprint 2.3 : Couche Modèle (POJO)

Création des classes Etudiant, Session, Voeu, Campagne, etc. dans fr.esigelec.model.

Phase 3 : L'Accès aux Données (Couche DAO)

Il faut que notre code Java puisse parler à la base de données de manière sécurisée.

Sprint 3.1 : Singleton de Connexion

Création de la classe DatabaseConnection pour gérer la connexion unique à la base.

Sprint 3.2 : Implémentation des interfaces DAO

UtilisateurDAO : Méthode authenticate(email, password).

SessionDAO : Méthode findAllByCampagne(campagneId).

VoeuDAO : Méthode insertVoeu(voeu) avec gestion stricte des exceptions SQL (contraintes d'unicité).

Phase 4 : Le Cœur Métier (Couche Service & Algorithme)

C'est ici que se trouve l'intelligence de l'application.

Sprint 4.1 : Services CRUD basiques

CampagneService : Gérer l'ouverture/fermeture et la validation des contraintes.

EtudiantService : Vérifier qu'un étudiant ne dépasse pas le nombre de vœux $N$.

Sprint 4.2 : Le Moteur d'Attribution (CRITIQUE)

Création de AttributionService.

Développement de l'algorithme qui lit les vœux ordonnés, vérifie les places restantes dans les sessions, et génère les objets Inscription.

Validation par Tests Unitaires (JUnit) obligatoire sur cette partie.

Phase 5 : Câblage de l'IHM (Contrôleurs)

Nous donnons vie aux écrans que nous avons maquettés.

Sprint 5.1 : Contrôleur d'Authentification

LoginController : Relier le bouton "Se connecter" au UtilisateurDAO, puis déclencher MainFrame.switchTo(...).

Sprint 5.2 : Espace Étudiant

StudentDashboardController : Charger dynamiquement le tableau des sessions depuis la DB.

Gérer la logique de Drag & Drop pour la liste des vœux et l'enregistrement final.

Sprint 5.3 : Espace Administrateur

AdminDashboardController : Rendre les boutons de gestion de campagne fonctionnels.

Brancher le bouton "Lancer le traitement" sur le AttributionService via un SwingWorker (pour ne pas figer l'interface).

Phase 6 : Tests Finaux et Livraison

Sprint 6.1 : Tests d'Intégration

Simuler 500 étudiants (script automatisé) et lancer l'algorithme depuis l'interface pour vérifier le temps de réponse et l'intégrité des données.

Sprint 6.2 : Packaging

Création du fichier .jar exécutable avec toutes les dépendances incluses (Fat/Uber JAR).

Sprint 6.3 : Documentation

Génération de la JavaDoc et rédaction du manuel d'utilisation (Admin et Étudiant).
