package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionAgrandirRetrecir extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Agrandir/Rétrécir";
	private PanneauDessin panneauDessin;

	public ActionAgrandirRetrecir(PanneauDessin panneauDessin) {
		super(NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	/* Maximize or minimize the main frame of the application.
	 * (Called by the "Agrandir/Rétrécir" button on the main menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame fenetre = this.panneauDessin.getParent();
		int state = fenetre.getExtendedState();
		if (state == Frame.MAXIMIZED_BOTH) {
			fenetre.setExtendedState(Frame.NORMAL);
		} else {
			fenetre.setExtendedState(Frame.MAXIMIZED_BOTH);
		}
	}

}
