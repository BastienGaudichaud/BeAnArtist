package fr.eseo.poo.projet.artiste.modele.formes;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class Cercle extends Ellipse {

	public Cercle() {
		super(LARGEUR_PAR_DEFAUT, LARGEUR_PAR_DEFAUT);
	}
	
	public Cercle(Coordonnees coordonnees) {
		super(coordonnees, LARGEUR_PAR_DEFAUT, LARGEUR_PAR_DEFAUT);
	}
	
	public Cercle(double taille) {
		super(taille, taille);
	}
	
	public Cercle(Coordonnees coordonnees, double taille) {
		super(coordonnees, taille, taille);
	}

	@Override
	public void setLargeur(double largeur) {
		if (largeur < 0) {
			throw new IllegalArgumentException("Largeur négative !");
		} else {
			super.setLargeur(largeur);
			super.setHauteur(largeur);
		}
	}

	@Override
	public void setHauteur(double hauteur) {
		if (hauteur < 0) {
			throw new IllegalArgumentException("Hauteur négative !");
		} else {
			super.setLargeur(hauteur);
			super.setHauteur(hauteur);
		}
	}

	@Override
	public double perimetre() {
		return Math.PI * getLargeur();
	}

}
