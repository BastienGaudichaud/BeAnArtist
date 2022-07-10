package fr.eseo.poo.projet.artiste.modele.formes;

import java.awt.Font;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class Texte extends Rectangle {

	private String contenu;
	private Font police;
	private int tailePolice = 24;

	public Texte() {
		this(COORDONNEES_PAR_DEFAUT, "");
	}
	
	public Texte(Coordonnees position, String texte) {
		super(position);
		this.setTexte(texte);
		this.setLargeur((double) texte.length() * 15 * tailePolice / 24);
		this.setHauteur(tailePolice);
	}

	@Override
	public String getTexte() {
		return contenu;
	}

	public Font getPolice() {
		return police;
	}

	public int getTailePolice() {
		return tailePolice;
	}

	public void setTexte(String texte) {
		this.contenu = texte;
	}

	public void setPolice(int taillePolice) {
		String texte = getTexte();
		String[] textes = texte.split("\\R");
		int nbLignes = textes.length;
		this.police = new Font("Police texte", Font.BOLD, taillePolice);
		this.tailePolice = taillePolice;
		this.setLargeur((double) contenu.length() * 15 * tailePolice / 24);
		this.setHauteur(taillePolice * nbLignes);
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
