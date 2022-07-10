package fr.eseo.poo.projet.artiste.controleur.outils;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class OutilCrayonTest {
	public OutilCrayonTest() {
		JFrame fenetre = new JFrame("OutilCrayon test");
		PanneauDessin panneau = new PanneauDessin(fenetre);
		OutilCrayon outil = new OutilCrayon();
		panneau.associerOutil(outil);
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
				new OutilCrayonTest();
			}
		});
	}
}
