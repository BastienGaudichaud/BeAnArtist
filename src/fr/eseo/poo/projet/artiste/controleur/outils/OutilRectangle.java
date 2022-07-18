package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.Color;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;
import fr.eseo.poo.projet.artiste.vue.formes.VueRectangle;

public class OutilRectangle extends OutilForme {

	private static final long serialVersionUID = 1L;

	/* Create a rectangle according to the action of the user drawing.
	 * (See the documentation in the Application for more details.)
	 * (Called by OutilForme.)*/
	@Override
	protected VueForme creerVueForme() {
		Coordonnees c1 = getDebut();
		Coordonnees c2 = getFin();
		double largeur = Math.abs(c2.getAbscisse() - c1.getAbscisse());
		double hauteur = Math.abs(c2.getOrdonnee() - c1.getOrdonnee());
		Coordonnees position = new Coordonnees(Math.min(c1.getAbscisse(), c2.getAbscisse()),
				Math.min(c1.getOrdonnee(), c2.getOrdonnee()));
		Color couleur = this.getPanneauDessin().getCouleurContourCourante();
		Color couleur2 = this.getPanneauDessin().getCouleurRemplissageCourante();
		int epaisseur = this.getPanneauDessin().getEpaisseurCourante();
		boolean rempli = this.getPanneauDessin().getModeRemplissage();
		Rectangle rectangle;
		if (c1.equals(c2)) {
			rectangle = new Rectangle(c1);
		} else {
			rectangle = new Rectangle(position, largeur, hauteur);

		}
		rectangle.setCouleurContour(couleur);
		rectangle.setCouleurRemplissage(couleur2);
		rectangle.setEpaisseur(epaisseur);
		rectangle.setRempli(rempli);
		return new VueRectangle(rectangle);
	}

}
