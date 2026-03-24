Sujet du Projet : Gestion des Inscriptions aux Dominantes (ESIGELEC)

1. Contexte et Objectifs

L'ESIGELEC organise des sessions de présentation de ses "dominantes" (spécialisations de cycle ingénieur) sur une journée banalisée.
Chaque dominante propose plusieurs créneaux de présentation de 30 minutes, avec une capacité d'accueil limitée par salle.

L'objectif du système est de permettre aux étudiants de s'inscrire à un nombre limité de sessions selon leurs préférences, et de fournir à l'administration un moteur de traitement automatique pour valider ces inscriptions en tenant compte de deux facteurs critiques :

Les vœux de l'étudiant (ordre de préférence).

Les capacités maximales des sessions.

Contrainte métier forte : Les sessions ne peuvent se dérouler que sur deux plages horaires strictes :

Matin : entre 8h30 et 12h30.

Après-midi : entre 13h30 et 17h30.

2. Périmètre Fonctionnel et Rôles

Le système distingue deux acteurs principaux :

A. L'Administrateur (Scolarité)

Paramétrage : Crée les dominantes, les sessions associées, et la promotion concernée. Il définit le paramètre $N$ (le nombre maximum de choix possibles par étudiant).

Pilotage de la campagne : Ouvre et ferme la campagne d'inscription manuellement.

Traitement : Lance l'algorithme d'attribution automatique des places une fois la campagne fermée.

Consultation : Accède aux statistiques de remplissage et gère le CRUD des sessions (uniquement avant l'ouverture).

B. L'Étudiant

Recherche : Visualise le catalogue des sessions (filtrage par nom de dominante et horaire).

Saisie des vœux : Sélectionne jusqu'à $N$ sessions et les ordonne par ordre de préférence (Choix 1, Choix 2, ..., Choix N).

Modification : Peut modifier sa liste tant que l'administrateur maintient l'état de la campagne à "Ouverte".

Consultation : Visualise son affectation finale une fois que l'administrateur a validé les résultats du traitement.

3. Cycle de Vie des Inscriptions (Machine à États)

Le système repose sur un cycle de vie strict porté par l'entité "Campagne" :

En préparation : L'admin crée et modifie les sessions. Les étudiants ne voient rien.

Ouvertes : Les étudiants peuvent saisir et ordonner leurs vœux.

Fermées : Fin de la saisie. Plus aucune modification étudiante n'est autorisée.

Traitement : L'admin lance l'algorithme qui attribue les places (selon places disponibles et ordre de préférence).

Validées : Les résultats définitifs sont publiés et visibles par les étudiants.

Archivées : La campagne est clôturée et conservée pour historique.

4. Les Cas d'Usage Principaux (Résumé)

Admin : CRUD Dominantes/Sessions (avant ouverture), paramétrage de la campagne (dates, nombre de choix max), gestion complète du cycle de vie.

Étudiant : Recherche multicritères, saisie de $N$ choix ordonnés, consultation des résultats post-validation.

5. Environnement Technique Cible

Type d'application : Client lourd (Desktop).

Interface Graphique : Java Swing.

Base de données : Base de données relationnelle (SQL) pour garantir l'intégrité des données (contraintes d'unicité, clés étrangères).