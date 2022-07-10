package fr.eseo.poo.projet.artiste.modele.formes;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Locale;

import fr.eseo.poo.projet.artiste.modele.Coloriable;
import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public abstract class Forme implements Coloriable {

	public static final Coordonnees COORDONNEES_PAR_DEFAUT = new Coordonnees();
	public static final double LARGEUR_PAR_DEFAUT = 100;
	public static final double HAUTEUR_PAR_DEFAUT = 150;
	public static final Color COULEUR_PAR_DEFAUT = javax.swing.UIManager.getColor("Panel.foreground");
	private static final int EPAISSEUR_PAR_DEFAUT = 4;
	private Coordonnees position;
	private double largeur;
	private double hauteur;
	private Color couleurContour;
	private Color couleurRemplissage;
	private int epaisseur;



	protected Forme() {
		this(COORDONNEES_PAR_DEFAUT, LARGEUR_PAR_DEFAUT, HAUTEUR_PAR_DEFAUT);
	}

	protected Forme(Coordonnees position) {
		this(position, LARGEUR_PAR_DEFAUT, HAUTEUR_PAR_DEFAUT);
	}

	protected Forme(double largeur, double hauteur) {
		this(COORDONNEES_PAR_DEFAUT, largeur, hauteur);
	}

	protected Forme(Coordonnees position, double largeur, double hauteur) {
		this.position = position;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.couleurContour = COULEUR_PAR_DEFAUT;
		this.couleurRemplissage = COULEUR_PAR_DEFAUT;
		this.epaisseur = EPAISSEUR_PAR_DEFAUT;
	}

	public Coordonnees getPosition() {
		return position;
	}

	public double getLargeur() {
		return largeur;
	}

	public double getHauteur() {
		return hauteur;
	}

	@Override
	public Color getCouleurContour() {
		return this.couleurContour;
	}

	public Color getCouleurRemplissage() {
		return couleurRemplissage;
	}

	public int getEpaisseur() {
		return epaisseur;
	}

	public double getCadreMinX() {
		if (this.largeur > 0) {
			return this.position.getAbscisse();
		} else {
			Coordonnees positionbis = new Coordonnees(this.getPosition().getAbscisse(),
					this.getPosition().getOrdonnee());
			positionbis.deplacerDe(this.largeur, this.hauteur);
			return positionbis.getAbscisse();
		}
	}

	public double getCadreMaxX() {
		if (this.largeur > 0) {
			Coordonnees positionbis = new Coordonnees(this.getPosition().getAbscisse(),
					this.getPosition().getOrdonnee());
			positionbis.deplacerDe(this.largeur, this.hauteur);
			return positionbis.getAbscisse();
		} else {
			return this.position.getAbscisse();
		}
	}

	public double getCadreMinY() {
		if (this.hauteur > 0) {
			return position.getOrdonnee();
		} else {
			Coordonnees positionbis = new Coordonnees(this.getPosition().getAbscisse(),
					this.getPosition().getOrdonnee());
			positionbis.deplacerDe(this.largeur, this.hauteur);
			return positionbis.getOrdonnee();
		}
	}

	public double getCadreMaxY() {
		if (this.hauteur > 0) {
			Coordonnees positionbis = new Coordonnees(this.getPosition().getAbscisse(),
					this.getPosition().getOrdonnee());
			positionbis.deplacerDe(this.largeur, this.hauteur);
			return positionbis.getOrdonnee();
		} else {
			return this.position.getOrdonnee();
		}
	}

	public void setPosition(Coordonnees position) {
		this.position = position;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	@Override
	public void setCouleurContour(Color couleur) {
		this.couleurContour = couleur;
	}

	public void setCouleurRemplissage(Color couleurRemplissage) {
		this.couleurRemplissage = couleurRemplissage;
	}

	public void setEpaisseur(int epaisseur) {
		this.epaisseur = epaisseur;
	}

	public void deplacerDe(double deltaX, double deltaY) {
		this.position.deplacerDe(deltaX, deltaY);
		setPosition(getPosition());
	}

	public void deplacerVers(double abscisse, double ordonnee) {
		this.position.deplacerVers(abscisse, ordonnee);
	}

	public boolean estRempli() {
		return false;
	}

	public void setRempli(boolean modeRemplissage) {
	}
	
	public String getTexte() {
		return null;
	}
	
	@Override
	public String toString() {
		String pattern = "##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		String string = "[" + this.getClass().getSimpleName();
		if (this.estRempli()) {
			string += "-Rempli";
		}
		if (Locale.getDefault().equals(Locale.ENGLISH)) {
			string += "] /position : " + getPosition() 
					+ "/dimension : " + decimalFormat.format(getLargeur()) + " x "+ decimalFormat.format(getHauteur()) 
					+ "/perimeter : " + decimalFormat.format(perimetre())
					+ "/surface : " + decimalFormat.format(aire()) 
					+ "/side thickness : " + this.getEpaisseur() 
					+ "/color : R" + getCouleurContour().getRed() + ",G" + getCouleurContour().getGreen() + ",B" + getCouleurContour().getBlue();
			if (this.estRempli()) {
				string += "/fill color : R" + getCouleurRemplissage().getRed()+ ",G" + getCouleurRemplissage().getGreen() + ",B" + getCouleurRemplissage().getBlue();
			}
		} else {
			string += "] /position : " + getPosition() 
					+ "/dimension : " + decimalFormat.format(getLargeur()) + " x " + decimalFormat.format(getHauteur()) 
					+ "/périmètre : " + decimalFormat.format(perimetre())
					+ "/aire : " + decimalFormat.format(aire()) 
					+ "/épaisseur du contour : " + this.getEpaisseur() 
					+ "/couleur : R" + getCouleurContour().getRed() + ",V" + getCouleurContour().getGreen() + ",B" + getCouleurContour().getBlue();
			if (this.estRempli()) {
				string += "/couleur remplissage : R" + getCouleurRemplissage().getRed()+ ",V" + getCouleurRemplissage().getGreen() + ",B" + getCouleurRemplissage().getBlue();
			}
		}
		string = string.replace("/", System.lineSeparator());
		return string;
	}

	public abstract double perimetre();

	public abstract double aire();

	public abstract boolean contient(Coordonnees coordonnees);
}
