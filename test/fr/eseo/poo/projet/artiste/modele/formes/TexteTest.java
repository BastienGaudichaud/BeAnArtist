package fr.eseo.poo.projet.artiste.modele.formes;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Test;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class TexteTest {
	private static final String ERREUR_POSITION = "Erreur position";
	private static final String ERREUR_HAUTEUR = "Erreur hauteur";
	private static final String ERREUR_LARGEUR = "Erreur Largeur";
	private static final String ERREUR_TEXTE = "Erreur texte";
	private static final String ERREUR_REMPLI = "Erreur rempli";
	private static final double EPSILON = 0.001;

	@Test
	public void constructeurVideTest() {
		Texte texte = new Texte();

		assertEquals(ERREUR_LARGEUR, 0, texte.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 24, texte.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), texte.getPosition());
		assertEquals(ERREUR_TEXTE, "", texte.getTexte());

	}

	@Test
	public void constructeurPleinTest() {
		Texte texte = new Texte(new Coordonnees(5, 5), "texte");
		
		assertEquals(ERREUR_LARGEUR, 75, texte.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 24, texte.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, 5), texte.getPosition());
		assertEquals(ERREUR_TEXTE, "texte", texte.getTexte());
	}

	@Test
	public void setPoliceTest() {
		Texte texte = new Texte();
		texte.setPolice(12);
		assertEquals(ERREUR_LARGEUR, 0, texte.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 12, texte.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), texte.getPosition());
		assertEquals(ERREUR_TEXTE, "", texte.getTexte());
	}

	@Test
	public void setRempliTest() {
		Texte texte = new Texte();
		texte.setRempli(true);
		assertEquals(ERREUR_REMPLI, false, texte.estRempli());
	}

	@Test
	public void toStringTest() {
		Locale.setDefault(Locale.FRENCH);
		Texte texte = new Texte(new Coordonnees(0, 0), "texte");
		String line = System.lineSeparator();
		assertEquals("Erreur toString Français",
				"[Texte] " + line
				+ "position : (0,0 , 0,0)" + line
				+ "dimension : 75,0 x 24,0" + line
				+ "périmètre : 198,0" + line
				+ "aire : 1800,0" + line
				+ "épaisseur du contour : 4" + line
				+ "couleur : R51,V51,B51" + line
				+ "texte : texte",
				texte.toString());
		Locale.setDefault(Locale.ENGLISH);
		assertEquals("Erreur toString Anglais",
				"[Texte] " + line
				+ "position : (0.0 , 0.0)" + line
				+ "dimension : 75.0 x 24.0" + line
				+ "perimeter : 198.0" + line
				+ "surface : 1800.0" + line
				+ "side thickness : 4" + line
				+ "color : R51,G51,B51" + line
				+ "texte : texte",
				texte.toString());
		Locale.setDefault(Locale.FRENCH);
	}

}
