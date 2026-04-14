# Gestion des Étudiants (Lab15)

Cette application Android permet de gérer une liste d'étudiants en utilisant une base de données locale SQLite. Elle offre une interface intuitive basée sur Material Design pour effectuer les opérations de base (CRUD).

## Fonctionnalités

- **Ajout d'étudiants** : Saisissez le nom et le prénom pour enregistrer un nouvel étudiant.
- **Recherche par ID** : Retrouvez rapidement les informations d'un étudiant via son identifiant unique.
- **Suppression** : Supprimez un étudiant directement depuis l'écran principal ou la liste.
- **Liste complète** : Visualisez tous les étudiants enregistrés dans un tableau organisé.
- **Mise à jour** : Modifiez les informations d'un étudiant via une boîte de dialogue dédiée dans la liste.

## Technologies Utilisées

- **Langage** : Java
- **Base de données** : SQLite (via `MySQLiteHelper`)
- **UI Components** :
    - Material Design (TextInputLayout, FloatingActionButton, etc.)
    - CardView pour une présentation moderne.
    - TableLayout pour l'affichage de la liste.
    - ConstraintLayout pour des interfaces réactives.
- **Architecture** : Utilisation d'une couche Service (`EtudiantService`) pour séparer la logique métier de l'interface utilisateur.

## Structure du Projet

- `com.example.lab15.classes` : Contient le modèle `Etudiant`.
- `com.example.lab15.service` : Contient la logique de gestion des données.
- `com.example.lab15.util` : Contient l'assistant de base de données SQLite.
- `MainActivity` : Écran d'accueil pour l'ajout, la recherche et la suppression rapide.
- `ListEtudiantActivity` : Écran affichant le tableau de tous les étudiants.

## Installation

1. Clonez le dépôt.
2. Ouvrez le projet dans **Android Studio**.
3. Synchronisez le projet avec les fichiers Gradle.
4. Lancez l'application sur un émulateur ou un appareil physique (Android 5.0+).

---
*Développé dans le cadre du TP de Gestion des Étudiants.*