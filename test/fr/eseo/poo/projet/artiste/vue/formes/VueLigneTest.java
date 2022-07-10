package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Ligne;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class VueLigneTest {

	public VueLigneTest() {
		JFrame fenetre = new JFrame("Ligne test");
		PanneauDessin panneau = new PanneauDessin(fenetre);
		Ligne ligne = new Ligne();
		Ligne ligne2 = new Ligne(new Coordonnees(100, 245), 56, 38);
		ligne2.setCouleurContour(Color.RED);
		Ligne ligne3 = new Ligne(new Coordonnees(389, 127), -231, 52);
		ligne3.setCouleurContour(Color.BLUE);
		VueLigne vueLigne = new VueLigne(ligne);
		VueLigne vueLigne2 = new VueLigne(ligne2);
		VueLigne vueLigne3 = new VueLigne(ligne3);
		panneau.ajouterVueForme(vueLigne);
		panneau.ajouterVueForme(vueLigne2);
		panneau.ajouterVueForme(vueLigne3);
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
				new VueLigneTest();
			}
		});
	}

}
