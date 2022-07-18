package fr.eseo.poo.projet.artiste.controleur.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.eseo.poo.projet.artiste.vue.formes.VueForme;
import fr.eseo.poo.projet.artiste.vue.ihm.PanneauDessin;
import fr.eseo.poo.projet.artiste.xml.LecteurSVG;

public class ActionOuvrir extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Ouvrir";
	private PanneauDessin panneauDessin;	
	private JFrame fenetre;
	private JFileChooser selecteur;
	private JFrame fenetre2;
	
	public ActionOuvrir(PanneauDessin panneauDessin) {
		super(NOM_ACTION);
		this.panneauDessin = panneauDessin;
	}

	/* Open a file selector.
	 * (Called by the "Ouvrir" button on the main menu.) */
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetre = new JFrame("Ouvrir fichier");
		selecteur = new JFileChooser(System.getProperty("user.dir"));
	    selecteur.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filtre = new FileNameExtensionFilter("svg uniquement", "svg");
		selecteur.addChoosableFileFilter(filtre);
		fenetre.add(selecteur);
		int etat = selecteur.showOpenDialog(fenetre);
		if (etat == JFileChooser.APPROVE_OPTION) {
			this.action();
		} 
		if (etat == JFileChooser.CANCEL_OPTION) {
			fenetre.dispose();
		}
	}
	
	public void action() {
		File fichier=selecteur.getSelectedFile();
		LecteurSVG lecteur = new LecteurSVG();
		List<VueForme> dessin;
		try {
			panneauDessin.setCouleurFondCourante(lecteur.getFond(fichier));
			dessin = lecteur.lisDessin(fichier);
			panneauDessin.getVueFormes().clear();
			for (VueForme vueForme : dessin) {
				panneauDessin.ajouterVueForme(vueForme);
			}
			panneauDessin.repaint();
			fenetre.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			if(fenetre2!=null) {
				fenetre2.dispose();
			}
			fenetre2 = new JFrame();
			JLabel labelTexte = new JLabel("Fichier introuvable");
			labelTexte.setHorizontalAlignment(SwingConstants.CENTER);
			fenetre2.add(labelTexte);
			fenetre2.pack();
			fenetre2.setSize(300, 100);
			fenetre2.setLocationRelativeTo(null);
			fenetre2.setVisible(true);
		}
	}

}