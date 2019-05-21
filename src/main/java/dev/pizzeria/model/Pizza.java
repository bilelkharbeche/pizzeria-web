package dev.pizzeria.model;

/**
 * Classe représentant une Pizza
 * 
 * @author Kevin.s
 *
 */
public class Pizza {

	/** libelle : String */
	private String libelle;
	/** reference : String */
	private String reference;
	/** prix : int */
	private double prix;
	/** label : String */
	private String photo;

	/**
	 * Constructeur
	 * 
	 * @param libelle
	 *            lebellé de la pizza
	 * @param reference
	 *            référence de la pizza
	 * @param prix
	 *            prix de la pizza
	 * @param photo
	 *            url de la photo
	 */
	public Pizza(String libelle, String reference, double prix, String photo) {
		super();
		this.libelle = libelle;
		this.reference = reference;
		this.prix = prix;
		this.photo = photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = (int) (prime * result + prix);
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (prix != other.prix)
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

	/**
	 * Getter
	 * 
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Setters
	 * 
	 * @param libelle
	 *            the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Getter
	 * 
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Setters
	 * 
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Getter
	 * 
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Setters
	 * 
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(int prix) {
		this.prix = prix;
	}

	/**
	 * Getter
	 * 
	 * @return the label
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Setters
	 * 
	 * @param label
	 *            the label to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
