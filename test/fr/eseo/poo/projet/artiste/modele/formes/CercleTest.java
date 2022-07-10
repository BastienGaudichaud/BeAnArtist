package fr.eseo.poo.projet.artiste.modele.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class CercleTest {

	private static final String ERREUR_POSITION = "Erreur position";
	private static final String ERREUR_HAUTEUR = "Erreur hauteur";
	private static final String ERREUR_LARGEUR = "Erreur Largeur";
	private static final double EPSILON = 0.001;

	@Test
	public void constructeurVideTest() {
		Cercle cercle = new Cercle();

		assertEquals(ERREUR_LARGEUR, 100, cercle.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 100, cercle.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), cercle.getPosition());
	}

	@Test
	public void constructeurCoordonneesTest() {
		Cercle cercle = new Cercle(new Coordonnees(5, 5));

		assertEquals(ERREUR_LARGEUR, 100, cercle.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 100, cercle.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, 5), cercle.getPosition());
	}

	@Test
	public void constructeurDimensionsTest() {
		Cercle cercle = new Cercle(10);

		assertEquals(ERREUR_LARGEUR, 10, cercle.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 10, cercle.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), cercle.getPosition());
	}

	@Test
	public void constructeurPleinTest() {
		Cercle cercle = new Cercle(new Coordonnees(5, 5), 10);

		assertEquals(ERREUR_LARGEUR, 10, cercle.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 10, cercle.getHauteur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, 5), cercle.getPosition());
	}

	@Test
	public void setLargeurTest() {
		Cercle cercle = new Cercle();
		cercle.setLargeur(10.432);

		assertEquals(ERREUR_LARGEUR, 10.432, cercle.getLargeur(), EPSILON);
		assertEquals(ERREUR_HAUTEUR, 10.432, cercle.getHauteur(), EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setLargeurNegativeTest() {
		Cercle cercle = new Cercle();
		cercle.setLargeur(-5);
	}

	@Test
	public void setHauteurTest() {
		Cercle cercle = new Cercle();
		cercle.setHauteur(7.65);

		assertEquals("Erreur Hauteur", 7.65, cercle.getHauteur(), EPSILON);
		assertEquals(ERREUR_LARGEUR, 7.65, cercle.getLargeur(), EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setHauteurNegativeTest() {
		Cercle cercle = new Cercle();
		cercle.setHauteur(-5);
	}

	@Test
	public void contientTest() {
		Cercle cercle = new Cercle();
		assertTrue("Erreur contient", cercle.contient(new Coordonnees(50, 50)));
		assertFalse("Erreur contient pas", cercle.contient(new Coordonnees(200, 200)));
	}

	@Test
	public void perimetreTest() {
		Cercle cercle = new Cercle();
		assertEquals("Erreur périmetre", 314.159, cercle.perimetre(), EPSILON);
	}
}
