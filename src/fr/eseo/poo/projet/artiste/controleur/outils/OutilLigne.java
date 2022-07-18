package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.Color;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Ligne;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;
import fr.eseo.poo.projet.artiste.vue.formes.VueLigne;

public class OutilLigne extends OutilForme {

	private static final long serialVersionUID = 1L;

	/* Create a line according to the action of the user drawing.
	 * (See the documentation in the Application for more details.)
	 * (Called by OutilForme.)*/
	@Override
	protected VueForme creerVueForme() {
		Coordonnees c1 = getDebut();
		Coordonnees c2 = getFin();
		Color couleur = this.getPanneauDessin().getCouleurContourCourante();
		int epaisseur = this.getPanneauDessin().getEpaisseurCourante();
		Ligne ligne = new Ligne(c1);
		ligne.setCouleurContour(couleur);
		ligne.setEpaisseur(epaisseur);
		if (c1.getAbscisse() != c2.getAbscisse() || c1.getOrdonnee() != c2.getOrdonnee()) {
			ligne.setC2(c2);
		}
		return new VueLigne(ligne);
	}

}
