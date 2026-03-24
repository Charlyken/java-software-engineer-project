CREATE TABLE UTILISATEURS (
    id_utilisateur INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('etudiant', 'admin'))
);

CREATE TABLE ETUDIANTS (
    id_etudiant INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    numero_etudiant VARCHAR(20) NOT NULL UNIQUE,
    promo VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_etudiant) REFERENCES UTILISATEURS(id_utilisateur)
);

CREATE TABLE ADMINS (
    id_admin INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    FOREIGN KEY (id_admin) REFERENCES UTILISATEURS(id_utilisateur)
);

CREATE TABLE CAMPAGNES (
    id_campagne INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nom_campagne VARCHAR(50) NOT NULL UNIQUE,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL
);

CREATE TABLE DOMINANTES (
    id_dominante INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nom_dominante VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE SESSIONS (
    id_session INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_dominante INT NOT NULL,
    id_campagne INT NOT NULL,
    date_session DATE DEFAULT CURRENT_DATE NOT NULL,
    heure_debut VARCHAR(5) NOT NULL,
    heure_fin VARCHAR(5) NOT NULL,
    capacite_max INT NOT NULL,
    FOREIGN KEY (id_dominante) REFERENCES DOMINANTES(id_dominante),
    FOREIGN KEY (id_campagne) REFERENCES CAMPAGNES(id_campagne),
    CONSTRAINT chk_horaires CHECK (
        (heure_debut = '08:30' AND heure_fin = '12:30') 
        OR 
        (heure_debut = '13:30' AND heure_fin = '17:30')
    )
);

CREATE TABLE CHOIX (
  id_choix INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  id_etudiant INT NOT NULL,
  id_session INT NOT NULL,
  id_campagne INT NOT NULL,
  date_saisie DATE DEFAULT CURRENT_DATE NOT NULL,
  priorite INT NOT NULL,
  FOREIGN KEY (id_etudiant) REFERENCES ETUDIANTS(id_etudiant),
  FOREIGN KEY (id_session) REFERENCES SESSIONS(id_session),
  FOREIGN KEY (id_campagne) REFERENCES CAMPAGNES(id_campagne),
  CONSTRAINT uq_etudiant_session UNIQUE (id_etudiant, id_session),
  CONSTRAINT uq_choix_priorite UNIQUE (id_etudiant, priorite, id_campagne)
);

CREATE TABLE INSCRIPTIONS (
    id_inscription INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_etudiant INT NOT NULL,
    id_session INT NOT NULL,
    id_campagne INT NOT NULL,
    date_inscription DATE DEFAULT CURRENT_DATE NOT NULL,
    FOREIGN KEY (id_etudiant) REFERENCES ETUDIANTS(id_etudiant),
    FOREIGN KEY (id_session) REFERENCES SESSIONS(id_session),
    FOREIGN KEY (id_campagne) REFERENCES CAMPAGNES(id_campagne)
);