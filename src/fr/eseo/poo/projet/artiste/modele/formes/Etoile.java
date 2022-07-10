package fr.eseo.poo.projet.artiste.modele.formes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.Remplissable;

public class Etoile extends Forme implements Remplissable {

	public static final int NOMBRE_BRANCHES_PAR_DEFAUT = 5;
	public static final double LONGUEUR_BRANCHE_PAR_DEFAUT = 0.5;
	public static final double ANGLE_PREMIERE_BRANCHE_PAR_DEFAUT = 0;
	private int nombreBranches;
	private double longueurBranche;
	private double anglePremiereBranche;
	private List<Coordonnees> coordonnees;
	private boolean estRempli;

	public Etoile() {
		this(COORDONNEES_PAR_DEFAUT, LARGEUR_PAR_DEFAUT, NOMBRE_BRANCHES_PAR_DEFAUT, ANGLE_PREMIERE_BRANCHE_PAR_DEFAUT,
				LONGUEUR_BRANCHE_PAR_DEFAUT);
	}

	public Etoile(Coordonnees position, double taille, int nombreBranches, double anglePremiereBranche, double longueurBranche) {
		setPosition(position);
		setLargeur(taille);
		setNombreBranches(nombreBranches);
		setAnglePremiereBranche(anglePremiereBranche);
		setLongueurBranche(longueurBranche);
	}
	

	public int getNombreBranches() {
		return this.nombreBranches;
	}

	public double getLongueurBranche() {
		return this.longueurBranche;
	}

	public double getAnglePremiereBranche() {
		return this.anglePremiereBranche;
	}

	public List<Coordonnees> getCoordonnees() {
		return coordonnees;
	}

	@Override
	public boolean estRempli() {
		return this.estRempli;
	}

	@Override
	public void setPosition(Coordonnees position) {
		super.setPosition(position);
		double angle = this.anglePremiereBranche;
		double taille = this.getLargeur() / 2;
		Coordonnees pos;
		double x;
		double y;
		if (this.coordonnees != null) {
			this.coordonnees.clear();
		} else {
			this.coordonnees = new ArrayList<>();
		}
		for (int i = 0; i < this.nombreBranches; i++) {
			pos = new Coordonnees(position.getAbscisse() + taille, position.getOrdonnee() + taille);
			x = taille * Math.cos(angle);
			y = taille * Math.sin(angle);
			pos.deplacerDe(x, y);
			this.coordonnees.add(pos);
			angle += (2 * Math.PI) / (this.nombreBranches * 2);
			pos = new Coordonnees(position.getAbscisse() + taille, position.getOrdonnee() + taille);
			x = (1 - this.longueurBranche) * taille * Math.cos(angle);
			y = (1 - this.longueurBranche) * taille * Math.sin(angle);
			pos.deplacerDe(x, y);
			this.coordonnees.add(pos);
			angle += (2 * Math.PI) / (this.nombreBranches * 2);
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

	public void setNombreBranches(int nombreBranches) {
		if (nombreBranches < 3 || nombreBranches > 15) {
			throw new IllegalArgumentException("Le nombre de branches doit être entre 3 et 15.");
		} else {
			this.nombreBranches = nombreBranches;
			setPosition(getPosition());
		}
	}

	public void setLongueurBranche(double longueurBranche) {
		if (longueurBranche < 0 || longueurBranche > 1) {
			throw new IllegalArgumentException("la longeur doit être comprise entre 0 et 1.");
		} else {
			this.longueurBranche = longueurBranche;
			setPosition(getPosition());
		}
	}

	public void setAnglePremiereBranche(double anglePremiereBranche) {
		if (anglePremiereBranche < -Math.PI || anglePremiereBranche > Math.PI) {
			throw new IllegalArgumentException("L'angle de départ doit être compris entre 0 et 2 pi.");
		} else {
			this.anglePremiereBranche = anglePremiereBranche;
			setPosition(getPosition());
		}
	}

	@Override
	public void setRempli(boolean modeRemplissage) {
		this.estRempli = modeRemplissage;
	}

	@Override
	public double perimetre() {
		double valeur = 0;
		for (int i = 0; i < this.coordonnees.size(); i++) {
			if (i == this.coordonnees.size() - 1) {
				double x = Math.abs(this.coordonnees.get(i).getAbscisse() - this.coordonnees.get(0).getAbscisse());
				double y = Math.abs(this.coordonnees.get(i).getOrdonnee() - this.coordonnees.get(0).getOrdonnee());
				valeur += Math.sqrt(x * x + y * y);
			} else {
				double x = Math.abs(this.coordonnees.get(i).getAbscisse() - this.coordonnees.get(i + 1).getAbscisse());
				double y = Math.abs(this.coordonnees.get(i).getOrdonnee() - this.coordonnees.get(i + 1).getOrdonnee());
				valeur += Math.sqrt(x * x + y * y);
			}
		}
		return valeur;
	}

	@Override
	public double aire() {
		double surface = 0;
		int nbBranches = this.nombreBranches;
		double xa = this.coordonnees.get(1).getAbscisse();
		double ya = this.coordonnees.get(1).getOrdonnee();
		double xb = this.coordonnees.get(2).getAbscisse();
		double yb = this.coordonnees.get(2).getOrdonnee();
		double xc = this.coordonnees.get(3).getAbscisse();
		double yc = this.coordonnees.get(3).getOrdonnee();
		double aireTriangle = 0.5 * Math.abs((xb - xa) * (yc - ya) - (xc - xa) * (yb - ya));
		surface += aireTriangle * nbBranches;
		double coteCentre = Math.sqrt(Math.pow(yc - ya, 2) + Math.pow(xc - xa, 2));
		double aireCentre = (nbBranches * coteCentre * coteCentre) / (4 * Math.tan(Math.PI / nbBranches));
		surface += aireCentre;
		return surface;
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
			string += "/number of branches :" + this.getNombreBranches();
			string += "/relative size of branches : " + this.getLongueurBranche();
		} else {
			string += "/nombre de branches :" + this.getNombreBranches();
			string += "/longueur proportionelle des branches : " + this.getLongueurBranche();
		}
		string = string.replace("/", System.lineSeparator());
		return string;
	}
}