package fr.eseo.poo.projet.artiste.modele.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Locale;

import org.junit.Test;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class LigneTest {

	private static final String ERREUR_POSITION = "Erreur position";
	private static final String ERREUR_HAUTEUR = "Erreur hauteur";
	private static final String ERREUR_LARGEUR = "Erreur largeur";
	private static final double EPSILON = 0.001;

	@Test
	public void constructeurVideTest() {
		Ligne ligne1 = new Ligne();

		assertEquals(ERREUR_LARGEUR, 100, ligne1.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 150, ligne1.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), ligne1.getPosition());
	}

	@Test
	public void constructeurCoordonneesTest() {
		Ligne ligne2 = new Ligne(new Coordonnees(5, 5));

		assertEquals(ERREUR_LARGEUR, 100, ligne2.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 150, ligne2.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, 5), ligne2.getPosition());
	}

	@Test
	public void constructeurDimensionsTest() {
		Ligne ligne3 = new Ligne(10, 10);

		assertEquals(ERREUR_LARGEUR, 10, ligne3.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 10, ligne3.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), ligne3.getPosition());
	}

	@Test
	public void constructeurPleinTest() {
		Ligne ligne4 = new Ligne(new Coordonnees(5, 5), 10, 10);

		assertEquals(ERREUR_LARGEUR, 10, ligne4.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 10, ligne4.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, 5), ligne4.getPosition());
	}

	@Test
	public void couleurTest() {
		Ligne ligne5 = new Ligne();
		ligne5.setCouleurContour(Color.RED);
		assertEquals("Erreur couleur", Color.RED, ligne5.getCouleurContour());
	}

	@Test
	public void rempliTest() {
		Ligne ligne6 = new Ligne();
		assertFalse("Erreur rempli", ligne6.estRempli());
	}

	@Test
	public void getC1Test() {
		Ligne ligne7 = new Ligne();
		assertEquals("Erreur position 1", new Coordonnees(), ligne7.getC1());
	}

	@Test
	public void getC2Test() {
		Ligne ligne8 = new Ligne();
		assertEquals("Erreur position 2", new Coordonnees(100, 150), ligne8.getC2());
	}

	@Test
	public void getMinXTest() {
		Ligne ligne9 = new Ligne();
		assertEquals("Erreur MinX positif", 0, ligne9.getCadreMinX(), EPSILON);
		Ligne ligne10 = new Ligne(-5, -5);
		assertEquals("Erreur MinX negatif", -5, ligne10.getCadreMinX(), EPSILON);
	}

	@Test
	public void getMaxXTest() {
		Ligne ligne11 = new Ligne();
		assertEquals("Erreur MaxX", 100, ligne11.getCadreMaxX(), EPSILON);
		Ligne ligne12 = new Ligne(-5, -5);
		assertEquals("Erreur MaxX negatif", 0, ligne12.getCadreMaxX(), EPSILON);
	}

	@Test
	public void getMinYTest() {
		Ligne ligne13 = new Ligne();
		assertEquals("Erreur MinY", 0, ligne13.getCadreMinY(), EPSILON);
		Ligne ligne14 = new Ligne(-5, -5);
		assertEquals("Erreur MinY negatif", -5, ligne14.getCadreMinY(), EPSILON);
	}

	@Test
	public void getMaxYTest() {
		Ligne ligne15 = new Ligne();
		assertEquals("Erreur MaxY", 150, ligne15.getCadreMaxY(), EPSILON);
		Ligne ligne16 = new Ligne(-5, -5);
		assertEquals("Erreur MaxY negatif", 0, ligne16.getCadreMaxY(), EPSILON);
	}

	@Test
	public void setPositionTest() {
		Ligne ligne17 = new Ligne();
		ligne17.setPosition(new Coordonnees(587.42, -3.189));

		assertEquals(ERREUR_POSITION, new Coordonnees(587.42, -3.189), ligne17.getPosition());
	}

	@Test
	public void setLargeurTest() {
		Ligne ligne18 = new Ligne();
		ligne18.setLargeur(-10.432);

		assertEquals(ERREUR_LARGEUR, -10.432, ligne18.getLargeur(), EPSILON);
	}

	@Test
	public void setHauteurTest() {
		Ligne ligne19 = new Ligne();
		ligne19.setHauteur(7.65);

		assertEquals(ERREUR_HAUTEUR, 7.65, ligne19.getHauteur(), EPSILON);
	}

	@Test
	public void setC1Test() {
		Ligne ligne20 = new Ligne();
		ligne20.setC1(new Coordonnees(5, 5));
		assertEquals("Erreur C1", new Coordonnees(5, 5), ligne20.getC1());
		assertEquals("Erreur C2", new Coordonnees(100, 150), ligne20.getC2());
		assertEquals(ERREUR_LARGEUR, 95, ligne20.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 145, ligne20.getHauteur(), EPSILON);
	}

	@Test
	public void setC2Test() {
		Ligne ligne21 = new Ligne();
		ligne21.setC2(new Coordonnees(5, 5));
		assertEquals("Erreur C1", new Coordonnees(0, 0), ligne21.getC1());
		assertEquals("Erreur C2", new Coordonnees(5, 5), ligne21.getC2());
		assertEquals(ERREUR_LARGEUR, 5, ligne21.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 5, ligne21.getHauteur(), EPSILON);
	}

	@Test
	public void deplacerDeTest() {
		Ligne ligne22 = new Ligne();
		ligne22.deplacerDe(5, -5);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, -5), ligne22.getPosition());
	}

	@Test
	public void deplacerVersTest() {
		Ligne ligne23 = new Ligne();
		ligne23.deplacerVers(5, -5);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, -5), ligne23.getPosition());
	}

	@Test
	public void perimetreTest() {
		Ligne ligne24 = new Ligne();
		assertEquals("Erreur périmetre", java.lang.Math.sqrt(32500), ligne24.perimetre(), EPSILON);
	}

	@Test
	public void aireTest() {
		Ligne ligne25 = new Ligne();
		assertEquals("Erreur aire", 0, ligne25.aire(), EPSILON);
	}

	@Test
	public void contientTest() {
		Ligne ligne26 = new Ligne();
		assertTrue("Erreur contient", ligne26.contient(new Coordonnees(50.2, 75)));
		assertFalse("Erreur contient pas", ligne26.contient(new Coordonnees(75, 75)));
	}

	@Test
	public void toStringTest() {
		Locale.setDefault(Locale.FRENCH);
		Ligne ligne27 = new Ligne(new Coordonnees(0, 0), 5, 5);
		assertEquals("Erreur toString()",
				"[Ligne] \r\n"
				+ "c1 : (0,0 , 0,0)\r\n"
				+ "c2 : (5,0 , 5,0)\r\n"
				+ "longueur : 7,07\r\n"
				+ "angle : 45,0°\r\n"
				+ "couleur = R51,V51,B51",
				ligne27.toString());
		Ligne ligne28 = new Ligne(new Coordonnees(0, 0), -5, -5);
		assertEquals("Erreur toString()",
				"[Ligne] \r\n"
				+ "c1 : (0,0 , 0,0)\r\n"
				+ "c2 : (-5,0 , -5,0)\r\n"
				+ "longueur : 7,07\r\n"
				+ "angle : 225,0°\r\n"
				+ "couleur = R51,V51,B51",
				ligne28.toString());
		Locale.setDefault(Locale.ENGLISH);
		assertEquals("Erreur toString Anglais",
				"[Ligne] \r\n"
				+ "c1 : (0.0 , 0.0)\r\n"
				+ "c2 : (-5.0 , -5.0)\r\n"
				+ "length : 7.07\r\n"
				+ "angle : 225.0°\r\n"
				+ "color = R51,G51,B51",
				ligne28.toString());
	}

}
