package dev.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import dev.pizzeria.model.Pizza;

/**
 * Classe contenant la liste des pizzas
 * 
 * @author Kevin.s
 *
 */
public class PizzaDao {

	/** listeClient : List<Client> */
	public static List<Pizza> listePizza = new ArrayList<>();

	/**
	 * Constructeur
	 * 
	 */
	private PizzaDao() {
		super();
	}

}
