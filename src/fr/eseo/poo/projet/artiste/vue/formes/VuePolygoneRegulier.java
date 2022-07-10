package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import fr.eseo.poo.projet.artiste.modele.formes.PolygoneRegulier;

/* Define how to draw an polygon. */
public class VuePolygoneRegulier extends VueForme {

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
		for (int i = 0; i < this.polygone.getCoordonnees().size(); i++) {
			xpoints[i] = (int) Math.round(this.polygone.getCoordonnees().get(i).getAbscisse());
			ypoints[i] = (int) Math.round(this.polygone.getCoordonnees().get(i).getOrdonnee());
		}
		Color couleur = g2d.getColor();
		if (this.forme.estRempli()) {
			g2d.setColor(this.getForme().getCouleurRemplissage());
			g2d.fillPolygon(xpoints, ypoints, nbPoint);
		}
		g2d.setColor(this.getForme().getCouleurContour());
		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		g2d.drawPolygon(xpoints, ypoints, nbPoint);
		g2d.setColor(couleur);
	}

}
