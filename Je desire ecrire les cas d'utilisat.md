# BLOC NOTE DU PDL



### CAS D'UTILISATION

 



Acteur : Administrateur



UC-A1 : Gérer le CRUD des Dominantes (Créer, Modifier, Supprimer).



UC-A2 : Planifier les sessions (Associer des créneaux horaires et des capacités aux dominantes).



UC-A3 : Piloter la campagne (Changer l'état global du système : Préparation -> Ouvert -> Fermé...).



UC-A4 : Exécuter l'attribution (Lancer l'algorithme de calcul des places).



UC-A5 : Analyser les statistiques (Identifier les sessions saturées ou les étudiants sans place





Acteur : Étudiant



UC-E1 : Rechercher des sessions (Filtrer par nom ou par heure).



UC-E2 : Saisir et ordonner ses vœux (Interface de sélection de N choix).



UC-E3 : Consulter son affectation (Une fois la phase de validation terminée).



UC-E4 : S’inscrire à N de sessions.





### LES CLASSES DE MON SYSTEME

**Utilisateur**(id,Nom, email, password)

**Admin** Herite **Utilisateur**

**Etudiant** Herite **Utilisateur**

**Dominante**(id, nom)

**Session de presentation**(id, date, heureDebut, heureFin, capacité)

**Campagne**(id, anneeAcademique, etat, nbreDeVoeux), etat (OUVERT, FERME, VALIDE...)

**Inscription**(id, dateDeValidation, dateAttribution, statut)

**Choix** (listeOrdoneéDominantes, dateChoix)

*Affectation(dateAttribution, statut), statut(CONFIRME, REFUSE, EN ATTENTE) ANNULEE*





### VERBES CLES

**Dominante** possede des **sessions de présentation**

**Etudiants** s’inscrit à un nombre limité de **sessions**

**Administrateur** paramètre la **Campagne**

**Administrateur** paramètre une **Inscription**

**Étudiant** saisit des **choix**

**Etudiant** consulte ses **Inscriptions**

**Choix** appartient à une **Campagne**

**Session** appartient à une **Campagne**



### CARDINALITES



|Relation|Type|Description|
|-|-|-|
|Dominante ↔ Session|1:N|Une dominante a plusieurs sessions.|
|Campagne ↔ Session|1:N|Une campagne regroupe toutes les sessions d'une année.|
|Etudiant ↔ Choix|1:N|Un étudiant émet N choix par campagne.|
|Session ↔ Choix|1:N|Une session reçoit les choix de plusieurs étudiants.|
|Etudiant ↔ Inscription|1:N||

### 

### TYPE DE RELATION : Agrégation, composition , heritage.



**Etudiant, Admin** hérite de **Utilisateur : Héritage**

Une **Campagne** est composée de **Session et Inscription: Composition**

Une **Session** a une **dominante** : **agrégation**

Une **Session** est composée d'**Inscription**s : **Composition**

Une **Campagne** est composée d'**Inscription** : **Composition**



### Realisation du DSL

#### Objectif du projet:

Utilisation de la **méthode SMART** comme pattern qui permet de mettre en place des objectifs clairs et mesurables et atteignables.

