package fr.eseo.poo.projet.artiste;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import fr.eseo.poo.projet.artiste.controleur.actions.ActionAfficheInfos;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauBarreOutils;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class EtreUnArtiste {

	public EtreUnArtiste() {
		JFrame fenetre = new JFrame("Etre Un Artiste");
		fenetre.setLayout(new BorderLayout());
		fenetre.setUndecorated(true);
		PanneauDessin panneau = new PanneauDessin(fenetre);
		fenetre.add(panneau, BorderLayout.CENTER);
		PanneauBarreOutils outils = new PanneauBarreOutils(panneau, null);
		fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fenetre.add(outils, BorderLayout.EAST);
		ActionAfficheInfos infos = new ActionAfficheInfos();
		infos.actionPerformed(null);
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
		fenetre.setExtendedState(Frame.MAXIMIZED_BOTH);
		fenetre.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new EtreUnArtiste();
			}
		});
	}
}
