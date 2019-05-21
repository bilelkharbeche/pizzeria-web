package dev.pizzeria.controller.livreur;

import static dev.pizzeria.controller.utils.RecupererHtmlUtils.recupererPageHtml;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.pizzeria.dao.LivreurDao;
import dev.pizzeria.exception.LivreurInvalideException;
import dev.pizzeria.model.Livreur;

/**
 * * Contrôleur responsable du traitement de la réquête : GET et POST /livreurs.
 * 
 * @author Kevin.s
 *
 */
public class LivreurController extends HttpServlet {

	private static final String TEMPLATE_GESTION_LIVREURS = "templates/gestionDesLivreurs.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.write(recupererPageHtml(this, TEMPLATE_GESTION_LIVREURS));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sBuilder = new StringBuilder();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();

		try {
			Livreur livreur = validerLivreur(req);
			LivreurDao.listeLivreur.add(livreur);
			resp.sendRedirect("/listeLivreurs");

		} catch (LivreurInvalideException e) {
			resp.setStatus(400);
			sBuilder.append(recupererPageHtml(this, TEMPLATE_GESTION_LIVREURS));
			sBuilder.append("<label style=\"color:red;\" >").append(e.getMessage()).append("</label>");
			writer.write(sBuilder.toString());

		}
	}

	/**
	 * methode qui recupere les parametre du formulaire de la requete et créé un
	 * Livreur si les champs sont valides
	 * 
	 * @param req
	 *            requete client
	 * @return un Livreur
	 * @throws LivreurInvalideException
	 *             champs invalide
	 */
	private Livreur validerLivreur(HttpServletRequest req) throws LivreurInvalideException {

		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");

		if (nom.length() > 0 && prenom.length() > 0) {
			return new Livreur(nom, prenom);
		} else {
			throw new LivreurInvalideException("Champs invalide");
		}

	}

}
