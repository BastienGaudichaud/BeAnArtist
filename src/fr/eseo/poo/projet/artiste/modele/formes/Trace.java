package fr.eseo.poo.projet.artiste.modele.formes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class Trace extends Forme {

	private static final long serialVersionUID = 1L;
	public static final double EPSILON = 0.1;
	private ArrayList<Coordonnees> points;

	public Trace(Coordonnees position) {
		this.points = new ArrayList<>();
		this.points.add(position);
		this.setPosition(position);
		tailleMin = -2000;
		tailleMax = 2000;
	}

	public List<Coordonnees> getPoints() {
		return this.points;
	}

	public void ajouterCoordonnees(Coordonnees pos) {
		this.points.add(pos);
	}
	
	@Override
	public void deplacerDe(double deltaX, double deltaY) {
		for(Coordonnees point : points) {
			point.deplacerDe(deltaX, deltaY);
		}
		setPosition(getPoints().get(getPoints().size() - 1));
	}
	@Override
	public double perimetre() {
		double perimetre = 0;
		for (int i = 0; i < this.getPoints().size() - 1; i++) {
			Coordonnees coordonnees1 = this.getPoints().get(i);
			Coordonnees coordonnees2 = this.getPoints().get(i + 1);
			double largeur = coordonnees2.getAbscisse() - coordonnees1.getAbscisse();
			double hauteur = coordonnees2.getOrdonnee() - coordonnees1.getOrdonnee();
			perimetre += Math.sqrt(Math.pow(largeur, 2) + Math.pow(hauteur, 2));
		}
		return perimetre;
	}

	@Override
	public double aire() {
		return 0;
	}

	@Override
	public boolean contient(Coordonnees coordonnees) {
		boolean result = false;
		for (int i = 0; i < points.size() - 1; i++) {
			double distanceC1 = Math.abs(getPoints().get(i).distanceVers(coordonnees));
			double distanceC2 = Math.abs(getPoints().get(i + 1).distanceVers(coordonnees));
			double distanceC1C2 = Math.abs(getPoints().get(i).distanceVers(getPoints().get(i + 1)));
			double delta = Math.abs(distanceC2 + distanceC1 - distanceC1C2);
			if (delta < (getEpaisseur() / 2.0)) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		String string;
		if (Locale.getDefault().equals(Locale.ENGLISH)) {
			string = "[Trace] /longueur : " + this.perimetre()
					+ "/start : " + this.getPoints().get(0)
					+ "/end : " + this.getPoints().get(this.getPoints().size()-1)
					+ "/thickness : " + this.getEpaisseur() 
					+ "/color : R" + getCouleurContour().getRed() + ",V" + getCouleurContour().getGreen() + ",B" + getCouleurContour().getBlue();
		} else {
			string = "[Trace] /longueur : " + this.perimetre()
					+ "/début : " + this.getPoints().get(0)
					+ "/fin : " + this.getPoints().get(this.getPoints().size()-1)
					+ "/épaisseur du trait : " + this.getEpaisseur() 
					+ "/couleur : R" + getCouleurContour().getRed() + ",V" + getCouleurContour().getGreen() + ",B" + getCouleurContour().getBlue();
		}
		string = string.replace("/", System.lineSeparator());
		return string;
	}
	
	
}
