package dev.pizzeria.controller.clients;

import static dev.pizzeria.controller.utils.RecupererHtmlUtils.recupererPageHtml;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.dao.ClientsDao;
import dev.pizzeria.exception.ClientInvalideException;
import dev.pizzeria.model.Client;

/**
 * Contrôleur responsable du traitement de la réquête : GET et POST /clients.
 */
public class ClientController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";
	public static final String TEMPLATE_GESTION_CLIENTS = "templates/gestionDesClients.html";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO insérer un nouveau client en base de données
		StringBuilder sBuilder = new StringBuilder();
		// réponse au format UTF-8 pour le support des accents
		resp.setCharacterEncoding("UTF-8");
		// écriture dans le corps de la réponse
		PrintWriter writer = resp.getWriter();

		try {

			Client client = validerClient(req);
			ClientsDao.listeClient.add(client);
			resp.sendRedirect("/listeClients");

		} catch (ClientInvalideException e) {
			LOGGER.error("Champs du formulaire d'ajout client invalide");
			resp.setStatus(400);
			sBuilder.append(recupererPageHtml(this, TEMPLATE_GESTION_CLIENTS));
			sBuilder.append("<label style=\"color:red;\" >").append(e.getMessage()).append("</label>");
			writer.write(sBuilder.toString());

		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.write(recupererPageHtml(this, TEMPLATE_GESTION_CLIENTS));
	}

	/**
	 * methode qui recupere les parametre du formulaire de la requete et créé un
	 * client si les champs sont valides
	 * 
	 * @param req
	 *            requete client
	 * @return un client
	 * @throws ClientInvalideException
	 *             champs invalide
	 */
	private Client validerClient(HttpServletRequest req) throws ClientInvalideException {
		try {
			String nom = req.getParameter("nom");
			String prenom = req.getParameter("prenom");
			String ville = req.getParameter("ville");
			int age = Integer.parseInt(req.getParameter("age"));

			if (nom.length() > 0 && prenom.length() > 0 && ville.length() > 0) {
				return new Client(nom, prenom, ville, age);
			} else {
				throw new ClientInvalideException("Champs invalide");
			}

		} catch (NumberFormatException e) {
			throw new ClientInvalideException("Age invalide");

		}
	}

}
