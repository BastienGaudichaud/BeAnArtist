package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirCouleurEtRemplissage extends AbstractAction {

	public static final String NOM_ACTION = "Couleur/Remplissage";
	public static final String NOM_REMPLISSAGE = "Remplissage";
	public static final String NOM_COULEUR_CONTOUR = "Couleur du Contour";
	public static final String NOM_COULEUR_REMPLISSAGE = "Couleur de Remplissage";
	public static final String NOM_EPAISSEUR_TRAIT = "Epaisseur du Contour";
	public static final String NOM_COULEUR_FOND = "Couleur du Fond";
	private static final long serialVersionUID = 8198531441756109065L;
	private PanneauDessin panneauDessin;

	public ActionChoisirCouleurEtRemplissage(PanneauDessin panneauDessin) {
		super(NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}

	/* Make the user choose all the colors and fill effects for the next drawings.
	 * (Called by the "Couleur/Remplissage" button on the main menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(panneauDessin, NOM_COULEUR_CONTOUR);
		Color couleur = JColorChooser.showDialog(panneauDessin, NOM_COULEUR_CONTOUR,
				panneauDessin.getCouleurContourCourante());
		if (couleur != null) {
			panneauDessin.setCouleurContourCourante(couleur);
		}
		
		JOptionPane.showMessageDialog(panneauDessin, NOM_COULEUR_REMPLISSAGE);
		couleur = JColorChooser.showDialog(panneauDessin, NOM_COULEUR_REMPLISSAGE,
				panneauDessin.getCouleurRemplissageCourante());
		if (couleur != null) {
			panneauDessin.setCouleurRemplissageCourante(couleur);
		}
		
		JOptionPane.showMessageDialog(panneauDessin, NOM_EPAISSEUR_TRAIT);
		int epaisseur = this.panneauDessin.getEpaisseurCourante();
		try {
			epaisseur = Integer.parseInt((String) JOptionPane.showInputDialog(this.panneauDessin, "Choisir l'épaisseur de votre choix.", "choix épaisseur", JOptionPane.QUESTION_MESSAGE, null, null, epaisseur));
			panneauDessin.setEpaisseurCourante(epaisseur);
		} catch (NumberFormatException | HeadlessException e1) {
			JOptionPane.showMessageDialog(panneauDessin, "taille non reconnue");
		}
		
		int remplissage = JOptionPane.showConfirmDialog(panneauDessin, "Voulez vous activer le remplissage",
				NOM_REMPLISSAGE, JOptionPane.YES_NO_OPTION);
		if (remplissage == JOptionPane.YES_OPTION) {
			this.panneauDessin.setModeRemplissage(true);
		}
		if (remplissage == JOptionPane.NO_OPTION) {
			this.panneauDessin.setModeRemplissage(false);
		}
		
		JOptionPane.showMessageDialog(panneauDessin, NOM_COULEUR_FOND);
		couleur = JColorChooser.showDialog(panneauDessin, NOM_COULEUR_FOND, panneauDessin.getCouleurFondCourante());
		if (couleur != null) {
			panneauDessin.setCouleurFondCourante(couleur);
		}

	}

}
