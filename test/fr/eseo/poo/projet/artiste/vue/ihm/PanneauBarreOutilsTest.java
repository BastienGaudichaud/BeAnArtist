package fr.eseo.poo.projet.artiste.vue.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class PanneauBarreOutilsTest {

	public PanneauBarreOutilsTest() {
		JFrame fenetre = new JFrame("Barre outil test");
		fenetre.setLayout(new BorderLayout());
		PanneauDessin panneau = new PanneauDessin(fenetre);
		fenetre.add(panneau, BorderLayout.CENTER);
		PanneauBarreOutils outils = new PanneauBarreOutils(panneau, null);
		fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fenetre.add(outils, BorderLayout.EAST);
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new PanneauBarreOutilsTest();
			}
		});
	}
}
