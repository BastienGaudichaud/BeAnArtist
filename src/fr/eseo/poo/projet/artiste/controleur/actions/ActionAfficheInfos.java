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

public class ActionAfficheInfos extends AbstractAction {

	private static final String U = "<u>";
	private static final String U2 = "</u>";
	private static final String BR = "<br>";
	public static final String NOM_ACTION = "Infos";
	private static final long serialVersionUID = 4753265740890908987L;
	private JFrame fenetre = new JFrame("Informations");

	public ActionAfficheInfos() {
		super(NOM_ACTION);
	}

	/* Display a text in French explaining how the buttons on the main menu works.
	 * (Called by the "Infos" button on the main menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		fenetre.setAlwaysOnTop(true);
		JPanel panneauInfos = new JPanel();
		panneauInfos.setLayout(new BoxLayout(panneauInfos, BoxLayout.Y_AXIS));
		JLabel label = new JLabel(
				"<html>" + BR 
				+ "Bienvenue dans la page d'information de l'application Be An Artist." + BR
				+ "Sur le panneau d'outils principal vous trouvez 10 boutons :" + BR 
				+ BR
				+ U + NOM_ACTION + U2 + " : Ouvre cette page d’information." + BR 
				+ BR
				+ U + "Dessiner" + U2 + " : Ouvre le panneau d’outils de dessin." + BR
				+ BR
				+ U + "Informations/Déplacer" + U2 + " : Permet de sélectionner une forme avec " + BR
				+ "un clic gauche, puis de la déplacer en bougeant la souris. Un clic" + BR
				+ "droit affiche les informations de la forme et la désélectionne. Si " + BR
				+ "on clique sur plusieurs formes superposées, une fenêtre apparaît " + BR
				+ "pour choisir celle à sélectionner." + BR
				+ BR
				+ U + "Modifier forme" + U2 + " : Permet de sélectionner une forme avec un " + BR
				+ "clic gauche. Un clic droit ensuite ouvre une succession de fenêtres " + BR
				+ "contextuelles qui permettent de modifier la forme. Tout d'abord il " + BR
				+ "est proposé de l'effacer. Dans le cas où l’on refuse de l’effacer, " + BR
				+ "il est ensuite demandé si l'on veut que la forme soit remplie " + BR
				+ "(dépendamment de son état actuel). Puis on a la possibilité de " + BR
				+ "changer la couleur de son contour. Ensuite si l’on a demandé à ce  " + BR
				+ "que la forme soit remplie, on peut choisir la couleur du remplissage. " + BR
				+ "Il est ensuite possible de changer l'épaisseur du contour de la forme. " + BR
				+ "De même, on peut modifier sa taille. Enfin on a la possiblité de " + BR
				+ "mettre la forme au premier plan du dessin." + BR
				+ BR
				+ U + "Couleur/Remplissage" + U2 + " : Permet de choisir la couleur de " + BR
				+ "contour, la couleur de remplissage, l'épaisseur de contourainsi que " + BR
				+ "l'état de remplissage des formes que l'on dessinera ensuite. Pour " + BR 
				+ "finir, il est possible de changer la couleur du fond." + BR
				+ BR
				+ U + "Effacer Tout" + U2 + " : Permet d'effacer la totalité des formes " + BR
				+ "dessinées. Attention cette action est définitive." + BR 
				+ BR
				+ U + "Ouvrir" + U2 + " : Permet d'ouvrir un fichier .svg correspondant à " + BR
				+ "l'application." + BR 
				+ BR
				+ U + "Enregistrer" + U2 + " : Permet d'enregister un fichier .svg dans le " + BR 
				+ "dossier actuel. Attention si un fichier identique existe, il sera " + BR
				+ "écrase." + BR
				+ BR
				+ U + "Agrandir/Rétrécir" + U2 + " : Redimensionne la zone de dessin." + BR 
				+ BR
				+ U + "Fermer" + U2 + " : Ferme l'application." 
				+ "</html>"
				);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		panneauInfos.add(label);
		fenetre.setLocation(0, 0);
		fenetre.add(panneauInfos);
		fenetre.setMinimumSize(new Dimension(440, 760));
		fenetre.setResizable(false);
		fenetre.pack();
		fenetre.setVisible(true);
	}
}
