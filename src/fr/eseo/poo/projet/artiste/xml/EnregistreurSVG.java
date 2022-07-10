package fr.eseo.poo.projet.artiste.xml;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Cercle;
import fr.eseo.poo.projet.artiste.modele.formes.Ellipse;
import fr.eseo.poo.projet.artiste.modele.formes.Etoile;
import fr.eseo.poo.projet.artiste.modele.formes.Forme;
import fr.eseo.poo.projet.artiste.modele.formes.Ligne;
import fr.eseo.poo.projet.artiste.modele.formes.PolygoneRegulier;
import fr.eseo.poo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.poo.projet.artiste.modele.formes.Texte;
import fr.eseo.poo.projet.artiste.modele.formes.Trace;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;

/**
 * L'enregistreur SVG est responsable de l'enregistrement d'un dessin au format
 * SVG (sous-ensemble du standard SVG).
 * 
 * Il utilise les classes java.io.FileWriter et java.io.Writer de l'API Java
 * standard qui permettent d'écrire très simplement dans un fichier texte.
 * 
 * Les méthodes ecrisXxxx devront être complétées.
 *
 */
public class EnregistreurSVG {

	private static final String POLYGON = "polygon";
	private static final String POINTS = "points='";
	private static final String PATTERN = "###0.0#";

	/**
	 * Enregistre le dessin donné dans un fichier.
	 * 
	 * @param nomFichier le nom du fichier de sauvegarde
	 * @param dessin     le dessin formé de la liste des vues des formes
	 * @throws IOException si l'écriture échoue
	 */
	public void enregistreDessin(String nomFichier, List<VueForme> dessin) throws IOException {
		try (Writer redacteur = new FileWriter(nomFichier)) {
			redacteur.write("<?xml version='1.0' encoding='UTF-8' ?>\n");
			ecrisDessin(dessin, redacteur);
		}
	}

	/**
	 * Ecris chaque forme du dessin
	 * 
	 * @param dessin    Le dessin à écrire
	 * @param redacteur Le rédacteur SVG
	 * @throws IOException si l'écriture échoue
	 */
	public void ecrisDessin(List<VueForme> dessin, Writer redacteur) throws IOException {
		redacteur.write("<svg xmlns='http://www.w3.org/2000/svg'>\n");
		for (VueForme vueForme : dessin) {
			ecrisForme(vueForme.getForme(), redacteur);
		}
		redacteur.write("</svg>");
	}

	/**
	 * Ecris la forme. Cette méthode invoque les méthodes ecris<Forme> en fonction
	 * du type de la forme.
	 * 
	 * @param dessin    Le dessin à écrire
	 * @param redacteur Le rédacteur SVG
	 * @throws IOException si l'écriture échoue
	 */
	public void ecrisForme(Forme forme, Writer redacteur) throws IOException {
		String nom = forme.getClass().getSimpleName();
		switch (nom) {
		case "Texte":
			nom = "text";
			break;
		case "Trace":
			nom = "polyline";
			break;
		case "Ligne":
			nom = "line";
			break;
		case "Rectangle":
			nom = "rect";
			break;
		case "Ellipse":
			nom = "ellipse";
			break;
		case "Cercle":
			nom = "circle";
			break;
		case "Etoile":
			nom = POLYGON;
			break;
		case "PolygoneRegulier":
			nom = POLYGON;
			break;
		default:
			break;
		}
		redacteur.write("\t<" + nom + " ");
		switch (nom) {
		case "text":
			ecrisTexte((Texte) forme, redacteur);
			break;
		case "polyline":
			ecrisTrace((Trace) forme, redacteur);
			break;
		case "line":
			ecrisLigne((Ligne) forme, redacteur);
			break;
		case "rect":
			ecrisRectangle((Rectangle) forme, redacteur);
			break;
		case "ellipse":
			ecrisEllipse((Ellipse) forme, redacteur);
			break;
		case "circle":
			ecrisCercle((Cercle) forme, redacteur);
			break;
		case POLYGON:
			try {
				ecrisEtoile((Etoile) forme, redacteur);
			} catch (Exception e) {
				ecrisPolygone((PolygoneRegulier) forme, redacteur);
			}
			break;
		default:
			break;
		}
		// définition de la couleur de la forme
		Color c = forme.getCouleurContour();
		String rouge = String.format("%02X", c.getRed());
		String vert = String.format("%02X", c.getGreen());
		String bleu = String.format("%02X", c.getBlue());
		String couleurContour = "#" + rouge + vert + bleu;
		if (forme.estRempli()) {
			Color c2 = forme.getCouleurRemplissage();
			String rouge2 = String.format("%02X", c2.getRed());
			String vert2 = String.format("%02X", c2.getGreen());
			String bleu2 = String.format("%02X", c2.getBlue());
			String couleurRemplissage = "#" + rouge2 + vert2 + bleu2;
			redacteur.write("fill='" + couleurRemplissage + "' ");
		} else {
			redacteur.write("fill='none' ");
		}
		redacteur.write("stroke='" + couleurContour + "'");
		if (nom.equals("text")) {
			String texte = forme.getTexte();
			texte = texte.replaceAll("\\R", "/");
			redacteur.write(">" + texte + "</text>\n");
		} else {
			redacteur.write("/>\n");
		}
	}

