package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauBarreOutils;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionTest {

	public ActionTest() {
		JFrame fenetre = new JFrame("Action test");
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
				new ActionTest();
			}
		});
	}

}
