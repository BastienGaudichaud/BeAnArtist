package fr.eseo.poo.projet.artiste.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ChargeurDOM {

	/* Convert an XML file into a document. */
	public Document chargeFile(File fichier) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			DocumentBuilder constructeur = factory.newDocumentBuilder();
			return constructeur.parse(fichier);
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			return null;
		}
	}
}
