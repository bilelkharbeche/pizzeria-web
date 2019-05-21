package dev.pizzeria.controller.livreur;

import static dev.pizzeria.controller.utils.RecupererHtmlUtils.recupererPageHtml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.pizzeria.dao.LivreurDao;
import dev.pizzeria.model.Livreur;

/**
 * Classe gérant l'affichage de la liste des Livreurs
 * 
 * @author Kevin.s
 *
 */
public class ListeLivreurController extends HttpServlet {

	/** LISTE_LIVREUR : List<Livreur> */
	private static final List<Livreur> LISTE_LIVREUR = LivreurDao.listeLivreur;

	/** TEMPLATE_LISTE_LIVREUR : String */
	private static final String TEMPLATE_LISTE_LIVREUR = "templates/listeLivreur.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sBuilder = new StringBuilder();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();

		genererListeLivreur(sBuilder);
		String pageListeLivreur = recupererPageHtml(this, TEMPLATE_LISTE_LIVREUR);
		pageListeLivreur = pageListeLivreur.replaceAll("LISTE_DE_LIVREUR_ICI", sBuilder.toString());
		writer.write(pageListeLivreur);

	}

	/**
	 * Genere la liste des Livreurs dans le StringBuilder passé en paramêtre
	 * 
	 * @param sBuilder
	 */
	private void genererListeLivreur(StringBuilder sBuilder) {
		for (int i = 0; i < LISTE_LIVREUR.size(); i++) {
			sBuilder.append("<tr>")
					.append("<td>").append(LISTE_LIVREUR.get(i).getNom()).append("</td>")
					.append("<td>").append(LISTE_LIVREUR.get(i).getPrenom()).append("</td>")
					.append("<td>").append("<a href = \"#\">Modifier</a>").append("</td>")
					.append("<td>").append("<a href = \"#\">Supprimer</a>").append("</td>")
					.append("</tr>");
		}
	}

}
