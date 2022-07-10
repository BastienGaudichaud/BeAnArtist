package fr.eseo.poo.projet.artiste.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Gère le chargement d'un fichier XML dans un document DOM.
 */
public class ChargeurDOM {

	/**
	 * Charge un fichier XML du nom donnée et renvoie le document DOM
	 * représentant ce fichier selon le standard DOM.
	 * 
	 * Si le fichier n'est pas trouvé, la valeur null est renvoyée.
	 * 
	 * @param nomFichier Le nom du fichier (contenant éventuellement son
	 *            chemin).
	 * @return Le document DOM représentant ce fichier.
	 */
	public Document charge(String nomFichier) {
		try {
			DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			File fichier = new File(nomFichier);
			return constructeur.parse(fichier);
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			return null;
		}
	}
	
	public Document chargeFile(File fichier) {
		try {
			DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			return constructeur.parse(fichier);
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			return null;
		}
	}

}
