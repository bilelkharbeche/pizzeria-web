package dev.pizzeria.exception;

/**
 * Exception lancé lorsque les données permettant de créer une nouvelle Pizza
 * sont invalides
 * 
 * @author Kevin.s
 *
 */
public class PizzaInvalideException extends Exception {

	/**
	 * Constructeur
	 * 
	 */
	public PizzaInvalideException() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param arg0
	 */
	public PizzaInvalideException(String arg0) {
		super(arg0);
	}

}
