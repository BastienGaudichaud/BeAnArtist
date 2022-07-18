package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import fr.eseo.poo.projet.artiste.modele.formes.Ellipse;

/* Define how to draw an ellipse. */
public class VueEllipse extends VueForme {

	private static final long serialVersionUID = 1L;

	public VueEllipse(Ellipse ellipse) {
		super(ellipse);
	}

	@Override
	public void affiche(Graphics2D g2d) {
		int x = (int) forme.getPosition().getAbscisse();
		int y = (int) forme.getPosition().getOrdonnee();
		int largeur = (int) forme.getLargeur();
		int hauteur = (int) forme.getHauteur();
		double xMoy = x + largeur / 2.0;
		double yMoy = y + hauteur / 2.0;
		double angle = Math.PI * forme.getAngle() / 180;
		Color couleur = g2d.getColor();
		g2d.rotate(angle, xMoy, yMoy);
		if (forme.estRempli()) {
			g2d.setColor(this.getForme().getCouleurRemplissage());
			g2d.fillOval(x, y, largeur, hauteur);
		}
		g2d.setColor(this.getForme().getCouleurContour());
		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		g2d.drawOval(x, y, largeur, hauteur);
		g2d.rotate(-angle, xMoy, yMoy);
		g2d.setColor(couleur);
	}

}
