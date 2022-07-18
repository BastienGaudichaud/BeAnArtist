package fr.eseo.poo.projet.artiste.modele.formes;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.Remplissable;

public class Rectangle extends Forme implements Remplissable {

	private static final long serialVersionUID = 1L;
	private boolean estRempli;

	public Rectangle() {
		super();
		tailleMin = 1;
		tailleMax = 2000;
	}

	public Rectangle(Coordonnees position) {
		super(position);
		tailleMin = 1;
		tailleMax = 2000;
	}

	public Rectangle(double largeur, double hauteur) {
		super(largeur, hauteur);
		tailleMin = 1;
		tailleMax = 2000;
		setLargeur(largeur);
		setHauteur(hauteur);
	}

	public Rectangle(Coordonnees position, double largeur, double hauteur) {
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
		return 2 * getHauteur() + 2 * getLargeur();
	}

	@Override
	public double aire() {
		return getLargeur() * getHauteur();
	}

	@Override
	public boolean contient(Coordonnees coordonnees) {
		return (coordonnees.getAbscisse() > getPosition().getAbscisse() - getEpaisseur() / 2.0
				&& coordonnees.getAbscisse() < getPosition().getAbscisse() + getLargeur() + getEpaisseur() / 2.0
				&& coordonnees.getOrdonnee() > getPosition().getOrdonnee() - getEpaisseur() / 2.0
				&& coordonnees.getOrdonnee() < getPosition().getOrdonnee() + getHauteur() + getEpaisseur() / 2.0);
	}

	

}
