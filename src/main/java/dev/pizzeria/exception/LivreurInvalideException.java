package dev.pizzeria.exception;

/**
 * Exception lancé lorsque les données permettant de créer un nouveau Livreur
 * sont invalides
 * 
 * @author Kevin.s
 *
 */
public class LivreurInvalideException extends Exception {

	/**
	 * Constructeur
	 * 
	 */
	public LivreurInvalideException() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param message
	 */
	public LivreurInvalideException(String message) {
		super(message);
	}

}
