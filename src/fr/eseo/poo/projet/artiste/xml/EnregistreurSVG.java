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

public class EnregistreurSVG {

	private static final String POLYGON = "polygon";
	private static final String POINTS = "points='";
	private static final String PATTERN = "###0.0#";

	/* Save the drawing in a SVG file. */
	public void enregistreDessin(String nomFichier, List<VueForme> dessin) throws IOException {
		try (Writer redacteur = new FileWriter(nomFichier)) {
			redacteur.write("<?xml version='1.0' encoding='UTF-8' ?>\n");
			ecrisDessin(dessin, redacteur);
		}
	}

	/* Write the beginning of the document. */
	private void ecrisDessin(List<VueForme> dessin, Writer redacteur) throws IOException {
		Color fond = dessin.get(0).getPanneau().getCouleurFondCourante();
		String couleur = "#"+Integer.toHexString(fond.getRGB()).substring(2);
		redacteur.write("<svg xmlns='http://www.w3.org/2000/svg'>\n");
		redacteur.write("<style>svg { background-color: " + couleur + "; }</style>\n");
		for (VueForme vueForme : dessin) {
			ecrisForme(vueForme.getForme(), redacteur);
		}
		redacteur.write("</svg>");
	}

	/* Add the objects. */
	private void ecrisForme(Forme forme, Writer redacteur) throws IOException {
		String nom = forme.getClass().getSimpleName();
		switch (nom) {
		case "Texte":
			nom = "text";
			redacteur.write("\t<" + nom + " ");
			ecrisTexteDebut((Texte) forme, redacteur);
			break;
		case "Trace":
			nom = "polyline";
			redacteur.write("\t<" + nom + " ");
			ecrisTrace((Trace) forme, redacteur);
			break;
		case "Ligne":
			nom = "line";
			redacteur.write("\t<" + nom + " ");
			ecrisLigne((Ligne) forme, redacteur);
			break;
		case "Rectangle":
			nom = "rect";
			redacteur.write("\t<" + nom + " ");
			ecrisRectangle((Rectangle) forme, redacteur);
			break;
		case "Ellipse":
			nom = "ellipse";
			redacteur.write("\t<" + nom + " ");
			ecrisEllipse((Ellipse) forme, redacteur);
			break;
		case "Cercle":
			nom = "circle";
			redacteur.write("\t<" + nom + " ");
			ecrisCercle((Cercle) forme, redacteur);
			break;
		case "Etoile":
			nom = POLYGON;
			redacteur.write("\t<" + nom + " ");
			ecrisEtoile((Etoile) forme, redacteur);
			break;
		case "PolygoneRegulier":
			nom = POLYGON;
			redacteur.write("\t<" + nom + " ");
			ecrisPolygone((PolygoneRegulier) forme, redacteur);
			break;
		default:
			break;
		}
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
			if (nom.equals("text")) {
				redacteur.write("fill='" + couleurContour + "' ");
			}
			else {
				redacteur.write("fill='none' ");
			}
		}
		redacteur.write("stroke='" + couleurContour + "'");
		if (nom.equals("text")) {
			ecritTexteFin(forme, redacteur);
		} else {
			int epaisseur = forme.getEpaisseur();
			redacteur.write(" stroke-width='" + epaisseur + "' ");
			int angle = forme.getAngle();
			if(angle != 0) {
				double x = forme.getPosition().getAbscisse() + forme.getLargeur() / 2;
				double y = forme.getPosition().getOrdonnee() + forme.getHauteur() / 2;
				redacteur.write("transform='rotate(" + angle + " " + x + " " + y + ")' ");
			}
			redacteur.write("/>\n");
		}
	}

	private void ecrisTexteDebut(Texte forme, Writer redacteur) throws IOException {
		double x = forme.getPosition().getAbscisse();
		double y = forme.getPosition().getOrdonnee();
		double fontSize = forme.getTaillePolice();
		redacteur.write("x='" + x + "' y='" + (y + fontSize) + "' font-size='" + fontSize + "' ");
	}

	private void ecritTexteFin(Forme forme, Writer redacteur) throws IOException {
		redacteur.write(" font-weight='bold'");
		int angle = forme.getAngle();
		if(angle != 0) {
			double x = forme.getPosition().getAbscisse() + forme.getLargeur() / 2;
			double y = forme.getPosition().getOrdonnee() + forme.getLargeur() / 2;
			redacteur.write("transform='rotate(" + angle + " " + x + " " + y + ")'");
		}
		String texte = forme.getTexte();
		String[] textes = texte.split("\\R");
		if(textes.length == 1) {
			redacteur.write(">" + texte + "</text>\n");
		}
		else {
			redacteur.write(">\n"); 
			double x = forme.getPosition().getAbscisse();
			double taille = ((Texte)forme).getTaillePolice();
	
			for (String string : textes) {
				redacteur.write("\t\t<tspan x='" + x + "' dy='" + taille + "'>" + string + "</tspan>\n");
			}
			redacteur.write("\t</text>\n");
		}
	}

	private void ecrisTrace(Trace forme, Writer redacteur) throws IOException {
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

	private void ecrisLigne(Ligne forme, Writer redacteur) throws IOException {
		double x1 = forme.getPosition().getAbscisse();
		double y1 = forme.getPosition().getOrdonnee();
		double x2 = forme.getC2().getAbscisse();
		double y2 = forme.getC2().getOrdonnee();
		redacteur.write("x1='" + x1 + "' y1='" + y1 + "' x2='" + x2 + "' y2='" + y2 + "' ");
	}

	private void ecrisRectangle(Rectangle forme, Writer redacteur) throws IOException {
		double x = forme.getPosition().getAbscisse();
		double y = forme.getPosition().getOrdonnee();
		double width = forme.getLargeur();
		double height = forme.getHauteur();
		redacteur.write("x='" + x + "' y='" + y + "' width='" + width + "' height='" + height + "' ");
	}

	private void ecrisEllipse(Ellipse forme, Writer redacteur) throws IOException {
		double rx = forme.getLargeur() / 2;
		double ry = forme.getHauteur() / 2;
		double cx = forme.getPosition().getAbscisse() + rx;
		double cy = forme.getPosition().getOrdonnee() + ry;
		redacteur.write("cx='" + cx + "' cy='" + cy + "' rx='" + rx + "' ry='" + ry + "' ");
	}

	private void ecrisCercle(Cercle forme, Writer redacteur) throws IOException {
		double r = forme.getLargeur() / 2;
		double cx = forme.getPosition().getAbscisse() + r;
		double cy = forme.getPosition().getOrdonnee() + r;
		redacteur.write("cx='" + cx + "' cy='" + cy + "' r='" + r + "' ");
	}

	private void ecrisEtoile(Etoile forme, Writer redacteur) throws IOException {
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

	private void ecrisPolygone(PolygoneRegulier forme, Writer redacteur) throws IOException {
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
