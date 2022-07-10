package fr.eseo.poo.projet.artiste.controleur.outils;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.eseo.poo.projet.artiste.controleur.actions.ActionSelectionner;
import fr.eseo.poo.projet.artiste.modele.Coordonnees;
import fr.eseo.poo.projet.artiste.modele.formes.Forme;
import fr.eseo.poo.projet.artiste.vue.formes.VueForme;

public class OutilSelectionner extends Outil {

	private static final long serialVersionUID = -1823103745060800154L;
	private Forme formeSelectionnee;

	/* Move the object selected according to mouse movement. */
	@Override
	public void mouseMoved(MouseEvent event) {
		if (this.formeSelectionnee != null) {
			this.setFin(new Coordonnees(event.getX(), event.getY()));
			double deplacementX = getFin().getAbscisse() - getDebut().getAbscisse();
			double deplacementY = getFin().getOrdonnee() - getDebut().getOrdonnee();
			this.formeSelectionnee.deplacerDe(deplacementX, deplacementY);
			getPanneauDessin().repaint();
			this.setDebut(new Coordonnees(event.getX(), event.getY()));
		}
	}

	/* Manage click action. */
	@Override
	public void mouseClicked(MouseEvent event) {
		this.setDebut(new Coordonnees(event.getX(), event.getY()));
		if (event.getButton() == MouseEvent.BUTTON1 && this.formeSelectionnee == null) {
			clicGauche(event);
		}
		else {
			if (event.getButton() == MouseEvent.BUTTON3 && this.formeSelectionnee != null) {
				clicDroit();
			}
		}
	}
	
	/* Right click deselect object and show information. */
	private void clicDroit() {
		JOptionPane.showMessageDialog(getPanneauDessin(), this.formeSelectionnee, ActionSelectionner.NOM_ACTION, JOptionPane.INFORMATION_MESSAGE);
		this.formeSelectionnee = null;
	}

	/* Left click launch the selection process. */
	private void clicGauche(MouseEvent event) {
		List<Forme> bonneForme = new ArrayList<>();
		Coordonnees click = new Coordonnees(event.getX(), event.getY());
		List<VueForme> vueFormes = getPanneauDessin().getVueFormes();
		int taille = vueFormes.size();
		for (int i = taille - 1; i >= 0; i--) {
			VueForme vueForme = vueFormes.get(i);
			Forme forme = vueForme.getForme();
			if (forme.contient(click)) {
				bonneForme.add(forme);
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
				this.formeSelectionnee = bonneForme.get(0);
			}
		} else {
			this.formeSelectionnee = null;
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
				formeSelectionnee = (Forme) selectionneur.getValue();	
				fenetre.dispose();
			}
		};
		ok.addActionListener(listener);
	}
}