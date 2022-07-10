package fr.eseo.poo.projet.artiste.modele.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class RectangleTest {

	private static final String ERREUR_POSITION = "Erreur position";
	private static final String ERREUR_HAUTEUR = "Erreur hauteur";
	private static final String ERREUR_LARGEUR = "Erreur Largeur";
	private static final double EPSILON = 0.001;

	@Test
	public void constructeurVideTest() {
		Rectangle rectangle = new Rectangle();

		assertEquals(ERREUR_LARGEUR, 100, rectangle.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 150, rectangle.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), rectangle.getPosition());
	}

	@Test
	public void constructeurCoordonneesTest() {
		Rectangle rectangle = new Rectangle(new Coordonnees(5, 5));

		assertEquals(ERREUR_LARGEUR, 100, rectangle.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 150, rectangle.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, 5), rectangle.getPosition());
	}

	@Test
	public void constructeurDimensionsTest() {
		Rectangle rectangle = new Rectangle(10, 10);

		assertEquals(ERREUR_LARGEUR, 10, rectangle.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 10, rectangle.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), rectangle.getPosition());
	}

	@Test
	public void constructeurPleinTest() {
		Rectangle rectangle = new Rectangle(new Coordonnees(5, 5), 10, 10);

		assertEquals(ERREUR_LARGEUR, 10, rectangle.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 10, rectangle.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, 5), rectangle.getPosition());
	}

	@Test
	public void setLargeurTest() {
		Rectangle rectangle = new Rectangle();
		rectangle.setLargeur(10.432);
		assertEquals(ERREUR_LARGEUR, 10.432, rectangle.getLargeur(), EPSILON);
	}

	@Test
	public void setHauteurTest() {
		Rectangle rectangle = new Rectangle();
		rectangle.setHauteur(7.65);

		assertEquals("Erreur Hauteur", 7.65, rectangle.getHauteur(), EPSILON);
	}

	@Test
	public void rempliTest() {
		Rectangle rectangle = new Rectangle();
		rectangle.setRempli(true);
		assertEquals("Erreur toString Français",
				"[Rectangle-Rempli] \r\n"
				+ "position : (0,0 , 0,0)\r\n"
				+ "dimension : 100,0 x 150,0\r\n"
				+ "périmètre : 500,0\r\n"
				+ "aire : 15000,0\r\n"
				+ "épaisseur du contour : 4\r\n"
				+ "couleur : R51,V51,B51\r\n"
				+ "couleur remplissage : R51,V51,B51",
				rectangle.toString());
	}

	@Test
	public void perimetreTest() {
		Rectangle rectangle = new Rectangle();
		assertEquals("Erreur périmetre", 500, rectangle.perimetre(), EPSILON);
	}

	@Test
	public void aireTest() {
		Rectangle rectangle = new Rectangle();
		assertEquals("Erreur aire", 15000, rectangle.aire(), EPSILON);
	}

	@Test
	public void contientTest() {
		Rectangle rectangle = new Rectangle();
		assertTrue("Erreur contient", rectangle.contient(new Coordonnees(50, 50)));
		assertFalse("Erreur contient pas", rectangle.contient(new Coordonnees(200, 200)));
	}

	@Test
	public void toStringTest() {
		Locale.setDefault(Locale.FRENCH);
		Rectangle rectangle = new Rectangle(new Coordonnees(0, 0), 5, 5);
		assertEquals("Erreur toString Français",
				"[Rectangle] \r\n"
				+ "position : (0,0 , 0,0)\r\n"
				+ "dimension : 5,0 x 5,0\r\n"
				+ "périmètre : 20,0\r\n"
				+ "aire : 25,0\r\n"
				+ "épaisseur du contour : 4\r\n"
				+ "couleur : R51,V51,B51",
				rectangle.toString());
		Locale.setDefault(Locale.ENGLISH);
		assertEquals("Erreur toString Anglais",
				"[Rectangle] \r\n"
				+ "position : (0.0 , 0.0)\r\n"
				+ "dimension : 5.0 x 5.0\r\n"
				+ "perimeter : 20.0\r\n"
				+ "surface : 25.0\r\n"
				+ "side thickness : 4\r\n"
				+ "color : R51,G51,B51",
				rectangle.toString());
		Locale.setDefault(Locale.FRENCH);
	}
}
