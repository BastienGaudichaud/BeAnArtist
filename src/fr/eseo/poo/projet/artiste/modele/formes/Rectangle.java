package fr.eseo.poo.projet.artiste.modele.formes;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.Remplissable;

public class Rectangle extends Forme implements Remplissable {

	private boolean estRempli;

	public Rectangle() {
		super();
	}

	public Rectangle(Coordonnees position) {
		super(position);
	}

	public Rectangle(double largeur, double hauteur) {
		super(largeur, hauteur);
	}

	public Rectangle(Coordonnees position, double largeur, double hauteur) {
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
			throw new IllegalArgumentException("Largeur négative !");
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
		return 2 * getHauteur() + 2 * getLargeur();
	}

	@Override
	public double aire() {
		return getLargeur() * getHauteur();
	}

	@Override
	public boolean contient(Coordonnees coordonnees) {
		return (coordonnees.getAbscisse() > getPosition().getAbscisse()
				&& coordonnees.getAbscisse() < getPosition().getAbscisse() + getLargeur()
				&& coordonnees.getOrdonnee() > getPosition().getOrdonnee()
				&& coordonnees.getOrdonnee() < getPosition().getOrdonnee() + getHauteur());
	}

	

}
