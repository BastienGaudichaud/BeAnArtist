package fr.eseo.poo.projet.artiste.vue.formes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class VueRectangleTest {

	public VueRectangleTest() {
		JFrame fenetre = new JFrame("Rectangle test");
		PanneauDessin panneau = new PanneauDessin(fenetre);
		Rectangle rectangle = new Rectangle();
		Rectangle rectangle2 = new Rectangle(new Coordonnees(5.48, 292.24), 317, 87);
		rectangle2.setCouleurContour(Color.RED);
		Rectangle rectangle3 = new Rectangle(new Coordonnees(389, 127), 555, 54);
		rectangle3.setCouleurContour(Color.BLUE);
		rectangle3.setRempli(true);
		Rectangle rectangle4 = new Rectangle(new Coordonnees(150, 150), 200, 432);
		rectangle4.setRempli(true);
		VueRectangle vueRectangle = new VueRectangle(rectangle);
		VueRectangle vueRectangle2 = new VueRectangle(rectangle2);
		VueRectangle vueRectangle3 = new VueRectangle(rectangle3);
		VueRectangle vueRectangle4 = new VueRectangle(rectangle4);
		panneau.ajouterVueForme(vueRectangle);
		panneau.ajouterVueForme(vueRectangle2);
		panneau.ajouterVueForme(vueRectangle3);
		panneau.ajouterVueForme(vueRectangle4);
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
				new VueRectangleTest();
			}
		});
	}

}
