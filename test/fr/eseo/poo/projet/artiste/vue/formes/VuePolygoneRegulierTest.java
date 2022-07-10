package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.PolygoneRegulier;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class VuePolygoneRegulierTest {

	public VuePolygoneRegulierTest() {
		JFrame fenetre = new JFrame("PolygoneRegulier test");
		PanneauDessin panneau = new PanneauDessin(fenetre);
		PolygoneRegulier polygone = new PolygoneRegulier();
		PolygoneRegulier polygone2 = new PolygoneRegulier(new Coordonnees(5.48, 292.24), 317, 8, Math.PI / 2);
		polygone2.setCouleurContour(Color.RED);
		PolygoneRegulier polygone3 = new PolygoneRegulier(new Coordonnees(389, 127), 555, 5, Math.PI / 3);
		polygone3.setCouleurContour(Color.BLUE);
		polygone3.setRempli(true);
		PolygoneRegulier polygone4 = new PolygoneRegulier(new Coordonnees(150, 150), 200, 4, -3 * Math.PI / 4);
		VuePolygoneRegulier vuePolygoneRegulier = new VuePolygoneRegulier(polygone);
		VuePolygoneRegulier vuePolygoneRegulier2 = new VuePolygoneRegulier(polygone2);
		VuePolygoneRegulier vuePolygoneRegulier3 = new VuePolygoneRegulier(polygone3);
		VuePolygoneRegulier vuePolygoneRegulier4 = new VuePolygoneRegulier(polygone4);
		panneau.ajouterVueForme(vuePolygoneRegulier);
		panneau.ajouterVueForme(vuePolygoneRegulier2);
		panneau.ajouterVueForme(vuePolygoneRegulier3);
		panneau.ajouterVueForme(vuePolygoneRegulier4);
		panneau.setBorder(BorderFactory.createLineBorder(Color.black));
		fenetre.add(panneau);
		fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new VuePolygoneRegulierTest();
			}
		});
	}
}
