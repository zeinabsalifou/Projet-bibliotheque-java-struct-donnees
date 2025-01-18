
package src;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionBibliotheque {

	// Méthode d'entrée
	public static void main(String[] args) {
		int capacite = 100; // capacité initiale
		Bibliotheque biblio = new Bibliotheque(capacite);

		Scanner scanner = new Scanner(System.in);

		System.out.println("---------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------");
		System.out.println();
		System.out.println("   BIENVENUE DANS LE SYSTEME DE GESTION  DE BIBLIOTHEQUE!");
		System.out.println();
		System.out.println("---------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------");

		// Initialisation des livres
		initialiserLivresPetitesBibliotheques(biblio);
		initialiserLivresTries(biblio);
		initialiserLivresHachage(biblio);
		initialiserLivresArbreBinaire(biblio);

		// Menu principale
		while (true) {
			System.out.println("\n***************** MENU PRINCIPAL ******************\n");
			System.out.println("1) Petite bibliothèque");
			System.out.println("2) Bibliothèque triée par titre");
			System.out.println("3) Bibliothèque organisée par catégorie ou genre");
			System.out.println("4) Bibliotheque basée sur l'ISBN ");
			System.out.println("0) Quitter");
			System.out.println();
			System.out.print("--*-- Veuillez choisir une option : ");

			try {
				int choix = scanner.nextInt();
				scanner.nextLine();

				switch (choix) {
				case 1:
					sousMenuPetitesBibliotheques(biblio, scanner);
					break;
				case 2:
					sousMenuTries(biblio, scanner);
					break;
				case 3:

					sousMenuArbreBinaire(biblio, scanner);

					break;
				case 4:
					sousMenuHachage(biblio, scanner);
					break;
				case 0:
					System.out.println();
					System.out
							.println("****Merci d'avoir utilisé le système de gestion de bibliothèque. Au revoir!****");
					scanner.close(); // Fermeture du scanner avant de quitter le programme
					System.exit(0); // Sortie du programme
				default:
					System.out.println("Option invalide. Veuillez choisir une option valide!");
				}
			} catch (InputMismatchException e) {
				System.out.println("Veuillez entrer un nombre entier correspondant à une option du menu!");
				scanner.nextLine(); // Pour consommer la nouvelle ligne erronée
			}
		}
	}

	// Sous-menu pour la petite bibliothèque
	public static void sousMenuPetitesBibliotheques(Bibliotheque biblio, Scanner scanner) {
		System.out.println();
		System.out.println("---------------  ---------------  ------------");
		System.out.println("\n*** SOUS-MENU : PETITE BIBLIOTHEQUE ***");
		System.out.println();

		System.out.println("1) Ajouter un livre");
		System.out.println("2) Rechercher un livre");
		System.out.println("3) Emprunter un livre");
		System.out.println("4) Rendre un livre");
		System.out.println("5) Afficher la liste des livres");
		System.out.println("0) Retour au menu principal");
		System.out.println();
		System.out.print("--*-- Veuillez choisir une option : ");
		int choix = scanner.nextInt();
		scanner.nextLine();

		switch (choix) {
		case 1:
			// Ajouter un livre a la petite bibliothèque
			System.out.println();

			System.out.println("Entrez le titre du livre : ");
			String titre = scanner.nextLine();
			System.out.println("Entrez le nom de l'auteur : ");
			String auteur = scanner.nextLine();
			System.out.println("Entrez l'ISBN du livre : ");
			String isbn = scanner.nextLine();

			Livre nouveauLivre = new Livre(titre, auteur, isbn);
			biblio.ajouterLivrePetitesBibliotheques(nouveauLivre);
			System.out.println("Le livre a été ajouté avec succès a la petite bibliothèque!");

			break;
		case 2:
			// Rechercher un livre dans la petite bibliothèque
			System.out.println();
			System.out.print("Entrez le titre du livre à rechercher : ");
			String titreRecherche = scanner.nextLine();
			Livre livreTrouve = biblio.rechercherLivrePetitesBibliotheques(titreRecherche);
			if (livreTrouve != null) {
				System.out.println("Livre trouvé : " + livreTrouve);
			} else {
				System.out.println("Livre non trouvé.");
			}
			break;

		case 3:
			// Emprunter un livre dans la petite bibliothèque
			biblio.emprunterLivrePetitesBibliotheques(scanner);

			break;
		case 4:
			// Rendre un livre dans la petite bibliothèque
			biblio.rendreLivrePetitesBibliotheques(scanner);
			break;
		case 5:
			// Afficher liste de livres
			afficherLivresPetites(biblio);
			break;
		case 0:
			// Retour au menu principal

			break;
		default:
			System.out.println("Option invalide. Veuillez choisir une option valide!");
		}

	}

	// Sous-menu pour la bibliothèque triée
	public static void sousMenuTries(Bibliotheque biblio, Scanner scanner) {
		System.out.println();
		System.out.println("---------------  ---------------  ------------");
		System.out.println("\n*** SOUS-MENU: BIBLIOTHEQUE TRIÉE ***");
		System.out.println();

		System.out.println("1) Ajouter un livre");
		System.out.println("2) Rechercher un livre");
		System.out.println("3) Emprunter un livre");
		System.out.println("4) Rendre un livre");
		System.out.println("5) Afficher la liste des livres");
		System.out.println("0) Retour au menu principal");
		System.out.println();
		System.out.print("--*-- Veuillez choisir une option : ");
		int choix = scanner.nextInt();
		scanner.nextLine(); 

		switch (choix) {
		case 1:
			// Ajouter un livre a la bibliotheque triée
			biblio.ajouterLivreTriés(scanner);
			break;
		case 2:
			// Rechercher un livre dans la bibliotheque triée
			System.out.println();
			System.out.println("Entrez le titre du livre que vous souhaitez rechercher dans la bibliothèque triée :");
			String titreRecherche = scanner.nextLine(); // Déclaration et récupération du titre recherché

			// Appel de la méthode de recherche binaire dans la bibliothèque triée
			Livre livreTrouve = biblio.rechercherLivreTrie(titreRecherche);

			if (livreTrouve != null) {
				System.out.println("Livre trouvé: " + livreTrouve);

			} else {
				System.out.println("Le livre n'existe pas dans la bibliothèque triée.");
			}
			break;

		case 3:
			// Emprunter un livre dans la bibliotheque triée
			biblio.emprunterLivreTrie(scanner);

			break;
		case 4:
			// Rendre un livre dans la bibliotheque triée
			biblio.rendreLivreTrie(scanner);
			break;
		case 5:
			// Afficher liste de livres dans les petites bibliothèques
			afficherLivresTriés(biblio);
			break;
		case 0:
			// Retour au menu principal
			break;
		default:
			System.out.println("Option invalide. Veuillez choisir une option valide!");
		}

	}

	// Sous-menu pour les recherches rapides basées sur l'ISBN
	public static void sousMenuHachage(Bibliotheque biblio, Scanner scanner) {
		System.out.println();
		System.out.println("---------------  ---------------  ------------");
		System.out.println("\n*** TABLE DE HACHAGE ***");
		System.out.println();

		System.out.println("1) Ajouter un livre");
		System.out.println("2) Rechercher un livre");
		System.out.println("3) Emprunter un livre");
		System.out.println("4) Rendre un livre");
		System.out.println("5) Afficher la liste des livres ");
		System.out.println("0) Retour au menu principal");
		System.out.println();
		System.out.print("--*-- Veuillez choisir une option : ");
		int choix = scanner.nextInt();
		scanner.nextLine();
		switch (choix) {
		case 1:
			// Ajouter un livre a la table de hachage
			biblio.ajoutLivreHachage(scanner);
			break;
		case 2:
			// Rechercher un livre dans la table de hachage
			System.out.println();
			System.out.print("Entrez l'ISBN du livre à rechercher: ");
			String isbnRecherche = scanner.nextLine();
			Livre livreTrouve = biblio.rechercherLivreHachage(isbnRecherche);
			if (livreTrouve != null) {
				System.out.println("Livre trouvé: " + livreTrouve);
			} else {
				System.out.println("Livre non trouvé.");
			}
			break;
		case 3:
			// Emprunter un livre dans la table de hachage
			System.out.print("Entrez l'ISBN du livre que vous souhaitez emprunter : ");
			String isbnEmprunt = scanner.nextLine();
			biblio.emprunterLivreHachage(isbnEmprunt);
			break;
		case 4:
			// Rendre un livre dans la table de hachage
			System.out.print("Entrez l'ISBN du livre que vous souhaitez rendre : ");
			String isbnRendu = scanner.nextLine();
			biblio.rendreLivreHachage(isbnRendu);
			break;
		case 5:
			// Afficher liste de livres dans la table de hachage
			afficherLivresHachage(biblio);
			break;
		case 0:
			// Retour au menu principal
			break;
		default:
			System.out.println("Option invalide. Veuillez choisir une option valide!");
		}

	}

	// Sous-menu pour les opérations sur l'arbre binaire
	public static void sousMenuArbreBinaire(Bibliotheque biblio, Scanner scanner) {
		System.out.println();
		System.out.println("---------------  ---------------  ------------");
		System.out.println("\n*** SOUS-MENU: BIBLIOTHEQUE ORGANISÉE PAR CATHÉGORIE OU GENRE ***");
		System.out.println();
		System.out.println("1) Ajouter un livre ");
		System.out.println("2) Rechercher un livre ");
		System.out.println("3) Emprunter un livre");
		System.out.println("4) Rendre un livre");
		System.out.println("5) Afficher la liste des livres ");
		System.out.println("0) Retour au menu principal");
		System.out.println();
		System.out.print("--*-- Veuillez choisir une option : ");
		int choix = scanner.nextInt();
		scanner.nextLine(); 

		switch (choix) {
		case 1:
			// Ajouter un livre a l'arbre binaire
			biblio.ajouterLivreArbre(scanner);
			break;
		case 2:
			// Rechercher un livre dans l'arbre binaire
			System.out.println();
			System.out.print("Entrez le titre du livre à rechercher : ");
			String titreRecherche = scanner.nextLine();
			Livre livreRecherche = biblio.rechercherLivreParTitreDansArbre(biblio.arbreBinaire.getRacine(),
					titreRecherche);
			if (livreRecherche != null) {
				System.out.println("Livre trouvé: " + livreRecherche);
			} else {
				System.out.println("Livre non trouvé!");
			}
			break;
		case 3:
			// Emprunter un livre dans l'arbre binaire
			System.out.print("Entrez le titre du livre à emprunter : ");
			String titreEmprunt = scanner.nextLine();
			biblio.emprunterLivre(titreEmprunt);

			break;
		case 4:
			// Rendre un livre dans l'arbre binaire
			System.out.print("Entrez le titre du livre à rendre : ");
			String titreRendu = scanner.nextLine();
			biblio.rendreLivre(titreRendu);
			break;
		case 5:
			// Afficher liste de livres dans l'arbre binaire
			afficherLivresArbre(biblio);
			break;
		case 0:
			// Retour au menu principal
			break;
		default:
			System.out.println("Option invalide. Veuillez choisir une option valide!");
		}

	}

	// Méthode pour initialiser les livres dans la petite bibliothèque
	public static void initialiserLivresPetitesBibliotheques(Bibliotheque biblio) {
		biblio.ajouterLivrePetitesBibliotheques(new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", "10"));
		biblio.ajouterLivrePetitesBibliotheques(new Livre("Harry Potter à l'école des sorciers", "J.K. Rowling", "20"));
		biblio.ajouterLivrePetitesBibliotheques(new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", "30"));
		biblio.ajouterLivrePetitesBibliotheques(new Livre("Orgueil et Préjugés", "Jane Austen", "40"));
	}

	// Méthode pour initialiser les livres dans la bibliothèque triée
	public static void initialiserLivresTries(Bibliotheque biblio) {
		biblio.ajouterLivreTrie(new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", "10"));
		biblio.ajouterLivreTrie(new Livre("Harry Potter à l'école des sorciers", "J.K. Rowling", "20"));
		biblio.ajouterLivreTrie(new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", "30"));
		biblio.ajouterLivreTrie(new Livre("Orgueil et Préjugés", "Jane Austen", "40"));
	}

	// Méthode pour initialiser les livres dans la bibliothèque de hachage
	public static void initialiserLivresHachage(Bibliotheque biblio) {
		biblio.ajouterLivreHachage(new Livre("Le Petit roi", "Antoine de Saint-Exupéry", "10"));
		biblio.ajouterLivreHachage(new Livre("Harry Potter à l'école des sorciers", "J.K. Rowling", "20"));
		biblio.ajouterLivreHachage(new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", "30"));
		biblio.ajouterLivreHachage(new Livre("Orgueil et Préjugés", "Jane Austen", "40"));
	}

	// Méthode pour initialiser les livres dans la bibliothèque d'arbre binaire
	public static void initialiserLivresArbreBinaire(Bibliotheque biblio) {
		biblio.ajouterLivreArbreBinaire(new Livre("Le Petit plombier", "Antoine de Saint-Exupéry", "10"));
		biblio.ajouterLivreArbreBinaire(new Livre("Harry Potter à l'école des sorciers", "J.K. Rowling", "20"));
		biblio.ajouterLivreArbreBinaire(new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", "30"));
		biblio.ajouterLivreArbreBinaire(new Livre("Orgueil et Préjugés", "Jane Austen", "40"));
	}

	// Méthode pour afficher la liste des livres disponibles dans la petite
	// bibliothèque
	public static void afficherLivresPetites(Bibliotheque biblio) {

		System.out.println("\n--*--*--Livres disponibles dans la petite bibliothèque :--*--*--");
		System.out.println();
		for (Livre livre : biblio.getPetitesBibliotheques()) {
			if (livre.isDisponible()) {
				System.out.println(livre);
			}
		}
	}

	// Méthode pour afficher la liste des livres disponibles
	public static void afficherLivresTriés(Bibliotheque biblio) {

		System.out.println("\n--*--*--Liste des livres dans la bibliothèque triée :--*--*--");
		System.out.println();
		for (Livre livre : biblio.getLivresTries()) {
			if (livre != null && livre.isDisponible()) {
				System.out.println(livre);
			}
		}
	}

	// Méthode pour afficher la liste des livres disponibles
	public static void afficherLivresHachage(Bibliotheque biblio) {

		System.out.println("\n--*--*--Liste des livres dans la table de hachage :--*--*--");
		System.out.println();
		for (Livre livre : biblio.getLivresParIsbn().values()) {
			if (livre != null && livre.isDisponible()) {
				System.out.println(livre);
			}
		}
	}

	// Méthode pour afficher la liste des livres disponibles
	public static void afficherLivresArbre(Bibliotheque biblio) {

		parcourirArbreBinaire(biblio.getArbreBinaire());
	}

	// Méthode pour parcourir arbre binaire
	public static void parcourirArbreBinaire(ArbreBinaire arbre) {
		if (arbre == null) {
			System.out.println("L'arbre est vide.");
			return;
		}

		System.out.println("-*-*-*-*-*-Liste des livres dans l'arbre binaire: -*-*-*-*-*-");
		System.out.println();
		parcourirNoeud(arbre.getRacine()); // Commencer le parcours à partir de la racine
	}

	private static void parcourirNoeud(Noeud racine) {
		if (racine == null) {
			return;
		}

		// Parcourir l'arbre en ordre : gauche, racine, droite 
		parcourirNoeud(racine.getGauche());
		if (racine.getLivre() != null && racine.getLivre().isDisponible()) {
			System.out.println(racine.getLivre()); // Afficher le livre du noeud courant s'il est disponible
		}
		parcourirNoeud(racine.getDroite());
	}
}