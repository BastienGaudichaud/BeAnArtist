package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Etoile;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class VueEtoileTest {

	public VueEtoileTest() {
		JFrame fenetre = new JFrame("Etoile test");
		PanneauDessin panneau = new PanneauDessin(fenetre);
		Etoile etoile = new Etoile();
		Etoile etoile2 = new Etoile(new Coordonnees(5.48, 292.24), 317, 8, Math.PI / 2, 0.7);
		etoile2.setCouleurContour(Color.RED);
		Etoile etoile3 = new Etoile(new Coordonnees(389, 127), 555, 5, Math.PI / 3, 0.4);
		etoile3.setCouleurContour(Color.BLUE);
		etoile3.setRempli(true);
		Etoile etoile4 = new Etoile(new Coordonnees(150, 150), 200, 14, 0, 0.8);
		VueEtoile vueEtoile = new VueEtoile(etoile);
		VueEtoile vueEtoile2 = new VueEtoile(etoile2);
		VueEtoile vueEtoile3 = new VueEtoile(etoile3);
		VueEtoile vueEtoile4 = new VueEtoile(etoile4);
		panneau.ajouterVueForme(vueEtoile);
		panneau.ajouterVueForme(vueEtoile2);
		panneau.ajouterVueForme(vueEtoile3);
		panneau.ajouterVueForme(vueEtoile4);
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
				new VueEtoileTest();
			}
		});
	}

}
