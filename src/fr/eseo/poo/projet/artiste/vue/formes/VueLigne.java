package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import fr.eseo.poo.projet.artiste.modele.formes.Ligne;

/* Define how to draw an line. */
public class VueLigne extends VueForme {

	private static final long serialVersionUID = 1L;

	public VueLigne(Ligne ligne) {
		super(ligne);
	}

	@Override
	public void affiche(Graphics2D g2d) {
		int xMin = (int) (forme.getPosition().getAbscisse());
		int yMin = (int) (forme.getPosition().getOrdonnee());
		int xMax = (int) (forme.getPosition().getAbscisse() + forme.getLargeur());
		int yMax = (int) (forme.getPosition().getOrdonnee() + forme.getHauteur());
		double xMoy = (xMin + xMax) / 2.0;
		double yMoy = (yMin + yMax) / 2.0;
		double angle = Math.PI * forme.getAngle() / 180;
		Color couleur = g2d.getColor();
		g2d.setColor(this.getForme().getCouleurContour());
		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		g2d.rotate(angle, xMoy, yMoy);
		g2d.drawLine(xMin, yMin, xMax, yMax);
		g2d.rotate(-angle, xMoy, yMoy);
		g2d.setColor(couleur);

	}

}
