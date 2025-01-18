
package src;

public class ArbreBinaire {
	private Noeud racine;

	public ArbreBinaire() {
		this.racine = null;
	}

	// Méthode pour obtenir la racine de l'arbre
	public Noeud getRacine() {
		return racine;
	}

	// Méthode pour ajouter un livre à l'arbre binaire
	public void ajouterLivre(Livre livre) {
		racine = ajouterLivreRecursive(racine, livre);
	}

	private Noeud ajouterLivreRecursive(Noeud noeud, Livre livre) {
		if (noeud == null) {
			return new Noeud(livre);
		}

		int compare = livre.getTitre().compareTo(noeud.getLivre().getTitre());

		if (compare < 0) {
			noeud.setGauche(ajouterLivreRecursive(noeud.getGauche(), livre));
		} else if (compare > 0) {
			noeud.setDroite(ajouterLivreRecursive(noeud.getDroite(), livre));
		} else {

			System.out.println("Le livre \"" + livre.getTitre() + "\" est déjà présent dans la bibliothèque.");

		}

		return noeud;
	}

	// Méthode pour rechercher un livre par titre dans l'arbre binaire
	public Livre rechercherLivre(String titre) {
		Noeud result = rechercherLivreRecursive(racine, titre);
		if (result != null) {
			return result.getLivre();
		}
		return null;
	}

	private Noeud rechercherLivreRecursive(Noeud noeud, String titre) {
		if (noeud == null || noeud.getLivre().getTitre().equals(titre)) {
			return noeud;
		}

		int compare = titre.compareTo(noeud.getLivre().getTitre());

		if (compare < 0) {
			return rechercherLivreRecursive(noeud.getGauche(), titre);
		} else {
			return rechercherLivreRecursive(noeud.getDroite(), titre);
		}
	}

}
