package fr.eseo.poo.projet.artiste.modele.formes;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.Remplissable;

public class Ellipse extends Forme implements Remplissable {

	private static final long serialVersionUID = 1L;
	private boolean estRempli;

	public Ellipse() {
		super();
		tailleMin = 1;
		tailleMax = 2000;
	}

	public Ellipse(Coordonnees position) {
		super(position);
		tailleMin = 1;
		tailleMax = 2000;
	}

	public Ellipse(double largeur, double hauteur) {
		super(largeur, hauteur);
		tailleMin = 1;
		tailleMax = 2000;
		setLargeur(largeur);
		setHauteur(hauteur);
	}

	public Ellipse(Coordonnees position, double largeur, double hauteur) {
		super(position, largeur, hauteur);
		tailleMin = 1;
		tailleMax = 2000;
		setLargeur(largeur);
		setHauteur(hauteur);
	}

	@Override
	public boolean estRempli() {
		return this.estRempli;
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
		double xA = getCadreMinX() - getEpaisseur() / 2.0;
		double yA = getCadreMinY() - getEpaisseur() / 2.0;
		double xB = getCadreMaxX()+ getEpaisseur() / 2.0;
		double yB = getCadreMaxY()+ getEpaisseur() / 2.0;
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

