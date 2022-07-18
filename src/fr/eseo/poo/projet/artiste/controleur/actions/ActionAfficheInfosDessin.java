package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class ActionAfficheInfosDessin extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Infos";
	private static final String U = "<u>";
	private static final String U2 = "</u>";
	private static final String BR = "<br>";
	private JFrame fenetre = new JFrame("Informations dessin");

	public ActionAfficheInfosDessin() {
		super(NOM_ACTION);
	}
	
	/* Display a text in French explaining how the buttons on the drawing menu works.
	 * (Called by the "Infos" button on the drawing menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		fenetre.setAlwaysOnTop(true);
		JPanel panneauInfos = new JPanel();
		panneauInfos.setLayout(new BoxLayout(panneauInfos, BoxLayout.Y_AXIS));
		JLabel label = new JLabel(
				"<html>" 
				+ BR 
				+ "Bienvenue dans la page d’information de l’outil de dessin." + BR
				+ "Sur le panneau d’outils de dessin vous trouvez 10 boutons :" + BR 
				+ BR
				+ U + NOM_ACTION + U2 + " : Ouvre cette page d’information." + BR 
				+ BR
				+ U + "Texte" + U2 + " : Permet de saisir un texte dans une fenêtre et de le " + BR
				+ "positionner sur le dessin en cliquant. Il peut mettre quelques " + BR
				+ "secondes à apparaître." + BR 
				+ BR
				+ U + "Crayon" + U2 + " : Permet de dessiner en pressant le bouton gauche de " + BR 
				+ "la souris et en la déplaçant." + BR
				+ BR 
				+ U + "Ligne" + U2 + " : Permet de tracer une ligne entre le point d’appui et " + BR
				+ "de relâchement de la souris, ou bien une ligne de format par défaut " + BR
				+ "en double-cliquant." + BR
				+ BR 
				+ U + "Rectangle" + U2 + " : Permet de tracer un rectangle s’étendant du point " + BR
				+ "d’appui au point de relâchement de la souris, ou bien un rectangle " + BR
				+ "de format par défaut en double-cliquant." + BR 
				+ BR
				+ U + "Ellipse" + U2 + " : Permet de tracer une ellipse délimitée par le " + BR
				+ "rectangle auquel correspondrait le déplacement de souris entre " + BR
				+ "l’appui et le relâchement, ou bien une ellipse de format par défaut " + BR 
				+ "en double-cliquant." + BR
				+ BR
				+ U + "Cercle" + U2 + " : Permet de tracer un cercle ayant une taille " + BR
				+ "proportionnelle au déplacement de la souris, ou bien un cercle de " + BR 
				+ "format par défaut en double-cliquant." + BR
				+ BR 
				+ U + "Etoile" + U2 + " : Permet de tracer une étoile. Le point d’appui " + BR
				+ "détermine la pointe d’une branche et celui de relâchement détermine " + BR
				+ "le centre. La fenêtre contextuelle permet de choisir le nombre de " + BR
				+ "branches et leur dimension. Il est ausi possible de dessiner une " + BR 
				+ "étoile de format par défaut en double-cliquant." + BR
				+ BR
				+ U + "Polygone Régulier" + U2+ " : Permet de tracer un polygone régulier. Le " + BR
				+ "point d’appui détermine la pointe d’un sommet et celui de " + BR
				+ "relâchement détermine le centre. La fenêtre contextuelle permet de " + BR
				+ "choisir le nombre de côtés. Il est ausi possiblede dessiner un " + BR
				+ "polygone régulier de format par défaut en double-cliquant." + BR
				+ BR
				+ U + "Cacher" + U2 + " : Cache la barre d'outils de dessin tout en" + BR
				+ " permettant de continuer à dessiner."
				+ "</html>"
				);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panneauInfos.add(label);
		fenetre.setLocation(0, 0);
		fenetre.add(panneauInfos);
		fenetre.setMinimumSize(new Dimension(440, 780));
		fenetre.setResizable(false);
		fenetre.pack();
		fenetre.setVisible(true);
	}
}
