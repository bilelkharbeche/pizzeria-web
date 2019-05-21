package dev.pizzeria.exception;

/**
 * Exception lancé lorsque les données permettant de créer un nouveau client
 * sont invalides
 * 
 * @author Kevin.s
 *
 */
public class ClientInvalideException extends Exception {

	/**
	 * Constructeur
	 * 
	 * @param arg0
	 *            message de l'exception
	 */
	public ClientInvalideException(String arg0) {
		super(arg0);
	}

}
