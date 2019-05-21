package dev.pizzeria.controller.clients;

import static dev.pizzeria.controller.utils.RecupererHtmlUtils.recupererPageHtml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.pizzeria.dao.ClientsDao;
import dev.pizzeria.model.Client;

/**
 * Classe gérant l'affichage de la liste des clients
 * 
 * @author Kevin.s
 *
 */
public class ListeClientController extends HttpServlet {

	/**
	 * TEMPLATE_LISTE_CLIENT : String chemin vers le template listeClients.html
	 */
	private static final String TEMPLATE_LISTE_CLIENT = "templates/listeClients.html";
	/** LISTE_CLIENT : List<Client> */
	private static final List<Client> LISTE_CLIENT = ClientsDao.listeClient;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sBuilder = new StringBuilder();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();

		genererListeClient(sBuilder);
		String pageListeClients = recupererPageHtml(this, TEMPLATE_LISTE_CLIENT);
		pageListeClients = pageListeClients.replaceAll("LISTE_DE_CLIENTS_ICI", sBuilder.toString());
		writer.write(pageListeClients);

	}

	/**
	 * Genere la liste des clients dans le StringBuilder passé en paramêtre
	 * 
	 * @param sBuilder
	 */
	private void genererListeClient(StringBuilder sBuilder) {
		// boucle générant la liste des clients
		for (int i = 0; i < LISTE_CLIENT.size(); i++) {
			sBuilder.append("<tr><td>").append(i).append("</td>")
					.append("<td>").append(LISTE_CLIENT.get(i).getNom()).append("</td>")
					.append("<td>").append(LISTE_CLIENT.get(i).getPrenom()).append("</td>")
					.append("<td>").append(LISTE_CLIENT.get(i).getVille()).append("</td>")
					.append("<td>").append(LISTE_CLIENT.get(i).getAge()).append("</td>")
					.append("<td>").append("<a href = \"#\">Modifier</a>").append("</td>")
					.append("<td>").append("<a href = \"#\">Supprimer</a>").append("</td>")
					.append("</tr>");
		}
	}

}
