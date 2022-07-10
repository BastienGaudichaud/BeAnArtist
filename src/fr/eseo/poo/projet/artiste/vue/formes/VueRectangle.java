package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import fr.eseo.poo.projet.artiste.modele.formes.Rectangle;

/* Define how to draw an rectangle. */
public class VueRectangle extends VueForme {

	private Rectangle rectangle;

	public VueRectangle(Rectangle rectangle) {
		super(rectangle);
		this.rectangle = rectangle;
	}

	@Override
	public void affiche(Graphics2D g2d) {
		int x = (int) this.rectangle.getPosition().getAbscisse();
		int y = (int) this.rectangle.getPosition().getOrdonnee();
		int largeur = (int) this.rectangle.getLargeur();
		int hauteur = (int) this.rectangle.getHauteur();
		Color couleur = g2d.getColor();
		if (this.forme.estRempli()) {
			g2d.setColor(this.getForme().getCouleurRemplissage());
			g2d.fillRect(x, y, largeur, hauteur);
		}
		g2d.setColor(this.getForme().getCouleurContour());
		g2d.setStroke(new BasicStroke(this.getForme().getEpaisseur()));
		g2d.drawRect(x, y, largeur, hauteur);
		g2d.setColor(couleur);
	}

}
