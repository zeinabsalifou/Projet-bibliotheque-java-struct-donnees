
package src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Bibliotheque {

	private LinkedList<Livre> petitesBibliotheques; // Liste chaînée pour les petites bibliothèques
	private Livre[] livresTries; // Tableau trié par titre
	private HashMap<String, Livre> livresParIsbn; // Table de hachage pour les recherches rapides
	ArbreBinaire arbreBinaire; // Arbre binaire

	private int compteurIsbn;

	// Constructeur
	public Bibliotheque(int capacite) {
		petitesBibliotheques = new LinkedList<>();
		livresTries = new Livre[capacite];
		livresParIsbn = new HashMap<>();
		arbreBinaire = new ArbreBinaire();

		compteurIsbn = 50; // Initialisation du compteur

	}

	public Livre[] getLivresTries() {
		return livresTries != null ? livresTries : new Livre[0];
	}

	// Méthode pour ajouter un livre aux petites bibliothèques
	public void ajouterLivrePetitesBibliotheques(Livre livre) {
		petitesBibliotheques.add(livre);
	}

	public Livre rechercherLivrePetitesBibliotheques(String titre) {
		for (Livre livre : petitesBibliotheques) {
			if (livre.getTitre().equalsIgnoreCase(titre)) {
				return livre; // Retourne le livre s'il est trouvé
			}
		}
		return null;
	}

	// Méthode pour emprunter un livre dans la petite bibliotheque
	public void emprunterLivrePetitesBibliotheques(Scanner scanner) {
		System.out.println();
		System.out.println("Entrez le titre du livre que vous souhaitez emprunter :");
		String titre = scanner.nextLine();

		// Rechercher le livre dans la petite bibliotheque
		Livre livre = rechercherLivrePetitesBibliotheques(titre);

		if (livre != null && livre.isDisponible()) {
			// Marquer le livre comme non disponible
			livre.setDisponible(false);
			System.out.println("Le livre '" + livre.getTitre() + "' a été emprunté avec succès.");
		} else {
			System.out.println("Le livre n'est pas disponible ou n'existe pas dans la petite bibliothèque.");
		}
	}

	// Méthode pour rendre un livre dans la petite bibliotheque
	public void rendreLivrePetitesBibliotheques(Scanner scanner) {
		System.out.println();
		System.out.println("Entrez le titre du livre que vous souhaitez rendre :");
		String titre = scanner.nextLine();

		// Rechercher le livre dans la liste des livres
		Livre livre = rechercherLivrePetitesBibliotheques(titre);

		if (livre != null && !livre.isDisponible()) {
			// Marquer le livre comme disponible
			livre.setDisponible(true);
			System.out.println("Le livre '" + livre.getTitre() + "' a été rendu avec succès.");
		} else {
			System.out.println("Le livre n'est pas emprunté ou n'existe pas dans la petite bibliothèque.");
		}
	}

	// Méthode pour ajouter un livre dans tableau trié
	public void ajouterLivreTriés(Scanner scanner) {
		System.out.println();

		System.out.print("Entrez le titre du livre : ");
		String titre = scanner.nextLine();

		System.out.print("Entrez l'auteur du livre : ");
		String auteur = scanner.nextLine();

		System.out.print("Entrez l'ISBN du livre : ");
		String isbn = scanner.nextLine();

		Livre livre = new Livre(titre, auteur, isbn);
		ajouterLivreTrie(livre);

		System.out.println("Le livre a été ajouté avec succès à la bibliothèque triée.");

	}

	// Méthode pour ajouter un livre dans tableau trié
	public void ajouterLivreTrie(Livre livre) {
		int indice = 0;
		// Trouver l'indice où insérer le livre en fonction du titre
		while (indice < livresTries.length && (livresTries[indice] != null
				&& livre.getTitre().compareToIgnoreCase(livresTries[indice].getTitre()) > 0)) {
			indice++;
		}

		// Décaler les livres pour insérer le nouveau livre de manière triée
		for (int i = livresTries.length - 1; i > indice; i--) {
			livresTries[i] = livresTries[i - 1];
		}

		livresTries[indice] = livre; // Insérer le livre dans le tableau trié
	}

	public ArbreBinaire getArbreBinaire() {
		return this.arbreBinaire;
	}

	// Méthode pour ajouter un livre à l'arbre binaire
	public void ajouterLivreArbreBinaire(Livre livre) {
		arbreBinaire.ajouterLivre(livre);
	}

	// Méthode pour rechercher un livre par titre dans le tableau trié
	public Livre rechercherLivreTrie(String titreRecherche) {
		int debut = 0;
		int fin = livresTries.length - 1;

		while (debut <= fin) {
			int milieu = (debut + fin) / 2;
			Livre livreMilieu = livresTries[milieu];

			// Vérifier si livreMilieu est null
			if (livreMilieu == null) {
				// Si livreMilieu est null, ajuster les indices de recherche et continuer la
				// boucle
				if (milieu == debut && livresTries[fin] == null) {
					// Si les éléments aux extrémités sont également nuls, déplacer les indices
					debut++;
					fin--;
				} else if (livresTries[debut] == null) {
					debut++;
				} else {
					fin--;
				}
				continue;
			}

			String titreMilieu = livreMilieu.getTitre();

			// Comparaison des titres
			int comparaison = titreMilieu.compareToIgnoreCase(titreRecherche);

			if (comparaison == 0) {
				// Le livre a été trouvé
				return livreMilieu;
			} else if (comparaison < 0) {
				debut = milieu + 1;
			} else {
				fin = milieu - 1;
			}
		}

		// Aucun livre trouvé, retourne null
		return null;
	}

	// Méthode pour emprunter un livre dans tableau trié
	public void emprunterLivreTrie(Scanner scanner) {
		System.out.println();
		System.out.print("Entrez le titre du livre que vous souhaitez emprunter : ");
		String titre = scanner.nextLine();

		// Recherche du livre dans la bibliothèque triée
		Livre livre = rechercherLivreTrie(titre);

		if (livre != null && livre.isDisponible()) {
			// Marquer le livre comme emprunté
			livre.setDisponible(false);
			System.out.println("Le livre '" + livre.getTitre() + "' a été emprunté avec succès.");
		} else if (livre != null && !livre.isDisponible()) {
			System.out.println("Le livre '" + livre.getTitre() + "' n'est pas disponible pour le moment.");
		} else {
			System.out.println("Le livre '" + titre + "' n'existe pas dans la bibliothèque triée.");
		}
	}

	// Méthode pour rendre un livre dans tableau trié
	public void rendreLivreTrie(Scanner scanner) {
		System.out.println();
		System.out.print("Entrez le titre du livre que vous souhaitez rendre : ");
		String titre = scanner.nextLine();

		// Recherche du livre dans la bibliothèque triée
		Livre livre = rechercherLivreTrie(titre);

		if (livre != null && !livre.isDisponible()) {
			// Marquer le livre comme disponible
			livre.setDisponible(true);
			System.out.println("Le livre '" + livre.getTitre() + "' a été rendu avec succès.");
		} else if (livre != null && livre.isDisponible()) {
			System.out.println("Le livre '" + livre.getTitre() + "' est déjà disponible.");
		} else {
			System.out.println("Le livre '" + titre + "' n'a pas été emprumté.");
		}
	}

	// Méthode pour générer un ISBN unique
	private String genererIsbn() {
		String isbn = "" + compteurIsbn;
		compteurIsbn++; // Incrémentation du compteur pour le prochain ISBN
		return isbn;
	}

	// Méthode pour ajouter un livre dans table de hachage
	public void ajoutLivreHachage(Scanner scanner) {
		System.out.println();
		System.out.print("Entrez le titre du livre : ");
		String titre = scanner.nextLine();
		System.out.print("Entrez le nom de l'auteur : ");
		String auteur = scanner.nextLine();
		String isbn = genererIsbn(); // Génération de l'ISBN unique
		Livre nouveauLivre = new Livre(titre, auteur, isbn);
		livresParIsbn.put(isbn, nouveauLivre);
		System.out.println("Le livre a été ajouté avec succès!");
	}

	public void ajouterLivreHachage(Livre livre) {
		livresParIsbn.put(livre.getIsbn(), livre);
	}

	// Méthode pour rechercher un livre dans table de hachage
	public Livre rechercherLivreHachage(String isbn) {
		// Ignore les espaces, convertir en minuscules et supprimer les espaces avant
		// et après
		String formattedIsbn = isbn.trim().toLowerCase().replace(" ", "");

		// Rechercher le livre dans la table de hachage
		return livresParIsbn.get(formattedIsbn);
	}

	// Méthode pour emprunter un livre dans table de hachage
	public void emprunterLivreHachage(String isbn) {
		Livre livre = rechercherLivreHachage(isbn); // Recherche du livre par ISBN dans la table de hachage
		if (livre != null && livre.isDisponible()) { // Vérification de la disponibilité du livre
			livre.setDisponible(false); // Marquer le livre comme emprunté
			System.out.println("Le livre '" + livre.getTitre() + "' a été emprunté avec succès!");
		} else {
			System.out.println("Le livre correspondant à l'ISBN '" + isbn + "' n'est pas disponible pour l'emprunt.");
		}
	}

	// Méthode pour rendre un livre dans table de hachage
	public void rendreLivreHachage(String isbn) {
		Livre livre = rechercherLivreHachage(isbn); // Recherche du livre par ISBN dans la table de hachage
		if (livre != null && !livre.isDisponible()) { // Vérification si le livre est emprunté
			livre.setDisponible(true); // Marquer le livre comme disponible après le retour
			System.out.println("Le livre '" + livre.getTitre() + "' a été rendu avec succès.");
		} else {
			System.out.println("Le livre correspondant à l'ISBN '" + isbn + "' n'a pas été emprunté ou n'existe pas!");
		}
	}

	// Méthode pour ajouter un livre dans l'arbre
	public void ajouterLivreArbre(Scanner scanner) {
		System.out.println();
		System.out.print("Entrez le titre du livre que vous voulez ajouter: ");
		String titre = scanner.nextLine();
		System.out.print("Entrez le nom de l'auteur: ");
		String auteur = scanner.nextLine();
		System.out.print("Entrez l'ISBN : ");
		String isbn = scanner.nextLine();

		Livre nouveauLivre = new Livre(titre, auteur, isbn);
		ajouterLivreArbreBinaire(nouveauLivre);

		System.out.println("Le livre a été ajouté avec succès à la bibliothèque.");
	}

	Livre rechercherLivreParTitreDansArbre(Noeud noeud, String titre) {
		if (noeud == null) {
			return null; // Livre non trouvé dans cet arbre
		}

		if (titre.equals(noeud.getLivre().getTitre())) {
			// Vérifie que le livre trouvé appartient bien à cet arbre
			if (rechercherLivreDansListe(titre)) {
				return noeud.getLivre(); // Livre trouvé, retourne le livre
			} else {
				return null; // Le livre trouvé n'appartient pas à cet arbre
			}
		} else {
			Livre livreGauche = rechercherLivreParTitreDansArbre(noeud.getGauche(), titre);
			Livre livreDroit = rechercherLivreParTitreDansArbre(noeud.getDroite(), titre);
			return livreGauche != null ? livreGauche : livreDroit; // Retourne le livre trouvé à gauche ou à droite
		}
	}

	private boolean rechercherLivreDansListe(String titre) {
		
		if (arbreBinaire == null) {
			System.out.println("L'arbre n'est pas initialisé.");
			return false;
		}

		return rechercherLivreDansArbreBinaire(arbreBinaire.getRacine(), titre);
	}


	private boolean rechercherLivreDansArbreBinaire(Noeud racine, String titre) {
		if (racine == null) {
			return false; // Livre non trouvé dans cet arbre
		}

		// Parcourir l'arbre en ordre : gauche, racine, droite (inorder traversal)
		if (rechercherLivreDansArbreBinaire(racine.getGauche(), titre)) {
			return true; // Livre trouvé dans le sous-arbre gauche
		}

		if (racine.getLivre() != null && racine.getLivre().getTitre().equals(titre)) {
			return true; // Livre trouvé dans le nœud courant
		}

		return rechercherLivreDansArbreBinaire(racine.getDroite(), titre); // Chercher dans le sous-arbre droit
	}

	// Méthode pour emprunter un livre dans l'arbre binaire
	public void emprunterLivre(String titre) {
		Noeud noeud = rechercherNoeudParTitre(arbreBinaire.getRacine(), titre);
		if (noeud != null) {
			Livre livre = noeud.getLivre();
			if (livre.isDisponible()) {
				livre.setDisponible(false);
				System.out.println("Le livre " + titre + " a été emprunté avec succès.");
			} else {
				System.out.println("Le livre " + titre + " n'est pas disponible pour l'emprunt.");
			}
		} else {
			System.out.println("Le livre " + titre + " n'a pas été trouvé.");
		}
	}

	// Méthode pour rechercher un Noeud par titre dans l'arbre binaire
	private Noeud rechercherNoeudParTitre(Noeud racine, String titre) {
		if (racine == null || racine.getLivre().getTitre().equals(titre)) {
			return racine;
		}

		int compare = titre.compareTo(racine.getLivre().getTitre());

		if (compare < 0) {
			return rechercherNoeudParTitre(racine.getGauche(), titre);
		} else {
			return rechercherNoeudParTitre(racine.getDroite(), titre);
		}
	}

	// Méthode pour rendre un livre dans l'arbre
	public void rendreLivre(String titre) {
		Noeud noeud = rechercherNoeudParTitre(arbreBinaire.getRacine(), titre);
		if (noeud != null) {
			Livre livre = noeud.getLivre();
			if (!livre.isDisponible()) {
				livre.setDisponible(true); // Marquer le livre comme disponible
				System.out.println("Le livre \"" + titre + "\" a été rendu avec succès.");
			} else {
				System.out.println("Le livre \"" + titre + "\" est déjà disponible.");
			}
		} else {
			System.out.println("Le livre \"" + titre + "\" n'a pas été emprunté ou n'existe pas.");
		}
	}

	public LinkedList<Livre> getPetitesBibliotheques() {
		return petitesBibliotheques;
	}

	public HashMap<String, Livre> getLivresParIsbn() {
		return livresParIsbn;
	}

}
