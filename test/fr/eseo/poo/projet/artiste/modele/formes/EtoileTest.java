package fr.eseo.poo.projet.artiste.modele.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;

public class EtoileTest {

	private static final String ERREUR_TAILLE = "Erreur taille";
	private static final String ERREUR_POSITION = "Erreur position";
	private static final String ERREUR_NOMBRE_DE_BRANCHES = "Erreur nombre de branches";
	private static final String ERREUR_LONGUEUR_BRANCHE = "Erreur longueur branche";
	private static final String ERREUR_ANGLE_BRANCHE = "Erreur angle branche";
	private static final String ERREUR_LARGEUR = "Erreur Largeur";
	private static final double EPSILON = 0.001;

	@Test
	public void constructeurVideTest() {
		Etoile etoile = new Etoile();

		assertEquals(ERREUR_TAILLE, 100, etoile.getLargeur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(), etoile.getPosition());
		assertEquals(ERREUR_NOMBRE_DE_BRANCHES, 5, etoile.getNombreBranches());
		assertEquals(ERREUR_ANGLE_BRANCHE, 0, etoile.getAnglePremiereBranche(), EPSILON);
		assertEquals(ERREUR_LONGUEUR_BRANCHE, 0.5, etoile.getLongueurBranche(), EPSILON);
	}

	@Test
	public void constructeurPleinTest() {
		Etoile etoile = new Etoile(new Coordonnees(5, 5), 10, 3, Math.PI, 0.7);

		assertEquals(ERREUR_LARGEUR, 10, etoile.getLargeur(), EPSILON);
		assertEquals(ERREUR_POSITION, new Coordonnees(5, 5), etoile.getPosition());
		assertEquals(ERREUR_NOMBRE_DE_BRANCHES, 3, etoile.getNombreBranches());
		assertEquals(ERREUR_ANGLE_BRANCHE, Math.PI, etoile.getAnglePremiereBranche(), EPSILON);
		assertEquals(ERREUR_LONGUEUR_BRANCHE, 0.7, etoile.getLongueurBranche(), EPSILON);
	}

	@Test
	public void rempliTest() {
		Etoile etoile = new Etoile();
		etoile.setRempli(true);
		assertEquals("Erreur toString Français",
				"[Etoile-Rempli] \r\n"
				+ "position : (0,0 , 0,0)\r\n"
				+ "dimension : 100,0 x 100,0\r\n"
				+ "périmètre : 332,03\r\n"
				+ "aire : 3673,66\r\n"
				+ "épaisseur du contour : 4\r\n"
				+ "couleur : R51,V51,B51\r\n"
				+ "couleur remplissage : R51,V51,B51\r\n"
				+ "nombre de branches :5\r\n"
				+ "longueur proportionelle des branches : 0.5",
				etoile.toString());
	}

	@Test
	public void setLargeurTest() {
		Etoile etoile = new Etoile();
		etoile.setLargeur(10.432);
		assertEquals(ERREUR_LARGEUR, 10.432, etoile.getLargeur(), EPSILON);
		assertEquals("Erreur Hauteur", 10.432, etoile.getHauteur(), EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setLargeurNegativeTest() {
		Etoile etoile = new Etoile();
		etoile.setLargeur(-5);
	}

	@Test
	public void setHauteurTest() {
		Etoile etoile = new Etoile();
		etoile.setHauteur(7.65);

		assertEquals("Erreur Hauteur", 7.65, etoile.getHauteur(), EPSILON);
		assertEquals(ERREUR_LARGEUR, 7.65, etoile.getLargeur(), EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setHauteurNegativeTest() {
		Etoile etoile = new Etoile();
		etoile.setHauteur(-5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setNombreBrancheTropPetitTest() {
		Etoile etoile = new Etoile();
		etoile.setNombreBranches(-4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setNombreBrancheTropGrandTest() {
		Etoile etoile = new Etoile();
		etoile.setNombreBranches(16);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setLongueurBrancheTropPetitTest() {
		Etoile etoile = new Etoile();
		etoile.setLongueurBranche(-4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setLongueurBrancheTropGrandTest() {
		Etoile etoile = new Etoile();
		etoile.setLongueurBranche(4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setAnglePremiereBrancheTropPetitTest() {
		Etoile etoile = new Etoile();
		etoile.setAnglePremiereBranche(-4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setAnglePremiereBrancheTropGrandTest() {
		Etoile etoile = new Etoile();
		etoile.setAnglePremiereBranche(4);
	}

	@Test
	public void perimetreTest() {
		Etoile etoile = new Etoile();
		assertEquals("Erreur périmetre", 332.033, etoile.perimetre(), EPSILON);
	}

	@Test
	public void aireTest() {
		Etoile etoile = new Etoile();
		assertEquals("Erreur aire", 3673.658, etoile.aire(), EPSILON);
	}

	@Test
	public void contientTest() {
		Etoile etoile = new Etoile();
		assertTrue("Erreur contient", etoile.contient(new Coordonnees(50, 50)));
		assertFalse("Erreur contient pas", etoile.contient(new Coordonnees()));
	}

	@Test
	public void toStringTest() {
		Locale.setDefault(Locale.FRENCH);
		Etoile etoile = new Etoile();
		assertEquals("Erreur toString Français",
				"[Etoile] \r\n"
				+ "position : (0,0 , 0,0)\r\n"
				+ "dimension : 100,0 x 100,0\r\n"
				+ "périmètre : 332,03\r\n"
				+ "aire : 3673,66\r\n"
				+ "épaisseur du contour : 4\r\n"
				+ "couleur : R51,V51,B51\r\n"
				+ "nombre de branches :5\r\n"
				+ "longueur proportionelle des branches : 0.5",
				etoile.toString());
		Locale.setDefault(Locale.ENGLISH);
		assertEquals("Erreur toString Anglais",
				"[Etoile] \r\n"
				+ "position : (0.0 , 0.0)\r\n"
				+ "dimension : 100.0 x 100.0\r\n"
				+ "perimeter : 332.03\r\n"
				+ "surface : 3673.66\r\n"
				+ "side thickness : 4\r\n"
				+ "color : R51,G51,B51\r\n"
				+ "number of branches :5\r\n"
				+ "relative size of branches : 0.5",
				etoile.toString());
		Locale.setDefault(Locale.FRENCH);
	}

}
