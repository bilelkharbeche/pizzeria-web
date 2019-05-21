package dev.pizzeria.controller.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * comporte une méthode pour récupérer le code html d'une page dans une chaine
 * de caractère
 * 
 * @author Kevin.s
 *
 */
public class RecupererHtmlUtils {

	/**
	 * méthode qui renvoie le code html d'une page à partir de son URL dans une
	 * chaine de caractère
	 * 
	 * @param url
	 *            chemin vers la page html
	 * @return une page html dans une chaine de caracteres
	 * @throws IOException
	 */
	public static String recupererPageHtml(Object obj, String url) throws IOException {

		String template;
		try {
			template = Files.readAllLines(Paths.get(obj.getClass().getClassLoader().getResource(url).toURI())).stream()
					.collect(Collectors.joining());
			return template;

		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}

	}

}
