package fr.eseo.poo.projet.artiste.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class CoordonneesTest {

	private static final double EPSILON = 0.001;

	@Test
	public void videTest() {
		Coordonnees coordonnees = new Coordonnees();
		assertNotEquals("Erreur equals vide", coordonnees, null);
	}

	@Test
	public void constructeurVideTest() {
		Coordonnees coordonnees = new Coordonnees();

		assertEquals("Erreur abscisse", 0, coordonnees.getAbscisse(), EPSILON);
		assertEquals("Erreur ordonnees", 0, coordonnees.getOrdonnee(), EPSILON);
	}

	@Test
	public void constructeurPleinTest() {
		Coordonnees coordonnees = new Coordonnees(8.3, -7.2);

		assertEquals("Erreur abscisse", 8.3, coordonnees.getAbscisse(), EPSILON);
		assertEquals("Erreur ordonnees", -7.2, coordonnees.getOrdonnee(), EPSILON);
	}

	@Test
	public void mutateurTest() {
		Coordonnees coordonnees = new Coordonnees();
		coordonnees.setAbscisse(5.4);
		coordonnees.setOrdonnee(-9.7);
		assertEquals("Erreur mutateur", new Coordonnees(5.4, -9.7), coordonnees);
	}

	@Test
	public void angleVersTest() {
		Coordonnees coordonnees = new Coordonnees(0, 0);
		Coordonnees coordonneesBis = new Coordonnees(10, 10);
		double anglebis = coordonnees.angleVers(coordonneesBis);

		assertEquals("Erreur Angle", Math.PI / 4, anglebis, EPSILON);
	}

	@Test
	public void distanceVersTest() {
		Coordonnees coordonnees = new Coordonnees(0, 0);
		Coordonnees coordonneesBis = new Coordonnees(10, 10);
		double distancebis = coordonnees.distanceVers(coordonneesBis);

		assertEquals("Erreur distance", Math.sqrt(200), distancebis, EPSILON);
	}

	@Test
	public void deplacerDeTest() {
		Coordonnees coordonnees = new Coordonnees(0, 0);
		coordonnees.deplacerDe(10, 10);

		assertEquals("Erreur deplacerDe", new Coordonnees(10, 10), coordonnees);
	}

	@Test
	public void deplacerVersTest() {
		Coordonnees coordonnees = new Coordonnees(0, 0);
		coordonnees.deplacerVers(10, 10);

		assertEquals("Erreur deplacerVers", new Coordonnees(10, 10), coordonnees);
	}

	@Test
	public void classeDifferenteTest() {
		Coordonnees coordonnees = new Coordonnees();
		String string = "test";
		coordonnees.hashCode();
		assertNotEquals("Erreur equals class", coordonnees, string);
	}

	@Test
	public void abscisseDifferenteTest() {
		Coordonnees coordonnees = new Coordonnees(0, 0);
		Coordonnees coordonneesBis = new Coordonnees(5, 0);
		assertNotEquals("Erreur equals different", coordonnees, coordonneesBis);
	}

	@Test
	public void ordonneeDifferenteTest() {
		Coordonnees coordonnees = new Coordonnees(0, 0);
		Coordonnees coordonneesBis = new Coordonnees(0, 5);
		assertNotEquals("Erreur equals different", coordonnees, coordonneesBis);
	}

	@Test
	public void identiqueTest() {
		Coordonnees coordonnees = new Coordonnees();
		assertEquals("Erreur equals identique", coordonnees, coordonnees);
	}

	@Test
	public void toStringTest() {
		Coordonnees coordonnees = new Coordonnees();
		assertEquals("Erreur toString()", "(0,0 , 0,0)", coordonnees.toString());
	}
}
