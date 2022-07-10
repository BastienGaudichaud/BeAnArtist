package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Forme;
import fr.eseo.poo.projet.artiste.modele.formes.Ligne;
import fr.eseo.poo.projet.artiste.modele.formes.Texte;
import fr.eseo.poo.projet.artiste.modele.formes.Trace;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;

public class OutilModifier extends Outil {

	private static final long serialVersionUID = 6274584872649754313L;
	private static final String TAILLE_NON_RECONNUE = "taille non reconnue";
	private VueForme vueFormeSelectionnee;
	
	/* Launch the appropriate action when a button is clicked. */
	@Override
	public void mouseClicked(MouseEvent event) {
		this.setDebut(new Coordonnees(event.getX(), event.getY()));
		if (event.getButton() == MouseEvent.BUTTON1) {
			clicGauche(event);
		}
		else {
			if (event.getButton() == MouseEvent.BUTTON3 && this.vueFormeSelectionnee != null) {
				clicDroit();
			}
		}
	}
	
	/* If it is a left click, it launch the object selection process*/
	private void clicGauche(MouseEvent event) {
		List<VueForme> bonneForme = new ArrayList<>();
		Coordonnees click = new Coordonnees(event.getX(), event.getY());
		List<VueForme> vueFormes = getPanneauDessin().getVueFormes();
		int taille = vueFormes.size();
		for (int i = taille - 1; i >= 0; i--) {
			VueForme vueForme = vueFormes.get(i);
			Forme forme = vueForme.getForme();
			if (forme.contient(click)) {
				bonneForme.add(vueForme);
			}
		}
		if (!bonneForme.isEmpty()) {
			if (bonneForme.size() > 1) {
				JFrame fenetre = new JFrame("choisir forme");
				SpinnerListModel model = new SpinnerListModel(bonneForme);
				JSpinner selectionneur = new JSpinner(model);
				update(fenetre, selectionneur);
				JButton ok = new JButton("ok");
				ok(fenetre, selectionneur, ok);
				fenetre.setLayout(new FlowLayout());
				fenetre.add(selectionneur);
				fenetre.add(ok);
				fenetre.pack();
				fenetre.setVisible(true);
			} else {
				this.vueFormeSelectionnee = bonneForme.get(0);
			}
		} else {
			this.vueFormeSelectionnee = null;
		}
	}
	
