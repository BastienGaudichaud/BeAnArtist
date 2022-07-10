package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.Color;
import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Cercle;
import fr.eseo.poo.projet.artiste.vue.formes.VueCercle;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;

public class OutilCercle extends OutilForme {

	private static final long serialVersionUID = 2339878176501138917L;

	/** Create a circle according to the action of the user drawing.
	 * (See the documentation in the Application for more details.)
	 * (Called by OutilForme.)*/
	@Override
	protected VueForme creerVueForme() {
		Coordonnees c1 = getDebut();
		Coordonnees c2 = getFin();
		Cercle cercle;
		Color couleur = this.getPanneauDessin().getCouleurContourCourante();
		Color couleur2 = this.getPanneauDessin().getCouleurRemplissageCourante();
		int epaisseur = this.getPanneauDessin().getEpaisseurCourante();

		boolean rempli = this.getPanneauDessin().getModeRemplissage();
		if (c1.getAbscisse() != c2.getAbscisse() && c1.getOrdonnee() != c2.getOrdonnee()) {
			double largeur = c2.getAbscisse() - c1.getAbscisse();
			double hauteur = c2.getOrdonnee() - c1.getOrdonnee();
			double cote;
			Coordonnees position;
			if (largeur > 0) {
				if (hauteur > 0) {
					cote = Math.max(hauteur, largeur);
					position = c1;
					cercle = new Cercle(position, cote);
				} else {
					cote = Math.max(-hauteur, largeur);
					position = new Coordonnees(c1.getAbscisse(), c1.getOrdonnee() - cote);
					cercle = new Cercle(position, cote);
				}
			} else {
				if (hauteur > 0) {
					cote = Math.max(hauteur, -largeur);
					position = new Coordonnees(c1.getAbscisse() - cote, c1.getOrdonnee());
					cercle = new Cercle(position, cote);
				} else {
					cote = Math.max(-hauteur, -largeur);
					position = new Coordonnees(c1.getAbscisse() - cote, c1.getOrdonnee() - cote);
					cercle = new Cercle(position, cote);
				}
			}
		} else {
			cercle = new Cercle(c1);
		}
		cercle.setCouleurContour(couleur);
		cercle.setCouleurRemplissage(couleur2);
		cercle.setEpaisseur(epaisseur);
		cercle.setRempli(rempli);
		return new VueCercle(cercle);
	}
}