	public void ecrisTexte(Texte forme, Writer redacteur) throws IOException {
		double x = forme.getPosition().getAbscisse();
		double y = forme.getPosition().getOrdonnee();
		double fontSize = forme.getTailePolice();
		redacteur.write("x='" + x + "' y='" + y + "' font-size='" + fontSize + "' ");
	}

	public void ecrisTrace(Trace forme, Writer redacteur) throws IOException {
		DecimalFormatSymbols symboles = new DecimalFormatSymbols(new Locale("en"));
		DecimalFormat decimalFormat = new DecimalFormat(PATTERN, symboles);
		redacteur.write(POINTS);
		for (Coordonnees point : forme.getPoints()) {
			String abscisse = decimalFormat.format(point.getAbscisse());
			String ordonnee = decimalFormat.format(point.getOrdonnee());
			redacteur.write(abscisse + "," + ordonnee + " ");
		}
		redacteur.write("' ");
	}

	/**
	 * Ecris la ligne.
	 * 
	 * @param forme     la forme à écrire
	 * @param redacteur Le rédacteur SVG
	 * @throws IOException si l'écriture échoue
	 */
	public void ecrisLigne(Ligne forme, Writer redacteur) throws IOException {
		double x1 = forme.getPosition().getAbscisse();
		double y1 = forme.getPosition().getOrdonnee();
		double x2 = forme.getC2().getAbscisse();
		double y2 = forme.getC2().getOrdonnee();
		redacteur.write("x1='" + x1 + "' y1='" + y1 + "' x2='" + x2 + "' y2='" + y2 + "' ");
	}

	public void ecrisRectangle(Rectangle forme, Writer redacteur) throws IOException {
		double x = forme.getPosition().getAbscisse();
		double y = forme.getPosition().getOrdonnee();
		double width = forme.getLargeur();
		double height = forme.getHauteur();
		redacteur.write("x='" + x + "' y='" + y + "' width='" + width + "' height='" + height + "' ");
	}

	/**
	 * Ecris l'ellispe.
	 * 
	 * @param forme     la forme à écrire
	 * @param redacteur Le rédacteur SVG
	 * @throws IOException si l'écriture échoue
	 */
	public void ecrisEllipse(Ellipse forme, Writer redacteur) throws IOException {
		double rx = forme.getLargeur() / 2;
		double ry = forme.getHauteur() / 2;
		double cx = forme.getPosition().getAbscisse() + rx;
		double cy = forme.getPosition().getOrdonnee() + ry;
		redacteur.write("cx='" + cx + "' cy='" + cy + "' rx='" + rx + "' ry='" + ry + "' ");
	}

	/**
	 * Ecris le cercle.
	 * 
	 * @param forme     la forme à écrire
	 * @param redacteur Le rédacteur SVG
	 * @throws IOException si l'écriture échoue
	 */
	public void ecrisCercle(Cercle forme, Writer redacteur) throws IOException {
		double r = forme.getLargeur() / 2;
		double cx = forme.getPosition().getAbscisse() + r;
		double cy = forme.getPosition().getOrdonnee() + r;
		redacteur.write("cx='" + cx + "' cy='" + cy + "' r='" + r + "' ");
	}

	/**
	 * Ecris l'étoile.
	 * 
	 * @param forme     la forme à écrire
	 * @param redacteur Le rédacteur SVG
	 * @throws IOException si l'écriture échoue
	 */
	public void ecrisEtoile(Etoile forme, Writer redacteur) throws IOException {
		DecimalFormatSymbols symboles = new DecimalFormatSymbols(new Locale("en"));
		DecimalFormat decimalFormat = new DecimalFormat(PATTERN, symboles);
		redacteur.write(POINTS);
		for (Coordonnees point : forme.getCoordonnees()) {
			String abscisse = decimalFormat.format(point.getAbscisse());
			String ordonnee = decimalFormat.format(point.getOrdonnee());
			redacteur.write(abscisse + "," + ordonnee + " ");
		}
		redacteur.write("' name='etoile' ");
	}

	public void ecrisPolygone(PolygoneRegulier forme, Writer redacteur) throws IOException {
		DecimalFormatSymbols symboles = new DecimalFormatSymbols(new Locale("en"));
		DecimalFormat decimalFormat = new DecimalFormat(PATTERN, symboles);
		redacteur.write(POINTS);
		for (Coordonnees point : forme.getCoordonnees()) {
			String abscisse = decimalFormat.format(point.getAbscisse());
			String ordonnee = decimalFormat.format(point.getOrdonnee());
			redacteur.write(abscisse + "," + ordonnee + " ");
		}
		redacteur.write("' name='polygon' ");
	}

}
