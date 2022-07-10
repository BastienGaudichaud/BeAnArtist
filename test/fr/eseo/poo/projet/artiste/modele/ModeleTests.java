package fr.eseo.poo.projet.artiste.modele;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.eseo.poo.projet.artiste.modele.formes.CercleTest;
import fr.eseo.poo.projet.artiste.modele.formes.EllipseTest;
import fr.eseo.poo.projet.artiste.modele.formes.EtoileTest;
import fr.eseo.poo.projet.artiste.modele.formes.EtoileTestParameterized;
import fr.eseo.poo.projet.artiste.modele.formes.LigneTest;
import fr.eseo.poo.projet.artiste.modele.formes.PolygoneRegulierTest;
import fr.eseo.poo.projet.artiste.modele.formes.RectangleTest;
import fr.eseo.poo.projet.artiste.modele.formes.TexteTest;

@RunWith(Suite.class)
@SuiteClasses({ CoordonneesTest.class,
				LigneTest.class, 
				RectangleTest.class, 
				EllipseTest.class, 
				CercleTest.class, 
				EtoileTest.class,
				EtoileTestParameterized.class, 
				PolygoneRegulierTest.class, 
				TexteTest.class })
public class ModeleTests {

}
