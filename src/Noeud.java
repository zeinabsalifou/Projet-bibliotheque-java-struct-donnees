
package src;

public class Noeud {
	private Livre livre;
	private Noeud gauche;
	private Noeud droite;

	public Noeud(Livre livre) {
		this.livre = livre;
		this.gauche = null;
		this.droite = null;
	}

	// Méthode pour accéder au livre du nœud
	public Livre getLivre() {
		return livre;
	}

	// Méthodes pour accéder aux nœuds enfants
	public Noeud getGauche() {
		return gauche;
	}

	public Noeud getDroite() {
		return droite;
	}

	public void setGauche(Noeud gauche) {
		this.gauche = gauche;
	}

	public void setDroite(Noeud droite) {
		this.droite = droite;
	}
}
