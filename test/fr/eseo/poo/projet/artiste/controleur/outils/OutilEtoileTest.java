package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauBarreOutils;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class OutilEtoileTest {

	public OutilEtoileTest() {
		JFrame fenetre = new JFrame("OutilEtoile test");
		fenetre.setLayout(new BorderLayout());
		PanneauDessin panneau = new PanneauDessin(fenetre);
		PanneauBarreOutils outils = new PanneauBarreOutils(panneau, null);
		OutilEtoile outil = new OutilEtoile();
		panneau.associerOutil(outil);
		fenetre.add(panneau);
		fenetre.add(outils, BorderLayout.EAST);
		fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new OutilEtoileTest();
			}
		});
	}
}