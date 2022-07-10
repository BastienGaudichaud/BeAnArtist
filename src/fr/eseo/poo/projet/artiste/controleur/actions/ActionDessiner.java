package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauBarreOutils;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionDessiner extends AbstractAction {

	public static final String NOM_ACTION = "Dessiner";
	private static final long serialVersionUID = -7969544781813047991L;
	private PanneauDessin panneauDessin;
	private PanneauBarreOutils panneauOutilsDessin;
	private PanneauBarreOutils barreOutils;

	public ActionDessiner(PanneauDessin panneauDessin, PanneauBarreOutils barreOutils) {
		super(NOM_ACTION);
		this.panneauDessin = panneauDessin;
		this.barreOutils = barreOutils;
	}
	
	/* Display the drawing menu.
	 * (Called by the "Dessiner" button on the main menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		int state = this.panneauDessin.getParent().getExtendedState();
		this.panneauOutilsDessin = new PanneauBarreOutils(panneauDessin, barreOutils);
		this.panneauDessin.getParent().add(panneauOutilsDessin, BorderLayout.WEST);
		this.panneauDessin.getParent().pack();
		this.panneauDessin.getParent().setExtendedState(state);

	}

	public PanneauBarreOutils getPanneauOutilsDessin() {
		return panneauOutilsDessin;
	}
}
