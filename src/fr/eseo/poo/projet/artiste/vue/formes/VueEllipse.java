package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import fr.eseo.poo.projet.artiste.modele.formes.Ellipse;

/* Define how to draw an ellipse. */
public class VueEllipse extends VueForme {

	public VueEllipse(Ellipse ellipse) {
		super(ellipse);
	}

	@Override
	public void affiche(Graphics2D g2d) {
		int x = (int) forme.getPosition().getAbscisse();
		int y = (int) forme.getPosition().getOrdonnee();
		int width = (int) forme.getLargeur();
		int height = (int) forme.getHauteur();
		Color couleur = g2d.getColor();
		if (forme.estRempli()) {
			g2d.setColor(this.getForme().getCouleurRemplissage());
			g2d.fillOval(x, y, width, height);
		}
		g2d.setColor(this.getForme().getCouleurContour());
		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		g2d.drawOval(x, y, width, height);
		g2d.setColor(couleur);
	}

}
