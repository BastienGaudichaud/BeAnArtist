package fr.eseo.poo.projet.artiste.modele.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class EllipseTest {

	private static final String ERREUR_POSITION = "Erreur position";
	private static final String ERREUR_HAUTEUR = "Erreur hauteur";
	private static final String ERREUR_LARGEUR = "Erreur Largeur";
	private static final double EPSILON = 0.001;

	@Test
	public void constructeurVideTest() {
		Ellipse ellipse = new Ellipse();

		assertEquals(ERREUR_LARGEUR, 100, ellipse.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 150, ellipse.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), ellipse.getPosition());
	}

	@Test
	public void constructeurCoordonneesTest() {
		Ellipse ellipse = new Ellipse(new Coordonnees(5, 5));

		assertEquals(ERREUR_LARGEUR, 100, ellipse.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 150, ellipse.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, 5), ellipse.getPosition());
	}

	@Test
	public void constructeurDimensionsTest() {
		Ellipse ellipse = new Ellipse(10, 10);

		assertEquals(ERREUR_LARGEUR, 10, ellipse.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 10, ellipse.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), ellipse.getPosition());
	}

	@Test
	public void constructeurPleinTest() {
		Ellipse ellipse = new Ellipse(new Coordonnees(5, 5), 10, 10);

		assertEquals(ERREUR_LARGEUR, 10, ellipse.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 10, ellipse.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, 5), ellipse.getPosition());
	}

	@Test
	public void rempliTest() {
		Ellipse ellipse = new Ellipse();
		ellipse.setRempli(true);
		assertEquals("Erreur toString Français",
				"[Ellipse-Rempli] \r\n"
				+ "position : (0,0 , 0,0)\r\n"
				+ "dimension : 100,0 x 150,0\r\n"
				+ "périmètre : 396,64\r\n"
				+ "aire : 11780,97\r\n"
				+ "épaisseur du contour : 4\r\n"
				+ "couleur : R51,V51,B51\r\n"
				+ "couleur remplissage : R51,V51,B51",
				ellipse.toString());
	}

	@Test
	public void setLargeurTest() {
		Ellipse ellipse = new Ellipse();
		ellipse.setLargeur(10.432);
		assertEquals(ERREUR_LARGEUR, 10.432, ellipse.getLargeur(), EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setLargeurNegativeTest() {
		Ellipse ellipse = new Ellipse();
		ellipse.setLargeur(-5);
	}

	@Test
	public void setHauteurTest() {
		Ellipse ellipse = new Ellipse();
		ellipse.setHauteur(7.65);

		assertEquals("Erreur Hauteur", 7.65, ellipse.getHauteur(), EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setHauteurNegativeTest() {
		Ellipse ellipse = new Ellipse();
		ellipse.setHauteur(-5);
	}

	@Test
	public void perimetreTest() {
		Ellipse ellipse = new Ellipse();
		assertEquals("Erreur périmetre", 396.636, ellipse.perimetre(), EPSILON);
	}

	@Test
	public void aireTest() {
		Ellipse ellipse = new Ellipse();
		assertEquals("Erreur aire", 11780.9725, ellipse.aire(), EPSILON);
	}

	@Test
	public void contientTest() {
		Ellipse ellipse = new Ellipse();
		assertTrue("Erreur contient", ellipse.contient(new Coordonnees(50, 50)));
		assertFalse("Erreur contient pas", ellipse.contient(new Coordonnees(200, 200)));
	}

	@Test
	public void toStringTest() {
		Locale.setDefault(Locale.FRENCH);
		Ellipse ellipse = new Ellipse(new Coordonnees(0, 0), 5, 5);
		assertEquals("Erreur toString Français",
				"[Ellipse] \r\n"
				+ "position : (0,0 , 0,0)\r\n"
				+ "dimension : 5,0 x 5,0\r\n"
				+ "périmètre : 15,71\r\n"
				+ "aire : 19,63\r\n"
				+ "épaisseur du contour : 4\r\n"
				+ "couleur : R51,V51,B51",
				ellipse.toString());
		Locale.setDefault(Locale.ENGLISH);
		assertEquals("Erreur toString Anglais",
				"[Ellipse] \r\n"
				+ "position : (0.0 , 0.0)\r\n"
				+ "dimension : 5.0 x 5.0\r\n"
				+ "perimeter : 15.71\r\n"
				+ "surface : 19.63\r\n"
				+ "side thickness : 4\r\n"
				+ "color : R51,G51,B51",
				ellipse.toString());
		Locale.setDefault(Locale.FRENCH);
	}
}
