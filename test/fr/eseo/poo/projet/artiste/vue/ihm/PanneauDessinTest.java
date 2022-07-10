package fr.eseo.poo.projet.artiste.vue.ihm;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class PanneauDessinTest {
	public PanneauDessinTest() {
		testConstructeurParDefaut();
		testConstructeur();
	}

	private void testConstructeur() {
		JFrame fenetre = new JFrame("Blues du Businessman");
		JPanel panneau = new PanneauDessin(600, 360, Color.LIGHT_GRAY);
		fenetre.add(panneau);
		fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fenetre.pack();
		fenetre.setVisible(true);
	}

	private void testConstructeurParDefaut() {
		JFrame fenetre = new JFrame("Etre un Artiste");
		JPanel panneau = new PanneauDessin(fenetre);
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
				new PanneauDessinTest();
			}
		});
	}
}
