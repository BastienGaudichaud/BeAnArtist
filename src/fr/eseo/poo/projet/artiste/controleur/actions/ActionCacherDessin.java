package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauBarreOutils;

public class ActionCacherDessin extends AbstractAction{
	
	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Cacher";
	private PanneauBarreOutils barreOutils;
	private PanneauBarreOutils barreDessin;

	public ActionCacherDessin(PanneauBarreOutils barreOutils, PanneauBarreOutils barreDessin) {
		super(NOM_ACTION);
		this.barreOutils = barreOutils;
		this.barreDessin = barreDessin;
	}
	
	/* Hide the drawing menu without disabling the drawing functionality.
	 * (Called by the "Cacher" button on the drawing menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		int state = barreOutils.getPanneauDessin().getParent().getExtendedState();
		barreOutils.getBoutons().clearSelection();
		barreOutils.getPanneauDessin().getParent().remove(barreDessin);
		barreOutils.getPanneauDessin().getParent().pack();
		barreOutils.getPanneauDessin().getParent().setExtendedState(state);
		}
}
