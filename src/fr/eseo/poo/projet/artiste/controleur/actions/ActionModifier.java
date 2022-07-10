package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.eseo.poo.projet.artiste.controleur.outils.OutilModifier;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauBarreOutils;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionModifier extends AbstractAction {

	public static final String NOM_ACTION = "Modifier forme";
	private static final long serialVersionUID = 1822369817001196604L;
	private PanneauDessin panneauDessin;
	private PanneauBarreOutils parent;

	public ActionModifier(PanneauDessin panneauDessin, PanneauBarreOutils parent) {
		super(NOM_ACTION);
		this.panneauDessin = panneauDessin;
		this.parent = parent;
	}

	/* Associate a tool to edit the drawings.
	 * (See OutilModifier.)
	 * (Called by the "Modifier forme" button on the main menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.parent.getDessin().getPanneauOutilsDessin() != null) {
			int state = this.panneauDessin.getParent().getExtendedState();
			this.panneauDessin.getParent().remove(this.parent.getDessin().getPanneauOutilsDessin());
			this.panneauDessin.getParent().pack();
			this.panneauDessin.getParent().setExtendedState(state);
		}
		this.panneauDessin.associerOutil(new OutilModifier());
	}

}
