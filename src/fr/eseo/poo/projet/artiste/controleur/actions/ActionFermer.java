package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionFermer extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Fermer";
	private PanneauDessin panneauDessin;

	public ActionFermer(PanneauDessin panneauDessin) {
		super(NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}

	/* Close the Application.
	 * (Called by the "Fermer" button on the main menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame fenetre = this.panneauDessin.getParent();
		fenetre.dispatchEvent(new WindowEvent(fenetre, WindowEvent.WINDOW_CLOSING));
	}

}
