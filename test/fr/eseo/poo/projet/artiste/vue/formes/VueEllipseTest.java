package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Ellipse;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class VueEllipseTest {

	public VueEllipseTest() {
		JFrame fenetre = new JFrame("Ellipse test");
		PanneauDessin panneau = new PanneauDessin(fenetre);
		Ellipse ellipse = new Ellipse();
		Ellipse ellipse2 = new Ellipse(new Coordonnees(100, 245), 56, 38);
		Ellipse ellipse3 = new Ellipse(new Coordonnees(89, 127), 231, 52);
		VueEllipse vueEllipse = new VueEllipse(ellipse);
		VueEllipse vueEllipse2 = new VueEllipse(ellipse2);
		VueEllipse vueEllipse3 = new VueEllipse(ellipse3);
		panneau.ajouterVueForme(vueEllipse);
		panneau.ajouterVueForme(vueEllipse2);
		panneau.ajouterVueForme(vueEllipse3);
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
				new VueEllipseTest();
			}
		});
	}

}
