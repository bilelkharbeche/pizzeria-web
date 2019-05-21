package dev.pizzeria.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.pizzeria.exception.TechnicalException;
import dev.pizzeria.model.Pizza;

/**
 * Classe contenant la liste des pizzas
 * 
 * @author Kevin.s
 *
 */
public class PizzaDao {

	/**
	 * @param pizza
	 *            ajoute une pizza
	 */
	public void addPizza(Pizza pizza) {
		Connection conn = ConnectionMgr.getInstance();
		Statement statement = null;

		try {
			conn.setAutoCommit(false);
			statement = conn.createStatement();

			statement
					.executeUpdate("INSERT INTO PIZZAS (LIBELLE, REFERENCE, PRIX, PHOTO) VALUES ('" + pizza.getLibelle()
							+ "','" + pizza.getReference() + "'," + pizza.getPrix() + ",'" + pizza.getPhoto() + "')");

			conn.commit();
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				throw new TechnicalException("Le rollback a échoué", e1);
			}
			throw new TechnicalException("La ligne n'a pas pu être insérée", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new TechnicalException("La fermeture de la base de données à échouée", e);

			}
		}
	}

	/**
	 * @return pizzas retourne la liste de pizza
	 */
	public List<Pizza> findAllPizza() {
		Connection conn = ConnectionMgr.getInstance();
		Statement statement = null;
		ResultSet cursor = null;

		ArrayList<Pizza> pizzas = new ArrayList<>();

		try {
			conn.setAutoCommit(false);
			statement = conn.createStatement();

			cursor = statement.executeQuery("SELECT * FROM PIZZAS");

			while (cursor.next()) {
				String libelle = cursor.getString("libelle");
				String reference = cursor.getString("reference");
				Integer prix = cursor.getInt("prix");
				String photo = cursor.getString("photo");

				Pizza pizza = new Pizza(libelle, reference, prix, photo);
				pizzas.add(pizza);
			}

			conn.commit();
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				throw new TechnicalException("Le rollback a échoué", e1);
			}
			throw new TechnicalException("Impossible d'afficher la liste", e);
		} finally {
			try {
				if (cursor != null) {
					cursor.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new TechnicalException("La fermeture de la base de données à échouée", e);
			}
		}
		return pizzas;
	}
}
