package fr.eseo.poo.projet.artiste.modele.formes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.Remplissable;

public class PolygoneRegulier extends Forme implements Remplissable {

	public static final int NOMBRE_DE_COTES_PAR_DEFAUT = 3;
	public static final double ANGLE_SOMMET_PAR_DEFAUT = -(3 * Math.PI / 4);
	private int nombreDeCotes;
	private double anglePremierSommet;
	private List<Coordonnees> coordonnees;
	private boolean estRempli;

	public PolygoneRegulier() {
		this(COORDONNEES_PAR_DEFAUT, LARGEUR_PAR_DEFAUT, NOMBRE_DE_COTES_PAR_DEFAUT, ANGLE_SOMMET_PAR_DEFAUT);
	}
	
	public PolygoneRegulier(Coordonnees position, double taille, int nombreDeCotes, double anglePremierSommet) {
		super(position, taille, taille);
		setNombreDeCotes(nombreDeCotes);
		setAnglePremierSommet(anglePremierSommet);
	}

	public int getNombreDeCotes() {
		return nombreDeCotes;
	}

	public double getAnglePremierSommet() {
		return this.anglePremierSommet;
	}

	/*
	 * Utilise la propriété des polygones réguliers telle que pour tout polygone
	 * régulier à n cotés d'apothème h et de coté a, p=a/(2tan(pi/n))
	 */
	public double getTailleCote() {
		double diametre = this.getLargeur();
		int n = getNombreDeCotes();
		return diametre * Math.sin(Math.PI / n);
	}

	public List<Coordonnees> getCoordonnees() {
		return this.coordonnees;
	}

	@Override
	public boolean estRempli() {
		return this.estRempli;
	}

	@Override
	public void setPosition(Coordonnees position) {
		super.setPosition(position);
		double angle = this.anglePremierSommet;
		double taille = this.getLargeur() / 2;
		Coordonnees pos;
		double x;
		double y;
		if (this.coordonnees != null) {
			this.coordonnees.clear();
		} else {
			this.coordonnees = new ArrayList<>();
		}
		for (int i = 0; i < nombreDeCotes; i++) {
			pos = new Coordonnees(position.getAbscisse() + taille, position.getOrdonnee() + taille);
			x = taille * Math.cos(angle);
			y = taille * Math.sin(angle);
			pos.deplacerDe(x, y);
			this.coordonnees.add(pos);
			angle += (2 * Math.PI) / (this.nombreDeCotes);
		}
	}

	@Override
	public void setLargeur(double largeur) {
		if (largeur < 0) {
			throw new IllegalArgumentException("Largeur négative !");
		} else {
			super.setLargeur(largeur);
			super.setHauteur(largeur);
			this.setPosition(getPosition());
		}
	}

	@Override
	public void setHauteur(double hauteur) {
		if (hauteur < 0) {
			throw new IllegalArgumentException("Largeur négative !");
		} else {
			super.setLargeur(hauteur);
			super.setHauteur(hauteur);
			this.setPosition(getPosition());
		}
	}

	public void setNombreDeCotes(int nombreDeCotes) {
		if (nombreDeCotes < 3 || nombreDeCotes > 15) {
			throw new IllegalArgumentException("Le nombre de branches doit être entre 3 et 15.");
		} else {
			this.nombreDeCotes = nombreDeCotes;
			setPosition(getPosition());
		}
	}

	public void setAnglePremierSommet(double anglePremierSommet) {
		if (anglePremierSommet < -Math.PI || anglePremierSommet > Math.PI) {
			throw new IllegalArgumentException("L'angle de départ doit être compris entre 0 et 2 pi.");
		} else {
			this.anglePremierSommet = anglePremierSommet;
			setPosition(getPosition());
		}
	}

	@Override
	public void setRempli(boolean modeRemplissage) {
		this.estRempli = modeRemplissage;
	}

	@Override
	public double perimetre() {
		double c = getTailleCote();
		int n = getNombreDeCotes();
		return n * c;
	}

	@Override
	public double aire() {
		double c = getTailleCote();
		int n = getNombreDeCotes();
		return (n * Math.pow(c, 2)) / (4 * Math.tan(Math.PI / n));
	}

	@Override
	public boolean contient(Coordonnees coordonnees) {
		boolean result = false;
		double pointIX;
		double pointIY;
		double pointJX;
		double pointJY;
		double testX;
		double testY;
		for (int i = 0, j = this.coordonnees.size() - 1; i < this.coordonnees.size(); j = i++) {
			pointIX = this.coordonnees.get(i).getAbscisse();
			pointIY = this.coordonnees.get(i).getOrdonnee();
			pointJX = this.coordonnees.get(j).getAbscisse();
			pointJY = this.coordonnees.get(j).getOrdonnee();
			testX = coordonnees.getAbscisse();
			testY = coordonnees.getOrdonnee();
			if (((pointIY > testY) != (pointJY > testY))
					&& (testX < (pointJX - pointIX) * (testY - pointIY) / (pointJY - pointIY) + pointIX)) {
				result = !result;
			}
		}
		double xa;
		double ya;
		double xb;
		double yb;
		for (int i = 0; i < this.coordonnees.size(); i++) {
			xa = this.coordonnees.get(i).getAbscisse();
			ya = this.coordonnees.get(i).getOrdonnee();
			if (i == this.coordonnees.size() - 1) {
				xb = this.coordonnees.get(0).getAbscisse();
				yb = this.coordonnees.get(0).getOrdonnee();
			} else {
				xb = this.coordonnees.get(i + 1).getAbscisse();
				yb = this.coordonnees.get(i + 1).getOrdonnee();
			}
			Ligne ligne = new Ligne(new Coordonnees(xa, ya), xb - xa, yb - ya);
			if (ligne.contient(coordonnees)) {
				return true;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		String string = super.toString();
		if (Locale.getDefault().equals(Locale.ENGLISH)) {
			string += "/number of sides :" + this.getNombreDeCotes();
		} else {
			string += "/nombre de côtés :" + this.getNombreDeCotes();
		}
		string = string.replace("/", System.lineSeparator());
		return string;
	}
}
