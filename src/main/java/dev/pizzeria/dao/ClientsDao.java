package dev.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import dev.pizzeria.model.Client;

/**
 * Classe contenant la liste des Clients
 * 
 * @author Kevin.s
 *
 */
public class ClientsDao {

	/** listeClient : List<Client> */
	public static List<Client> listeClient = new ArrayList<>();

	/**
	 * Constructeur
	 * 
	 */
	private ClientsDao() {
		super();
	}

}
