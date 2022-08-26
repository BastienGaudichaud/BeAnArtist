package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;
import fr.eseo.poo.projet.artiste.xml.EnregistreurSVG;

public class ActionEnregister extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Enregistrer";
	private PanneauDessin panneauDessin;	
	private JFrame fenetre;
	private JFileChooser selecteur;

	public ActionEnregister(PanneauDessin panneauDessin) {
		super(NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}
	
	/* Display a frame to save the drawing and choose is name.
	 * (Called by the "Enregistrer" button on the main menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre = new JFrame("Enregistrer fichier");
		selecteur = new JFileChooser(System.getProperty("user.dir"));
		selecteur.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filtre = new FileNameExtensionFilter("svg uniquement", "svg");
		selecteur.addChoosableFileFilter(filtre);
		fenetre.add(selecteur);
		int etat = selecteur.showSaveDialog(fenetre);
		if (etat == JFileChooser.APPROVE_OPTION) {
			this.action();
		} 
		if (etat == JFileChooser.CANCEL_OPTION) {
			fenetre.dispose();
		}
	}

	/* Save the drawing using EnregistreurSVG class.*/
	protected void action() {
		String nomFichier=selecteur.getSelectedFile().getName();
		if (!nomFichier.endsWith(".svg")) {
			nomFichier += ".svg";
		}
		EnregistreurSVG enregistreur = new EnregistreurSVG();
		try {
			enregistreur.enregistreDessin(nomFichier, panneauDessin.getVueFormes());
			fenetre.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
