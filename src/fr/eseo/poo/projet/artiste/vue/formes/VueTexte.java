package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.eseo.poo.projet.artiste.modele.formes.Texte;

/* Define how to draw an texte. */
public class VueTexte extends VueForme {

	private static final long serialVersionUID = 1L;
	private Texte texte;

	public VueTexte(Texte texte) {
		super(texte);
		this.texte = texte;
	}

	@Override
	public void affiche(Graphics2D g2d) {
		int x = (int) this.texte.getPosition().getAbscisse();
		int y = (int) this.texte.getPosition().getOrdonnee();
		int largeur = (int) this.texte.getLargeur();
		int hauteur = (int) this.texte.getHauteur();
		double xMoy = x + largeur / 2.0;
		double yMoy = y + hauteur / 2.0;
		double angle = Math.PI * forme.getAngle() / 180;
		g2d.rotate(angle, xMoy, yMoy);
		String[] lignes = texte.getTexte().split("\\R");
		for(int i = 0; i < lignes.length; i++) {
			Color couleur = g2d.getColor();
			g2d.setColor(texte.getCouleurContour());
			g2d.setFont(texte.getPolice());
			g2d.drawString(lignes[i], x, y + (i + 1) * texte.getTaillePolice());
			g2d.setColor(couleur);
		}
		g2d.rotate(-angle, xMoy, yMoy);

	}
}
