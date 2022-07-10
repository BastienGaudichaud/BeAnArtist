package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.eseo.poo.projet.artiste.modele.formes.Texte;

/* Define how to draw an texte. */
public class VueTexte extends VueForme {

	private Texte texte;

	public VueTexte(Texte texte) {
		super(texte);
		this.texte = texte;
	}

	@Override
	public void affiche(Graphics2D g2d) {
		int x = (int) this.texte.getPosition().getAbscisse();
		int y = (int) this.texte.getPosition().getOrdonnee();
		String[] lignes = texte.getTexte().split("\\R");
		for(int i = 0; i < lignes.length; i++) {
			Color couleur = g2d.getColor();
			g2d.setColor(texte.getCouleurContour());
			g2d.setFont(texte.getPolice());
			g2d.drawString(lignes[i], x, y + 15 + i * texte.getTailePolice());
			g2d.setColor(couleur);
		}
	}
}
