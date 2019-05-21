package dev.pizzeria.controller.pizza;

import static dev.pizzeria.controller.utils.RecupererHtmlUtils.recupererPageHtml;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.pizzeria.dao.PizzaDao;
import dev.pizzeria.exception.PizzaInvalideException;
import dev.pizzeria.model.Pizza;

/**
 * Contrôleur responsable du traitement de la réquête : GET et POST /pizzas.
 * 
 * @author Kevin.s
 *
 */
public class PizzaController extends HttpServlet {

	/** TEMPLATE_GESTION_PIZZAS : String */
	private static final String TEMPLATE_GESTION_PIZZAS = "templates/gestionDesPizzas.html";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.write(recupererPageHtml(this, TEMPLATE_GESTION_PIZZAS));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		StringBuilder sBuilder = new StringBuilder();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();

		try {
			Pizza pizza = validerPizza(req);
			PizzaDao pizzaDao = new PizzaDao();
			pizzaDao.addPizza(pizza);
			resp.sendRedirect("/listePizzas");

		} catch (PizzaInvalideException e) {
			resp.setStatus(400);
			sBuilder.append(recupererPageHtml(this, TEMPLATE_GESTION_PIZZAS));
			sBuilder.append("<label style=\"color:red;\" >").append(e.getMessage()).append("</label>");
			writer.write(sBuilder.toString());

		}
	}

	/**
	 * methode qui recupere les parametre du formulaire de la requete et créé
	 * une Pizza si les champs sont valides
	 * 
	 * @param req
	 *            requete pizza
	 * @return une Pizza
	 * @throws PizzaInvalideException
	 *             champs invalide
	 */
	private Pizza validerPizza(HttpServletRequest req) throws PizzaInvalideException {
		try {
			String libelle = req.getParameter("libelle");
			String reference = req.getParameter("reference");
			double prix = Double.parseDouble(req.getParameter("prix"));
			String photo = req.getParameter("photo");

			if (libelle.length() > 0 && reference.length() > 0 && photo.length() > 0) {
				return new Pizza(libelle, reference, prix, photo);
			} else {
				throw new PizzaInvalideException("Champs invalide");
			}

		} catch (NumberFormatException e) {
			throw new PizzaInvalideException("Prix invalide");

		}
	}
}