	private void update(JFrame fenetre, JSpinner selectionneur) {
		DocumentListener listener = new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				fenetre.pack();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				fenetre.pack();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				fenetre.pack();
				
			}
		};
		JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)selectionneur.getEditor();
		spinnerEditor.getTextField().getDocument().addDocumentListener(listener);
	}

	private void ok(JFrame fenetre, JSpinner selectionneur, JButton ok) {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vueFormeSelectionnee = (VueForme) selectionneur.getValue();	
				fenetre.dispose();
			}
		};
		ok.addActionListener(listener);
	}

	/* If it is a right click, it launch the modification process. */
	private void clicDroit() {
		int choix = JOptionPane.showConfirmDialog(getPanneauDessin(), "Voulez vous supprimer la forme ?", "Effacer",
				JOptionPane.YES_NO_OPTION);
		if (choix == JOptionPane.YES_OPTION) {
			getPanneauDessin().getVueFormes().remove(this.vueFormeSelectionnee);
			getPanneauDessin().repaint();
		} else {
			modification();
		}
	}

	/* Main body of modification, to let the user choose what needed to be modified. */
	private void modification() {
		if (vueFormeSelectionnee.getForme().getClass() != Trace.class &&
			vueFormeSelectionnee.getForme().getClass() != Texte.class &&
			vueFormeSelectionnee.getForme().getClass() != Ligne.class) {
			int choix = JOptionPane.showConfirmDialog(getPanneauDessin(),
					"Voulez vous remplir la forme ?", "Changer remplissage",
					JOptionPane.YES_NO_OPTION);
			if (choix == JOptionPane.YES_OPTION) {
				vueFormeSelectionnee.getForme().setRempli(true);
				getPanneauDessin().repaint();
			} 
			else {
				vueFormeSelectionnee.getForme().setRempli(false);
				getPanneauDessin().repaint();
			}
		}
		int choix2 = JOptionPane.showConfirmDialog(getPanneauDessin(),
				"Voulez vous changer la couleur de la forme ?", "Changer couleur", JOptionPane.YES_NO_OPTION);
		if (choix2 == JOptionPane.YES_OPTION) {
			changerCouleur();
		}
		if (vueFormeSelectionnee.getForme().getClass() != Trace.class) {
			int choix3 = JOptionPane.showConfirmDialog(getPanneauDessin(),
					"Voulez vous changer la taille de la forme ?", "Changer taille", JOptionPane.YES_NO_OPTION);
			if (choix3 == JOptionPane.YES_OPTION) {
				changerTaille();
			} 
		}
		if (vueFormeSelectionnee.getForme().getClass() != Texte.class) {
			int choix4 = JOptionPane.showConfirmDialog(getPanneauDessin(), 
					"Voulez vous changer l'épaisseur du contour ?", "Changer contour", JOptionPane.YES_NO_OPTION);
			if (choix4 == JOptionPane.YES_OPTION) {
				changerEpaisseur();
			} 
		}
		int choix5 = JOptionPane.showConfirmDialog(getPanneauDessin(), 
				"Voulez vous mettre la forme au premier plan ?", "Mettre à l'avant", JOptionPane.YES_NO_OPTION);
		if (choix5 == JOptionPane.YES_OPTION) {
			avancer();
		} 
	}

	/* Modify the colors. */
	private void changerCouleur() {
		Color couleur = JColorChooser.showDialog(getPanneauDessin(), "Choisir couleur contour",
				getPanneauDessin().getCouleurContourCourante());
		if (couleur != null) {
			vueFormeSelectionnee.getForme().setCouleurContour(couleur);
			getPanneauDessin().repaint();
		}
		if (vueFormeSelectionnee.getForme().estRempli()) {
			couleur = JColorChooser.showDialog(getPanneauDessin(), "Choisir couleur remplissage",
					getPanneauDessin().getCouleurRemplissageCourante());
			if (couleur != null) {
				vueFormeSelectionnee.getForme().setCouleurRemplissage(couleur);
				getPanneauDessin().repaint();
			}
		}
	}

	/* Modify the size. */ 
	private void changerTaille() {
		Forme forme = vueFormeSelectionnee.getForme();
		boolean tailleUnique = forme.getLargeur() == forme.getHauteur();
		if (forme.getClass() == Texte.class) {
			Texte texte = (Texte)forme;
			int taille = texte.getTailePolice();
			try {
				taille = Integer.parseInt((String) JOptionPane.showInputDialog(getPanneauDessin(), "Choisir la taille de votre choix.", "choix taille", JOptionPane.QUESTION_MESSAGE, null, null, taille));
				texte.setPolice(taille);
			} catch (NumberFormatException | HeadlessException e) {
				JOptionPane.showMessageDialog(getPanneauDessin(), TAILLE_NON_RECONNUE);
			}
		}
		else {
			if (tailleUnique) {
				double taille = forme.getLargeur();
				try {
					taille = Double.parseDouble((String) JOptionPane.showInputDialog(getPanneauDessin(), "Choisir la taille de votre choix.", "choix taille", JOptionPane.QUESTION_MESSAGE, null, null, taille));
					forme.setLargeur(taille);
					forme.setHauteur(taille);
				} catch (NumberFormatException | HeadlessException e) {
					JOptionPane.showMessageDialog(getPanneauDessin(), TAILLE_NON_RECONNUE);
				}
			}
			else {
				double largeur = forme.getLargeur();
				double hauteur = forme.getHauteur();
				try {
					largeur = Double.parseDouble((String) JOptionPane.showInputDialog(getPanneauDessin(), "Choisir la largeur de votre choix.", "choix largeur", JOptionPane.QUESTION_MESSAGE, null, null, largeur));
					hauteur = Double.parseDouble((String) JOptionPane.showInputDialog(getPanneauDessin(), "Choisir la hauteur de votre choix.", "choix hauteur", JOptionPane.QUESTION_MESSAGE, null, null, hauteur));
					forme.setLargeur(largeur);
					forme.setHauteur(hauteur);
				} catch (NumberFormatException | HeadlessException e) {
					JOptionPane.showMessageDialog(getPanneauDessin(), TAILLE_NON_RECONNUE);
				}
			}
		}
		getPanneauDessin().repaint();
		
	}

	private void changerEpaisseur() {
		Forme forme = vueFormeSelectionnee.getForme();
		int epaisseur = forme.getEpaisseur();
		try {
			epaisseur = Integer.parseInt((String) JOptionPane.showInputDialog(getPanneauDessin(), "Choisir l'épaisseur de votre choix.", "choix épaisseur", JOptionPane.QUESTION_MESSAGE, null, null, epaisseur));
			forme.setEpaisseur(epaisseur);
		} catch (NumberFormatException | HeadlessException e) {
			JOptionPane.showMessageDialog(getPanneauDessin(), TAILLE_NON_RECONNUE);
		}
		getPanneauDessin().repaint();
	}

	private void avancer() {
		List<VueForme> vueFormes = getPanneauDessin().getVueFormes();
		vueFormes.remove(vueFormeSelectionnee);
		vueFormes.add(vueFormeSelectionnee);
		getPanneauDessin().repaint();
	}
}
