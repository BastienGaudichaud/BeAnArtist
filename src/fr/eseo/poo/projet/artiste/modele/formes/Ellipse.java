package fr.eseo.poo.projet.artiste.modele.formes;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.Remplissable;

public class Ellipse extends Forme implements Remplissable {

	private boolean estRempli;

	public Ellipse() {
		super();
	}

	public Ellipse(Coordonnees position) {
		super(position);
	}

	public Ellipse(double largeur, double hauteur) {
		super(largeur, hauteur);
	}

	public Ellipse(Coordonnees position, double largeur, double hauteur) {
		super(position, largeur, hauteur);
	}

	@Override
	public boolean estRempli() {
		return this.estRempli;
	}

	@Override
	public void setLargeur(double largeur) {
		if (largeur < 0) {
			throw new IllegalArgumentException("Largeur négative !");
		} else {
			super.setLargeur(largeur);
		}
	}

	@Override
	public void setHauteur(double hauteur) {
		if (hauteur < 0) {
			throw new IllegalArgumentException("Hauteur négatie !");
		} else {
			super.setHauteur(hauteur);
		}
	}

	@Override
	public void setRempli(boolean modeRemplissage) {
		this.estRempli = modeRemplissage;
	}

	@Override
	public double perimetre() {
		double a = getLargeur() / 2;
		double b = getHauteur() / 2;
		double h = Math.pow((a - b) / (a + b), 2);
		return Math.PI * (a + b) * (1 + (3 * h) / (10 + Math.sqrt(4 - 3 * h)));
	}

	@Override
	public double aire() {
		return Math.PI * getLargeur() * getHauteur() / 4;
	}

	@Override
	public boolean contient(Coordonnees coordonnees) {
		double xA = getCadreMinX();
		double yA = getCadreMinY();
		double xB = getCadreMaxX();
		double yB = getCadreMaxY();
		double abscisse = coordonnees.getAbscisse();
		double ordonnee = coordonnees.getOrdonnee();
		double a = (xA + xB) / 2;
		double b = (yA + yB) / 2;
		double x = Math.abs(xB - xA) / 2;
		double y = Math.abs(yB - yA) / 2;
		double deltaX = Math.pow(((abscisse - a) / x), 2);
		double deltaY = Math.pow(((ordonnee - b) / y), 2);
		double delta = deltaX + deltaY;
		return (delta < 1);
	}
}

