package dev.pizzeria;

import java.sql.DriverManager;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import dev.pizzeria.controller.clients.ClientController;
import dev.pizzeria.controller.clients.ListeClientController;
import dev.pizzeria.controller.livreur.ListeLivreurController;
import dev.pizzeria.controller.livreur.LivreurController;
import dev.pizzeria.controller.pizza.ListePizzaController;
import dev.pizzeria.controller.pizza.PizzaController;

/**
 * point d'entrée du site et gestion du serveur web
 * 
 * @author Kevin.s
 *
 */
public class PizzeriaApp {

	public static void main(String[] args) throws Exception {

		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

		Server server = new Server();

		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });
		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");

		DefaultServlet defaultServlet = new DefaultServlet();
		ServletHolder holderPwd = new ServletHolder("default", defaultServlet);
		holderPwd.setInitParameter("resourceBase", "./src/main/webapp/static");
		context.addServlet(holderPwd, "/*");

		// Les contrôleurs de l'application

		// ClientController prend la main pour les requêtes /clients
		context.addServlet(ClientController.class, "/clients");
		context.addServlet(ListeClientController.class, "/listeClients");
		context.addServlet(PizzaController.class, "/pizzas");
		context.addServlet(ListePizzaController.class, "/listePizzas");
		context.addServlet(LivreurController.class, "/livreurs");
		context.addServlet(ListeLivreurController.class, "/listeLivreurs");

		server.setHandler(context);
		server.start();
		server.join();
	}
}
