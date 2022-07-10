package fr.eseo.poo.projet.artiste.modele.formes;

import java.text.DecimalFormat;
import java.util.Locale;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class Ligne extends Forme {

	public static final double EPSILON = 0.1;

	public Ligne() {
		super();
	}

	public Ligne(Coordonnees position) {
		super(position);
	}

	public Ligne(double largeur, double hauteur) {
		super(largeur, hauteur);
	}

	public Ligne(Coordonnees position, double largeur, double hauteur) {
		super(position, largeur, hauteur);
	}

	public Coordonnees getC1() {
		return new Coordonnees(getPosition().getAbscisse(), getPosition().getOrdonnee());
	}

	public Coordonnees getC2() {
		Coordonnees positionbis = new Coordonnees(getPosition().getAbscisse(), getPosition().getOrdonnee());
		positionbis.deplacerDe(getLargeur(), getHauteur());
		return positionbis;
	}

	public void setC1(Coordonnees position) {
		Coordonnees positionbis = getPosition();
		positionbis.deplacerDe(getLargeur(), getHauteur());
		setLargeur(positionbis.getAbscisse() - position.getAbscisse());
		setHauteur(positionbis.getOrdonnee() - position.getOrdonnee());
		setPosition(position);
	}

	public void setC2(Coordonnees position) {
		setLargeur(position.getAbscisse() - getPosition().getAbscisse());
		setHauteur(position.getOrdonnee() - getPosition().getOrdonnee());
	}

	@Override
	public double perimetre() {
		double largeur = getLargeur();
		double hauteur = getHauteur();
		return Math.sqrt(Math.pow(largeur, 2) + Math.pow(hauteur, 2));
	}

	@Override
	public double aire() {
		return 0;
	}

	@Override
	public boolean contient(Coordonnees coordonnees) {
		double distanceC1 = Math.abs(getC1().distanceVers(coordonnees));
		double distanceC2 = Math.abs(getC2().distanceVers(coordonnees));
		double delta = Math.abs(distanceC2 + distanceC1 - perimetre());
		return (delta < EPSILON);
	}

	@Override
	public String toString() {
		String pattern = "##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		Coordonnees c1 = getC1();
		Coordonnees c2 = getC2();
		double angle = ((c1.angleVers(c2) / Math.PI) * 180);
		if (angle < 0) {
			angle += 360;
		}
		String string;
		if (Locale.getDefault().equals(Locale.ENGLISH)) {
			string = "[Ligne] /c1 : " + c1 + "/c2 : " + c2 + "/length : " + decimalFormat.format(perimetre())
					+ "/angle : " + decimalFormat.format(angle) + "°/color = R" + getCouleurContour().getRed() + ",G"
					+ getCouleurContour().getGreen() + ",B" + getCouleurContour().getBlue();
		} else {
			string = "[Ligne] /c1 : " + c1 + "/c2 : " + c2 + "/longueur : " + decimalFormat.format(perimetre())
				+ "/angle : " + decimalFormat.format(angle) + "°/couleur = R" + getCouleurContour().getRed() + ",V"
				+ getCouleurContour().getGreen() + ",B" + getCouleurContour().getBlue();
		}
		string = string.replace("/", System.lineSeparator());
		return string;
	}

}
