package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.Color;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Ellipse;
import fr.eseo.poo.projet.artiste.vue.formes.VueEllipse;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;

public class OutilEllipse extends OutilForme {

	private static final long serialVersionUID = 5674622951624298778L;

	/* Create an ellipse according to the action of the user drawing.
	 * (See the documentation in the Application for more details.)
	 * (Called by OutilForme.)*/
	@Override
	protected VueForme creerVueForme() {
		Coordonnees c1 = getDebut();
		Coordonnees c2 = getFin();
		Ellipse ellipse;
		Color couleur = this.getPanneauDessin().getCouleurContourCourante();
		Color couleur2 = this.getPanneauDessin().getCouleurRemplissageCourante();
		int epaisseur = this.getPanneauDessin().getEpaisseurCourante();
		boolean rempli = this.getPanneauDessin().getModeRemplissage();
		if (c1.getAbscisse() != c2.getAbscisse() && c1.getOrdonnee() != c2.getOrdonnee()) {
			double largeur = c2.getAbscisse() - c1.getAbscisse();
			double hauteur = c2.getOrdonnee() - c1.getOrdonnee();
			Coordonnees position;
			if (largeur > 0) {
				if (hauteur > 0) {
					position = c1;
					ellipse = new Ellipse(position, largeur, hauteur);
				} else {
					position = new Coordonnees(c1.getAbscisse(), c1.getOrdonnee() + hauteur);
					ellipse = new Ellipse(position, largeur, -hauteur);
				}
			} else {
				if (hauteur > 0) {
					position = new Coordonnees(c1.getAbscisse() + largeur, c1.getOrdonnee());
					ellipse = new Ellipse(position, -largeur, hauteur);
				} else {
					position = new Coordonnees(c1.getAbscisse() + largeur, c1.getOrdonnee() + hauteur);
					ellipse = new Ellipse(position, -largeur, -hauteur);
				}
			}
		} else {
			ellipse = new Ellipse(c1);
		}
		ellipse.setCouleurContour(couleur);
		ellipse.setCouleurRemplissage(couleur2);
		ellipse.setEpaisseur(epaisseur);
		ellipse.setRempli(rempli);
		return new VueEllipse(ellipse);
	}
}
