package fr.eseo.poo.projet.artiste.modele.formes;

import java.awt.Font;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class Texte extends Rectangle {

	private static final long serialVersionUID = 1L;
	private String contenu;
	private Font police;
	private int taillePolice = 24;

	public Texte() {
		this(COORDONNEES_PAR_DEFAUT, "");
	}
	
	public Texte(Coordonnees position, String texte) {
		super(position);
		this.setTexte(texte);
		this.setLargeur((double) texte.length() * 15 * taillePolice / 24);
		this.setHauteur(taillePolice);
	}

	@Override
	public String getTexte() {
		return contenu;
	}

	public Font getPolice() {
		return police;
	}

	public int getTaillePolice() {
		return taillePolice;
	}

	public void setTexte(String texte) {
		this.contenu = texte;
	}

	public void setPolice(int taillePolice) {
		String texte = getTexte();
		String[] textes = texte.split("\\R");
		int longueur = 0;
		for (String string : textes) {
			if(string.length() > longueur) {
				longueur = string.length();
			}
		}
		int nbLignes = textes.length;
		this.police = new Font("Police texte", Font.BOLD, taillePolice);
		this.taillePolice = taillePolice;
		this.setLargeur((double) longueur * 15 * taillePolice / 24);
		this.setHauteur((double)taillePolice * nbLignes);
	}

	@Override
	public void setRempli(boolean modeRemplissage) {
		super.setRempli(false);
	}

	@Override
	public String toString() {
		String string = super.toString() + "/texte : " + contenu;
		string = string.replace("/", System.lineSeparator());
		return string;
	}

}
