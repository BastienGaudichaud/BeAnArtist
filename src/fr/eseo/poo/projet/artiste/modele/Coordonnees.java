package fr.eseo.poo.projet.artiste.modele;

import java.text.DecimalFormat;
import java.util.Objects;

public class Coordonnees {

	public static final double ABSCISSE_PAR_DEFAUT = 0;
	public static final double ORDONNEE_PAR_DEFAUT = 0;
	private double abscisse;
	private double ordonnee;

	public Coordonnees() {
		this(Coordonnees.ABSCISSE_PAR_DEFAUT, Coordonnees.ORDONNEE_PAR_DEFAUT);
	}

	public Coordonnees(double abscisse, double ordonnee) {
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
	}

	public double getAbscisse() {
		return this.abscisse;
	}

	public double getOrdonnee() {
		return this.ordonnee;
	}

	public void setAbscisse(double abscisse) {
		this.abscisse = abscisse;
	}

	public void setOrdonnee(double ordonnee) {
		this.ordonnee = ordonnee;
	}

	public double angleVers(Coordonnees coord) {
		return Math.atan2((coord.ordonnee - this.ordonnee), (coord.abscisse - this.abscisse));
	}

	public double distanceVers(Coordonnees coord) {
		double deltaAbscisse = coord.abscisse - this.abscisse;
		double deltaOrdonnee = coord.ordonnee - this.ordonnee;
		return Math.sqrt(Math.pow(deltaAbscisse, 2) + Math.pow(deltaOrdonnee, 2));
	}

	public void deplacerDe(double deltaX, double deltaY) {
		this.abscisse += deltaX;
		this.ordonnee += deltaY;
	}

	public void deplacerVers(double nouvelleAbscisse, double nouvelleOrdonnee) {
		this.abscisse = nouvelleAbscisse;
		this.ordonnee = nouvelleOrdonnee;
	}

	@Override
	public int hashCode() {
		return Objects.hash(abscisse, ordonnee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Coordonnees other = (Coordonnees) obj;
		return Double.doubleToLongBits(abscisse) == Double.doubleToLongBits(other.abscisse)
				&& Double.doubleToLongBits(ordonnee) == Double.doubleToLongBits(other.ordonnee);
	}

	@Override
	public String toString() {
		String pattern = "##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		String abscisseDec = decimalFormat.format(this.getAbscisse());
		String ordonneeDec = decimalFormat.format(this.getOrdonnee());
		return "(" + abscisseDec + " , " + ordonneeDec + ")";
	}

}