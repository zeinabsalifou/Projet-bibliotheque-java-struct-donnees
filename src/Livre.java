
package src;

public class Livre {
	private String titre;
	private String auteur;
	private String isbn;
	private boolean disponible;

	// Constructeur
	public Livre(String titre, String auteur, String isbn) {
		this.titre = titre;
		this.auteur = auteur;
		this.isbn = isbn;
		this.disponible = true; // Initialisation du livre comme disponible lors de sa création
	}

	// Getters et setters
	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public String getIsbn() {
		return isbn;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	@Override
	public String toString() {
		return "Livre{" + "titre='" + titre + '\'' + ", auteur='" + auteur + '\'' + ", isbn='" + isbn + '\''
				+ ", disponible=" + disponible + '}';
	}
}
