package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Cercle;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class VueCercleTest {
	public VueCercleTest() {
		JFrame fenetre = new JFrame("Cercle test");
		PanneauDessin panneau = new PanneauDessin(fenetre);
		Cercle cercle = new Cercle();
		Cercle cercle2 = new Cercle(new Coordonnees(100, 245), 56);
		Cercle cercle3 = new Cercle(new Coordonnees(89, 127), 131);
		VueCercle vueCercle = new VueCercle(cercle);
		VueCercle vueCercle2 = new VueCercle(cercle2);
		VueCercle vueCercle3 = new VueCercle(cercle3);
		panneau.ajouterVueForme(vueCercle);
		panneau.ajouterVueForme(vueCercle2);
		panneau.ajouterVueForme(vueCercle3);
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
				new VueCercleTest();
			}
		});
	}

}
