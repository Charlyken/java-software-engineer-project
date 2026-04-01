Modèle Physique de Données (MPD) - Base ESIGELEC

1. Pôle Utilisateurs et Authentification

Table utilisateurs

La table mère pour la connexion.

id_utilisateur (PK) : INT, Auto-incrément

email : VARCHAR(255), UNIQUE, NOT NULL

mot_de_passe : VARCHAR(255), NOT NULL (Hashé)

string : VARCHAR(50), NOT NULL (Valeurs : 'ADMIN', 'ETUDIANT')

Table etudiants

Table enfant (Héritage). Contient les infos spécifiques aux étudiants.

id_etudiant (PK) : INT

numero_etudiant : VARCHAR(50), UNIQUE, NOT NULL

promotion : VARCHAR(10)

Clé Étrangère (FK) : id_etudiant référence utilisateurs(id_utilisateur) ON DELETE CASCADE.

2. Pôle Académique et Paramétrage

Table campagnes

Gère le cycle de vie global.

id_campagne (PK) : INT, Auto-incrément

annee_universitaire : VARCHAR(9) (ex: "2023-2024")

etat : VARCHAR(20) (Valeurs : 'PREPARATION', 'OUVERT', 'FERME', 'TRAITEMENT', 'VALIDE')

nb_voeux_max : INT, NOT NULL (Le fameux paramètre $N$)

Table dominantes

Le catalogue des matières.

id_dominante (PK) : INT, Auto-incrément

nom : VARCHAR(100), NOT NULL

description : TEXT

Table sessions

Les créneaux horaires concrets associés aux dominantes.

id_session (PK) : INT, Auto-incrément

id_dominante (FK) : INT, NOT NULL

id_campagne (FK) : INT, NOT NULL

date_session : DATE, NOT NULL

heure_debut : TIME, NOT NULL

heure_fin : TIME, NOT NULL

capacite_max : INT, NOT NULL

Clés Étrangères (FK) : - id_dominante référence dominantes(id_dominante).

id_campagne référence campagnes(id_campagne).

3. Pôle Inscription (Les Tables de Jointure)

Table choix

Ce que l'étudiant souhaite (Table de jointure complexe).

id_choix (PK) : INT, Auto-incrément

id_etudiant (FK) : INT, NOT NULL

id_session (FK) : INT, NOT NULL

priorite : INT, NOT NULL (1, 2, 3...)

date_saisie : TIMESTAMP, DEFAULT CURRENT_TIMESTAMP

Clés Étrangères (FK) :

id_etudiant référence etudiants(id_etudiant).

id_session référence sessions(id_session).

Contraintes d'Unicité (Crucial pour l'algorithme) :

UNIQUE(id_etudiant, id_session) : Un étudiant ne peut pas faire deux vœux pour la même session.


Table inscriptions

Le résultat final validé par l'algorithme.

id_inscription (PK) : INT, Auto-incrément

id_etudiant (FK) : INT, NOT NULL

id_session (FK) : INT, NOT NULL

date_inscription : TIMESTAMP, DEFAULT CURRENT_TIMESTAMP

Clés Étrangères (FK) :

id_etudiant référence etudiants(id_etudiant).

id_session référence sessions(id_session).

Contrainte d'Unicité :

UNIQUE(id_etudiant, id_session) : Pas de double inscription.