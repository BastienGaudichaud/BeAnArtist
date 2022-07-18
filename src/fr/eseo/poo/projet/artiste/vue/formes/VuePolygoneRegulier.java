package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.PolygoneRegulier;

/* Define how to draw an polygon. */
public class VuePolygoneRegulier extends VueForme {

	private static final long serialVersionUID = 1L;
	private PolygoneRegulier polygone;

	public VuePolygoneRegulier(PolygoneRegulier polygone) {
		super(polygone);
		this.polygone = polygone;
	}

	@Override
	public void affiche(Graphics2D g2d) {
		int nbPoint = this.polygone.getCoordonnees().size();
		int[] xpoints = new int[nbPoint];
		int[] ypoints = new int[nbPoint];
		double x = 0;
		double y = 0;
		for (int i = 0; i < nbPoint; i++) {
			Coordonnees point = this.polygone.getCoordonnees().get(i);
			xpoints[i] = (int) Math.round(point.getAbscisse());
			ypoints[i] = (int) Math.round(point.getOrdonnee());
			x += point.getAbscisse();
			y += point.getOrdonnee();
		}
		double xMoy = x / nbPoint;
		double yMoy = y / nbPoint;
		double angle = Math.PI * forme.getAngle() / 180;
		Color couleur = g2d.getColor();
		g2d.rotate(angle, xMoy, yMoy);
		if (this.forme.estRempli()) {
			g2d.setColor(this.getForme().getCouleurRemplissage());
			g2d.fillPolygon(xpoints, ypoints, nbPoint);
		}
		g2d.setColor(this.getForme().getCouleurContour());
		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		g2d.drawPolygon(xpoints, ypoints, nbPoint);
		g2d.rotate(-angle, xMoy, yMoy);
		g2d.setColor(couleur);
	}

}
