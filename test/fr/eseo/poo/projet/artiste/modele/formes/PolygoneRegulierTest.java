package fr.eseo.poo.projet.artiste.modele.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class PolygoneRegulierTest {

	private static final String ERREUR_TAILLE = "Erreur taille";
	private static final String ERREUR_POSITION = "Erreur position";
	private static final String ERREUR_NOMBRE_DE_BRANCHES = "Erreur nombre de branches";
	private static final String ERREUR_ANGLE_BRANCHE = "Erreur angle branche";
	private static final String ERREUR_LARGEUR = "Erreur Largeur";
	private static final double EPSILON = 0.001;

	@Test
	public void constructeurVideTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();

		assertEquals(ERREUR_TAILLE, 100, polygone.getLargeur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), polygone.getPosition());
		assertEquals(ERREUR_NOMBRE_DE_BRANCHES, 3, polygone.getNombreDeCotes());
		assertEquals(ERREUR_ANGLE_BRANCHE, -3 * Math.PI / 4, polygone.getAnglePremierSommet(), EPSILON);
	}

	@Test
	public void constructeurPleinTest() {
		PolygoneRegulier polygone = new PolygoneRegulier(new Coordonnees(5, 5), 10, 3, Math.PI);

		assertEquals(ERREUR_LARGEUR, 10, polygone.getLargeur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, 5), polygone.getPosition());
		assertEquals(ERREUR_NOMBRE_DE_BRANCHES, 3, polygone.getNombreDeCotes());
		assertEquals(ERREUR_ANGLE_BRANCHE, Math.PI, polygone.getAnglePremierSommet(), EPSILON);
	}

	@Test
	public void setLargeurTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		polygone.setLargeur(10.432);
		assertEquals(ERREUR_LARGEUR, 10.432, polygone.getLargeur(), EPSILON);
		assertEquals("Erreur Hauteur", 10.432, polygone.getHauteur(), EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setLargeurNegativeTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		polygone.setLargeur(-5);
	}

	@Test
	public void setHauteurTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		polygone.setHauteur(7.65);

		assertEquals("Erreur Hauteur", 7.65, polygone.getHauteur(), EPSILON);
		assertEquals(ERREUR_LARGEUR, 7.65, polygone.getLargeur(), EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setHauteurNegativeTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		polygone.setHauteur(-5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setNombreBrancheTropPetitTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		polygone.setNombreDeCotes(-4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setNombreBrancheTropGrandTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		polygone.setNombreDeCotes(16);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setAnglePremiereBrancheTropPetitTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		polygone.setAnglePremierSommet(-4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setAnglePremiereBrancheTropGrandTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		polygone.setAnglePremierSommet(4);
	}

	@Test
	public void rempliTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		polygone.setRempli(true);
		assertEquals("Erreur toString Français",
				"[PolygoneRegulier-Rempli] \r\n"
				+ "position : (0,0 , 0,0)\r\n"
				+ "dimension : 100,0 x 100,0\r\n"
				+ "périmètre : 259,81\r\n"
				+ "aire : 3247,6\r\n"
				+ "épaisseur du contour : 4\r\n"
				+ "couleur : R51,V51,B51\r\n"
				+ "couleur remplissage : R51,V51,B51\r\n"
				+ "nombre de côtés :3",
				polygone.toString());
	}

	@Test
	public void perimetreTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		assertEquals("Erreur périmetre", 259.808, polygone.perimetre(), EPSILON);
	}

	@Test
	public void aireTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		assertEquals("Erreur aire", 3247.595, polygone.aire(), EPSILON);
	}

	@Test
	public void contientTest() {
		PolygoneRegulier polygone = new PolygoneRegulier();
		assertTrue("Erreur contient", polygone.contient(new Coordonnees(50, 50)));
		assertFalse("Erreur contient pas", polygone.contient(new Coordonnees()));
	}

	@Test
	public void toStringTest() {
		Locale.setDefault(Locale.FRENCH);
		PolygoneRegulier polygone = new PolygoneRegulier();
		assertEquals("Erreur toString Français",
				"[PolygoneRegulier] \r\n"
				+ "position : (0,0 , 0,0)\r\n"
				+ "dimension : 100,0 x 100,0\r\n"
				+ "périmètre : 259,81\r\n"
				+ "aire : 3247,6\r\n"
				+ "épaisseur du contour : 4\r\n"
				+ "couleur : R51,V51,B51\r\n"
				+ "nombre de côtés :3",
				polygone.toString());
		Locale.setDefault(Locale.ENGLISH);
		assertEquals("Erreur toString Anglais",
				"[PolygoneRegulier] \r\n"
				+ "position : (0.0 , 0.0)\r\n"
				+ "dimension : 100.0 x 100.0\r\n"
				+ "perimeter : 259.81\r\n"
				+ "surface : 3247.6\r\n"
				+ "side thickness : 4\r\n"
				+ "color : R51,G51,B51\r\n"
				+ "number of sides :3",
				polygone.toString());
		Locale.setDefault(Locale.FRENCH);
	}

}
