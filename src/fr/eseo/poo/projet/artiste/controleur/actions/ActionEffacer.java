package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionEffacer extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Effacer Tout";
	private PanneauDessin panneauDessin;

	public ActionEffacer(PanneauDessin panneauDessin) {
		super(NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}

	/* Delete all the drawings.
	 * (Called by the "Effacer Tout" button on the main menu.)*/
	@Override
	public void actionPerformed(ActionEvent event) {
		int choix = JOptionPane.showConfirmDialog(panneauDessin, "Voulez vous supprimer toutes les formes?", NOM_ACTION,
				JOptionPane.YES_NO_OPTION);
		if (choix == JOptionPane.YES_OPTION) {
			int choix2 = JOptionPane.showConfirmDialog(panneauDessin, "Cette action est définitive, êtes vous sûr ?", "Attention", JOptionPane.OK_CANCEL_OPTION);
			if (choix2 == JOptionPane.OK_OPTION) {
				this.panneauDessin.getVueFormes().clear();
				this.panneauDessin.repaint();
			}
		}
	}

}
