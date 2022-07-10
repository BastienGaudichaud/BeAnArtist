package fr.eseo.poo.projet.artiste.controleur.outils;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class OutilTexteTest {
	public OutilTexteTest() {
		JFrame fenetre = new JFrame("OutilTexte test");
		PanneauDessin panneau = new PanneauDessin(fenetre);
		OutilTexte outil = new OutilTexte();
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
				new OutilTexteTest();
			}
		});
	}
}
